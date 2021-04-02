package app.di

import data.dao.UserDao
import data.dao.UserDaoImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val daoModule = Kodein.Module("DAO") {
    bind() from singleton { UserDaoImpl() }
}