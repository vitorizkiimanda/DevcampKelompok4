package id.codepanda.tokofable.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.WindowManager
import android.widget.Toast
import id.codepanda.tokofable.R
import id.codepanda.tokofable.retrofit.ApiService
import id.codepanda.tokofable.util.SessionManagement
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast
import java.util.*

open class BaseOnBackActivity : AppCompatActivity() {
    private var backPressed: Long = 0
    lateinit var session: SessionManagement

    //retrofit
    val apiServe by lazy {
        ApiService.create(this)
    }
    var disposable: Disposable? = null
    //

    //Text To Speech
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        session = SessionManagement(applicationContext)
        super.onCreate(savedInstanceState)

        val locale = Locale("id", "ID")
        Locale.setDefault(locale)

        //TTS
        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR){
                //if there is no error then set language
                mTTS.language = locale
                mTTS.setSpeechRate(0.70f)
            }
        })
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

    fun textToSpeech(string: String){
        mTTS.stop()
        if (string == ""){
            //if there is no text in edit text
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        }
        else{
            //if there is text in edit text
            mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()){
            mTTS.stop()
            super.onBackPressed()
        }
        else {
            textToSpeech(getString(R.string.press_to_exit))
            toast(getString(R.string.press_to_exit))
        }
        backPressed = System.currentTimeMillis()
    }

}
