package kg.token.keepmoney.model

import android.graphics.Bitmap

class User (
    val name: String,
    val email: String? = null,
    val avatar: Bitmap? = null
)