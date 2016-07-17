package org.coronado.guatetur.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.coronado.guatetur.Activities.Sitios;
import org.coronado.guatetur.Adapters.SitiosAdapter;
import org.coronado.guatetur.Decoration.DividerItemDecoration;
import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;
import org.coronado.guatetur.bean.UsuarioLogeado;
import org.coronado.guatetur.volley.WebService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by informatica on 03/06/2016.
 */
public class FragmentVisitas extends Fragment{

    private RecyclerView listaSitios;
    private ArrayList<SitioTuristico> listaVistados;
    private ArrayList<SitioTuristico> sitios;

    public FragmentVisitas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visitas, container, false);
        listaSitios = (RecyclerView) view.findViewById(R.id.listavisitas);
        sitios = new ArrayList<SitioTuristico>();
        listaVistados = new ArrayList<SitioTuristico>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, WebService.sitios, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray sitiosObtenidos = response.getJSONArray("sitios");
                            JSONArray fotosObtenidas = response.getJSONArray("fotos");
                            JSONArray sitiosdisponibles = response.getJSONArray("sitiosdisponibles");
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

                                for(int e=0;e<sitiosdisponibles.length();e++){
                                    JSONObject disp = sitiosdisponibles.getJSONObject(e);
                                    for(int cont=0;cont<sitios.size();cont++){
                                        if(disp.getInt("usuarioIdUsuario") == UsuarioLogeado.getUsuario().getId_Usuario()){
                                            if(disp.getInt("sitioturisticoIdSitioTuristico") == sitios.get(cont).getId_sitioTuristico()){
                                                listaVistados.add(sitios.remove(cont));
                                            }
                                        }
                                    }
                                }
                                listaSitios.setHasFixedSize(true);
                                final SitiosAdapter adapter = new SitiosAdapter(listaVistados);
                                listaSitios.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
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
            public void onErrorResponse(VolleyError err) {}
        }
        );
        WebService.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request);
        return view;
    }
}


