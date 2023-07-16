package com.fcprovin.chukcheck.view.fragemnt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected var mContext: Context? = null
    protected var mView: View? = null
    protected var notFgRecycler = false
    protected var isLoadData = false
    protected var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val resId = inflateResourceId
        if (resId <= 0) {
            return null
        }
        if (mView != null && !notFgRecycler) {
            return mView
        }
        mView = inflater.inflate(resId, container, false)
        init()
        return mView
    }

    protected abstract val inflateResourceId: Int

    protected abstract fun init()
    abstract fun onChangeFragment()
}