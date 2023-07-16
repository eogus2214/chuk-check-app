package com.fcprovin.chukcheck.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fcprovin.chukcheck.R
import com.fcprovin.chukcheck.view.customview.CusTopView
import com.fcprovin.chukcheck.view.fragemnt.BaseFragment
import com.fcprovin.chukcheck.view.fragemnt.UserDetialFragment


class MainActivity : AppCompatActivity() {

    private var mCusTopView: CusTopView? = null
    private var mContext: Context? = null

    val MAIN_USER_INFO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCusTopView = findViewById(R.id.cus_top_view)
        mContext = this
        init()
    }

    private fun init() {
        mCusTopView?.setTitle("축첵")
        startActivity(Intent(this, NewLoginActivity::class.java))
//        FragmentView(MAIN_USER_INFO)
    }

    private fun FragmentView(type: Int) {
        var fg: BaseFragment? = null
        val args = Bundle()
        when (type) {
            MAIN_USER_INFO -> {
                fg = UserDetialFragment()
            }
        }
        fg?.let {
            it.arguments = args
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
        }
    }

}
