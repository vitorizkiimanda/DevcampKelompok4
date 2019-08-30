package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.codepanda.tokofable.base.BaseActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(session.checkLogin()) startActivity(intentFor<MainActivity>().noAnimation())
        else startActivity(intentFor<MainActivity>().noAnimation())
        finish()
    }
}
