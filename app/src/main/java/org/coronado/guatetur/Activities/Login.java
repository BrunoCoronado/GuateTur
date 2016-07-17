package org.coronado.guatetur.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.Usuario;
import org.coronado.guatetur.bean.UsuarioLogeado;
import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class Login extends AppCompatActivity {
    private EditText txtCorreo = null;
    private EditText txtPassword = null;
    private Button btnIniciarSesion = null;
    private Button btnRegistrarse = null;
    private Usuario userLogged = null;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnLogin);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistro);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {

                if (txtCorreo.getText().toString().equals("") && txtPassword.getText().toString().equals("")) {
                    Snackbar snackbar = Snackbar.make(v, "Credenciales Incorrectas", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.getView().setBackgroundColor(Color.RED);
                    snackbar.show();
                }else{
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Iniciando Sesion");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("correo", txtCorreo.getText().toString());
                    params.put("password", txtPassword.getText().toString());

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.autenticar, new JSONObject(params), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray listaUsuarios = response.getJSONArray("user");
                                if (listaUsuarios.length() > 0) {
                                    JSONObject user = listaUsuarios.getJSONObject(0);
                                    userLogged = new Usuario(user.getInt("id_usuario"), user.getString("nombre"), user.getString("correo"), user.getString("nick"));
                                    UsuarioLogeado.setUsuario(userLogged);
                                    progressBar.dismiss();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                } else {
                                    progressBar.dismiss();
                                    Snackbar snackbar = Snackbar.make(v, "Credenciales Incorrectas", Snackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);
                                    snackbar.getView().setBackgroundColor(Color.RED);
                                    snackbar.show();
                                    txtPassword.setText(null);
                                    txtPassword.setHint("Contraseña");
                                }
                            } catch (Exception ex) {
                                Log.e("Response exception ", ex.getMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError err) {
                            Log.d("Error: Response ", "Error en el servidor");
                        }
                    }

                    );
                    WebService.getInstance(v.getContext()).addToRequestQueue(request);
                }
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });

    }
}
