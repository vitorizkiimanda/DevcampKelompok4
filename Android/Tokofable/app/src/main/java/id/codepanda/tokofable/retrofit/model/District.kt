package id.codepanda.rembukdesa.retrofit.model

object District {
    data class Result(
            val districts: ArrayList<DistrictData>)

    data class DistrictData(
            val _id: Number,
            val regency_id: Number,
            val name: String)
}