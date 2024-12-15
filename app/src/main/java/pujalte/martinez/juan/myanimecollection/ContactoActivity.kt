package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import android.text.InputType
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        fun setupSection(section: LayoutSectionBinding, @DrawableRes resId: Int, contentDescription: String, inputType: Int) {
            section.button.icon = AppCompatResources.getDrawable(this, resId)
            section.button.contentDescription = contentDescription
            section.editText.inputType = inputType
        }

        setupSection(binding.phone, R.drawable.phone_call, "Phone", InputType.TYPE_CLASS_PHONE)
        setupSection(binding.mail, R.drawable.email, "Mail", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        setupSection(binding.location, R.drawable.baseline_add_location_24, "Location", InputType.TYPE_CLASS_TEXT)

    }
}