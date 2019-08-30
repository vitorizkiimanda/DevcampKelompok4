package id.codepanda.tokofable.base

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import id.codepanda.tokofable.R
import id.codepanda.tokofable.retrofit.ApiService
import id.codepanda.tokofable.util.SessionManagement
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast
import java.util.*

open class BaseOnBackActivity : AppCompatActivity() {
    var backPressed: Long = 0
    lateinit var session: SessionManagement
//    lateinit var mediaPlayer: MediaPlayer

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

//        //mediaPlayer
//        mediaPlayer = MediaPlayer.create(this, R.raw.exit_apps)
//        mediaPlayer?.setOnPreparedListener {
//            Log.d("kohchigga", "Audio ready")
//        }


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

    private fun textToSpeech(){
        val toSpeak = getString(R.string.press_to_exit)
        if (toSpeak == ""){
            //if there is no text in edit text
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        }
        else{
            //if there is text in edit text
            Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()){
            mTTS.stop()
            super.onBackPressed()
        }
        else {
            textToSpeech()
            toast(getString(R.string.press_to_exit))
        }
        backPressed = System.currentTimeMillis()
    }

}
