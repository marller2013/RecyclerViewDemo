package com.demo.recyclerview.demo2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.demo.recyclerview.R
import com.demo.recyclerview.demo1.ObjectModel
import org.jetbrains.anko.find

/**
 * @author Administrator
 * @date 2018/5/8 0008
 */
class NormalAdapter(var dataList: MutableList<ObjectModel>?, val listener: OnStartDragListener) : RecyclerView.Adapter<NormalAdapter.Companion.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NormalAdapter.Companion.ViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_3, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NormalAdapter.Companion.ViewHolder?, position: Int) {
        val model: ObjectModel = dataList!![position]
        holder?.title?.text = model.title
        holder?.number?.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                listener.startDrag(holder)
            }
            false
        })
    }

    override fun getItemCount(): Int {
        return dataList?.size as Int
    }

    companion object {
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.find(R.id.title)
            val number: ImageView = view.find(R.id.number)
        }
    }
}