package org.codeforiraq.drug;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class notif extends AppCompatActivity {
EditText e3;
Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        e3=findViewById(R.id.e3);
        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
               Bitmap bmp =BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.logo);
                NotificationCompat.Builder b=new NotificationCompat.Builder(notif.this,"1")
                        .setContentTitle("hiii")
                        .setContentText("im here")
                        .setSmallIcon(R.drawable.lo2)
                        .setLargeIcon(bmp)
                        .setAutoCancel(true)
                        .setNumber(1);
                b.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                b.setVibrate(new long[]{500,1000,500,100,500});
                b.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ana));
                nm.notify(1,b.build());


            }
        });
    }
}
