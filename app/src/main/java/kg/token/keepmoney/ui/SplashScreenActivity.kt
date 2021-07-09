package kg.token.keepmoney.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kg.token.keepmoney.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AuthorizationActivity.start(this)
        finish()

        //check is user authorized
        val account = GoogleSignIn.getLastSignedInAccount(this)?.let {
            //google authorized
            Toast.makeText(this, it.email, Toast.LENGTH_SHORT).show()
        }

        val accessToken = AccessToken.getCurrentAccessToken()
        accessToken?.let {
            if (!it.isExpired) {
                //facebook authorized
            }
        }
    }
}