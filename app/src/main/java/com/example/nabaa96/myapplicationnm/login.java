package com.example.nabaa96.myapplicationnm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.login.widget.LoginButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    private static final String TAG = "login";
    EditText a;
    DatabaseReference mDatabase;
    String u;
    EditText a1;
    Button b;
    FirebaseAuth mauth;
    CallbackManager mCallbackManager;
    ImageButton face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCallbackManager = CallbackManager.Factory.create();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u=" ";
        a = findViewById(R.id.e1);
        a1 = findViewById(R.id.e2);
        b = findViewById(R.id.b1);
        Button dd = findViewById(R.id.b2);

        mauth = FirebaseAuth.getInstance();
        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), registeration.class);
                startActivity(i);
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n1 = a1.getText().toString();
                String n = a.getText().toString();
                if (n.equals("")) {
                    a.setError("must be not empty");
                } else if (n1.equals("")) {
                    a1.setError("must be not empty");
                }




     /*   a.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            String n = a.getText().toString();

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (a.getText().equals(" ")) {

                    a.setError("feild cant be empity");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(n).matches()) {

                    a.setError("please enter valid email ");
                    return;
                }


            }
        });


        a1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // String nn=a1.getText().toString();

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (a1.getText().equals(" ")) {

                    a1.setError("feild cant be empity");
                }

            }
        });
*/


                else {

                    userlogin();
                }
            }
        });


    }


    private void userlogin() {
        final String n = a.getText().toString();
        final String nn = a1.getText().toString();

        mauth.signInWithEmailAndPassword(n, nn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = mauth.getCurrentUser();

                    final String uid = user.getUid();
                    mDatabase.child(uid+"/name").addValueEventListener(new ValueEventListener() {

//
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                                 String mn=dataSnapshot.getValue(String.class);
                            Log.i(TAG, "onDataChange: "+dataSnapshot.toString());
                            Log.i(TAG, "onDataChange: name :"+mn);
                            Log.i(TAG, "onDataChange: Uid : "+uid);
                                 u=mn;
                            Log.i(TAG, "onDataChange: u : "+u);




                            Intent i =new Intent(getBaseContext(),popup.class);
                            i.putExtra("n",u);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);






                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });



                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();


                }

            }


        });
    }


}