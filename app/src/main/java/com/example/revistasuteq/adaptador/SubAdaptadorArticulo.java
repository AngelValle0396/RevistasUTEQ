package com.example.revistasuteq.adaptador;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.R;
import com.example.revistasuteq.modelo.Articulo;
import com.example.revistasuteq.modelo.Edicion;

import java.util.List;

public class SubAdaptadorArticulo extends RecyclerView.Adapter<SubAdaptadorArticulo.SubItemViewHolder> {

    private List<Articulo> subItemList;
    private Edicion e;

    SubAdaptadorArticulo(List<Articulo> subItemList) {
        this.subItemList = subItemList;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_item_category, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {
        Articulo subItem = subItemList.get(i);
        subItemViewHolder.tvSubItemTitle.setText("Título: "+subItem.getTituloA());
        subItemViewHolder.fechaA.setText("Fecha: "+subItem.getFechaA());
        subItemViewHolder.autores.setText("Autores: "+subItem.getAutoresA());
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle,fechaA,doiA,autores;
        ImageView img;
        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
            fechaA=itemView.findViewById(R.id.fechaAR);
            img=itemView.findViewById(R.id.img_sub_item);
            //doiA=itemView.findViewById(R.id.doiAR);
           autores=itemView.findViewById(R.id.autoresAR);
        }
    }
}