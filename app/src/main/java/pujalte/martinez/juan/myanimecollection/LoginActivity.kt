package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
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
        binding.userInputLayout.editText?.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                binding.userInputLayout.error = getString(R.string.cant_be_empty)
                binding.userInputLayout.isErrorEnabled = true
            } else {
                binding.userInputLayout.error = null
                binding.userInputLayout.isErrorEnabled = false
            }
        }
        binding.passwordInputLayout.editText?.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                binding.passwordInputLayout.error = getString(R.string.cant_be_empty)
                binding.passwordInputLayout.isErrorEnabled = true
            } else {
                binding.passwordInputLayout.error = null
                binding.passwordInputLayout.isErrorEnabled = false
            }
        }
    }
}