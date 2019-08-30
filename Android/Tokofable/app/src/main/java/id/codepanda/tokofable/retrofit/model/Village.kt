package id.codepanda.rembukdesa.retrofit.model

object Village {
    data class Result(
            val villages: ArrayList<VillageData>)

    data class VillageData(
            val _id: Number,
            val district_id: Number,
            val name: String)
}