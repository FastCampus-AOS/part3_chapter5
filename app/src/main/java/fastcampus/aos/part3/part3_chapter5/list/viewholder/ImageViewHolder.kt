package fastcampus.aos.part3.part3_chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import fastcampus.aos.part3.part3_chapter5.databinding.ItemImageBinding
import fastcampus.aos.part3.part3_chapter5.model.ImageItem
import fastcampus.aos.part3.part3_chapter5.model.ListItem

class ImageViewHolder(
    private val binding: ItemImageBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as ImageItem
        binding.item = item
    }
}