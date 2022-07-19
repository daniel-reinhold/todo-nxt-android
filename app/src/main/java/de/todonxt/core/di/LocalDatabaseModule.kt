package de.todonxt.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.todonxt.core.data.source.local.LocalDatabase
import de.todonxt.core.data.source.local.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): LocalDatabase = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, "todonxt.db"
    ).build()


    @Singleton
    @Provides
    fun provideTaskDao(db: LocalDatabase): TaskDao {
        return db.getTaskDao()
    }

}