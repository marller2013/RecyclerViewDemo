package com.demo.recyclerview.demo1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Administrator
 * @date 2018/5/7 0007
 */
class DividerItemDecoration(context: Context, orient: Int) : RecyclerView.ItemDecoration() {
    companion object {
        val ATTRS: IntArray = kotlin.IntArray(1) { android.R.attr.listDivider }
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }

    var mDivider: Drawable? = null
    var mOrientation: Int = Companion.VERTICAL_LIST

    init {
        val a = context.obtainStyledAttributes(Companion.ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
        mOrientation = orient
    }

    fun setOrientation(orientation: Int) {
        if (orientation != Companion.HORIZONTAL_LIST && orientation != Companion.VERTICAL_LIST) throw IllegalArgumentException("invalid orientation")
        else mOrientation = orientation
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (mOrientation === Companion.VERTICAL_LIST) drawVertical(c!!, parent!!) else drawHorizontal(c!!, parent!!)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (mOrientation == VERTICAL_LIST) {
            val position = (view!!.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val count = parent!!.adapter.itemCount
            if (position < count - 1) {
                outRect!!.set(0, 0, 0, mDivider!!.intrinsicHeight)
            }
        } else {
            outRect!!.set(0, 0, mDivider!!.intrinsicWidth, 0)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent?.paddingLeft
        val right = parent?.width?.minus(parent?.paddingRight)
        val childCount = parent?.childCount
        for (i in 0..childCount!!.minus(1)) {
            val child = parent?.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child))
            val bottom: Int = top + mDivider!!.intrinsicHeight
            mDivider?.bounds = Rect(left as Int, top, right as Int, bottom)
            mDivider?.draw(canvas)
        }
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent?.paddingTop
        val bottom = parent?.height?.minus(parent?.paddingBottom)
        val childCount = parent?.childCount
        for (i in 0..childCount!!.minus(1)) {
            val child = parent?.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left: Int = child.right + params.rightMargin + Math.round(ViewCompat.getTranslationX(child))
            val right: Int = left + mDivider!!.intrinsicWidth
            mDivider?.bounds = Rect(left, top, right, bottom)
            mDivider?.draw(canvas)
        }
    }
}