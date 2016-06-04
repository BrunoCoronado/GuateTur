package org.coronado.guatetur.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {
    private EditText txtCorreo = null;
    private EditText txtPassword = null;
    private Button btnIniciarSesion = null;
    private Usuario userLogged = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnLogin);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {

                Map<String,String> params =  new HashMap<String, String>();
                params.put("correo",txtCorreo.getText().toString());
                params.put("password", txtPassword.getText().toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.autenticar, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray listaUsuarios=response.getJSONArray("user");
                            if(listaUsuarios.length()>0){
                                JSONObject user=listaUsuarios.getJSONObject(0);
                                userLogged = new Usuario(user.getInt("id_usuario"),user.getString("nombre"),user.getString("correo"),user.getString("nick"));
                                UsuarioLogeado.setUsuario(userLogged);
                                startActivity(new Intent(Login.this,MainActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(),"Verifique sus Credenciales",Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception ex){
                            Log.e("Response exception ",ex.getMessage());
                        }

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError err) {
                        Log.d("Error: Response ",err.getMessage());
                    }
                }

                );
                WebService.getInstance(v.getContext()).addToRequestQueue(request);
            }
        });

    }
}
