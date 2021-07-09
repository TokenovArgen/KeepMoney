package kg.token.keepmoney.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kg.token.keepmoney.R
import kg.token.keepmoney.model.User
import kg.token.keepmoney.util.toast
import kotlinx.android.synthetic.main.activity_authorization.*


const val GOOGLE_SIGN_IN = 25

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var googleAuthClient: GoogleSignInClient
    private lateinit var fbCallback: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        googleAuthClient = initGoogle()
        fbCallback = CallbackManager.Factory.create()
        initFacebook()
        initButtons()
    }

    private fun onSuccessAuth(authType: String) {
        when(authType) {
            GOOGLE -> {
                GoogleSignIn.getLastSignedInAccount(this)?.let {
                    it.displayName?.let {
                        User(it)
                    }
                }
            }
            FACEBOOK -> {
                object : ProfileTracker() {
                    override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                        oldProfile?.let {

                        }
                    }
                }
            }
        }
    }

    private fun initButtons() {
        iv_google.setOnClickListener {
            UsernameActivity.start(this)
//            startActivityForResult(googleAuthClient.signInIntent, GOOGLE_SIGN_IN)
        }
        iv_facebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
        }
    }

    private fun processGoogleAuth(task: Task<GoogleSignInAccount>) {
        try {
            onSuccessAuth(GOOGLE)
        } catch (e: ApiException) {
            e.localizedMessage?.let { toast(it) }
        }
    }

    private fun initFacebook() {
        LoginManager.getInstance()
            .registerCallback(fbCallback, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { onSuccessAuth(FACEBOOK) }
                override fun onCancel() { toast("fb cancelled") }
                override fun onError(exception: FacebookException) { exception.localizedMessage?.let { toast(it) } }
            })
    }

    private fun initGoogle(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fbCallback.onActivityResult(requestCode, resultCode, data);
        when (requestCode) {
            GOOGLE_SIGN_IN -> processGoogleAuth(GoogleSignIn.getSignedInAccountFromIntent(data))
        }
    }

    companion object {
        const val VKONTAKTE = "VK"
        const val FACEBOOK = "FB"
        const val GOOGLE = "GOOGLE"

        fun start(context: Context) {
            context.startActivity(Intent(context, AuthorizationActivity::class.java))
        }
    }
}