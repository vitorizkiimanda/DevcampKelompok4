package id.codepanda.rembukdesa.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

object Inbox {

    @Parcelize
    data class Result(
        val personals: ArrayList<Data>
    ) : Parcelable

    @Parcelize
    data class Data(
        val lastMessage: LastMessage? = null,
        val deleted: Boolean,
        val _id: String,
        val userOne: UserChatData? = null,
        val userTwo: UserChatData? = null,
        val updatedAt: Date,
        val createdAt: Date
    ) : Parcelable

    @Parcelize
    data class LastMessage(
        val isRead: Boolean,
        val deleted: Boolean,
        val _id: String,
        val personalRoom: String,
        val sender: String,
        val receiver: String,
        val contentType: String,
        val content: String,
        val createdAt: Date
    ) : Parcelable

    @Parcelize
    data class UserChatData(
        val privilege: ArrayList<String>? = null,
        val villageId: String? = null,
        val phone: String? = null,
        val image: String? = null,
        val _id: String? = null,
        val name: String? = null,
        val email: String? = null
    ) : Parcelable
}