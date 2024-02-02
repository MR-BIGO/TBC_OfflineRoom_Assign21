package com.example.tbc_offlineroom_assign21.presentation.screen.home.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_offlineroom_assign21.R
import com.example.tbc_offlineroom_assign21.databinding.SectionItemLayoutBinding
import com.example.tbc_offlineroom_assign21.presentation.model.Section

class SectionsRvAdapter : RecyclerView.Adapter<SectionsRvAdapter.SectionsRvViewHolder>() {

    private var sections: List<Section> = listOf()
    var itemOnClick: ((Int) -> Unit)? = null
    private var prevItem = 0

    inner class SectionsRvViewHolder(private val binding: SectionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) = with(binding) {
            val section = sections[position]

            btnSection.text = section.title

            if (section.isSelected) {
                btnSection.setBackgroundDrawable(
                    ResourcesCompat.getDrawable(
                        root.resources,
                        R.drawable.background_section_item_selected,
                        root.resources.newTheme()
                    )
                )
                btnSection.setTextColor(
                    root.resources.getColor(
                        R.color.white,
                        root.resources.newTheme()
                    )
                )
            } else {
                btnSection.setBackgroundDrawable(
                    ResourcesCompat.getDrawable(
                        root.resources,
                        R.drawable.background_section_item_unselected,
                        root.resources.newTheme()
                    )
                )
                btnSection.setTextColor(
                    root.resources.getColor(
                        R.color.grey,
                        root.resources.newTheme()
                    )
                )
            }
        }

        fun listeners(position: Int) = with(binding) {

            root.setOnClickListener {
                itemOnClick!!(sections[position].id)
                notifyItemChanged(position)
                notifyItemChanged(prevItem)
                prevItem = position
            }
        }
    }

    fun setData(data: List<Section>) {
        sections = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsRvViewHolder {
        return SectionsRvViewHolder(
            SectionItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = sections.size
    override fun onBindViewHolder(holder: SectionsRvViewHolder, position: Int) {
        holder.bind(position)
        holder.listeners(position)
    }
}