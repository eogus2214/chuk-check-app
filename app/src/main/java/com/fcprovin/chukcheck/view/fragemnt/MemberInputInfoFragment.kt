package com.fcprovin.chukcheck.view.fragemnt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fcprovin.chukcheck.databinding.FragmentMemberInputInfoBinding

class MemberInputInfoFragment : Fragment(){
    private var mContext: Context? = null
    private var mBinding : FragmentMemberInputInfoBinding? = null

    fun newInstance(): MemberInputInfoFragment? {
        return MemberInputInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMemberInputInfoBinding.inflate(inflater, container,false)
        return mBinding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}