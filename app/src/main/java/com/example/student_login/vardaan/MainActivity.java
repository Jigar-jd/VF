package com.example.student_login.vardaan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.student_login.vardaan.About.AboutFragment;
import com.example.student_login.vardaan.About.HomeFragment;
import com.example.student_login.vardaan.Activities.ActivitiesFragment;
import com.example.student_login.vardaan.Contact.ContactFragment;
import com.example.student_login.vardaan.Donation.DonationFragment;
import com.example.student_login.vardaan.Founders.DarshanFragment;
import com.example.student_login.vardaan.Founders.VarshaFragment;
import com.example.student_login.vardaan.Gallery.GalleryFragment;
import com.example.student_login.vardaan.Login.LoginActivity;
import com.example.student_login.vardaan.OurTeam.OurTeamFragment;
import com.example.student_login.vardaan.Project.Project;
import com.example.student_login.vardaan.Volunteer.applyVolunteerFragment;

import static com.example.student_login.vardaan.Login.LoginActivity.PREF_FILE;
import static com.example.student_login.vardaan.Login.LoginActivity.USERNAME;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Intent shareIntent;
    String sharebody;
    int[] sampleImages = {R.drawable.slider_01,R.drawable.slider_about_us,R.drawable.slider2,R.drawable.slider3,R.drawable.sliider1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=this;



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
        drawer.addDrawerListener(toggle);        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView textName = (TextView) headerView.findViewById(R.id.textName);

        SharedPreferences sharedPref=getSharedPreferences(PREF_FILE,MODE_PRIVATE);
        String username=sharedPref.getString(USERNAME,null);
        if(username!=null){
            textName.setText(username);
        }


        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MainLayout, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }







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
        getMenuInflater().inflate(R.menu.main, menu);
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
            logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new HomeFragment()).commit();

        } else if (id == R.id.nav_aboutUs) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new AboutFragment()).commit();

        } else if (id == R.id.nav_activities) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new ActivitiesFragment()).commit();

        } else if (id == R.id.nav_projects) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new Project()).commit();

        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new GalleryFragment()).commit();
        } else if (id == R.id.nav_contact) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new ContactFragment()).commit();
        }
        else if (id == R.id.nav_team) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new OurTeamFragment()).commit();

        }else if (id == R.id.nav_applyVoulnteer) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new applyVolunteerFragment()).commit();

        }else if (id == R.id.nav_varsha) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new VarshaFragment()).commit();

        }else if (id == R.id.nav_darshan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new DarshanFragment()).commit();

        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "http://www.vardaanfoundation.com");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
            startActivity(Intent.createChooser(intent, "Share"));

        }else if (id == R.id.nav_send) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new ContactFragment()).commit();
        }else if (id == R.id.nav_donate) {
            getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout,new DonationFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE,context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
        finish();
        editor.clear();
        editor.apply();
    }

}
