package in.codingninjas.envision.androidservices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    MediaPlayer player;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","Service started");

        player.start();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("abc","ABC", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"abc");
        builder.setContentTitle("My service");
        builder.setContentText("Service is running in foreground");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notification = builder.build();
        startForeground(1,notification);

        //stopForeground(true);
        return START_REDELIVER_INTENT;


    }


    public void startPlayer(){
        player.start();
    }

    public void stopPlayer(){
        player.pause();
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        super.onCreate();
    }

    @Override
    public void onDestroy() {

        player.stop();
        player.release();
        super.onDestroy();
    }

    public class MyBinder extends  Binder {

        public MyService getService(){
            return MyService.this;
        }

    }
}
