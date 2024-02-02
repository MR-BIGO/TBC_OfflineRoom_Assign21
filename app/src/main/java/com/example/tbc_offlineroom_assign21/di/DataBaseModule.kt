package com.example.tbc_offlineroom_assign21.di

import android.content.Context
import androidx.room.Room
import com.example.tbc_offlineroom_assign21.data.local.dao.ShopItemDao
import com.example.tbc_offlineroom_assign21.data.local.database.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): ItemsDatabase =
        Room.databaseBuilder(
            context, ItemsDatabase::class.java, "ITEMS_DATABASE"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserAccountDao(database: ItemsDatabase): ShopItemDao {
        return database.shopItemDao()
    }
}