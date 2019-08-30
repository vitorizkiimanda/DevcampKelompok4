package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseActivity
import kotlinx.android.synthetic.main.activity_lainnya.*

class LainnyaActivity : BaseActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedBack: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lainnya)
        init()
    }


    private fun welcomeSpeech(string: String){
        mTTS.stop()
        mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun init(){
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Daftar Layanan Keuangan, tekan tengah kiri untuk pinjaman online, tekan tengah kanan untuk pinjaman modal, tekan kiri bawah untuk kartu kredit, tekan kanan bawah untuk asuransi, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan") },
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
        more_back.setOnClickListener {
            if (backPressedBack + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                finish()
            } else {
                textToSpeech("Kembali, Tekan dua kali untuk kembali ke halaman beranda")
            }
            backPressedBack = System.currentTimeMillis()
        }

        more_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                textToSpeech("Halaman Daftar Layanan Keuangan, tekan tengah kiri untuk pinjaman online, tekan tengah kanan untuk pinjaman modal, tekan kiri bawah untuk kartu kredit, tekan kanan bawah untuk asuransi, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan")
            } else {
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        more_pinjaman_online.setOnClickListener {
            textToSpeech("Pinjaman Online")
        }
        more_pinjaman_modal.setOnClickListener {
            textToSpeech("Pinjaman Modal")
        }
        more_kartu_kredit.setOnClickListener {
            textToSpeech("Kartu Kredit")
        }
        more_asuransi.setOnClickListener {
            textToSpeech("Asuransi")
        }
    }
}
