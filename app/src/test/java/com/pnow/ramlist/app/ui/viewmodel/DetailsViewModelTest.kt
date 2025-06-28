package com.pnow.ramlist.app.ui.viewmodel

import android.os.Looper
import com.pnow.ramlist.app.data.mapper.DetailsUiMapper
import com.pnow.ramlist.app.data.model.EpisodeModel
import com.pnow.ramlist.app.data.model.LocationModel
import com.pnow.ramlist.app.data.repository.RickAndMortyRepository
import com.pnow.ramlist.app.ui.model.LocationUIModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

class DetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val mockMapper =
        mockk<DetailsUiMapper>(relaxed = true, relaxUnitFun = true) {
            every { mapToLocationUiModel(any<LocationModel>()) } returns LocationUIModel("Earth", "Normal", "XD")
        }
    private val mockRepo =
        mockk<RickAndMortyRepository>(relaxed = true, relaxUnitFun = true) {
            coEvery { getEpisode(any()) } returns mockk<EpisodeModel>(relaxed = true)
        }
    private val tested = DetailsViewModel(mockRepo, mockMapper, dispatcher)

    val looper =
        mockk<Looper> {
            every { thread } returns Thread.currentThread()
        }

    @Test
    fun fetchDetails() {
        mockkStatic(Looper::class)
        every { Looper.getMainLooper() } returns looper

        val episodeUrl = listOf("https://rickandmortyapi.com/api/episode/3")
        val originUrl = "https://rickandmortyapi.com/api/location/2"
        val locationUrl = "https://rickandmortyapi.com/api/location/4"

        tested.fetchDetails(episodeUrl, locationUrl, originUrl)

        verify { mockMapper.mapToLocationUiModel(any<LocationModel>()) }
    }
}
