package com.codeforiraq.drug;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class not extends ContextWrapper {


    private static final  String chid="com.nabaa96.myapplicationnm.EDMTDEV";




            private static final String edch="EDMTDEV Channel";
    private NotificationManager m;



    @RequiresApi(api = Build.VERSION_CODES.O)
    public not(Context base) {
        super(base);
      createchannel();
    }


   @RequiresApi(api = Build.VERSION_CODES.O)
   public void  createchannel() {
       NotificationChannel edmtcha = new NotificationChannel(chid, edch, NotificationManager.IMPORTANCE_DEFAULT);
       edmtcha.enableLights(true);
       edmtcha.enableVibration(true);
       edmtcha.setLightColor(Color.GREEN);
       edmtcha.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);


       getManager().createNotificationChannel(edmtcha);

   }


   public NotificationManager getManager(){


        if(m == null){


            m= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return  m;
    }


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getee(String tit, String  body){

        return new Notification.Builder(getApplicationContext(),chid).setContentText(body).setContentTitle(tit).setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true);



    }
}
