package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pujalte.martinez.juan.myanimecollection.databinding.ActivityRvBinding

class RVActivity : AppCompatActivity() {
    
    private val binding: ActivityRvBinding by lazy {
        ActivityRvBinding.inflate(layoutInflater)
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

    private fun initialize(saveInstanceState: Bundle?) {

    }
}