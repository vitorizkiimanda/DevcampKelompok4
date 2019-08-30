package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseActivity
import kotlinx.android.synthetic.main.activity_emas.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class EmasActivity : BaseActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedBack: Long = 0
    private var backPressedJual: Long = 0
    private var backPressedBeli: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emas)
        init()
    }


    private fun welcomeSpeech(string: String) {
        mTTS.stop()
        mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun init() {
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Emas, saat ini kamu punya 10 gram emas, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan") },
            "com.google.android.tts"
        )
        onClick()
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            mTTS.stop()
            finish()
            super.onBackPressed()
        } else {
            textToSpeech("Tekan dua kali untuk kembali ke halaman beranda")
        }
        backPressed = System.currentTimeMillis()
    }

    private fun onClick() {
        gold_back.setOnClickListener {
            if (backPressedBack + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                finish()
            } else {
                textToSpeech("Kembali, Tekan dua kali untuk kembali ke halaman beranda")
            }
            backPressedBack = System.currentTimeMillis()
        }

        gold_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                textToSpeech("Halaman Emas, Tekan tengah untuk informasi emas, tekan kanan bawah untuk beli, tekan kiri bawah untuk jual, tekan kiri atas untuk kembali dan tekan kanan atas untuk bantuan")
            } else {
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        gold_overview.setOnClickListener {
            textToSpeech("Informasi Emas, nilai emas Anda 170000 rupiah dengan total 10 gram emas")
        }

        gold_buy.setOnClickListener {
            if (backPressedJual + 2000 > System.currentTimeMillis()) {
                startActivity<EmasBeliActivity>()
                mTTS.stop()
            } else {
                textToSpeech("Beli Emas, harga beli emas per satu gram adalah 741000 rupiah tekan dua kali untuk beli emas")
            }
            backPressedJual = System.currentTimeMillis()
        }

        gold_sell.setOnClickListener {
            if (backPressedBeli + 2000 > System.currentTimeMillis()) {
                startActivity<EmasJualActivity>()
                mTTS.stop()
            } else {
                textToSpeech("Jual Emas, harga jual emas per satu gram adalah 707000 rupiah tekan dua kali untuk jual emas")
            }
            backPressedBeli = System.currentTimeMillis()
        }
    }
}
