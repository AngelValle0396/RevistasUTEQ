package com.example.revistasuteq.adaptador;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistasuteq.R;
import com.example.revistasuteq.modelo.Edicion;
import com.example.revistasuteq.modelo.Revista;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorEdicion  extends RecyclerView.Adapter<AdaptadorEdicion.ViewHolder>implements View.OnClickListener {
    private Context mContext ;
    private ArrayList<Edicion> mData ;
    private View.OnClickListener onClickListener;
    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListenerp){
        this.onClickListener=onClickListenerp;
    }
    public AdaptadorEdicion(Context context, ArrayList<Edicion> lista){
        mContext= context;
        mData= lista;
    }
    @NonNull
    @Override
    public AdaptadorEdicion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.item_issue,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        item.setOnClickListener(this);
        return new AdaptadorEdicion.ViewHolder(item);
    }
    public String negrita(String text){
        String res=Html.fromHtml( text ).toString();
        return res;
    }
    @Override
    public void onBindViewHolder(@NonNull AdaptadorEdicion.ViewHolder holder, int position) {

        holder.tituloIS.setText(negrita("Título: ")+mData.get(position).getTituloIS());
        holder.idIS=(mData.get(position).getIdIS());
        holder.volumenIS.setText(negrita("Volúmen: ")+mData.get(position).getVolumenIS());
        holder.numeroIS.setText(negrita("Número: ")+mData.get(position).getNumeroIS());
        holder.fechaIS.setText(negrita("Fecha de publicación: ")+mData.get(position).getFechaIS());
        holder.añoIS.setText(negrita("Año: ")+mData.get(position).getAñoIS());
        holder.doiIS.setText(negrita("DOI: ")+mData.get(position).getDoiIS());

        Picasso.with(mContext)
                .load(mData.get(position).getImgIS())
                .into(holder.imgIS);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tituloIS,volumenIS,numeroIS, fechaIS,añoIS,doiIS;
        String idIS;
        ImageView imgIS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idIS= "";
            tituloIS= itemView.findViewById(R.id.txtNombreIS);
            volumenIS= itemView.findViewById(R.id.txtVolIS);
            numeroIS= itemView.findViewById(R.id.txtNumerIS);
            fechaIS= itemView.findViewById(R.id.txtFechaIS);
            añoIS= itemView.findViewById(R.id.txtañoIS);
            doiIS= itemView.findViewById(R.id.txtDOIIS);
            imgIS= itemView.findViewById(R.id.imgIS);
        }
    }
}
