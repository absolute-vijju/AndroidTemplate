package com.example.androidtemplate.fragments.calender_range

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ItemCalenderDateBinding
import com.example.androidtemplate.util.Constants
import com.example.androidtemplate.util.GeneralFunctions
import com.example.androidtemplate.util.ItemClickListener

class CalenderAdapter(
    private val context: Context,
    private var dateList: ArrayList<DateModel>,
    private var calenderAdapterType: Constants.Companion.CalenderAdapterType
) :
    RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        return CalenderViewHolder(
            ItemCalenderDateBinding.bind(
                LayoutInflater.from(context).inflate(R.layout.item_calender_date, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.bindData(dateList[position])
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    inner class CalenderViewHolder(private val mBinding: ItemCalenderDateBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bindData(dateModel: DateModel) {

            dateModel?.let {

                if (calenderAdapterType == Constants.Companion.CalenderAdapterType.DAY) {
                    /*if (adapterPosition == 0)
                        mBinding.tvDate.text = context.getString(R.string.start_date)
                    else*/
                    mBinding.tvDate.text =
                        it.date?.let { it1 ->
                            GeneralFunctions.formatDate(
                                it1.time,
                                Constants.DAY_FORMAT
                            )
                        }
                } else if (calenderAdapterType == Constants.Companion.CalenderAdapterType.WEEK) {
                    /*if (adapterPosition == 0)
                        mBinding.tvDate.text = context.getString(R.string.start_date)
                    else*/
                    mBinding.tvDate.text =
                        it.startDate?.let { it1 ->
                            GeneralFunctions.formatDate(it1.time, Constants.WEEK_FORMAT)
                                .plus(" - ").plus(
                                    it.endDate?.let { it2 ->
                                        GeneralFunctions.formatDate(
                                            it2.time,
                                            Constants.WEEK_FORMAT
                                        )
                                    }
                                )
                        }
                } else if (calenderAdapterType == Constants.Companion.CalenderAdapterType.DAY) {
                    /*if (adapterPosition == 0)
                        mBinding.tvDate.text = context.getString(R.string.start_date)
                    else*/
                    mBinding.tvDate.text =
                        it.date?.let { it1 ->
                            GeneralFunctions.formatDate(
                                it1.time,
                                Constants.MONTH_FORMAT
                            )
                        }
                }

                mBinding.root.setOnClickListener {
                    selectedPosition = adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun changeAdapterData(
        dateList: ArrayList<DateModel>,
        calenderAdapterType: Constants.Companion.CalenderAdapterType
    ) {
        this.dateList = dateList
        this.calenderAdapterType = calenderAdapterType
        notifyDataSetChanged()
    }

}