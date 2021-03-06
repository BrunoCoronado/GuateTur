package org.coronado.guatetur.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.coronado.guatetur.Activities.Sitios;
import org.coronado.guatetur.Adapters.SitiosAdapter;

import org.coronado.guatetur.Decoration.DividerItemDecoration;
import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;
import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentSitios extends Fragment {

    private RecyclerView listaSitios;
    private ArrayList<SitioTuristico> sitios;
    private ProgressDialog progress;
    public FragmentSitios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sitios, container, false);
        listaSitios = (RecyclerView) view.findViewById(R.id.listaSitios);
        sitios = new ArrayList<SitioTuristico>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, WebService.sitios, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray sitiosObtenidos = response.getJSONArray("sitios");
                            JSONArray fotosObtenidas = response.getJSONArray("fotos");
                            if(sitiosObtenidos.length()>0){
                                for(int i=0;i<sitiosObtenidos.length();i++){
                                    JSONObject s = sitiosObtenidos.getJSONObject(i);
                                    SitioTuristico sitio = new SitioTuristico(s.getInt("id_sitioTuristico"),s.getString("nombre"),s.getString("descripcion"));
                                    if(fotosObtenidas.length()>0){
                                        for(int e=0;e<fotosObtenidas.length();e++){
                                            JSONObject f = fotosObtenidas.getJSONObject(e);
                                            if(sitio.getId_sitioTuristico()==f.getInt("sitioturisticoIdSitioTuristico"))
                                                sitio.set_urlImagen(f.getString("direccion"));
                                        }
                                    }
                                    sitios.add(sitio);
                                }
                                listaSitios.setHasFixedSize(true);
                                final SitiosAdapter adapter = new SitiosAdapter(sitios);
                                listaSitios.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
                                listaSitios.setAdapter(adapter);
                                listaSitios.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));

                                adapter.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Intent intent = new Intent(getActivity(),Sitios.class);
                                        intent.putExtra("id_sitio",listaSitios.getChildAdapterPosition(v));
                                        Log.d("enviando","numero"+listaSitios.getChildAdapterPosition(v));
                                        startActivity(intent);
                                    }
                                });
                                listaSitios.notifyAll();
                            }
                        }catch (Exception ex){
                            Log.e("Responese exception", ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError err) {}
        }
        );
        WebService.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
        return view;
    }
/*
    private class CargandoDatos extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... Void) {

            return null;
        }
    }*/
}



