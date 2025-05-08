package com.pnow.rick_and_morty_list.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pnow.rick_and_morty_list.app.ui.model.EpisodeUIModel
import com.pnow.rick_and_morty_list.databinding.ItemEpisodeBinding
import javax.inject.Inject

class EpisodeListAdapter @Inject constructor() :
    ListAdapter<EpisodeUIModel, EpisodeListAdapter.EpisodeViewHolder>(EpisodeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodeBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EpisodeUIModel) {
            binding.episodeNumber.text = item.episodeNumber
            binding.episodeName.text = item.name
            binding.episodeDate.text = item.date
        }
    }

    class EpisodeDiffCallback : DiffUtil.ItemCallback<EpisodeUIModel>() {
        override fun areItemsTheSame(oldItem: EpisodeUIModel, newItem: EpisodeUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeUIModel, newItem: EpisodeUIModel): Boolean {
            return oldItem == newItem
        }
    }
}
