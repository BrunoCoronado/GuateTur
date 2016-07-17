package org.coronado.guatetur.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.coronado.guatetur.R;
import org.coronado.guatetur.bean.SitioTuristico;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        private ImageView imgSitio;
        private Context context;

        public ViewHolderSitios(View itemView){
            super(itemView);
            txtNombreSitio = (TextView) itemView.findViewById(R.id.lblNombreSitio);
            txtDescripcionSito = (TextView) itemView.findViewById(R.id.lblDescripcionSitio);
            imgSitio = (ImageView)itemView.findViewById(R.id.imgSitio);
            context = itemView.getContext();
        }

        public void bindSitio(SitioTuristico sitio){
            txtNombreSitio.setText(sitio.getNombre());
            txtDescripcionSito.setText(sitio.getDescripcion());
            Glide.with(context).load(sitio.get_urlImagen()).override(800,1800).into(imgSitio);
        }
    }
}
