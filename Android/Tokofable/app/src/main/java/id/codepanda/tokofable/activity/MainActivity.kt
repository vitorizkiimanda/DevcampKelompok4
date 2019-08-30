package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseOnBackActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseOnBackActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedEmas: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Beranda, Tekan tengah untuk emas, Tekan kiri bawah untuk reksadana, tekan kanan bawah untuk layanan keuangan lainnya, dan tekan kanan atas untuk bantuan") },
            "com.google.android.tts"
        )
        onClick()
    }

    private fun onClick() {
        home_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                textToSpeech("Halaman Beranda, Tekan tengah untuk emas, Tekan kiri bawah untuk reksadana, dan tekan kanan bawah untuk layanan keuangan lainnya, dan tekan kanan atas untuk bantuan")
            } else {
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        home_gold.setOnClickListener {
            if (backPressedEmas + 2000 > System.currentTimeMillis()) {
                startActivity<EmasActivity>()
                mTTS.stop()
            } else {
                textToSpeech("Emas, tekan dua kali untuk transaksi emas")
            }
            backPressedEmas = System.currentTimeMillis()
        }
    }

    private fun welcomeSpeech(string: String){
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
}
