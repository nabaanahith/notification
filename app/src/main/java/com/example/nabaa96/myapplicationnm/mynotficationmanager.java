package com.example.nabaa96.myapplicationnm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class mynotficationmanager {


    private Context mctx;
    private  static  mynotficationmanager minstance;
    private mynotficationmanager(Context con){

        mctx = con;


    }
    public static synchronized mynotficationmanager getinstance(Context context){


        if(minstance==null){


            minstance = new mynotficationmanager(context);
        }
        return minstance;

    }







    public  void displaynotif(String title,String body){

        NotificationCompat.Builder b =new NotificationCompat.Builder(mctx,constans.channelid)
                .setSmallIcon(R.drawable.logo).setContentTitle(title).setContentText(body);

        Intent i=new Intent(mctx,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(mctx,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        b.setContentIntent(pi);
        b.setNumber(1);
        NotificationManager mnoti = (NotificationManager) mctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if(mnoti != null){

            mnoti.notify(1,b.build());
        }

    }



}
