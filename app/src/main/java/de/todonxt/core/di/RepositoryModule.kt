package de.todonxt.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.todonxt.core.data.repository.TaskRepository
import de.todonxt.core.data.source.local.dao.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }

}