package id.codepanda.rembukdesa.retrofit.model

object Province {
    data class Result(
            val provinces: ArrayList<ProvinceData>)

    data class ProvinceData(
            val _id: Number,
            val name: String)
}