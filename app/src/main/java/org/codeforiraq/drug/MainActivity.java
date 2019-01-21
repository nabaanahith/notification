package org.codeforiraq.drug;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        final Button h=findViewById(R.id.bg);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i2=new Intent(getBaseContext(),mann.class);
                startActivity(i2);

            }
        });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mno= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationChannel mch = new NotificationChannel(constans.channelid,constans.channelname,NotificationManager.IMPORTANCE_HIGH);
            mch.setDescription(constans.channedescription);
            mch.enableLights(true);
            mch.setLightColor(Color.RED);
            mch.enableVibration(true);
            mch.setVibrationPattern(new long[] {100,200,300,400,500,400,300,200,400});


            mno.createNotificationChannel(mch);








        }
    }




    }

