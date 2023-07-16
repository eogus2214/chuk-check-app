package com.fcprovin.chukcheck.view.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.fcprovin.chukcheck.R

class CusTopView : RelativeLayout, View.OnClickListener {

    private var mContext: Context? = null
    var mTvTitle : TextView? = null

    interface onClickListener {
        fun onClick()
    }

    constructor(context: Context) : super(context) {
        initAttrs(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context)
    }

    private fun initAttrs(context: Context) {
        mContext = context
        init()
    }

    private fun init() {
        LayoutInflater.from(mContext).inflate(R.layout.custome_top_view, this, true)
        mTvTitle = findViewById(R.id.tv_title)
        (findViewById<View>(R.id.tv_logout) as TextView).setOnClickListener(this)
    }

    fun setTitle(title : String){
        mTvTitle?.text = title
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_logout -> Log.d("dhkim","logout")
        }
    }
}