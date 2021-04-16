package com.example.androidtemplate.fragments.content_provider_resolver_cursor

import android.Manifest
import android.app.Dialog
import android.content.ContentProviderOperation
import android.content.ContentProviderResult
import android.content.ContentResolver
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.DialogRoomBinding
import com.example.androidtemplate.databinding.FragmentRoomDashboardBinding
import com.example.androidtemplate.fragments.home_fragment.HomeAdapter
import com.example.androidtemplate.util.*


class ContactDashboardFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentRoomDashboardBinding
    private val alContact = arrayListOf<String>()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRoomDashboardBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHelper(object :
            SwipeToDeleteHelper.SwipeToDelete {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val name = alContact[position].split("\n")[0]
                GeneralFunctions.showAlert(
                    requireContext(),
                    getString(R.string.are_you_sure),
                    getString(R.string.contact_will_be_deleted),
                    getString(R.string.ok),
                    getString(R.string._cancel),
                    object : GeneralFunctions.DialogListener {
                        override fun onPositiveClicked() {
//                            deleteContacts(name)
                        }

                        override fun onNegativeClicked() {
                            //TODO
                        }

                        override fun onNeutralClicked() {
                            //TODO
                        }
                    })
                homeAdapter?.notifyItemRemoved(position)
            }
        }))
        itemTouchHelper.attachToRecyclerView(mBinding.rvRoomDashboard)
        mBinding.rvRoomDashboard.layoutManager = LinearLayoutManager(requireContext())
        homeAdapter = HomeAdapter(requireContext(), alContact, object : ItemClickListener {
            override fun onItemClicked(
                viewHolder: RecyclerView.ViewHolder,
                position: Int,
                viewId: Int?
            ) {
                showDialog(
                    alContact[position].split("\n")[2],
                    alContact[position].split("\n")[0],
                    alContact[position].split("\n")[1]
                )
            }
        })
        mBinding.rvRoomDashboard.adapter = homeAdapter

        checkPermission()

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS),
                Constants.READ_AND_WRITE_CONTACTS
            )
            return
        }
        getAllContacts()
    }

    private fun getAllContacts() {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI    // table name
        val columns = arrayOf(                            //  column
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        )
        val sortOrder =
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " DESC"   //  sort by
        val selection =
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " = ?"    // where clause
        val selectionArgs = arrayOf("Vijay Koshti")

        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(uri, columns, null, null, sortOrder)

        alContact.clear()

        cursor?.let {
            if (it.count > 0) {
                while (it.moveToNext())
                    alContact.add("${it.getString(0)}\n${it.getString(1)}\n${it.getString(2)}")

                homeAdapter.changeAdapterData(alContact)
            } else
                requireContext().showShortToast("No contacts found.")
        }

        cursor?.close()
    }

    override fun setListener() {
        mBinding.fabAddUser.setOnClickListener(this)
    }

    private fun updateContact(contactId: String, name: String, number: String) {
        val where = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = '$contactId' "
        val contentValues = ContentValues()
        contentValues.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY, name)
        requireActivity().contentResolver.update(
            ContactsContract.RawContacts.CONTENT_URI,
            contentValues,
            where,
            null
        )
    }

    private fun deleteContacts(name: String) {
        val whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = '" + name + "'"
        requireActivity().contentResolver.delete(
            ContactsContract.RawContacts.CONTENT_URI,
            whereClause,
            null
        )
    }

    private fun addContact(name: String, number: String) {
        val ops = ArrayList<ContentProviderOperation>()

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()
        )

        //------------------------- Names -----------------------------//
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build()
        )

        //----------------------- Mobile Number -------------------------------//
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .withValue(
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                )
                .build()
        )

        try {
            requireActivity().contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
            requireActivity().showShortToast("Contact added")
            getAllContacts()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun showDialog(contactId: String, name: String, number: String) {
        val dialogRoomBinding: DialogRoomBinding = DialogRoomBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogRoomBinding.root)

        dialogRoomBinding.etFullName.setText(name)
        dialogRoomBinding.etNumber.setText(number)

        dialogRoomBinding.btnSubmit.setOnClickListener {
            /*addContact(
                dialogRoomBinding.etFullName.text.toString(),
                dialogRoomBinding.etNumber.text.toString()
            )*/
            updateContact(contactId, name, number)
            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_AND_WRITE_CONTACTS && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            getAllContacts()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fabAddUser -> showDialog("", "", "")
        }
    }
}