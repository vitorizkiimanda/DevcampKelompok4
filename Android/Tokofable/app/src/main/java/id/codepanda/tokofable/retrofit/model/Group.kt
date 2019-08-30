package id.codepanda.rembukdesa.retrofit.model

import java.io.Serializable

object Group {
    data class Result(
        val message: String,
        val token: String,
        val group: GroupData)

    data class GroupData(
        val _id: String? = null,
        val name: String? = null,
        val image: String? = null,
        val description: String? = null,
        val join: Boolean?= null) : Serializable
}