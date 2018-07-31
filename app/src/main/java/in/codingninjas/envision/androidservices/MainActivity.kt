package `in`.codingninjas.envision.androidservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

class MainActivity : AppCompatActivity() {

    var myBinder: MyService.MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSer(view: View){

        var connection: ServiceConnection = object: ServiceConnection {

            override fun onServiceDisconnected(p0: ComponentName?) {

            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                myBinder = p1 as MyService.MyBinder
            }

        }

        val intent = Intent(this,MyService::class.java);
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
    }

    fun stopSer(view: View){
        val intent = Intent(this,MyService::class.java);
        stopService(intent)

    }

    fun startPlayer(view: View){
        if(myBinder != null){
            myBinder!!.service.startPlayer()
        }

    }

    fun stopPlayer(view: View){
        if(myBinder != null){
            myBinder!!.service.stopPlayer()
        }

    }
}
