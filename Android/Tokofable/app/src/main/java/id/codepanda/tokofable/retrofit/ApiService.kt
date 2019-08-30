package id.codepanda.tokofable.retrofit

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import android.util.Log
import id.codepanda.rembukdesa.retrofit.model.*
import id.codepanda.tokofable.BuildConfig
import id.codepanda.tokofable.util.SessionManagement
import okhttp3.RequestBody
import retrofit2.http.PartMap
import okhttp3.MultipartBody
import retrofit2.http.POST
import retrofit2.http.Multipart


interface ApiService {

    //Authentication
    @POST("/auth/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ):
            Observable<User.Result>



    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var session: SessionManagement

        fun create(context: Context): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            // Session Manager
            session = SessionManagement(context)

            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headersInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        private fun headersInterceptor(): Interceptor {
            return if (session.token.isEmpty()) {
                Interceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .build()
                    )
                }
            } else {
                Interceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("Authorization", session.token)
                            .build()
                    )
                }
            }
        }
    }
}