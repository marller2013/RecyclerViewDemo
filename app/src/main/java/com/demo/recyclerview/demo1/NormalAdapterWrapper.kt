package com.demo.recyclerview.demo1

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * @author Administrator
 * @date 2018/5/7 0007
 */
class NormalAdapterWrapper(val adapter: NormalAdapter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }

    var mHeaderView: View? = null
    var mFooterView: View? = null

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            NormalAdapterWrapper.ITEM_TYPE.HEADER.ordinal
        } else if (position == adapter.itemCount + 1) {
            NormalAdapterWrapper.ITEM_TYPE.FOOTER.ordinal
        } else {
            NormalAdapterWrapper.ITEM_TYPE.NORMAL.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (position == 0) {
            return
        } else if (position == adapter.itemCount + 1) {
            return
        } else {
            adapter.onBindViewHolder(holder as NormalAdapter.Companion.ViewHolder, position - 1)
        }
    }

    override fun getItemCount(): Int {
        return adapter.itemCount + 2
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NormalAdapterWrapper.ITEM_TYPE.HEADER.ordinal) {
            object : RecyclerView.ViewHolder(mHeaderView!!) {

            }
        } else if (viewType == NormalAdapterWrapper.ITEM_TYPE.FOOTER.ordinal) {
            object : RecyclerView.ViewHolder(mFooterView!!) {

            }
        } else {
            adapter.onCreateViewHolder(parent, viewType)
        }
    }
}