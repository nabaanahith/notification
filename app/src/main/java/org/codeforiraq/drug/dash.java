package org.codeforiraq.drug;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class dash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mauth;
    EditText m;
    Button notifiation;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        m=findViewById(R.id.notmes);
        notifiation = findViewById(R.id.not);

        notifiation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentnotification();
            }
        });
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        NavigationView navigationVieww;
        navigationVieww = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu3w = navigationVieww.getMenu();
        nav_Menu3w.findItem(R.id.dash).setVisible(false);
        nav_Menu3w.findItem(R.id.logout).setVisible(false);
        mauth = FirebaseAuth.getInstance();

        final String[] u = new String[1];

        DatabaseReference mDatabase;

        mauth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = mauth.getCurrentUser();
        final String[] mn = {" "};
        if(mauth.getCurrentUser()!=null) {
            final String uid = user.getUid();
            mDatabase.child(uid + "/name").addValueEventListener(new ValueEventListener() {

                //
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mn[0] = dataSnapshot.getValue(String.class);
                    Log.i(TAG, "onDataChange: " + dataSnapshot.toString());
                    Log.i(TAG, "onDataChange: name :" + mn[0]);
                    Log.i(TAG, "onDataChange: Uid : " + uid);
                    u[0] = mn[0];


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });
            mDatabase.child(uid + "/toggle").addValueEventListener(new ValueEventListener() {

                //
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mn[0] = dataSnapshot.getValue(String.class);
                    Log.i(TAG, "onDataChange: " + dataSnapshot.toString());
                    Log.i(TAG, "onDataChange: toggle :" + mn[0]);
                    Log.i(TAG, "onDataChange: Uid : " + uid);
                    u[0] = mn[0];
                    if (mn[0].equals("1")) {
                        NavigationView navigationView2;
                        navigationView2 = (NavigationView) findViewById(R.id.nav_view);
                        Menu nav_Menu2 = navigationView2.getMenu();
                        nav_Menu2.findItem(R.id.dash).setVisible(true);
                    } else {
                        NavigationView navigationView3;
                        navigationView3 = (NavigationView) findViewById(R.id.nav_view);
                        Menu nav_Menu3 = navigationView3.getMenu();
                        nav_Menu3.findItem(R.id.dash).setVisible(false);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
    }

    private void sentnotification() {
final String message= m.getText().toString();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {


                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        EditText mess=findViewById(R.id.notmes);
                        String m=mess.getText().toString();
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic OTM5NTVmY2EtNTc5Yy00NjdmLTk0MmUtMGNlMzRlNmIwYWRm");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"d4c22182-dd8e-4490-8e6c-e46dd2bd2eb1\","
                                + "\"included_segments\": [\"All\"],"
                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \""+message+"\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

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
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}

/*
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            b = new not(this);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            Button nn = findViewById(R.id.oi);
            nn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notification.Builder nn = b.getee("rr", "fff");
                    b.getManager().notify(new Random().nextInt(), nn.build());
                    // createNotification("hi");

                }


            });
        }

    }
public  String  accept(String descript){
    String accept="0";
     TextView v=findViewById(R.id.viewcm);
    v.setText(descript);
    Button acc=findViewById(R.id.approve);
    acc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String accept="1";
        }
    });
        return  accept;

}

























    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }










}
*/