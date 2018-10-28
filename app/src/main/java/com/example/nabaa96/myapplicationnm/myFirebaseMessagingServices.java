package com.example.nabaa96.myapplicationnm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

 class myFirebaseMessagingServices extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String body =remoteMessage.getNotification().getBody();
        mynotficationmanager.getinstance(getApplicationContext()).displaynotif(title,body);
    }
}
