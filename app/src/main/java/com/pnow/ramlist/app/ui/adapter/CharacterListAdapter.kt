package com.pnow.ramlist.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterListAdapter(private val onClick: (CharacterInfo.ListItem) -> Unit) :
    PagingDataAdapter<CharacterInfo.ListItem, CharacterListAdapter.CharacterViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState ->
            holder.bindCharacterInfo(
                userItemUiState,
                onClick
            )
        }
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCharacterInfo(
            character: CharacterInfo.ListItem, onClick: (CharacterInfo.ListItem) -> Unit
        ) {
            with(binding) {
                root.setOnClickListener { onClick(character) }
                characterNameDescription.text = character.name
                characterStatusDescription.text = character.status
                Picasso.get().load(character.imageUrl).into(characterIcon)
                characterStatusColorContainer.background =
                    ContextCompat.getDrawable(root.context, character.statusDrawable.drawable)
            }
        }

    }
}

object Comparator : DiffUtil.ItemCallback<CharacterInfo.ListItem>() {
    override fun areItemsTheSame(
        oldItem: CharacterInfo.ListItem,
        newItem: CharacterInfo.ListItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CharacterInfo.ListItem,
        newItem: CharacterInfo.ListItem
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

sealed class CharacterStatus(@DrawableRes val drawable: Int) {
    data object Alive : CharacterStatus(R.drawable.character_status_background_alive)
    data object Dead : CharacterStatus(R.drawable.character_status_background_dead)
    data object Unknown : CharacterStatus(R.drawable.character_status_background_unknown)
}