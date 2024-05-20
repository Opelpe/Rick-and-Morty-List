package com.pnow.rick_and_morty_list.app.data.mapper

import com.pnow.rick_and_morty_list.app.ui.adapter.CharacterStatus
import org.junit.Test

class CharacterStatusMapperTest {


    val tested = CharacterStatusMapper()
    @Test
    fun mapToStatusDrawableTest(){
        val status = "Alive"

        val result = tested.mapToStatusDrawable(status)

        val expected = CharacterStatus.Alive
        assert(result == expected)
    }

}