package pujalte.martinez.juan.myanimecollection

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import pujalte.martinez.juan.myanimecollection.databinding.ActivityContactoBinding
import pujalte.martinez.juan.myanimecollection.databinding.LayoutSectionBinding

class ContactoActivity : AppCompatActivity() {

    private val binding: ActivityContactoBinding by lazy {
        ActivityContactoBinding.inflate(layoutInflater)
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

        val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {
                granted: Boolean ->
            if (granted) {
                val intent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse("tel:691313272")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                Snackbar.make(binding.root, "Feature not available cause permission denied", Snackbar.LENGTH_LONG).show()
            }
        }

        fun setupSection(section: LayoutSectionBinding, @DrawableRes resId: Int, contentDescription: String, inputType: Int) {
            section.button.icon = AppCompatResources.getDrawable(this, resId)
            section.button.contentDescription = contentDescription
            section.editText.inputType = inputType
            section.button.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel:691313272")
                    }
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        Snackbar.make(binding.root, "Permission required", Snackbar.LENGTH_LONG).show()
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            }
        }

        setupSection(binding.phone, R.drawable.phone_call, "Phone", InputType.TYPE_CLASS_PHONE)
        setupSection(binding.mail, R.drawable.email, "Mail", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        setupSection(binding.location, R.drawable.baseline_add_location_24, "Location", InputType.TYPE_CLASS_TEXT)

    }
}