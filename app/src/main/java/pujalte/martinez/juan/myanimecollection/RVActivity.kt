package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pujalte.martinez.juan.myanimecollection.adapters.ItemAdapter
import pujalte.martinez.juan.myanimecollection.data.Item
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
        val items = listOf(
            Item("Naruto", "Primer Anime", R.drawable.icons8_google_480),
            Item("One Piece", "Segundo Anime", R.drawable.icons8_facebook_500),
            Item("Bleach", "Tercer Anime", R.drawable.ic_launcher_foreground),
            Item("Dragon Ball", "Cuarto Anime", R.drawable.icons8_google_480),
            Item("Fairy Tail", "Quinto Anime", R.drawable.icons8_facebook_500),
            Item("One Punch Man", "Sexto Anime", R.drawable.ic_launcher_foreground),
            Item("Hunter X Hunter", "Septimo Anime", R.drawable.icons8_google_480),
            Item("Tokyo Ghoul", "Octavo Anime", R.drawable.icons8_facebook_500),
            Item("Death Note", "Noveno Anime", R.drawable.ic_launcher_foreground),
            Item("Attack On Titan", "Decimo Anime", R.drawable.icons8_google_480),
            Item("Fullmetal Alchemist", "Onceavo Anime", R.drawable.icons8_facebook_500),
            Item("Black Clover", "Doceavo Anime", R.drawable.ic_launcher_foreground),
        )

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = ItemAdapter(items)
    }
}