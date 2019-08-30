package id.codepanda.rembukdesa.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

object RoomPersonal {

    @Parcelize
    data class Result(
        val personalRoom: Data
    ) : Parcelable

    @Parcelize
    data class Data(
        val userOne: UserChatData? = null,
        val userTwo: UserChatData? = null
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