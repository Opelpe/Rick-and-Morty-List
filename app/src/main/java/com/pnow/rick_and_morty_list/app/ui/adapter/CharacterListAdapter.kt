package com.pnow.rick_and_morty_list.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pnow.rick_and_morty_list.R
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import com.pnow.rick_and_morty_list.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterListAdapter(private val onClick: (CharacterUIModel) -> Unit) :
    PagingDataAdapter<CharacterUIModel, CharacterListAdapter.CharacterViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.bindCharacterInfo(userItemUiState, onClick) }
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCharacterInfo(
            character: CharacterUIModel, onClick: (CharacterUIModel) -> Unit
        ) {
            with(binding) {
                root.setOnClickListener { onClick(character) }
                characterNameDescription.text = character.name
                characterStatusDescription.text = character.status
                Picasso.get().load(character.imageUrl).into(characterIcon)
                characterStatusColorContainer.background = ContextCompat.getDrawable(root.context, character.statusDrawable.drawable)
            }
        }

    }
}

object Comparator : DiffUtil.ItemCallback<CharacterUIModel>() {
    override fun areItemsTheSame(oldItem: CharacterUIModel, newItem: CharacterUIModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CharacterUIModel,
        newItem: CharacterUIModel
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

sealed class CharacterStatus(@DrawableRes val drawable: Int) {
    data object Alive : CharacterStatus(R.drawable.character_status_background_alive)
    data object Dead : CharacterStatus(R.drawable.character_status_background_dead)
    data object Unknown : CharacterStatus(R.drawable.character_status_background_unknown)
}