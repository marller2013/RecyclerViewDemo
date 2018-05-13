package com.demo.recyclerview.demo5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.ImageView
import com.demo.recyclerview.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_6.*
import layout.ImageDisplayActivity
import org.jetbrains.anko.intentFor

/**
 * @author Administrator
 * @date 2018/5/12 0012
 */
class Demo5Activity : AppCompatActivity(), ImageDisplayAdapter.OnPhotoClickListener {
    override fun onPhotoClick(position: Int) {
        val preURL: String? = if (position == 0) null else datas!![position - 1]
        val postURL: String? = if (position == datas?.size?.minus(-1)) null else datas!![position + 1]
        startActivity(intentFor<ImageDisplayActivity>("url" to datas!![position], "preurl" to preURL, "posturl" to postURL))
    }


    val datas: MutableList<String>? = initData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

        rv_demo5.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val mAdapter = object : ImageDisplayAdapter<String>(datas!!) {
            override fun getLayoutId(viewType: Int): Int {
                return R.layout.item_6
            }

            override fun convert(holder: ViewHolder?, data: String, position: Int) {
                val imageView: ImageView = holder?.getView(R.id.image)!!
                Picasso.with(this@Demo5Activity).load(data).error(R.mipmap.ic_launcher).into(imageView)
            }

        }
        mAdapter.onPhotoClickListener = this
        mAdapter.setHasStableIds(true)
        (rv_demo5.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        rv_demo5.adapter = mAdapter
    }

    fun initData(): MutableList<String> {
        val imageUrls: MutableList<String> = mutableListOf(
                "http://img5.imgtn.bdimg.com/it/u=1957152431,1883245968&fm=27&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2323198081,67703957&fm=27&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=2772101807,2217640443&fm=27&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=2667035454,1324878895&fm=27&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=807871879,3461425799&fm=27&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=3698568762,2507880363&fm=27&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=2640755477,2424106772&fm=27&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=4217756496,841228056&fm=27&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=401994104,4197149341&fm=27&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3795966303,2203370609&fm=27&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=158210710,2338583362&fm=27&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=3766649311,1641233970&fm=27&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=2948582993,445816014&fm=27&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=3957745361,2457715169&fm=27&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=485210453,665155686&fm=27&gp=0.jpg")
        return imageUrls
    }
}