package fastcampus.aos.part3.part3_chapter5.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aos.part3.part3_chapter5.model.ListItem
import androidx.recyclerview.widget.ListAdapter
import fastcampus.aos.part3.part3_chapter5.databinding.ItemImageBinding
import fastcampus.aos.part3.part3_chapter5.databinding.ItemVideoBinding
import fastcampus.aos.part3.part3_chapter5.list.viewholder.ImageViewHolder
import fastcampus.aos.part3.part3_chapter5.list.viewholder.VideoViewHolder
import fastcampus.aos.part3.part3_chapter5.model.ImageItem

class ListAdapter (
    private val itemHandler: ItemHandler? = null
): ListAdapter<ListItem, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is ImageItem) IMAGE else VIDEO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == IMAGE) {
            ImageViewHolder(ItemImageBinding.inflate(inflater, parent, false), itemHandler)
        } else {
            VideoViewHolder(ItemVideoBinding.inflate(inflater, parent, false), itemHandler)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (getItemViewType(position) == IMAGE) {
            (holder as ImageViewHolder).bind(item)
        } else {
            (holder as VideoViewHolder).bind(item)
        }
    }

    override fun submitList(list: List<ListItem?>?) {
        Log.d("hyunsu Adapter", "submitList called with ${list?.size ?: 0} items")
        super.submitList(list)
    }

    companion object {

        private const val IMAGE = 0
        private const val VIDEO = 1

        private val diffUtil = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(
                oldItem: ListItem,
                newItem: ListItem
            ): Boolean {
                return oldItem.thumbnailUrl == newItem.thumbnailUrl
            }

            override fun areContentsTheSame(
                oldItem: ListItem,
                newItem: ListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}