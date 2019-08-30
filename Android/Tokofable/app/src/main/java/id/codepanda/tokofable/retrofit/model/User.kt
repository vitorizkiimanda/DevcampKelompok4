package id.codepanda.rembukdesa.retrofit.model

object User {
    data class Result(
            val name: String,
            val id: String,
            val token: String)
}