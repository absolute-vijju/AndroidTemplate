package com.example.androidtemplate.fragments.calender_range

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentCalenderRangeBinding
import com.example.androidtemplate.databinding.FragmentCheckAppInstalledBinding
import com.example.androidtemplate.util.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class CalenderRangeFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentCalenderRangeBinding
    private var startDate = Calendar.getInstance()
    private var endDate = Calendar.getInstance()
    private lateinit var dayAdapter: CalenderAdapter
    private lateinit var weekAdapter: CalenderAdapter
    private lateinit var monthAdapter: CalenderAdapter

    private var daysList = arrayListOf<DateModel>()
    private var weekList = arrayListOf<DateModel>()
    private var monthList = arrayListOf<DateModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCalenderRangeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        mBinding.rvDay.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvWeek.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.rvMonth.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        dayAdapter = CalenderAdapter(
            requireContext(),
            arrayListOf(),
            Constants.Companion.CalenderAdapterType.DAY
        )
        weekAdapter = CalenderAdapter(
            requireContext(),
            arrayListOf(),
            Constants.Companion.CalenderAdapterType.WEEK
        )
        monthAdapter = CalenderAdapter(
            requireContext(),
            arrayListOf(),
            Constants.Companion.CalenderAdapterType.MONTH
        )

        mBinding.rvDay.adapter = dayAdapter
        mBinding.rvWeek.adapter = weekAdapter
        mBinding.rvMonth.adapter = monthAdapter
    }

    override fun setListener() {
        mBinding.tvStartDate.setOnClickListener(this)
        mBinding.tvEndDate.setOnClickListener(this)
        mBinding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvStartDate -> GeneralFunctions.getDate(
                requireContext(),
                Constants.Companion.DateLimitType.NONE,
                object : DateSelectionListener {
                    override fun onDateSelected(calender: Calendar) {
                        startDate = calender
                        mBinding.tvStartDate.text = getString(R.string.start_date).plus("\n")
                            .plus(GeneralFunctions.formatDate(startDate.time))
                    }
                })
            R.id.tvEndDate -> GeneralFunctions.getDate(
                requireContext(),
                Constants.Companion.DateLimitType.NONE,
                object : DateSelectionListener {
                    override fun onDateSelected(calender: Calendar) {
                        endDate = calender
                        mBinding.tvEndDate.text = getString(R.string.end_date).plus("\n")
                            .plus(GeneralFunctions.formatDate(endDate.time))
                    }
                })
            R.id.btnSubmit -> {
                if (checkDate())
                    bindAdapter()
            }
        }
    }

    @SuppressLint("LogNotTimber")
    private fun bindAdapter() {
        /**
         *  Make conditional arraylist for adapter
         *  Day > 0, Week > 7 ,Month > 30
         */

        val daysDifference = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)
        val calendar = Calendar.getInstance()
        daysList.clear()
        weekList.clear()
        monthList.clear()

        if (daysDifference > 0) {
            for (i in 0..daysDifference) {
                calendar.time = startDate.time
                calendar.add(Calendar.DATE, i)
//                calendar.add(Calendar.DATE, -abs(i))  // Use this if you want to decrease days
                daysList.add(DateModel(calendar.clone() as Calendar, null, null))
            }
        }

        for (i in daysList) {
            Log.d("daysList", GeneralFunctions.formatDate(i.date!!.time))
        }

        dayAdapter.changeAdapterData(daysList, Constants.Companion.CalenderAdapterType.DAY)

        if (daysDifference >= 7) {
            val weeks = daysDifference / 7
            for (i in 0..weeks) {
                calendar.time = startDate.time
                calendar.add(Calendar.DATE, i * 7)

                calendar.firstDayOfWeek = Calendar.SUNDAY
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                val startDateCalender = calendar.clone() as Calendar

                calendar.firstDayOfWeek = Calendar.SUNDAY
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
                val endDateCalender = calendar.clone() as Calendar

                weekList.add(DateModel(null, startDateCalender, endDateCalender))
            }
        }

        for (i in weekList) {
            Log.d(
                "weekList",
                "Start date: " + GeneralFunctions.formatDate(i.startDate!!.time) + " End Date: " + GeneralFunctions.formatDate(
                    i.endDate!!.time
                )
            )
        }

        weekAdapter.changeAdapterData(weekList, Constants.Companion.CalenderAdapterType.WEEK)

        if (daysDifference > 28) {
            val months = daysDifference / 28
            for (i in 1..months) {
                calendar.time = startDate.time
                calendar.add(Calendar.MONTH, i - 1)

                monthList.add(DateModel(calendar.clone() as Calendar, null, null))
            }
        }

        for (i in monthList) {
            Log.d("monthList", GeneralFunctions.formatDate(i.date!!.time))
        }

        monthAdapter.changeAdapterData(monthList, Constants.Companion.CalenderAdapterType.MONTH)
    }

    private fun checkDate(): Boolean {
        if (startDate.timeInMillis > endDate.timeInMillis) {
            requireContext().showShortToast(getString(R.string.start_date_can_t_bigger_than_end_date))
            return false
        } else if (GeneralFunctions.formatDate(startDate.time)
                .equals(GeneralFunctions.formatDate(endDate.time), true)
        ) {
            requireContext().showShortToast(getString(R.string.both_date_can_t_be_same))
            return false
        }
        requireContext().showShortToast(getString(R.string.success))
        return true
    }
}