package com.demo.recyclerview.demo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.demo.recyclerview.R
import com.demo.recyclerview.demo1.ObjectModel
import kotlinx.android.synthetic.main.activity_3.*

/**
 * @author Administrator
 * @date 2018/5/8 0008
 */
class Demo2Activity : OnStartDragListener, AppCompatActivity() {

    var datas: MutableList<ObjectModel> = mutableListOf()
    var helper: ItemTouchHelper? = null

    override fun startDrag(viewHolder: RecyclerView.ViewHolder) {
        helper?.startDrag(viewHolder)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        rv_demo2.layoutManager = LinearLayoutManager(this)
        val datas = initData()
        val mNormalAdapter = NormalAdapter(datas, this)
        rv_demo2.adapter = mNormalAdapter
        helper = ItemTouchHelper(SimpleItemTouchCallback(mNormalAdapter, datas))
        helper?.attachToRecyclerView(rv_demo2)
    }

    fun initData(): MutableList<ObjectModel> {
        val titles: Array<String> = resources.getStringArray(R.array.title_array)
        for (i in 0 until titles.size) {
            val model = ObjectModel()
            model.title = titles[i]
            model.number = i + 1
            datas?.add(model)
        }
        return datas!!
    }
}

interface OnStartDragListener {
    fun startDrag(viewHolder: RecyclerView.ViewHolder)
}