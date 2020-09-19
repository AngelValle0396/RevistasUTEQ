package com.example.revistasuteq.adaptador;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.example.revistasuteq.R;
import com.example.revistasuteq.modelo.Revista;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorRevista extends RecyclerView.Adapter<AdaptadorRevista.ViewHolder>implements View.OnClickListener {
    private Context mContext ;
    private ArrayList<Revista> mData ;
    private View.OnClickListener onClickListener;


    public AdaptadorRevista(Context context, ArrayList<Revista> lista){
        mContext= context;
        mData= lista;
    }



    public void setOnClickListener(View.OnClickListener onClickListenerp){
        this.onClickListener=onClickListenerp;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.item_journal,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        item.setOnClickListener(this);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s=Html.fromHtml( mData.get(position).getDescripJ()).toString();
        holder.nombreJ.setText(mData.get(position).getNombreJ());
        holder.descripJ.setText(s);
        Picasso.with(mContext)
                .load(mData.get(position).getImgUrlJ())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreJ, descripJ;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgjrn);
            nombreJ= itemView.findViewById(R.id.txtJournalN);
            descripJ= itemView.findViewById(R.id.txtJournalD);
        }
    }
}
