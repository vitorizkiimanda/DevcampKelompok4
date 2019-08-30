package id.codepanda.tokofable.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import id.codepanda.rembukdesa.retrofit.model.User
import java.util.HashMap

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SessionManagement(private var context: Context) {
    // Shared Preferences
    private var pref: SharedPreferences

    // Editor for Shared preferences
    private var editor: SharedPreferences.Editor

    // Shared pref mode
    private var PRIVATE_MODE = 0

    private val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    val token: String
        get() {
            return pref.getString(KEY_TOKEN,"")
        }

    val user: HashMap<String, String>
        get() {
            val user = HashMap<String, String>()
            user[KEY_ID] = pref.getString(KEY_ID, "")
            user[KEY_NAME] = pref.getString(KEY_NAME, "")
            user[KEY_PRIVILEGE] = pref.getString(KEY_PRIVILEGE, "")
            user[KEY_EMAIL] = pref.getString(KEY_EMAIL, "")
            user[KEY_IMAGE] = pref.getString(KEY_IMAGE, "")
            user[KEY_PHONE] = pref.getString(KEY_PHONE, "")
            user[KEY_VILLAGE_ID] = pref.getString(KEY_VILLAGE_ID, "")
            return user
        }

    // functions
    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(data: User.Result) {
        editor.putBoolean(IS_LOGIN, true)

        editor.putString(KEY_TOKEN, data.token)
        editor.putString(KEY_ID, data.user._id)
        editor.putString(KEY_NAME, data.user.name)
        editor.putString(KEY_EMAIL, data.user.email)
        editor.putString(KEY_PRIVILEGE, data.user.privilege[0])
        editor.putString(KEY_IMAGE, data.user.image)
        editor.putString(KEY_PHONE, data.user.phone)
        editor.putString(KEY_VILLAGE_ID, data.user.villageId.toString())
        editor.commit()
    }

    fun checkLogin():Boolean {
        return this.isLoggedIn
    }

    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.remove("IsLoggedIn")
        editor.remove("token")

        editor.remove("id")
        editor.remove("name")
        editor.remove("email")
        editor.remove("privilege")
        editor.remove("image")
        editor.remove("phone")
        editor.remove("villageid")
        editor.commit()
    }

    companion object {
        // Sharedpref file name
        private const val PREF_NAME = "TokofablePref"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        private const val IS_FIRST = "isFirstOpen"

        val KEY_TOKEN = "token"

        val KEY_ID = "id"
        val KEY_NAME = "name"
        val KEY_PRIVILEGE= "privilege"
        val KEY_EMAIL = "email"
        val KEY_IMAGE = "image"
        val KEY_PHONE = "phone"
        val KEY_VILLAGE_ID = "villageid"
    }
}
