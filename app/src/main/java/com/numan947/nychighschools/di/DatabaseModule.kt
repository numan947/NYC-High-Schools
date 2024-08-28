package com.numan947.nychighschools.di

import android.content.Context
import androidx.room.Room
import com.numan947.nychighschools.data.local.AppDB
import com.numan947.nychighschools.data.local.SchoolDetailsDao
import com.numan947.nychighschools.data.local.SchoolDetailsRepository
import com.numan947.nychighschools.data.local.SchoolDetailsRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context,
            AppDB::class.java,
            "app_db"
        ).build()
    }

    @Provides
    fun provideSchoolDetailsDao(appDB: AppDB) = appDB.schoolDetailsDao()

    @Provides
    fun provideSchoolDetailsRepository(schoolDetailsDao: SchoolDetailsDao) = SchoolDetailsRepository(schoolDetailsDao) as SchoolDetailsRepositoryInterface
}