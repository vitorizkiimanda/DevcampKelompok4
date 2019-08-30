package id.codepanda.tokofable.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.text.format.DateUtils
import android.widget.Toast
import id.codepanda.tokofable.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.notificationManager
import org.jetbrains.anko.yesButton
import org.json.JSONObject
import retrofit2.HttpException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

fun isPhone(input: String?): Boolean{
    val phoneRegex = Pattern.compile(
            "08[0-9]{7,11}"
    )

    return !phoneRegex.matcher(input).matches()
}

fun apiErrorHandling(error : HttpException, context: Context?){
    val errorBody = error.response().errorBody()!!.string()
    val statusCode = error.code()
    if (statusCode != 502){
        if (!errorBody.isNullOrBlank()) {
            val errorJson = JSONObject(errorBody)
            if (errorJson["message"].toString().isNotEmpty()) Toast.makeText(context, errorJson["message"].toString(), Toast.LENGTH_LONG).show()
        } else Toast.makeText(context, error.message().toString(), Toast.LENGTH_LONG).show()
    } else Toast.makeText(context, "Bad Gateway (502)", Toast.LENGTH_LONG).show()
}


fun isNetworkConnected(context: Context?): Boolean {
    val connectivityManager  = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun errNoInternet(context: Context?){
    context?.alert(context.getString(R.string.err_internet)) {
        yesButton {}
    }?.show()
}

fun formatMoney(input: String): String{
    val formatter = DecimalFormat("#,###")
    return "Rp" + formatter.format(input.toInt())
}

fun formatUrl(input: String): String{
    return "http://" + input.replace("\\", "")
}

