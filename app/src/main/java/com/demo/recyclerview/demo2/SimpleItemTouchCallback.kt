package com.demo.recyclerview.demo2

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.demo.recyclerview.demo1.ObjectModel
import org.jetbrains.anko.backgroundColor
import java.util.*

/**
 * @author Administrator
 * @date 2018/5/9 0009
 */
class SimpleItemTouchCallback(val adapter: NormalAdapter, val dataList: MutableList<ObjectModel>) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlag: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag: Int = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        val from: Int = viewHolder?.adapterPosition as Int
        val to: Int = target?.adapterPosition as Int
        Collections.swap(dataList, from, to)
        adapter.notifyItemMoved(from, to)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        val position: Int = viewHolder?.adapterPosition as Int
        dataList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            val holder: NormalAdapter.Companion.ViewHolder = viewHolder as NormalAdapter.Companion.ViewHolder
            holder.itemView.backgroundColor = -0x434344
        }
    }

}