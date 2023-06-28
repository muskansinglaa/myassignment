package com.example.newassignment

import android.app.Application
import androidx.room.Room
import com.example.newassignment.network.ApiInterface
import com.example.newassignment.network.LoggingInterceptor
import com.example.newassignment.room.AppDatabase
import com.example.newassignment.ui.product.ProductRepository
import com.example.newassignment.ui.product.ProductViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }

    private val appModule = module {
        single { createOkHttpClient() }
        single { createRetrofit(get()) }
        single { get<Retrofit>().create(ApiInterface::class.java) }
        single { ProductRepository(get()) }
        single {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database")
                .build()
        }
        single { get<AppDatabase>().favoriteProductDao() }
        viewModel { ProductViewModel(get(), get(),get()) }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = LoggingInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().create()
//        https://jsonplaceholder.typicode.com/posts
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


}
