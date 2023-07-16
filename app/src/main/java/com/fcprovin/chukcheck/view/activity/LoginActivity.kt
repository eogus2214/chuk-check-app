package com.fcprovin.chukcheck.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fcprovin.chukcheck.databinding.FragementLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {

    lateinit var mContext : Context

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            Toast.makeText(this,"로그인 되어있습니다.",Toast.LENGTH_SHORT).show()
        }?: Toast.makeText(this,"로그인을 진행해 주세요",Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = FragementLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        mContext = this
        setResultSignUp()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        with(binding) {
            tvGoogleLogin.setOnClickListener {
                signIn()
            }

            tvKakaoLogin.setOnClickListener {
                Toast.makeText(mContext,"준비 중입니다..",Toast.LENGTH_SHORT).show()
            }
            tvNaverLogin.setOnClickListener {
                Toast.makeText(mContext,"준비 중입니다..",Toast.LENGTH_SHORT).show()
            }
            tvAppleLogin.setOnClickListener {
                Toast.makeText(mContext,"준비 중입니다..",Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(binding.root)
    }

    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

            if (result.resultCode == Activity.RESULT_OK){
                val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
            val givenName = account?.givenName.toString()
            val displayName = account?.displayName.toString()
            val photoUrl = account?.photoUrl.toString()
            val test = account?.id.toString()
            Toast.makeText(this,"로그인 되어있습니다.",Toast.LENGTH_SHORT).show()
            finish()
            Log.d("로그인한 이메일",email)
            Log.d("로그인한 성",familyName)
            Log.d("로그인한 이름",givenName)
            Log.d("로그인한 전체이름",displayName)
            Log.d("로그인한 프로필 주소",photoUrl)
            Log.d("로그인한 id",test)
        }catch (e : ApiException){
            Log.w("failed","signInResult:failed code"+e.statusCode)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCanceledListener(this) {

        }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCanceledListener(this) {

        }
    }

    private fun getCurrentUserProfile(){
        val curUser = GoogleSignIn.getLastSignedInAccount(this)
        curUser?.let {
            val email = curUser.email.toString()
            val familyName = curUser.familyName.toString()
            val givenName = curUser?.givenName.toString()
            val displayName = curUser?.displayName.toString()
            val photoUrl = curUser?.photoUrl.toString()

            Log.d("현재 로그인된 이메일",email)
            Log.d("현재 로그인된 성",familyName)
            Log.d("현재 로그인된 이름",givenName)
            Log.d("현재 로그인된 전체이름",displayName)
            Log.d("현재 로그인된 프로필 주소",photoUrl)
        }

    }
}