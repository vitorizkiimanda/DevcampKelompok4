package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseActivity
import kotlinx.android.synthetic.main.activity_reksadana.*

class ReksadanaActivity : BaseActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedBack: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reksadana)
        init()
    }


    private fun welcomeSpeech(string: String){
        mTTS.stop()
        mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun init(){
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Reksadana, kamu belum punya investasi, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan") },
            "com.google.android.tts"
        )
        onClick()
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()){
            mTTS.stop()
            finish()
            super.onBackPressed()
        }
        else {
            textToSpeech("Tekan dua kali untuk kembali ke halaman beranda")
        }
        backPressed = System.currentTimeMillis()
    }


    private fun onClick() {
        reksa_back.setOnClickListener {
            if (backPressedBack + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                finish()
            } else {
                textToSpeech("Kembali, Tekan dua kali untuk kembali ke halaman beranda")
            }
            backPressedBack = System.currentTimeMillis()
        }

        reksa_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                textToSpeech("Halaman reksa dana, tekan tengah untuk ringkasan investasi, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan")
            } else {
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        reksa_overview.setOnClickListener {
            textToSpeech("Ringkasan Reksa dana, kamu belum punya investasi, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan")
        }
        reksa_sell.setOnClickListener {
            textToSpeech("Jual reksa dana")
        }
        reksa_buy.setOnClickListener {
            textToSpeech("Beli reksa dana")
        }
    }
}
