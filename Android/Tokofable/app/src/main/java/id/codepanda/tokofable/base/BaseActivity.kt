package id.codepanda.tokofable.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import id.codepanda.tokofable.retrofit.ApiService
import id.codepanda.tokofable.util.SessionManagement
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {
    var backPressed: Long = 0
    lateinit var session: SessionManagement

    //retrofit
    val apiServe by lazy {
        ApiService.create(this)
    }
    var disposable: Disposable? = null
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        session = SessionManagement(applicationContext)
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    fun disableTouch(status: Boolean) {
        if (status) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}
