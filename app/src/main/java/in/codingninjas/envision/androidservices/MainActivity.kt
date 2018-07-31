package `in`.codingninjas.envision.androidservices

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSer(view: View){
        val intent = Intent(this,MyIntentService::class.java);
        startService(intent)
    }

    fun stopSer(view: View){
        val intent = Intent(this,MyIntentService::class.java);
        stopService(intent)

    }
}
