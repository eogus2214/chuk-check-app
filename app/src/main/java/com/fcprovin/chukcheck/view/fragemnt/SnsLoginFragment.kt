package com.fcprovin.chukcheck.view.fragemnt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.fcprovin.chukcheck.R
import com.fcprovin.chukcheck.view.activity.LoginActivity
import com.fcprovin.chukcheck.common.SharedPrefManager
import com.fcprovin.chukcheck.databinding.FragementLoginBinding
import com.fcprovin.chukcheck.view.activity.NewLoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class SnsLoginFragment : Fragment(), View.OnClickListener {
    private var mContext: Context? = null
    private var mBinding: FragementLoginBinding? = null

    private var mSnsType: String? = null

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    lateinit var mGoogleSignInClient: GoogleSignInClient

    fun newInstance(): SnsLoginFragment? {
        return SnsLoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragementLoginBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onStart() {
        super.onStart()
        activity?.let { context ->
            val pGoogleAccount = GoogleSignIn.getLastSignedInAccount(context)
            pGoogleAccount?.let {
                Toast.makeText(context, "구글 로그인이 되어있습니다.", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(context, "로그인을 진행해 주세요", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity

        setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mContext?.let {
            mGoogleSignInClient = GoogleSignIn.getClient(it, gso)
        }

        mBinding?.tvGoogleLogin?.setOnClickListener(this)
        mBinding?.tvKakaoLogin?.setOnClickListener(this)
        mBinding?.tvAppleLogin?.setOnClickListener(this)
        mBinding?.tvNaverLogin?.setOnClickListener(this)

    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == Activity.RESULT_OK) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleSignInResult(task)
                }

            }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
            val givenName = account?.givenName.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
            val test = account?.id.toString()
            Toast.makeText(mContext, "로그인 되어있습니다.", Toast.LENGTH_SHORT).show()
            Log.d("로그인한 되어있습니다", "1")
            SharedPrefManager.setChukCheckSnsType(mSnsType)
            SharedPrefManager.setChukCheckUserUuId(account?.id.toString())
            MemberInputInfoFragment().newInstance()?.let {
                Log.d("로그인한 되어있습니다", "2")
                (activity as NewLoginActivity?)?.replaceFragment(it)
            }
            Log.d("로그인한 이메일", email)
            Log.d("로그인한 성", familyName)
            Log.d("로그인한 이름", givenName)
            Log.d("로그인한 전체이름", displayName)
            Log.d("로그인한 프로필 주소", photoUrl)
            Log.d("로그인한 id", test)
        } catch (e: ApiException) {
            Log.w("failed", "signInResult:failed code" + e.statusCode)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCanceledListener {

        }


    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCanceledListener {

        }
    }

    private fun getCurrentUserProfile() {
        mContext?.let {
            val curUser = GoogleSignIn.getLastSignedInAccount(it)
            curUser?.let {
                val email = curUser.email.toString()
                val familyName = curUser.familyName.toString()
                val givenName = curUser?.givenName.toString()
                val displayName = curUser?.displayName.toString()
                val photoUrl = curUser?.photoUrl.toString()

                Log.d("현재 로그인된 이메일", email)
                Log.d("현재 로그인된 성", familyName)
                Log.d("현재 로그인된 이름", givenName)
                Log.d("현재 로그인된 전체이름", displayName)
                Log.d("현재 로그인된 프로필 주소", photoUrl)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_google_login -> {
                signIn()
            }
            R.id.tv_kakao_login -> {
                Toast.makeText(mContext, "준비 중입니다..", Toast.LENGTH_SHORT).show()
            }
            R.id.tv_naver_login->{
                Toast.makeText(mContext, "준비 중입니다..", Toast.LENGTH_SHORT).show()
            }
            R.id.tv_apple_login -> {
                Toast.makeText(mContext, "준비 중입니다..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}