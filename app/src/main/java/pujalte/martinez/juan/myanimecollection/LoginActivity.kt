package pujalte.martinez.juan.myanimecollection

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

        val splashScreen = installSplashScreen()
        var keepOnScreenCondition = true
        splashScreen.setKeepOnScreenCondition { keepOnScreenCondition }
        Handler(Looper.getMainLooper()).postDelayed({
            keepOnScreenCondition = false
        }, 3000)

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
        fun updateLoginSignupIsEnabled() {
            val isEnabled =
                !binding.userInput.text.isNullOrBlank() && !binding.passwordInput.text.isNullOrBlank()
            binding.loginButton.isEnabled = isEnabled
            binding.signupButton.isEnabled = isEnabled
        }

        fun setupTextInputLayoutError(
            textInputLayout: TextInputLayout, error: String = getString(R.string.cant_be_empty)
        ) {
            textInputLayout.editText?.doAfterTextChanged {
                if (it.isNullOrBlank()) {
                    textInputLayout.error = error
                    textInputLayout.isErrorEnabled = true
                    updateLoginSignupIsEnabled()
                } else {
                    textInputLayout.error = null
                    textInputLayout.isErrorEnabled = false
                    updateLoginSignupIsEnabled()
                }
            }
        }

        setupTextInputLayoutError(binding.userInputLayout)
        setupTextInputLayoutError(binding.passwordInputLayout)

        fun setupButtonSnackbar(
            button: Button,
            snackbarText: CharSequence = button.text,
            duration: Int = Snackbar.LENGTH_SHORT,
            actionText: CharSequence? = null,
            listener: View.OnClickListener = View.OnClickListener {}
        ) {
            button.setOnClickListener {
                Snackbar.make(binding.root, snackbarText, duration).setAction(actionText, listener)
                    .show()
            }
        }

        setupButtonSnackbar(
            binding.loginButton,
            getString(R.string.login_snackbar_text),
            Snackbar.LENGTH_INDEFINITE,
            getString(R.string.snackbar_goto_action_text)
        ) {
            val intent = Intent(this, RVActivity::class.java)
            startActivity(intent)
        }
        setupButtonSnackbar(binding.signupButton)
        setupButtonSnackbar(binding.forgotPasswordButton)
        setupButtonSnackbar(
            binding.googleLoginButton,
            getString(R.string.third_party_login_snackbar_text),
            Snackbar.LENGTH_INDEFINITE,
            getString(R.string.snackbar_goto_action_text)
        ) {
            val intent = Intent(this, ContactoActivity::class.java)
            startActivity(intent)
        }
        setupButtonSnackbar(
            binding.facebookLoginButton,
            getString(R.string.third_party_login_snackbar_text),
            Snackbar.LENGTH_INDEFINITE,
            getString(R.string.snackbar_goto_action_text)
        ) {
            val intent = Intent(this, ContactoActivity::class.java)
            startActivity(intent)
        }
    }
}