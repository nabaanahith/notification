package com.example.nabaa96.myapplicationnm;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class contactus extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView text;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        NavigationView navigationVieww;
        navigationVieww = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu3w = navigationVieww.getMenu();
        nav_Menu3w.findItem(R.id.dash).setVisible(false);
        nav_Menu3w.findItem(R.id.logout).setVisible(false);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent n=new Intent(Intent.ACTION_SEND);
                n.setData(Uri.parse("mailto:"));
                String [] s={"ncahdi20188@yahoo.com "};
                n.putExtra(Intent.EXTRA_EMAIL,s);
                n.setType("message/rfc822");
                startActivity(Intent.createChooser(n,"ارسل تعليقك عبر الايميل"));



            }
        });

*/


        Button btn;

        btn = findViewById(R.id.but10);
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
                        nav_Menu2.findItem(R.id.logout).setVisible(true);
                    } else {
                        NavigationView navigationView3;
                        navigationView3 = (NavigationView) findViewById(R.id.nav_view);
                        Menu nav_Menu3 = navigationView3.getMenu();
                        nav_Menu3.findItem(R.id.dash).setVisible(false);
                        nav_Menu3.findItem(R.id.logout).setVisible(true);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mauth = FirebaseAuth.getInstance();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        text=findViewById(R.id.em);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(Intent.ACTION_SEND);
                n.setData(Uri.parse("mailto:"));
                String [] s={"naba.nahith@gmail.com "};
                n.putExtra(Intent.EXTRA_EMAIL,s);
                n.setType("message/rfc822");
                startActivity(Intent.createChooser(n,"send feedback"));
            }
        });
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
        getMenuInflater().inflate(R.menu.contactus, menu);
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
    public void facebook(View view) {
        Intent fac =openface(contactus.this);
        startActivity(fac);


    }




    public static  Intent openface(Context context){

        try{

            context.getPackageManager().getPackageInfo("com.example.nabaa96.task",0);
            return  new Intent(Intent.ACTION_VIEW,Uri.parse("http://iraqhome.org/"));

        }
        catch (Exception e){

            return  new Intent(Intent.ACTION_VIEW,Uri.parse("http://iraqhome.org/"));


        }




    }
    public void webs(View view) {


        Intent browser=new Intent(Intent.ACTION_VIEW,Uri.parse("http://iraqhome.org"));
        startActivity(browser);

    }




}

