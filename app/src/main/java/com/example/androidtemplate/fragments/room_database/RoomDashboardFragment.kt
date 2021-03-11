package com.example.androidtemplate.fragments.room_database

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.DialogRoomBinding
import com.example.androidtemplate.databinding.FragmentRoomDashboardBinding
import com.example.androidtemplate.fragments.home_fragment.HomeAdapter
import com.example.androidtemplate.fragments.room_database.database.AppDatabase
import com.example.androidtemplate.fragments.room_database.database.UserDao
import com.example.androidtemplate.fragments.room_database.database.UserEntity
import com.example.androidtemplate.util.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class RoomDashboardFragment : BaseFragment(), View.OnClickListener, ItemClickListener {

    private lateinit var mBinding: FragmentRoomDashboardBinding;
    private var userDao: UserDao? = null
    private var userList: List<UserEntity>? = null
    private var homeAdapter: HomeAdapter? = null
    private var stringList = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRoomDashboardBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        activity?.let {
            userDao = AppDatabase.getDatabase(it).getDao()

            val itemTouchHelper =
                ItemTouchHelper(SwipeToDeleteHelper(object : SwipeToDeleteHelper.SwipeToDelete {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        CoroutineScope(Dispatchers.IO).launch {
                            userDao?.deleteUser(userList!![position])

                            withContext(Dispatchers.Main) {
                                stringList.removeAt(position)
                                homeAdapter?.notifyItemRemoved(position)
                            }
                        }
                    }
                }))
            mBinding.rvRoomDashboard.layoutManager = LinearLayoutManager(it)
            itemTouchHelper.attachToRecyclerView(mBinding.rvRoomDashboard)
            homeAdapter = HomeAdapter(it, arrayListOf(), this)
            mBinding.rvRoomDashboard.adapter = homeAdapter
        }
        getData()
    }

    override fun setListener() {
        mBinding.fabAddUser.setOnClickListener(this)
    }

    private fun showDialog(userEntity: UserEntity? = null) {
        val dialogRoomBinding: DialogRoomBinding = DialogRoomBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogRoomBinding.root)

        userEntity?.let {
            dialogRoomBinding.etFullName.setText(it.fullName)
        }

        dialogRoomBinding.btnSubmit.setOnClickListener {
            dialog.dismiss()
            activity?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    if (userEntity != null) {
                        userDao?.insertUser(
                            UserEntity(
                                userEntity.userId,
                                dialogRoomBinding.etFullName.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            context?.showShortToast(getString(R.string.user_updated))
                        }
                    } else {
                        userDao?.insertUser(
                            UserEntity(
                                0,
                                dialogRoomBinding.etFullName.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            context?.showShortToast(getString(R.string.user_added))
                        }
                    }
                    getData()
                }
            }
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            userList = userDao?.getUsers()
            withContext(Dispatchers.Main) {
                setAdapter()
            }

        }
    }

    private fun setAdapter() {
        if (!userList.isNullOrEmpty()) {
            userList?.let { usersList ->
                stringList.clear()
                for (userData in usersList) {
                    stringList.add(userData.fullName)
                }
                homeAdapter?.changeAdapterData(stringList)
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fabAddUser -> {
                showDialog()
            }
        }
    }

    override fun onItemClicked(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        activity?.let {
            showDialog(userList!![position])
        }
    }
}