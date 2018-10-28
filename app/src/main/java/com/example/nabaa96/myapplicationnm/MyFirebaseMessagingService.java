package com.example.nabaa96.myapplicationnm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import static android.support.constraint.Constraints.TAG;

 public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override

        public void onNewToken(String token) {
            Log.e("mmy", "Refreshed token: " + token);


        }
    }


