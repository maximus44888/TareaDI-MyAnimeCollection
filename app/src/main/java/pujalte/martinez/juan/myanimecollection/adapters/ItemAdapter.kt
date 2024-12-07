package pujalte.martinez.juan.myanimecollection.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pujalte.martinez.juan.myanimecollection.data.Item
import pujalte.martinez.juan.myanimecollection.databinding.LayoutItemBinding
import pujalte.martinez.juan.myanimecollection.R

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView: ImageView = binding.imageView
        private val textView: TextView = binding.textView
        private val floatingActionButton: FloatingActionButton = binding.floatingActionButton

        fun bind(item: Item) {
            imageView.setImageResource(item.image)
            textView.text = item.title
            floatingActionButton.setOnClickListener {
                item.isFavorite = !item.isFavorite
                if (item.isFavorite) {
                    floatingActionButton.setImageResource(R.drawable.fav_selected)
                } else {
                    floatingActionButton.setImageResource(R.drawable.fav_unselected)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ItemViewHolder(LayoutItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}