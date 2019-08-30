package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import id.codepanda.rembukdesa.retrofit.model.User
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseOnBackActivity
import id.codepanda.tokofable.util.apiErrorHandling
import id.codepanda.tokofable.util.errNoInternet
import id.codepanda.tokofable.util.isNetworkConnected
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.json.JSONObject
import retrofit2.HttpException

class MainActivity : BaseOnBackActivity() {

    private var backPressedHelp: Long = 0
    private var backPressedEmas: Long = 0
    private var backPressedReksadana: Long = 0
    private var backPressedMore: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        init()
        super.onResume()
    }


    private fun showProgress(show: Boolean) {
        home_progress.bringToFront()
        home_progress.visibility = if (show) View.VISIBLE else View.GONE
        disableTouch(show)
    }

    private fun login() {
        if (!isNetworkConnected(this)) {
            errNoInternet(this)
            return
        }
        showProgress(true)
        disposable = apiServe.login("zulfahmiibnuhabibi@gmail.com", "123456")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    dataLogic(result)
                },
                { error ->
                    showProgress(false)
                    apiErrorHandling(error as HttpException, this)
                }
            )
    }


    private fun dataLogic(result: User.Result) {

        session.createLoginSession(result)
        TextToSpeech(
            applicationContext,
            TextToSpeech.OnInitListener { welcomeSpeech("Halaman Beranda, Tekan tengah untuk emas, Tekan kiri bawah untuk reksadana, tekan kanan bawah untuk layanan keuangan lainnya, dan tekan kanan atas untuk bantuan") },
            "com.google.android.tts"
        )
    }

    private fun init() {
//        login()
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

        home_reksadana.setOnClickListener {
            if (backPressedReksadana + 2000 > System.currentTimeMillis()) {
                startActivity<ReksadanaActivity>()
                mTTS.stop()
            } else {
                textToSpeech("Reksadana, tekan dua kali untuk transaksi reksadana")
            }
            backPressedReksadana = System.currentTimeMillis()
        }

        home_more.setOnClickListener {
            if (backPressedMore + 2000 > System.currentTimeMillis()) {
                startActivity<LainnyaActivity>()
                mTTS.stop()
            } else {
                textToSpeech("Lainnya, tekan dua kali untuk layanan keuangan lainnya")
            }
            backPressedMore = System.currentTimeMillis()
        }
    }

    private fun welcomeSpeech(string: String) {
        mTTS.stop()
        if (string == "") {
            //if there is no text in edit text
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        } else {
            //if there is text in edit text
            mTTS.speak(string, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}
