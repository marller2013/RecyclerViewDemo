package com.demo.recyclerview.demo1

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import jp.wasabeef.recyclerview.animators.BaseItemAnimator

/**
 * @author Administrator
 * @date 2018/5/7 0007
 */
class DefaultItemAnimator : BaseItemAnimator() {
    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder?) {
        ViewCompat.animate(holder?.itemView)
                .alpha(0f)
                .setDuration(0)
                .setListener(DefaultRemoveVpaListener(holder))
                .setStartDelay(getRemoveDelay(holder))
                .start()
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder?) {
        ViewCompat.animate(holder?.itemView)
                .alpha(1f)
                .setDuration(addDuration)
                .setListener(DefaultAddVpaListener(holder))
                .setStartDelay(getAddDelay(holder))
                .start()
    }
}