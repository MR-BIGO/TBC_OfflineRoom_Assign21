package com.example.tbc_offlineroom_assign21.presentation.screen.home.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_offlineroom_assign21.R
import com.example.tbc_offlineroom_assign21.databinding.ShopItemLayoutBinding
import com.example.tbc_offlineroom_assign21.presentation.model.ShopItemPres

class ShopItemsRvAdapter : RecyclerView.Adapter<ShopItemsRvAdapter.ShopItemsViewHolder>() {

    private var items: List<ShopItemPres> = listOf()
    var itemOnClick: ((Int) -> Unit)? = null

    inner class ShopItemsViewHolder(private val binding: ShopItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            val item = items[position]

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

        fun listeners(position: Int) = with(binding) {
            ivHeart.setOnClickListener {
                itemOnClick!!(items[position].id)
                notifyItemChanged(position)
            }
        }
    }

    fun setData(data: List<ShopItemPres>){
        items = data
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

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShopItemsViewHolder, position: Int) {
        holder.bind(position)
        holder.listeners(position)
    }
}