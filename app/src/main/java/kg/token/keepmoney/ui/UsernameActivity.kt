package kg.token.keepmoney.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.token.keepmoney.R

class UsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsernameActivity::class.java))
        }
    }

}