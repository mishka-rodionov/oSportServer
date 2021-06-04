package app.di

import data.repository.CompetitionRepositoryImpl
import data.repository.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module("REPOSITORY_MODULE") {
    bind() from singleton { UserRepositoryImpl(instance()) }
    bind() from singleton { CompetitionRepositoryImpl(instance()) }
}