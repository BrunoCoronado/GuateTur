package org.coronado.guatetur.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.coronado.guatetur.Fragments.FragmentHoteles;
import org.coronado.guatetur.Fragments.FragmentSitios;
import org.coronado.guatetur.Fragments.LoadingView;
import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.UsuarioLogeado;

public class MainActivity extends AppCompatActivity {
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private View view;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentSitios()).commit();
        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);
        view = (View)findViewById(R.id.drawer_layout);

        if(UsuarioLogeado.getSesionIniciada() != false){
            Snackbar snackbar =  Snackbar.make(view,"Bienvenido "+UsuarioLogeado.getUsuario().getNick(),Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
            UsuarioLogeado.setSesionIniciada(false);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

            boolean fragmentTransaction = false;

            switch (menuItem.getItemId()) {
                case R.id.opcion1:
                    fragment = new FragmentSitios();
                    fragmentTransaction = true;
                    Log.i("NavigationView", "opcion1");
                    break;
                case R.id.opcion2:
                   fragment = new FragmentHoteles();
                    fragmentTransaction = true;
                    break;
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
