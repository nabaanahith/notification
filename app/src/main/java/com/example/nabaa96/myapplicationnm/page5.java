package com.example.nabaa96.myapplicationnm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class page5 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        if (id == R.id.ra) {
            final Intent i2=new Intent(getBaseContext(),mann.class);
            startActivity(i2);
            // Handle the camera action
        }
        if (id == R.id.nav_camera) {
            final Intent i2=new Intent(getBaseContext(),page1.class);
            startActivity(i2);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            final Intent i2=new Intent(getBaseContext(),page2.class);
            startActivity(i2);

        } else if (id == R.id.nav_slideshow) {
            final Intent i2=new Intent(getBaseContext(),page3.class);
            startActivity(i2);

        } else if (id == R.id.nav_manage) {
            final Intent i2=new Intent(getBaseContext(),page4.class);
            startActivity(i2);
        } else if (id == R.id.a) {
            final Intent i2=new Intent(getBaseContext(),page5.class);
            startActivity(i2);

        } else if (id == R.id.b) {
            final Intent i2=new Intent(getBaseContext(),page6.class);
            startActivity(i2);

        }
        else if (id == R.id.c) {
            final Intent i2 = new Intent(getBaseContext(), page7.class);
            startActivity(i2);
        }
        else if (id == R.id.d) {
            final Intent i2 = new Intent(getBaseContext(), page8.class);
            startActivity(i2);
        }
        else if (id == R.id.e) {
            final Intent i2 = new Intent(getBaseContext(), page9.class);
            startActivity(i2);

        }


        else if (id == R.id.f) {
            final Intent i2 = new Intent(getBaseContext(), page10.class);
            startActivity(i2);}

        else if (id == R.id.f1) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);


        }

        else if (id == R.id.nav_share) {
            final Intent i2 = new Intent(getBaseContext(), about1.class);
            startActivity(i2);}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}