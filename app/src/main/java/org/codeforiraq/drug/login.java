package org.codeforiraq.drug;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
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

import java.util.HashMap;

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
    EditText nameth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference mdatabase = database.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCallbackManager = CallbackManager.Factory.create();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameth=findViewById(R.id.e4);
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
                final String n = a.getText().toString();

                if (n.equals("")) {
                    a.setError("must be not empty");
                } else if (n1.equals("")) {
                    a1.setError("must be not empty");
                }
              if(nameth!=null) {

                  mDatabase.addValueEventListener(new ValueEventListener() {
                      String namethree = nameth.getText().toString();

                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {


                          for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                              String nameauth = postSnapshot.child("name").getValue().toString();
                              Log.i("popup", "onDataChange: res des: " + postSnapshot.child("email").getValue().toString() + "s :");
                              String emailauth = postSnapshot.child("email").getValue().toString();
                              Log.i("popup", "onDataChange: emailauth: " + postSnapshot.child("email").getValue().toString() + "s :");
                              if (emailauth.equals(n)) {
                                  if (!(namethree.equals(nameauth))) {

                                      nameth.setError("your name not correct!");
                                      Toast.makeText(login.this, "your name not correct!", Toast.LENGTH_SHORT).show();


                                  }
                                  else {
                                      userlogin();

                                  }
                              }

                              //   String describtion=postSnapshot.child("desc").getValue().toString();

                              // String s= itm.accept("describtion");
                          }
                      }


                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });


              }



            }
        });


    }


    private void userlogin() {
        final String n = a.getText().toString();
        final String nn = a1.getText().toString();
        final String namet=nameth.getText().toString();
        mauth.signInWithEmailAndPassword(n, nn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = mauth.getCurrentUser();

                    final String uid = user.getUid();
                    mDatabase.child(uid+"/toggle").addValueEventListener(new ValueEventListener() {

//
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                                 String mn=dataSnapshot.getValue(String.class);
                            Log.i(TAG, "onDataChange: "+dataSnapshot.toString());
                            Log.i(TAG, "onDataChange: name :"+mn);
                            Log.i(TAG, "onDataChange: Uid : "+uid);
                                 u=mn;
                            Log.i(TAG, "onDataChange: u : "+u);

                            String uid = task.getResult().getUser().getUid();
                            HashMap<String,Object> userInfo=new HashMap<>();
                            userInfo.put("name",namet);
                            userInfo.put("email",n);
                            userInfo.put("pass",nn);
                            userInfo.put("toggle",mn);
                            mdatabase.child(uid).setValue(userInfo);



                            Intent i =new Intent(getBaseContext(),page1.class);
                            i.putExtra("n",namet);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
finish();




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