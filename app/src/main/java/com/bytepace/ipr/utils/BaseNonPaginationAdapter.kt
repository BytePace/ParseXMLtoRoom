package com.bytepace.ipr.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytepace.ipr.R
import com.bytepace.ipr.database.model.BaseDBObject

const val VIEW_HOLDER = 12
const val PROGRESS_HOLDER = 13

abstract class BaseNonPaginationAdapter<T: BaseDBObject>(callback: NonPaginationAdapterCallback<T>): RecyclerView.Adapter<BaseNonPaginationAdapter.BaseHolder>() {

    protected val items: ArrayList<T> = ArrayList()
    private var isInit = false

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && items.size == 0) {
            return PROGRESS_HOLDER
        }
        return VIEW_HOLDER
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseHolder {
        return if (p1 == PROGRESS_HOLDER) {
            ProgressHolder(LayoutInflater.from(p0.context), p0)
        } else {
            getViewHolder(LayoutInflater.from(p0.context), p0, p1)
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty() && !isInit) {
            1
        } else {
            items.size
        }
    }

    open fun addItems(data: Array<T>) {
        items.addAll(data)
        isInit = true
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface NonPaginationAdapterCallback<T: BaseDBObject> {
        fun onItemClick(item: T)
    }

    abstract fun getViewHolder(inflater: LayoutInflater, root: ViewGroup, viewType: Int): BaseHolder

    abstract class BaseHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class ProgressHolder(inflater: LayoutInflater, root: ViewGroup):
        BaseHolder(inflater.inflate(R.layout.view_progressbar_bottom, root, false))
}