package com.pnow.ramlist.app.data.mapper

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.pnow.ramlist.app.data.model.LocationModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class DetailsUiMapperTest {
    private val context =
        mockk<Context>(relaxed = true) {
            every { getString(any()) } returns "Unknown"
        }
    private val tested = DetailsUiMapper(context)

    @Test
    fun mapToLocationUiModelTest() {
        val locationToTest = LocationModel(1, "earth", "normal", "sun")

        val result = tested.mapToLocationUiModel(locationToTest)

        assertThat(result.name).isEqualTo("Earth")
    }

    @Test
    fun mapToLocationU2iModelTest() {
        val locationToTest = LocationModel(1, "", "normal", "sun")

        val result = tested.mapToLocationUiModel(locationToTest)

        assertThat(result.name).isEqualTo("Unknown")
    }
}
