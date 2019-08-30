package id.codepanda.rembukdesa.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

object ChatData {

    @Parcelize
    data class Result(
            val personalMessage: ChatData
    ): Parcelable

    @Parcelize
    data class ChatData(
        val isRead: Boolean?= null,
        val deleted: Boolean?= null,
        val _id: String?= null,
        val personalRoom: String?= null,
        val receiver: String?= null,
        val contentType: String?= null,
        val content : String?= null,
        val createdAt : Date?= null
    ): Parcelable
}