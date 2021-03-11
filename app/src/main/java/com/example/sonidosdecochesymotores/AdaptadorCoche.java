package com.example.sonidosdecochesymotores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCoche extends BaseAdapter {
    private final Context context;
    private ArrayList<Coche> coches;

    public AdaptadorCoche(Context context, int layout, ArrayList<Coche> coches) {
        this.context = context;
        this.coches = coches;
    }
    @Override
    public int getCount() {
        return this.coches.size();
    }
    @Override
    public Object getItem(int position) {
        return this.coches.get(position);
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.activity_adaptador_coche, null);
        String nombre = coches.get(position).getNombre();
        ImageView imagenview = (ImageView) v.findViewById(R.id.foto);
        imagenview.setImageResource(coches.get(position).getImagen());
        TextView tvNombre = (TextView) v.findViewById(R.id.nombre);
        if(coches.get(position).getNombreEnOtroIdioma()!=null){
            tvNombre.setText(coches.get(position).getNombreEnOtroIdioma());
        }
        else tvNombre.setText(nombre);
        return v;
    }
}
