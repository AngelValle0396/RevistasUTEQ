package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.revistasuteq.WebServices.Asynchtask;
import com.example.revistasuteq.WebServices.WebService;
import com.example.revistasuteq.adaptador.AdaptadorCategoria;
import com.example.revistasuteq.adaptador.AdaptadorEdicion;
import com.example.revistasuteq.adaptador.AdaptadorRevista;
import com.example.revistasuteq.modelo.Articulo;
import com.example.revistasuteq.modelo.Categoria;
import com.example.revistasuteq.modelo.Edicion;
import com.example.revistasuteq.modelo.Revista;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Categorias extends AppCompatActivity {
    RecyclerView rvItem;
    private RequestQueue queue;
    List<Categoria> itemList;
    LinearLayoutManager layoutManager;
    Bundle bundle;
    List<Articulo> subItemList;
    String idC,idIS;
    ImageView img;
    Intent intent;
    String idISAUX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        rvItem =  findViewById(R.id.rv_item);
        img=findViewById(R.id.img_sub_item);
        rvItem.setLayoutManager(new LinearLayoutManager(Categorias.this));
        queue= Volley.newRequestQueue(Categorias.this);

        bundle=this.getIntent().getExtras();
        layoutManager= new LinearLayoutManager(Categorias.this);
idISAUX=bundle.getString("idIS");
        handleSSLHandshake();
        LgVolley(idISAUX);


    }

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }


    private void LgVolley(String idIs){
        final String urllg="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+idIs;
        idIS=idIs;
        intent= new Intent(Categorias.this, com.example.revistasuteq.Articulo.class);
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, urllg, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonlista= new JSONArray(response);
                        subItemList=Articulo.JsonObjectsBuild(jsonlista);
                        itemList = Categoria.JsonObjectBuild(subItemList);
                        AdaptadorCategoria adaptadorCategoria= new AdaptadorCategoria(itemList);
                        rvItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bundle=new Bundle();
                                bundle.putString("submissID",   subItemList.get(rvItem.getChildAdapterPosition(v)).getSubmissA());
                                bundle.putString("issueID",    idISAUX);
                                intent.putExtras(bundle);
                                Toast.makeText(getApplicationContext(),"Seleccionó el artículo: " + subItemList.get(rvItem.getChildAdapterPosition(v)).getTituloA(),Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        });
                        rvItem.setAdapter(adaptadorCategoria);

                    }catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG);
                }
            });
            queue.add(stringRequest);
        }
        catch (Exception EX){
            String s;
            s=EX.getMessage();
        }


    }



}