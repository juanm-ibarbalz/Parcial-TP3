package com.parcial.tp3.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

import com.parcial.tp3.utils.Constants

import com.parcial.tp3.data.local.dao.FavouriteDao
import com.parcial.tp3.data.local.db.AppDatabase
import com.parcial.tp3.data.local.service.FavouriteServiceImpl

import com.parcial.tp3.data.remote.api.ProductApiService
import com.parcial.tp3.data.remote.api.AuthApiService
import com.parcial.tp3.data.remote.api.CartApiService
import com.parcial.tp3.data.service.AuthServiceImpl
import com.parcial.tp3.data.service.CartServiceImpl
import com.parcial.tp3.data.service.ProductServiceImpl
import com.parcial.tp3.shared.IProductService
import com.parcial.tp3.shared.IAuthService
import com.parcial.tp3.shared.ICartService
import com.parcial.tp3.shared.IFavouriteService


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Network Layer
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // OkHttpClient Instance
    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // Retrofit Instance
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Room Database Instance
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "petshop-db"
        ).build()
    }

    //API Services
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApiService =
        retrofit.create(ProductApiService::class.java)

    @Provides
    @Singleton
    fun provideCartApiService(retrofit: Retrofit): CartApiService =
        retrofit.create(CartApiService::class.java)

    //Service Implementations
    @Provides
    @Singleton
    fun provideAuthService(
        api: AuthApiService
    ): IAuthService = AuthServiceImpl(api)

    @Provides
    @Singleton
    fun provideProductService(
        api: ProductApiService
    ): IProductService = ProductServiceImpl(api)

    @Provides
    @Singleton
    fun provideCartService(api: CartApiService): ICartService =
        CartServiceImpl(api)

    // Favourites DAO & Service
    @Provides
    @Singleton
    fun provideFavouriteDao(db: AppDatabase): FavouriteDao =
        db.favouriteDao()

    @Provides
    @Singleton
    fun provideFavouriteService(dao: FavouriteDao): IFavouriteService =
        FavouriteServiceImpl(dao)

}