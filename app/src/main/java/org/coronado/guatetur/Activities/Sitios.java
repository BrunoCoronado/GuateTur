package org.coronado.guatetur.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.coronado.guatetur.R;
import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sitios extends AppCompatActivity {
    private ImageView fotoSitio;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios);
        Bundle bundle = getIntent().getExtras();

        fotoSitio = (ImageView) findViewById(R.id.headerSitio);
        Map<String,Integer> params = new HashMap<String,Integer>();
        Integer id= 1+bundle.getInt("id_sitio");
        JSONObject peticion = new JSONObject();
        try {
            peticion.put("id_sitioTuristico", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.verSitio, peticion, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray sitio = response.getJSONArray("sitios");
                    JSONArray fotosObtenidos = response.getJSONArray("fotos");
                    JSONObject f = fotosObtenidos.getJSONObject(0);
                    Log.d("recibiendo","url"+f.getString("direccion"));
                    Glide.with(context).load(f.getString("direccion")).override(800,1800).into(fotoSitio);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: Response ", "Error en el servidor");
            }
        }
        );
        WebService.getInstance(context).addToRequestQueue(request);
    }
}
