package com.demo.recyclerview.demo5

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find

/**
 * @author Administrator
 * @date 2018/5/12 0012
 */
abstract class ImageDisplayAdapter<T>(var datas: MutableList<T>) : RecyclerView.Adapter<ImageDisplayAdapter.Companion.ViewHolder>() {

    var onPhotoClickListener: OnPhotoClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder.get(parent, getLayoutId(viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.setOnClickListener {
            onPhotoClickListener?.onPhotoClick(position)
        }
        convert(holder, datas[position], position)
    }

    override fun getItemCount(): Int {
        return datas.size as Int
    }

    abstract fun getLayoutId(viewType: Int): Int

    abstract fun convert(holder: ViewHolder?, data: T, position: Int)

    companion object {
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            private var mViews: SparseArray<View>? = null
            private var convertView: View? = null

            init {
                mViews = SparseArray()
                convertView = view
            }

            companion object {
                fun get(parent: ViewGroup?, layoutId: Int): ViewHolder {
                    val mConvertView: View = LayoutInflater.from(parent?.context).inflate(layoutId, parent, false)
                    return ViewHolder(mConvertView)
                }
            }

            fun <T : View> getView(id: Int): T {
                var v: View? = mViews?.get(id)
                if (v == null) {
                    v = convertView?.find(id)!!
                    mViews!!.put(id, v)
                }
                return v as T
            }

            fun setText(id: Int, value: String) {
                val textView: TextView = getView(id)
                textView.text = value
            }
        }
    }

    interface OnPhotoClickListener {
        fun onPhotoClick(position: Int)
    }
}