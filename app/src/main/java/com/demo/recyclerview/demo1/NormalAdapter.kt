package com.demo.recyclerview.demo1

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.demo.recyclerview.R
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * @author Administrator
 * @date 2018/5/7 0007
 */
class NormalAdapter(val data: MutableList<ObjectModel>?) : RecyclerView.Adapter<NormalAdapter.Companion.ViewHolder>() {
    override fun onBindViewHolder(holder: NormalAdapter.Companion.ViewHolder?, position: Int) {
        Log.d("NormalAdapter", "position->" + position)
        val model: ObjectModel? = data!![position]
        holder?.title?.text = model?.title
        holder?.number?.text = model?.number.toString()
        holder?.itemView?.setOnClickListener {
            holder?.itemView?.context?.toast("点击了${position}")
        }
    }

    override fun getItemCount(): Int {
        return data?.size as Int
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NormalAdapter.Companion.ViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_1, parent, false)
        return ViewHolder(view)
    }

    companion object {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var title: TextView? = null
            var number: TextView? = null

            init {
                title = view.find(R.id.title)
                number = view.find(R.id.number)
            }
        }
    }
}