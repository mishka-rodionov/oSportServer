package app.di

import data.dao.UserDaoImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal val daoModule = Kodein.Module("DAO") {
    bind() from singleton { UserDaoImpl() }
}