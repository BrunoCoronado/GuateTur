package org.coronado.guatetur.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.coronado.guatetur.R;


/**
 * Created by TrexT on 03/06/2016.
 */
public class SplashView extends Activity {
    private static int tiempo = 1250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, tiempo);
    }
}

