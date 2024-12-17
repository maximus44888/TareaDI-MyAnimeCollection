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
                "Naruto",
                "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc.",
                R.drawable.naruto
            ),
            Item(
                "One Piece",
                "Gold Roger was known as the Pirate King, the strongest and most infamous being to have sailed the Grand Line. The capture and death of Roger by the World Government brought a change throughout the world.",
                R.drawable.one_piece
            ),
            Item(
                "Bleach",
                "Ichigo Kurosaki is a rather normal high school student apart from the fact he has the ability to see ghosts. This ability never impacted his life in a major way until the day he encounters the Shinigami Kuchiki Rukia.",
                R.drawable.bleach
            ),
            Item(
                "Dragon Ball",
                "Goku Son is a young boy who lives in the woods all alone until Bulma runs into him in her search for a set of magical objects called the \"Dragon Balls\". The artifacts are said to grant one wish to whoever collects all seven.",
                R.drawable.dragon_ball
            ),
            Item(
                "Fairy Tail",
                "Across the Fiore kingdom, wizards join guilds and make their pay by filling magical needs—but one guild has a reputation as the roughest, rowdiest, most dangerous of all: Fairy Tail!",
                R.drawable.fairy_tail
            ),
            Item(
                "One Punch Man",
                "Saitama has a rather peculiar hobby, being a superhero, but despite his heroic deeds and superhuman abilities, a shadow looms over his life. He's become much too powerful, to the point that every opponent ends up defeated with a single punch.",
                R.drawable.one_punch_man
            ),
            Item(
                "Hunter X Hunter",
                "A Hunter is one who travels the world doing all sorts of dangerous tasks. From capturing criminals to searching deep within uncharted lands for any lost treasures. Gon is a young boy whose father disappeared long ago, being a Hunter.",
                R.drawable.hunter_x_hunter
            ),
            Item(
                "Tokyo Ghoul",
                "The suspense horror/dark fantasy story is set in Tokyo, which is haunted by mysterious \"ghouls\" who are devouring humans. People are gripped by the fear of these ghouls whose identities are masked in mystery.",
                R.drawable.tokyo_ghoul
            ),
            Item(
                "Death Note",
                "Light Yagami is a genius high school student who is about to learn about life through a book of death. When a bored shinigami, a God of Death, drops a black notepad called a Death Note, Light receives power over life and death with the stroke of a pen.",
                R.drawable.death_note
            ),
            Item(
                "Attack On Titan",
                "Several hundred years ago, humans were nearly exterminated by titans. Titans are typically several stories tall, seem to have no intelligence, devour human beings and, worst of all, seem to do it for the pleasure rather than as a food source.",
                R.drawable.attack_on_titan
            ),
        )

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = ItemAdapter(items)
    }
}