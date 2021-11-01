package app.orienti.android.ui.components

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import app.orienti.android.R
import app.orienti.android.databinding.CommonInputTextBinding
import dagger.hilt.android.AndroidEntryPoint
import sk.backbone.parent.ui.components.ParentLinearLayout

@AndroidEntryPoint
open class CommonInputText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ParentLinearLayout(context, attrs, defStyleAttr) {
    private val viewBinding: CommonInputTextBinding by lazy {
        CommonInputTextBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var text: String?
        get() = this.viewBinding.inputEditText.text.toString()
        set(value) {
            this.viewBinding.inputEditText.text = Editable.Factory.getInstance().newEditable(value ?: "")
        }

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CommonInputText)

        viewBinding.headerTextView.text = attributes.getString(R.styleable.CommonInputText_android_title)
        viewBinding.inputEditText.hint = attributes.getString(R.styleable.CommonInputText_android_hint)
        viewBinding.inputEditText.inputType = attributes.getInt(R.styleable.CommonInputText_android_inputType, InputType.TYPE_CLASS_TEXT)
        viewBinding.inputEditText.isEnabled = attributes.getBoolean(R.styleable.CommonInputText_android_enabled, true)

        val maxLength = attributes.getInt(R.styleable.CommonInputText_android_maxLength, 0)

        if(maxLength > 0){
            viewBinding.inputEditText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        }

        attributes.recycle()
    }
}