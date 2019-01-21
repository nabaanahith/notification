package org.codeforiraq.drug;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

public class MyApp extends Application {
FirebaseAuth mauth;
FirebaseUser user;

    @Override
    public void onCreate() {

        super.onCreate();
        FirebaseDatabase.getInstance()
                .setPersistenceEnabled(true); // enable offline mode
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
