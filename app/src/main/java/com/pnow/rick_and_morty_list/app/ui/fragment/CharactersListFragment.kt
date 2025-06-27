package com.pnow.rick_and_morty_list.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pnow.rick_and_morty_list.R
import com.pnow.rick_and_morty_list.app.ui.adapter.CharacterListAdapter
import com.pnow.rick_and_morty_list.app.ui.viewmodel.CharacterViewModel
import com.pnow.rick_and_morty_list.databinding.FragmentCharactersListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
    private val characterVM: CharacterViewModel by viewModels()

    private val adapter = CharacterListAdapter { clickedItem ->
        val bundle = Bundle()
        bundle.putSerializable(MODEL_BUNDLE, clickedItem)
        view?.findNavController()?.navigate(R.id.characterDetails, bundle)
    }

    companion object {
        const val MODEL_BUNDLE = "model"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)

        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.charactersRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            characterVM.charactersState.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

}