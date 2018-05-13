package com.demo.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.recyclerview.demo1.Demo1Activity
import com.demo.recyclerview.demo2.Demo2Activity
import com.demo.recyclerview.demo5.Demo5Activity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        demo1.setOnClickListener {
            startActivity(intentFor<Demo1Activity>())
        }
        demo2.setOnClickListener {
            startActivity(intentFor<Demo2Activity>())
        }
        demo5.setOnClickListener {
            startActivity(intentFor<Demo5Activity>())
        }
    }
}
