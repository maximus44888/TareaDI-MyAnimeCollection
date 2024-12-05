package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import pujalte.martinez.juan.myanimecollection.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialize(savedInstanceState)
    }

    private fun initialize(savedInstanceState: Bundle?) {
        val snackbar: Snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)

        fun setupTextInputLayoutError(
            textInputLayout: TextInputLayout,
            error: String = getString(R.string.cant_be_empty)
        ) {
            textInputLayout.editText?.doAfterTextChanged {
                if (it.isNullOrBlank()) {
                    textInputLayout.error = error
                    textInputLayout.isErrorEnabled = true
                } else {
                    textInputLayout.error = null
                    textInputLayout.isErrorEnabled = false
                }
            }
        }

        fun setupButtonSnackbar(button: Button) {
            button.setOnClickListener {
                snackbar.setText(button.text).show()
            }
        }

        setupTextInputLayoutError(binding.userInputLayout)
        setupTextInputLayoutError(binding.passwordInputLayout)

        setupButtonSnackbar(binding.loginButton)
        setupButtonSnackbar(binding.signupButton)
        setupButtonSnackbar(binding.forgotPasswordButton)
        setupButtonSnackbar(binding.googleLoginButton)
        setupButtonSnackbar(binding.facebookLoginButton)
    }
}