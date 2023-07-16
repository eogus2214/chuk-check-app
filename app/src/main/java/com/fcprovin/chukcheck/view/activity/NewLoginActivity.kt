package com.fcprovin.chukcheck.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.fcprovin.chukcheck.R
import com.fcprovin.chukcheck.view.fragemnt.SnsLoginFragment


class NewLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        SnsLoginFragment().newInstance()?.let {
            fragmentTransaction.replace(R.id.fragment_container, it).commit()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment).commit()
    }


}
