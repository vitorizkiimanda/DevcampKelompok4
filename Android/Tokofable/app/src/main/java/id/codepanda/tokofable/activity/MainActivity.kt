package id.codepanda.tokofable.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.codepanda.tokofable.R
import id.codepanda.tokofable.base.BaseOnBackActivity

class MainActivity : BaseOnBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
