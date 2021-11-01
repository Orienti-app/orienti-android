package app.orienti.android.app.dependency_injection

import android.content.Context
import app.orienti.android.repositories.room.AppDatabase
import com.google.firebase.crashlytics.internal.common.AppData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sk.backbone.parent.repositories.server.client.ITokensProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun appDatabaseProvider(@ApplicationContext context: Context): AppDatabase  {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideTokenProvider(): ITokensProvider<Unit>  {
        return object: ITokensProvider<Unit> {
            override fun getLocalTokens(): Unit = Unit
        }
    }
}