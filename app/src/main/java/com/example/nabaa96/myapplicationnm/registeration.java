package com.example.nabaa96.myapplicationnm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class registeration extends AppCompatActivity {
    EditText email, password, fullname;
    Button butt;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference mdatabase = database.getReference("users");
Button notifiation;
static String n1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);


        email = findViewById(R.id.email);
        password = findViewById(R.id.passwor1);
        fullname = findViewById(R.id.fullname);
        mAuth = FirebaseAuth.getInstance();
        butt = findViewById(R.id.button9);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill, pass, full;
                emaill = email.getText().toString();
                pass = password.getText().toString();
                full = fullname.getText().toString();

notifiation = findViewById(R.id.not);






                notifiation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sentnotification();
                    }
                });








                n1 = email.getText().toString();
                String n = password.getText().toString();
                String nn = fullname.getText().toString();
                if (n.equals("")) {
                    email.setError("must be not empty");
                } else if (n1.equals("")) {
                    password.setError("must be not empty");
                } else if (nn.equals("")) {
                    fullname.setError("must be not empty");
                } else {


                    regester(emaill, pass, full);

                    Intent i = new Intent(getBaseContext(), MyApp.class);


                }
            }
        });


    }

    private void regester(final String email, final String pass, final String fulln) {


        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            obj n = new obj(fulln, email, pass);
                            Toast.makeText(registeration.this, "regester success.", Toast.LENGTH_SHORT).show();

                            String uid = task.getResult().getUser().getUid();


                            OneSignal.startInit(registeration.this)
                                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                                    .unsubscribeWhenNotificationsAreDisabled(true)
                                    .init();

                            OneSignal.sendTag("User_ID",email);
                            sentnotification();



                            //ظ mdatabase.child(uid).child("name").setValue(fulln);
                         //   mdatabase.child(uid).child("email").setValue(email);
                          // add more info of user
//                             mdatabase.child(uid).child( info key ).setValue(info value );

                            // or make hashmap ..

                        /*    HashMap<String,Object> userInfo=new HashMap<>();
                            userInfo.put("name",fulln);
                            userInfo.put("email",email);
                          //  userInfo.put("age",23); // get age from user and replace it here*/
                            mdatabase.child(uid).setValue(n);

                            Intent i =new Intent(getBaseContext(),popup.class);
                            i.putExtra("n",fulln);
                               i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                          startActivity(i);
                            // Toast.makeText(registeration.this, b , Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(registeration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

private  void  sentnotification(){


    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {
            int SDK_INT = Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {


                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String send_email;
                if (registeration.n1.equals("oklop@gmail.com")) {
                    send_email = "oklop@gmail.com";


                } else {

                    send_email = "samt@gmail.com";


                }


                try {

                    String jsonResponse;
                    URL url = new URL("https://onesignal.com/api/v1/notifications");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setUseCaches(false);
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-type", "application/json; charset-UTF-8");
                    con.setRequestProperty("Authorization ", "basic OTM5NTVmY2EtNTc5Yy00NjdmLTk0MmUtMGNlMzRlNmIwYWRm");
                    con.setRequestMethod("POST");
                    String strjsonbody = "["
                            + "\"app_id\": \"d4c22182-dd8e-4490-8e6c-e46dd2bd2eb1\","
                            + "\"filters\": [{\"field\": \"tag\" ,\"key\": \"User_ID\", \"relation\" : \"" + send_email
                            + "\"}]," + "\"data\": [\"foo\": \"bar\"],"
                            + "\"contents\": [\"en\": \"English Message\"]"
                            + "]";


                    System.out.println("strjsonbody:\n" + strjsonbody);
                    byte[] sendbyte = strjsonbody.getBytes("UTF-8");

                    con.setFixedLengthStreamingMode(sendbyte.length);
                    OutputStream outputStream = con.getOutputStream();
                    outputStream.write(sendbyte);
                    int httpResponse = con.getResponseCode();
                    System.out.println("httpResponse : " + httpResponse);
                    if (httpResponse >= HttpURLConnection.HTTP_OK
                            && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {


                        Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                        jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                        scanner.close();
                    } else {
                        Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                        jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                        scanner.close();
                    }
                    System.out.println("jsonResponse : \n" + jsonResponse);


                } catch (Throwable t) {


                    t.printStackTrace();
                }
            }
        }
    });






}
}