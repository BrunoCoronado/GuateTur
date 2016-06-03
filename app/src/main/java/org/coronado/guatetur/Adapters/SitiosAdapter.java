package org.coronado.guatetur.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;
import java.util.ArrayList;

public class SitiosAdapter extends RecyclerView.Adapter<SitiosAdapter.ViewHolderSitios> implements View.OnClickListener{

    private ArrayList<SitioTuristico> sitios;
    private View.OnClickListener listener;

    public SitiosAdapter(ArrayList<SitioTuristico> sitios){
        this.sitios = sitios;
    }

    @Override
    public ViewHolderSitios onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sitio, parent, false);

        itemView.setOnClickListener(this);

        ViewHolderSitios viewHolderSitios = new ViewHolderSitios(itemView);
        return viewHolderSitios;
    }

    @Override
    public void onBindViewHolder(ViewHolderSitios holder, int position) {
        SitioTuristico sitio = sitios.get(position);

        holder.bindSitio(sitio);
    }

    @Override
    public int getItemCount() {
        return sitios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)
            listener.onClick(v);
    }


    public static class ViewHolderSitios extends RecyclerView.ViewHolder{

        private TextView txtNombreSitio;
        private TextView txtDescripcionSito;

        public ViewHolderSitios(View itemView){
            super(itemView);
            txtNombreSitio = (TextView) itemView.findViewById(R.id.lblNombreSitio);
            txtDescripcionSito = (TextView) itemView.findViewById(R.id.lblDescripcionSitio);
        }

        public void bindSitio(SitioTuristico sitio){
            txtNombreSitio.setText(sitio.getNombre());
            txtDescripcionSito.setText(sitio.getDescripcion());
        }
    }
}
