package pujalte.martinez.juan.myanimecollection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
        installSplashScreen()
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
            Item(
                "Naruto", getString(R.string.naruto_description), R.drawable.naruto
            ),
            Item(
                "One Piece", getString(R.string.one_piece_description), R.drawable.one_piece
            ),
            Item(
                "Bleach", getString(R.string.bleach_description), R.drawable.bleach
            ),
            Item(
                "Dragon Ball", getString(R.string.dragon_ball_description), R.drawable.dragon_ball
            ),
            Item(
                "Fairy Tail", getString(R.string.fairy_tail_description), R.drawable.fairy_tail
            ),
            Item(
                "One Punch Man",
                getString(R.string.one_punch_man_description),
                R.drawable.one_punch_man
            ),
            Item(
                "Hunter X Hunter",
                getString(R.string.hunter_x_hunter_description),
                R.drawable.hunter_x_hunter
            ),
            Item(
                "Tokyo Ghoul", getString(R.string.tokyo_ghoul_description), R.drawable.tokyo_ghoul
            ),
            Item(
                "Death Note", getString(R.string.death_note_description), R.drawable.death_note
            ),
            Item(
                "Attack On Titan",
                getString(R.string.attack_on_titan_description),
                R.drawable.attack_on_titan
            ),
        )

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = ItemAdapter(items)
    }
}