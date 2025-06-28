package com.pnow.ramlist.app.data.mapper

import com.pnow.ramlist.app.ui.adapter.CharacterStatus
import org.junit.Test

class CharacterStatusMapperTest {
    val tested = CharacterStatusMapper()

    @Test
    fun mapToStatusDrawableTest() {
        val status = "Alive"

        val result = tested.mapToStatusDrawable(status)

        val expected = CharacterStatus.Alive
        assert(result == expected)
    }
}
