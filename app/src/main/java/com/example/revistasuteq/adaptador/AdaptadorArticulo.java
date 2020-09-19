package com.example.revistasuteq.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.Articulo;
import com.example.revistasuteq.R;

import java.util.ArrayList;

public class AdaptadorArticulo extends RecyclerView.Adapter<AdaptadorArticulo.ViewHolder>{
    private Context mContext ;
    private ArrayList<com.example.revistasuteq.modelo.Articulo> mData ;
    public AdaptadorArticulo(Context context, ArrayList<com.example.revistasuteq.modelo.Articulo> lista){
        mContext= context;
        mData= lista;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.item_articulo,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);

        return new AdaptadorArticulo.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tituloA.setText(mData.get(position).getTituloA());
        holder.doiA.setText(mData.get(position).getDoiA());
        holder.abstractA.setText(mData.get(position).getAbstractA());
        holder.fechaA.setText(mData.get(position).getFechaA());
        holder.autoresA.setText(mData.get(position).getAutoresA());
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tituloA, doiA,abstractA,fechaA,urlPDFA,urlHTMLA,keywordsA,section,autoresA;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloA= itemView.findViewById(R.id.txtNombreAR);
            doiA= itemView.findViewById(R.id.txtDoiAR);
            abstractA= itemView.findViewById(R.id.txtresumenAR);
            fechaA= itemView.findViewById(R.id.txtFechaAR);
            autoresA= itemView.findViewById(R.id.txtAutoresAR);
        }
    }
}
