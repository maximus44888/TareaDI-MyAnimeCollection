package pujalte.martinez.juan.myanimecollection

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import pujalte.martinez.juan.myanimecollection.databinding.ActivityContactoBinding

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
		fun setupButton(
			clickable: View,
			editText: EditText,
			permission: String?,
			intentSupplier: (clickable: EditText) -> Intent,
			rationalePermission: String = "Some permissions are required for this feature to work", // TODO: Add R string
			rationaleUnavailability: String = "Feature won't be available", // TODO: Add R string,
			unableToResolveActivityMessage: String = "No app found", // TODO: Add R string
		) {
			fun runIntent() {
				val intent = intentSupplier(editText)
				if (intent.resolveActivity(packageManager) != null) {
					startActivity(intent)
				} else {
					Snackbar.make(
						binding.root, unableToResolveActivityMessage, Snackbar.LENGTH_LONG
					).show()
				}
			}
			
			val onClickListener = if (permission == null) {
				View.OnClickListener {
					runIntent()
				}
			} else {
				val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {
					if (it) {
						runIntent()
					} else {
						Snackbar.make(
							binding.root, rationaleUnavailability, Snackbar.LENGTH_LONG
						).show()
					}
				}
				View.OnClickListener {
					when {
						ContextCompat.checkSelfPermission(
							this, permission
						) == PERMISSION_GRANTED -> {
							runIntent()
						}
						
						shouldShowRequestPermissionRationale(permission) -> {
							Snackbar.make(
								binding.root, rationalePermission, Snackbar.LENGTH_LONG
							).show()
						}
						
						else -> {
							requestPermissionLauncher.launch(permission)
						}
					}
					
				}
			}
			
			clickable.setOnClickListener(onClickListener)
		}
		
		setupButton(
			binding.phoneButton, binding.phone, Manifest.permission.CALL_PHONE, { editText ->
				Intent(Intent.ACTION_CALL, Uri.parse("tel:${editText.text}"))
			}, "You need to grant call permissions", // TODO: Add R string
			"You'll be unable to make a call" // TODO: Add R string
		)
		setupButton(binding.whatsappButton, binding.phone, null, { editText ->
			Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${editText.text}")).apply {
				setPackage("com.whatsapp")
			}
		})
		setupButton(binding.emailButton, binding.email, null, { editText ->
			Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${editText.text}"))
		})
		setupButton(
			binding.locationButton,
			binding.location,
			Manifest.permission.ACCESS_COARSE_LOCATION,
			{ editText ->
				Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${editText.text}"))
			},
			"You need to grant location permissions", // TODO: Add R string
			"You'll be unable to view the map" // TODO: Add R string
		)
		setupButton(
			binding.map, binding.location, Manifest.permission.ACCESS_COARSE_LOCATION, { editText ->
				Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${editText.text}"))
			}, "You need to grant location permissions", // TODO: Add R string
			"You'll be unable to view the map" // TODO: Add R string
		)
	}
}