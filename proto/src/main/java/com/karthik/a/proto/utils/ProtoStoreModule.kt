package com.karthik.a.proto.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.karthik.a.proto.UserDetail
import com.karthik.a.proto.UserDetailSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    // Create a Proto DataStore instance for UserDetail
    @Provides
    @Singleton
    fun provideUserDetailDataStore(@ApplicationContext context: Context): DataStore<UserDetail> {
        return DataStoreFactory.create(
            serializer = UserDetailSerializer,
            produceFile = { context.dataStoreFile("user_detail.pb") }
        )
    }
}
