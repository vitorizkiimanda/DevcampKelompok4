package id.codepanda.rembukdesa.retrofit.model

object Regency {
    data class Result(
            val regencies: ArrayList<RegencyData>)

    data class RegencyData(
            val _id: Number,
            val province_id: Number,
            val name: String)
}