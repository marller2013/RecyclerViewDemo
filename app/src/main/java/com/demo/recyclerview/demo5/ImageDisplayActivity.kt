package layout

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MotionEvent
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

/**
 * @author Administrator
 * @date 2018/5/12 0012
 */
class ImageDisplayActivity : AppCompatActivity() {

    var imageView: ImageView? = null
    var preURL: String? = null
    var postURL: String? = null
    var x1: Float = 0.0f
    var x2: Float = 0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_image_display)
        val url = intent.getStringExtra("url")
        preURL = intent.getStringExtra("preurl")
        postURL = intent.getStringExtra("posturl")
        frameLayout {
            backgroundColor = Color.parseColor("#000000")
            imageView = imageView {
                adjustViewBounds = true
            }.lparams(width = wrapContent, height = matchParent, gravity = Gravity.CENTER)
        }
        Picasso.with(this).load(url).into(imageView)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            x1 = event?.rawX
        }
        if (event?.action == MotionEvent.ACTION_UP) {
            x2 = event?.rawX
            if (x2.minus(x1) > 50) {
                Picasso.with(this@ImageDisplayActivity).load(preURL).into(imageView)
            } else if (x2.minus(x1) < -50) {
                Picasso.with(this@ImageDisplayActivity).load(postURL).into(imageView)
            }

        }
        return super.onTouchEvent(event)
    }
}