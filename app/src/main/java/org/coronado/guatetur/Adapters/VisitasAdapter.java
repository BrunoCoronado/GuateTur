package org.coronado.guatetur.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;

import java.util.ArrayList;

/**
 * Created by informatica on 03/06/2016.
 */
public class VisitasAdapter  extends RecyclerView.Adapter<VisitasAdapter.ViewHolderVisitas>{
    private ArrayList<SitioTuristico> visita;
    private View.OnClickListener listener;

    public VisitasAdapter(ArrayList<SitioTuristico> sitios){
        this.visita = sitios;
    }

    @Override
    public ViewHolderVisitas onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita, parent, false);

        ViewHolderVisitas viewHolderVisitas = new ViewHolderVisitas(itemView);
        return viewHolderVisitas;
    }

    @Override
    public void onBindViewHolder(ViewHolderVisitas holder, int position) {
        SitioTuristico sitio = visita.get(position);
        holder.bindSitio(sitio);
    }

    @Override
    public int getItemCount() {
        return visita.size();
    }



    public class ViewHolderVisitas extends RecyclerView.ViewHolder {
        private TextView txtNombreSitio;
        private TextView txtDescripcionSito;
        private ImageView imgSitio;
        private Context context;

        public ViewHolderVisitas(View itemView){
            super(itemView);
            txtNombreSitio = (TextView) itemView.findViewById(R.id.lblNombreVisita);
            txtDescripcionSito = (TextView) itemView.findViewById(R.id.lblDescripcionVisita);
            imgSitio = (ImageView)itemView.findViewById(R.id.imgVisita);
            context = itemView.getContext();
        }

        public void bindSitio(SitioTuristico sitio){
            txtNombreSitio.setText(sitio.getNombre());
            txtDescripcionSito.setText(sitio.getDescripcion());
            Glide.with(context).load(sitio.get_urlImagen()).override(800,1800).into(imgSitio);
        }
    }
}
