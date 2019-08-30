package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        onClick()
    }

    private fun onClick() {
        home_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                textToSpeech("Halaman Beranda, Tekan pada kanan atas untuk bantuan, Tekan tengah untuk emas, Tekan kiri bawah untuk reksadana, dan tekan kanan bawah untuk layanan keuangan lainnya")
            } else {
                mTTS.stop()
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        home_gold.setOnClickListener {
            if (backPressedEmas + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                startActivity<EmasActivity>()
            } else {
                mTTS.stop()
                textToSpeech("Emas, tekan dua kali untuk transaksi emas")
            }
            backPressedEmas = System.currentTimeMillis()
        }
    }
}
