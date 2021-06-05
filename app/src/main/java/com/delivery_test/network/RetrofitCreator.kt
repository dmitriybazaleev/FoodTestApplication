package com.delivery_test.network

import com.delivery_test.network.api.FoodApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Тут инициализируется Retrofit и OkHttp
 */
@Module
class RetrofitCreator {

    @Provides
    fun onCreateRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(onCreateOkHttpClient())
            .build()
    }

    @Provides
    fun onCreateOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .readTimeout(NetworkConstants.TIME_CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIME_CONNECTION, TimeUnit.SECONDS)
            .connectTimeout(NetworkConstants.TIME_CONNECTION, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun onCreateApi(): FoodApi {
        return this.onCreateRetrofit().create(FoodApi::class.java)
    }
}