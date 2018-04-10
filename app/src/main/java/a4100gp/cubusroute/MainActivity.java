package a4100gp.cubusroute;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean viewIsAtHome = true;
    private FragmentManager fragmentManager;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewIsAtHome == true){
                Snackbar.make(view, "Search with Location", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
                else if(viewIsAtHome == false){
                    Snackbar.make(view, "Search with Bus Route", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                displayView(R.id.fab);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(0); // call search fragment.
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!viewIsAtHome) { //if the current view is not the News fragment
                displayView(R.id.nav_home);
                viewIsAtHome = true;
                //display the News fragment
            } else {
                moveTaskToBack(true);  //If view is in News fragment, exit application
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.search_menu, menu);
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
//        if (id == R.id.nav_home) {
//            fragment = new MainFragment();
//            android.support.v4.app.FragmentTransaction fragmentTransaction =
//                    getSupportFragmentManager().beginTransaction();
//
//            fragmentTransaction.replace(R.id.fragment_container, fragment);
//            fragmentTransaction.commit();
//        } else if (id == R.id.nav_search) {
//
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.content_frame,fragment);
//        } else if (id == R.id.nav_slideshow) {
//        }
//
        displayView(id); // call search fragment.

        return true;
    }
    private void displayView(int position) {
        Fragment fragment = null;
        //String title = getString(R.string.app_name);
        String fragmentTags = "";
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case  R.id.nav_home:
                fragment = new MainFragment();

                break;
            case R.id.nav_search:
                fragment = new MainFragment();

                break;
            case R.id.nav_slideshow:
                fragment = new MapFragment();
                viewIsAtHome = false;
                break;
            case R.id.nav_map:
                break;
            case R.id.fab:
                if (viewIsAtHome == true) {
                    fragment = new LocationSearchFragment();
                    viewIsAtHome = false;
                    break;
                }
                else if(viewIsAtHome == false){
                    fragment = new MainFragment();
                    viewIsAtHome = true;
                    break;
                }
        }

        if (fragment != null) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, fragmentTags).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}