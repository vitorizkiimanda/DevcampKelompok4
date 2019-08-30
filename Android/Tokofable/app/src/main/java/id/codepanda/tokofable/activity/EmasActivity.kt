package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseActivity

class EmasActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emas)
        init()
    }


    private fun welcomeSpeech(string: String){
        mTTS.stop()
        mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun init(){
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Emas, saat ini kamu punya 10 gram emas, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan") },
            "com.google.android.tts"
        )
//        onClick()
    }

}
