package com.example.revistasuteq.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.R;
import com.example.revistasuteq.modelo.Articulo;

import java.util.List;

public class SubAdaptadorArticulo extends RecyclerView.Adapter<SubAdaptadorArticulo.SubItemViewHolder> {

    private List<Articulo> subItemList;

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
        subItemViewHolder.tvSubItemTitle.setText(subItem.getTituloA());
        subItemViewHolder.fechaA.setText(subItem.getFechaA());
        subItemViewHolder.doiA.setText(subItem.getDoiA());
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle,fechaA,doiA,autores;

        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
            fechaA=itemView.findViewById(R.id.fechaAR);
            doiA=itemView.findViewById(R.id.doiAR);
           // autores=itemView.findViewById(R.id.a)
        }
    }
}