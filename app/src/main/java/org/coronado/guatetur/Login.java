package org.coronado.guatetur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText txtCorreo = null;
    private EditText txtContraseña = null;
    private Button btnIniciarSesion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                Map<String,String> params =  new HashMap<String, String>();
                params.put("correo",txtCorreo.getText().toString());
                params.put("password", txtContraseña.getText().toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.autenticar, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray listaUsuarios=response.getJSONArray("user");
                            if(listaUsuarios.length()>0){
                                //Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                                JSONObject user=listaUsuarios.getJSONObject(0);

                                startActivity(new Intent(Login.this,Sitios.class));
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
