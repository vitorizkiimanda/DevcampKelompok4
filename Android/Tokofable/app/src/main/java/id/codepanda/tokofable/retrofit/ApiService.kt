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

    @POST("/auth/register")
    @FormUrlEncoded
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("privilege") privilege: String,
        @Field("villageId") villageId: Number,
        @Field("phone") phone: String,
        @Field("image") image: String
    ):
            Observable<User.Result>

    @POST("/auth/google")
    @FormUrlEncoded
    fun loginGoogle(
        @Field("idToken") idToken: String
    ):
            Observable<User.Result>

    //Address
    @GET("/address/provinces")
    fun getProvince():
            Observable<Province.Result>

    @GET
    fun getRegency(@Url url: String):
            Observable<Regency.Result>

    @GET
    fun getDistrict(@Url url: String):
            Observable<District.Result>

    @GET
    fun getVillage(@Url url: String):
            Observable<Village.Result>

    //Chatting
    @GET
    fun getChatByRoomId(@Url url: String):
            Observable<Chat.Result>

    @POST("/personal-message")
    @FormUrlEncoded
    fun chatCreate(
        @Field("personalRoom") personalRoom: String,
        @Field("receiver") receiver: String,
        @Field("contentType") contentType: String,
        @Field("content") content: String
    ):
            Observable<ChatData.Result>

    @GET("/personal-room/?populateLastMessage=1&populateUser=1")
    fun getInbox():
            Observable<Inbox.Result>

    @GET
    fun getRoomPersonalDetail(@Url url: String):
            Observable<RoomPersonal.Result>

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