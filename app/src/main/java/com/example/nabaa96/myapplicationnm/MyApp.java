package com.example.nabaa96.myapplicationnm;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onesignal.OneSignal;

public class MyApp extends Application {
FirebaseAuth mauth;
FirebaseUser user;

    @Override
    public void onCreate() {

        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        //user=FirebaseAuth.getInstance().getCurrentUser();
       // user = mauth.getCurrentUser();

   // String emaill=user.getEmail();
      OneSignal.sendTag("User_ID","id");

    }
}
