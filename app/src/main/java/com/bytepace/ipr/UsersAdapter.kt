package com.bytepace.ipr

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bytepace.ipr.database.model.relation.UserRelation
import com.bytepace.ipr.utils.BaseNonPaginationAdapter
import kotlinx.android.synthetic.main.view_user.view.*

class UsersAdapter(private val callback: NonPaginationAdapterCallback<UserRelation>):
    BaseNonPaginationAdapter<UserRelation>(callback) {
    override fun getViewHolder(inflater: LayoutInflater, root: ViewGroup, viewType: Int): BaseHolder {
        return ViewHolder(inflater, root)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (position < items.size) {
            val item = items[position]
            holder as ViewHolder
            holder.bind(item) {
                callback.onItemClick(it)
            }
        }
    }

    class ViewHolder(inflater: LayoutInflater, root: ViewGroup): BaseHolder(inflater.inflate(R.layout.view_user, root,
        false)) {
        fun bind(item: UserRelation, onShowMoreCallback: (item: UserRelation) -> Unit) {
            itemView.textID.text = item.id
            itemView.textName.text = item.name
            itemView.textDevice.text = item.device?.name
            itemView.btnShowMore.setOnClickListener { onShowMoreCallback(item) }
        }
    }
}