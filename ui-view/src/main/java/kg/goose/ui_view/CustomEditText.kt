package kg.goose.ui_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.custom_edit_text.view.*

class CustomEditText (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.custom_edit_text, this)
        context.obtainStyledAttributes(attrs, R.styleable.CustomEditText).run {
            setHint(getString(R.styleable.CustomEditText_android_hint))
            recycle()
        }
    }

    private fun setHint(string: String?) {
        string?.let { et_input.hint = it }
    }

    private fun setHint(@StringRes resource: Int?) {
        resource?.let { et_input.setHint(resource)  }
    }

    private fun setText(string: String?) {
        et_input.setText(string)
    }

    private fun setText(@StringRes resource: Int?) {
        resource?.let { et_input.setText(it) }
    }

}