package com.example.lw.scale.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var margin: Float = 0f
    lateinit var guideline: Guideline
    lateinit var parent: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        margin = resources.getDimension(R.dimen.margin_8dp)
        guideline = findViewById(R.id.guideline)
        parent = findViewById(R.id.parent)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        //根
        (recyclerView.parent as View).setOnTouchListener { _, event ->
            recyclerView.onTouchEvent(event)
            true
        }
        //必须使用View的post方法，才能拿到宽高，使用Handler post拿不到
        recyclerView.post {
            recyclerView.adapter = Adapter(parent, margin, guideline)
        }
    }

    private class Adapter(val parent: View, val margin: Float, val guideline: Guideline) :
        RecyclerView.Adapter<Adapter.TestVH>() {

        val itemSize = parent.resources.getDimension(R.dimen.itemSize)

        class TestVH(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestVH {
            val contentView =  LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
            return TestVH(contentView)
        }

        override fun getItemCount(): Int = 20

        override fun onBindViewHolder(holder: TestVH, position: Int) {
            val params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
            val guideLineParams = guideline.layoutParams as ConstraintLayout.LayoutParams
            params.marginStart = margin.toInt()
            params.marginEnd = margin.toInt()

            if (position == 0)
                params.marginStart = (guideLineParams.guidePercent * parent.width - itemSize / 2).toInt()
            else if (position == itemCount - 1)
                params.marginEnd = (guideLineParams.guidePercent * parent.width + itemSize / 2).toInt()

            if (position != 0)
                params.width  = itemSize.toInt()
                params.height = itemSize.toInt()

            holder.itemView.requestLayout()
        }
    }
}
