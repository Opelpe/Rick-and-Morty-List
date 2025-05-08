package com.pnow.rick_and_morty_list.app.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pnow.rick_and_morty_list.app.ui.adapter.EpisodeListAdapter
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import com.pnow.rick_and_morty_list.app.ui.model.DetailsUIModel
import com.pnow.rick_and_morty_list.app.ui.model.LocationUIModel
import com.pnow.rick_and_morty_list.app.ui.viewmodel.DetailsViewModel
import com.pnow.rick_and_morty_list.databinding.FragmentCharacterDeatailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var episodeAdapter: EpisodeListAdapter

    private lateinit var binding: FragmentCharacterDeatailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var args: CharacterUIModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(
                CharactersListFragment.MODEL_BUNDLE,
                CharacterUIModel::class.java
            )
        } else {
            arguments?.getSerializable(CharactersListFragment.MODEL_BUNDLE) as CharacterUIModel
        }

        binding = FragmentCharacterDeatailsBinding.inflate(layoutInflater, container, false)

        setupAdapter()
        setProgressVisibility(true)
        getDetails()
        observeDetailsState()
        bindCharacterDetails()

        return binding.root
    }

    private fun setupAdapter() {
        binding.episodesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.episodesRecyclerView.adapter = episodeAdapter
    }

    private fun observeDetailsState() {
        lifecycleScope.launch {
            detailsViewModel.detailsState.collect { details ->
                details?.let {
                    updateDetailsView(it)
                }
            }
        }
    }

    private fun updateDetailsView(model: DetailsUIModel) {
        setProgressVisibility(false)
        episodeAdapter.submitList(model.episodeModel)
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
                        resId
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

    private fun setProgressVisibility(visible: Boolean) {
        if (visible) binding.progressView.visibility = View.VISIBLE
        else binding.progressView.visibility = View.GONE
    }
}
