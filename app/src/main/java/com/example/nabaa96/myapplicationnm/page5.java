package com.example.nabaa96.myapplicationnm;

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

public class page5 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationVieww;
        navigationVieww = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu3w = navigationVieww.getMenu();
        nav_Menu3w.findItem(R.id.dash).setVisible(false);
        nav_Menu3w.findItem(R.id.logout).setVisible(false);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        });*/

        Button btn;

        btn = findViewById(R.id.but5);
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
        final FirebaseUser firebaseUser =mauth.getCurrentUser();
        if (firebaseUser != null) {
            NavigationView navigationView;
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.reg).setVisible(false);
            nav_Menu.findItem(R.id.logout).setVisible(true);
            String adminemail = firebaseUser.getEmail();
            Log.i(TAG, "onDataChange: adminemail : " + adminemail);
            String emaill = getIntent().getStringExtra(registeration.emailll);
            NavigationView uu;
            uu = (NavigationView) findViewById(R.id.nav_view);

            View jj = uu.getHeaderView(0);
            TextView yy = jj.findViewById(R.id.textViewgmail);
            yy.setText(adminemail);
            if (emaill == null) {
                emaill = " ";

            }
           /* if (adminemail.equals("nabaa@gmail.com") || emaill.equals("nabaa@gmail.com")) {
                NavigationView navigationView2;
                navigationView2 = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu2 = navigationView2.getMenu();
                nav_Menu2.findItem(R.id.dash).setVisible(true);
            } else {
                NavigationView navigationView3;
                navigationView3 = (NavigationView) findViewById(R.id.nav_view);
                Menu nav_Menu3 = navigationView3.getMenu();
                nav_Menu3.findItem(R.id.dash).setVisible(false);
            }*/
        }
        else{
            NavigationView uu;
            uu = (NavigationView) findViewById(R.id.nav_view);

            View jj=uu.getHeaderView(0);
            TextView yy=jj.findViewById(R.id.textViewgmail);
            yy.setText(" ");

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser == null) {

                    Intent i = new Intent(getBaseContext(), login.class);
                    startActivity(i);
                }
                else{
                    Intent ii = new Intent(getBaseContext(), popup5.class);
                    ii.putExtra("n", u[0]);
                    startActivity(ii);}
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.page5, menu);
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

        if (id == R.id.dash) {
            final Intent i2 = new Intent(getBaseContext(), dash.class);
            startActivity(i2);
            // Handle the camera action
        }
        if (id == R.id.nav_camera) {
            final Intent i2 = new Intent(getBaseContext(), page1.class);
            startActivity(i2);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            final Intent i2 = new Intent(getBaseContext(), page2.class);
            startActivity(i2);

        }
        else if (id == R.id.aboutapp) {

            final Intent i2 = new Intent(getBaseContext(), aboutapp.class);
            startActivity(i2);

        }
        else if (id == R.id.aboutapp2) {

            final Intent i2 = new Intent(getBaseContext(), aboutappnotcopy.class);
            startActivity(i2);

        }
        else if (id == R.id.aboutapp) {
            final Intent i2 = new Intent(getBaseContext(), aboutapp.class);
            startActivity(i2);


        }else if (id == R.id.nav_slideshow) {
            final Intent i2 = new Intent(getBaseContext(), page3.class);
            startActivity(i2);

        } else if (id == R.id.nav_manage) {
            final Intent i2 = new Intent(getBaseContext(), page4.class);
            startActivity(i2);
        } else if (id == R.id.a) {
            final Intent i2 = new Intent(getBaseContext(), page5.class);
            startActivity(i2);

        }

        else if (id == R.id.nav_send) {

            final Intent i2 = new Intent(getBaseContext(), aboutme.class);
            startActivity(i2);

        }



        else if (id == R.id.logout) {

            mauth.signOut();
            NavigationView navigationView2;
            navigationView2 = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_Menu2 = navigationView2.getMenu();
            nav_Menu2.findItem(R.id.dash).setVisible(false);
            Intent i= new Intent(getBaseContext(),MainActivity.class);
            startActivity(i);

        }








        else if (id == R.id.reg) {
            final Intent i2 = new Intent(getBaseContext(), login.class);
            startActivity(i2);

        }












        else if (id == R.id.b) {
            final Intent i2 = new Intent(getBaseContext(), page6.class);
            startActivity(i2);

        } else if (id == R.id.c) {
            final Intent i2 = new Intent(getBaseContext(), page7.class);
            startActivity(i2);
        } else if (id == R.id.d) {
            final Intent i2 = new Intent(getBaseContext(), page8.class);
            startActivity(i2);
        } else if (id == R.id.e) {
            final Intent i2 = new Intent(getBaseContext(), page9.class);
            startActivity(i2);

        } else if (id == R.id.f) {
            final Intent i2 = new Intent(getBaseContext(), page10.class);
            startActivity(i2);
        }
        else if (id == R.id.reg) {
            final Intent i2 = new Intent(getBaseContext(), registeration.class);
            startActivity(i2);
            // NavigationView.getMenu().findItem(R.id.ra).setVisible(false);
        }

        else if (id == R.id.f1) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);


        }

        else if (id == R.id.contact) {

            final Intent i2 = new Intent(getBaseContext(), ccountact.class);
            startActivity(i2);



        }


        if (id == R.id.ra) {
            final Intent i2 = new Intent(getBaseContext(), mann.class);
            startActivity(i2);
            // Handle the camera action
        } else if (id == R.id.nav_share) {
            final Intent i2 = new Intent(getBaseContext(), about1.class);
            startActivity(i2);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;}}