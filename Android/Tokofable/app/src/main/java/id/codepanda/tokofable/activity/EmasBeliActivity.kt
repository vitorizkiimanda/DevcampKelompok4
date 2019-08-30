package id.codepanda.tokofable.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseActivity
import id.codepanda.tokofable.util.formatMoney
import kotlinx.android.synthetic.main.activity_emas.*
import kotlinx.android.synthetic.main.activity_emas_beli.*
import org.jetbrains.anko.*

class EmasBeliActivity : BaseActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedBack: Long = 0
    private var backPressedOverView: Long = 0

    private var goldAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emas_beli)
        init()
    }


    private fun welcomeSpeech(string: String) {
        mTTS.stop()
        mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun init() {
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Beli Emas, Harga emas per gram adalah 743000 rupiah, tekan kanan bawah untuk tambah 1 gram,  tekan kiri bawah kurang 1 gram, tekan tengah untuk memproses pembelian, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan") },
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
            textToSpeech("Tekan dua kali untuk kembali ke halaman emas")
        }
        backPressed = System.currentTimeMillis()
    }

    @SuppressLint("SetTextI18n")
    private fun onClick() {
        gold_buy_back.setOnClickListener {
            if (backPressedBack + 2000 > System.currentTimeMillis()) {
                mTTS.stop()
                finish()
            } else {
                textToSpeech("Kembali, Tekan dua kali untuk kembali ke halaman emas")
            }
            backPressedBack = System.currentTimeMillis()
        }

        gold_buy_help.setOnClickListener {
            if (backPressedHelp + 2000 > System.currentTimeMillis()) {
                textToSpeech("Halaman Beli Emas, , tekan kanan bawah untuk tambah 1 gram,  tekan kiri bawah kurang 1 gram, tekan tengah untuk memproses pembelian, tekan kiri atas untuk kembali, dan tekan kanan atas untuk bantuan")
            } else {
                textToSpeech("Bantuan, tekan dua kali untuk penjelasan halaman")
            }
            backPressedHelp = System.currentTimeMillis()
        }

        gold_buy_overview.setOnClickListener {
            if (backPressedOverView + 2000 > System.currentTimeMillis()) {
                textToSpeech("Berhasil membeli $goldAmount gram emas dengan total ${goldAmount * 741000} rupiah, saldo Ovo saat ini 2375000 rupiah, terima kasih mengalihkan ke beranda")
                startActivity(intentFor<MainActivity>().clearTask().newTask())
            } else {
                if (goldAmount > 0)
                    textToSpeech("Akan membeli $goldAmount gram emas dengan total ${goldAmount * 741000} rupiah, saldo Ovo saat ini sejumlah 5000000 rupiah, tekan dua kali untuk memproses pembelian")
                else textToSpeech("0 gram, tekan kanan bawah untuk menambahkan jumlah beli emas")
            }
            backPressedOverView = System.currentTimeMillis()
        }

        gold_buy_plus.setOnClickListener {
            goldAmount++
            gold_amout_gram.text = "(${goldAmount} gram)"
            gold_amout_rupiah.text = formatMoney((goldAmount * 741000).toString())

            textToSpeech("$goldAmount gram")
        }

        gold_buy_min.setOnClickListener {
            if (goldAmount > 0) {
                goldAmount--
                gold_amout_gram.text = "(${goldAmount} gram)"
                gold_amout_rupiah.text = formatMoney((goldAmount * 741000).toString())

                if (goldAmount > 0) {
                    textToSpeech("$goldAmount gram")
                } else textToSpeech("0 gram, tekan kanan bawah untuk menambahkan jumlah beli emas")
            } else textToSpeech("0 gram, tekan kanan bawah untuk menambahkan jumlah beli emas")
        }
    }
}
