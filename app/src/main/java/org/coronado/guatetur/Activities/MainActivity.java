package org.coronado.guatetur.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.coronado.guatetur.Fragments.FragmentSitios;
import org.coronado.guatetur.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentSitios()).commit();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

            boolean fragmentTransaction = false;
            Fragment fragment = null;

            switch (menuItem.getItemId()) {
                case R.id.opcion1:
                    fragment = new FragmentSitios();
                    fragmentTransaction = true;
                    Log.i("NavigationView", "opcion1");
                    break;
                case R.id.opcion2:
                   /* fragment = new Fragment2();
                    fragmentTransaction = true;
                    break;*/
                case R.id.opcion3:
                    /*fragment = new Fragment3();
                    fragmentTransaction = true;
                    break;*/
                case R.id.subOpcion1:
                    Log.i("NavigationView", "Pulsada opción 1");
                    break;
                case R.id.subOpcion2:
                    Log.i("NavigationView", "Pulsada opción 2");
                    break;
            }
            if(fragmentTransaction) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                menuItem.setChecked(true);
                getSupportActionBar().setTitle(menuItem.getTitle());
            }
            drawerLayout.closeDrawers();
            return true;
        }
    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.opcionLogin:
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
