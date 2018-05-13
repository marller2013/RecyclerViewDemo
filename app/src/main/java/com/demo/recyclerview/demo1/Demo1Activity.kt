package com.demo.recyclerview.demo1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.demo.recyclerview.R
import com.demo.recyclerview.R.layout.activity_1
import kotlinx.android.synthetic.main.activity_1.*

/**
 * @author Administrator
 * @date 2018/5/7 0007
 */
class Demo1Activity : AppCompatActivity() {
    var datas: MutableList<ObjectModel> = mutableListOf()
    var mAdapter: NormalAdapterWrapper? = null
    var divider: DividerItemDecoration? = null
    var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_1)
        divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST)
        layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager
        rv.addItemDecoration(divider)

        val mNoHeaderAdapter = NormalAdapter(initData())
        mAdapter = NormalAdapterWrapper(mNoHeaderAdapter)
        val headerView = LayoutInflater.from(this).inflate(R.layout.item_header, rv, false)
        val footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, rv, false)
        mAdapter?.mHeaderView = headerView
        mAdapter?.mFooterView = footerView
        rv.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> {
                val model: ObjectModel = ObjectModel()
                model.title = "Insert"
                model.number = 0
                datas.add(0, model)
                mAdapter?.notifyItemInserted(1)
            }
            R.id.item_delete -> {
                datas.removeAt(0)
                mAdapter?.notifyItemRemoved(1)
            }
            R.id.item_change_divider -> {
                divider?.mDivider = resources.getDrawable(R.drawable.divider)
                mAdapter?.notifyDataSetChanged()
            }
            R.id.item_hlistview -> {
                rv.removeItemDecoration(divider)
                divider = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST)
                layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rv.layoutManager = layoutManager
                rv.addItemDecoration(divider)
            }
        }
        return super.onOptionsItemSelected(item)
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

    override fun onStop() {
        datas?.clear()
        super.onStop()
    }
}