package com.example.tbc_offlineroom_assign21.presentation.screen.home.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_offlineroom_assign21.R
import com.example.tbc_offlineroom_assign21.databinding.ShopItemLayoutBinding
import com.example.tbc_offlineroom_assign21.presentation.model.ShopItemPres

class ShopItemsRvAdapter :
    ListAdapter<ShopItemPres, ShopItemsRvAdapter.ShopItemsViewHolder>(DiffCallback())
{

    var itemOnClick: ((Int) -> Unit)? = null

    inner class ShopItemsViewHolder(private val binding: ShopItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding) {
            val item = currentList[adapterPosition]

            tvTitle.text = item.title
            tvPrice.text = item.price
            Glide.with(itemView.context).load(item.img).into(ivWoman)

            if (item.isFavourite) {
                ivHeart.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        root.resources,
                        R.drawable.ic_heart_selected,
                        root.resources.newTheme()
                    )
                )
            } else {
                ivHeart.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        root.resources,
                        R.drawable.ic_heart_unselected,
                        root.resources.newTheme()
                    )
                )
            }
        }

        fun listeners() = with(binding) {
            ivHeart.setOnClickListener {
                itemOnClick!!(currentList[adapterPosition].id)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    fun setData(data: List<ShopItemPres>) {
        submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemsViewHolder {
        return ShopItemsViewHolder(
            ShopItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopItemsViewHolder, position: Int) {
        holder.bind()
        holder.listeners()
    }

    private class DiffCallback : DiffUtil.ItemCallback<ShopItemPres>() {
        override fun areItemsTheSame(oldItem: ShopItemPres, newItem: ShopItemPres): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItemPres, newItem: ShopItemPres): Boolean {
            return oldItem == newItem
        }
    }
}