package com.example.lw.scale.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = Adapter(resources.getDimension(R.dimen.margin_8dp)
        )
    }

    private class Adapter(val margin: Float): RecyclerView.Adapter<Adapter.TestVH>(){


        class TestVH(itemView:View): RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestVH {
            val contentView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
            return TestVH(contentView)
        }

        override fun getItemCount(): Int = 20

        override fun onBindViewHolder(holder: TestVH, position: Int) {
            Log.e("....",".....")
            val params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
            params.marginStart = margin.toInt()
            params.marginEnd = margin.toInt()
        }
    }
}
