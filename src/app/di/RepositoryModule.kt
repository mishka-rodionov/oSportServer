package app.di

import domain.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module("REPOSITORY_MODULE") {
    bind() from singleton { UserRepositoryImpl(instance()) }
}