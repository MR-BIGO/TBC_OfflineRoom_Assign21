package com.example.tbc_offlineroom_assign21.di

import android.content.Context
import androidx.room.Room
import com.example.tbc_offlineroom_assign21.data.local.dao.ShopItemDao
import com.example.tbc_offlineroom_assign21.data.local.database.ItemsDatabase
import com.example.tbc_offlineroom_assign21.data.local.database.ItemsDatabaseMigration1to2
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
            .addMigrations(ItemsDatabaseMigration1to2.MIGRATION_1_2)
            .build()

    @Provides
    fun provideUserAccountDao(database: ItemsDatabase): ShopItemDao {
        return database.shopItemDao()
    }
}