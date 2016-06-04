package org.coronado.guatetur.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.coronado.guatetur.Adapters.SitiosAdapter;

import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;
import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentSitios extends Fragment {

    private RecyclerView listaSitios;
    private ArrayList<SitioTuristico> sitios;

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
                        Log.d("Response", response.toString());
                        try {
                            JSONArray sitiosObtenidos = response.getJSONArray("sitios");
                            if(sitiosObtenidos.length()>0){
                                for(int i=0;i<sitiosObtenidos.length();i++){
                                    JSONObject s = sitiosObtenidos.getJSONObject(i);
                                    sitios.add(new SitioTuristico(s.getInt("id_sitioTuristico"),s.getString("nombre"),s.getString("descripcion")));
                                }


                                listaSitios.setHasFixedSize(true);

                                final SitiosAdapter adapter = new SitiosAdapter(sitios);

                                adapter.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v){
                                        Log.i("DemoRecView", "Pulsado el elemento " + listaSitios.getChildAdapterPosition(v));
                                    }
                                });

                                listaSitios.setAdapter(adapter);
                                listaSitios.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                                listaSitios.notifyAll();

                            }
                        }catch (Exception ex){
                            Log.e("Responese exception", ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError err) {
                //Log.d("Error: Response ",err.getMessage());
            }
        }
        );
        WebService.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);


        return view;
    }
}
