package com.pnow.data.di

import com.pnow.data.character.repository.CharacterRepositoryImpl
import com.pnow.data.episode.EpisodeRepositoryImpl
import com.pnow.data.location.LocationRepositoryImpl
import com.pnow.domain.repository.CharacterRepository
import com.pnow.domain.repository.EpisodeRepository
import com.pnow.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        impl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    abstract fun bindEpisodeRepository(
        impl: EpisodeRepositoryImpl
    ): EpisodeRepository

    @Binds
    abstract fun bindLocationRepository(
        impl: LocationRepositoryImpl
    ): LocationRepository
}
