package id.codepanda.rembukdesa.retrofit.model

object User {
    data class Result(
            val message: String,
            val token: String,
            val user: UserData)

    data class UserData(
            val privilege: ArrayList<String>,
            val villageId: Number,
            val phone: String,
            val image: String? = null,
            val _id: String? = null,
            val name: String? = null,
            val email: String? = null)
}