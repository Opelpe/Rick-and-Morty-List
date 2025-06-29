package com.pnow.ramlist.app.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pnow.ramlist.app.ui.adapter.EpisodeListAdapter
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.model.LocationUIModel
import com.pnow.ramlist.app.ui.viewmodel.DetailsUiState
import com.pnow.ramlist.app.ui.viewmodel.DetailsViewModel
import com.pnow.ramlist.databinding.FragmentCharacterDeatailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private lateinit var episodeAdapter: EpisodeListAdapter

    private lateinit var binding: FragmentCharacterDeatailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var args: CharacterInfo.ListItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        args =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(
                    CharactersListFragment.MODEL_BUNDLE,
                    CharacterInfo.ListItem::class.java,
                )
            } else {
                arguments?.getSerializable(CharactersListFragment.MODEL_BUNDLE) as CharacterInfo.ListItem
            }

        binding = FragmentCharacterDeatailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        getDetails()
        observeDetailsState()
        bindCharacterDetails()
    }

    private fun setupAdapter() {
        episodeAdapter = EpisodeListAdapter()
        binding.episodesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.episodesRecyclerView.adapter = episodeAdapter
    }

    private fun observeDetailsState() {
        lifecycleScope.launch {
            detailsViewModel.detailsState.collect { state ->
                when (state) {
                    DetailsUiState.CharacterInfoLoading -> setCharacterInfoProgress(true)
                    is DetailsUiState.CharacterInfoUpdated -> updateCharacterInfo(state.info)
                    is DetailsUiState.EpisodesUpdated -> episodeAdapter.submitList(state.episodes)
                    is DetailsUiState.EpisodesLoading -> setEpisodesProgress(state.isLoading)
                    is DetailsUiState.Failure -> handleError(state.error)
                }
            }
        }
    }

    private fun handleError(error: String) {
        setCharacterInfoProgress(false)
        setEpisodesProgress(false)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun updateCharacterInfo(model: CharacterInfo.Details) {
        setCharacterInfoProgress(false)
        bindOriginInfo(model.originModel)
        bindLocationInfo(model.locationModel)
    }

    private fun getDetails() {
        val episodesList = args?.episodeUrl
        val location = args?.location
        val origin = args?.origin
        bindLocationInfo(detailsViewModel.getLocationDescription(location))
        bindOriginInfo(detailsViewModel.getLocationDescription(origin))
        detailsViewModel.fetchDetails(episodesList, location?.url, origin?.url)
    }

    private fun bindCharacterDetails() {
        with(binding) {
            characterNameDescription.text = args?.name
            characterSpeciesDescription.text = args?.species
            characterGenderDescription.text = args?.gender
            characterStatusDescription.text = args?.status
            characterStatusColorContainer.background =
                args?.statusDrawable?.drawable?.let { resId ->
                    ContextCompat.getDrawable(
                        requireContext(),
                        resId,
                    )
                }
            originNameDescription.text = detailsViewModel.getLocationDescription(args?.origin).name
            Picasso.get().load(args?.imageUrl).into(characterIcon)
        }
    }

    private fun bindOriginInfo(originModel: LocationUIModel) {
        with(binding) {
            originNameDescription.text = originModel.name
            originTypeDescription.text = originModel.type
            originDimensionDescription.text = originModel.dimension
        }
    }

    private fun bindLocationInfo(locationModel: LocationUIModel) {
        with(binding) {
            locationNameDescription.text = locationModel.name
            locationTypeDescription.text = locationModel.type
            locationDimensionDescription.text = locationModel.dimension
        }
    }

    private fun setCharacterInfoProgress(visible: Boolean) {
        if (visible) {
            binding.progressView.visibility = View.VISIBLE
        } else {
            binding.progressView.visibility = View.GONE
        }
    }

    private fun setEpisodesProgress(visible: Boolean) {
        if (visible) {
            binding.episodesProgress.visibility = View.VISIBLE
        } else {
            binding.episodesProgress.visibility = View.GONE
        }
    }
}
