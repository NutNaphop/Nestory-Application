package com.naphop.nestory.di

import androidx.room.Room
import com.naphop.nestory.data.local.NestoryDatabase
import com.naphop.nestory.data.repository.BoxRepositoryImpl
import com.naphop.nestory.data.repository.CategoryRepositoryImpl
import com.naphop.nestory.data.repository.InventoryRepositoryImpl
import com.naphop.nestory.domain.repository.BoxRepository
import com.naphop.nestory.domain.repository.CategoryRepository
import com.naphop.nestory.domain.repository.InventoryRepository
import com.naphop.nestory.feature.home.ExpirationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NestoryDatabase::class.java,
            "nestory_database"
        ).build()
    }

    single { get<NestoryDatabase>().boxDao() }
    single { get<NestoryDatabase>().inventoryDao() }
    single { get<NestoryDatabase>().categoryDao() }

    single<BoxRepository> { BoxRepositoryImpl(get()) }
    single<InventoryRepository> { InventoryRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    viewModel { ExpirationViewModel(get()) }
}