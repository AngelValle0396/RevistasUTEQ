package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.revistasuteq.WebServices.Asynchtask;
import com.example.revistasuteq.WebServices.WebService;
import com.example.revistasuteq.adaptador.AdaptadorEdicion;
import com.example.revistasuteq.adaptador.AdaptadorRevista;
import com.example.revistasuteq.modelo.Edicion;
import com.example.revistasuteq.modelo.Revista;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Ediciones extends AppCompatActivity implements Asynchtask {
    ArrayList<Edicion> lstediciones;
    RecyclerView myrv;
    AdaptadorEdicion adapator;
    private  Bundle bundle;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);
        bundle = this.getIntent().getExtras();
        myrv =  findViewById(R.id.rwedicion);
        myrv.setLayoutManager(new LinearLayoutManager(Ediciones.this));
        handleSSLHandshake();
        llamadoWS();
    }
    private void llamadoWS(){
        String url ="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+bundle.getString("id");
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,
                datos, Ediciones.this, Ediciones.this);
        ws.execute("GET");
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
    @Override
    public void processFinish(String result) throws JSONException {
        try {
            intent= new Intent(Ediciones.this, Categorias.class);
            JSONArray jsonlista= new JSONArray(result);
            lstediciones = Edicion.JsonObjectsBuild(jsonlista);
            adapator= new AdaptadorEdicion(getApplicationContext(), lstediciones);
            adapator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle=new Bundle();
                    bundle.putString("idIS",   lstediciones.get(myrv.getChildAdapterPosition(v)).getIdIS());
                    bundle.putString("urlS",   lstediciones.get(myrv.getChildAdapterPosition(v)).getImgIS());
                    intent.putExtras(bundle);
                    Toast.makeText(getApplicationContext(),"Seleccionó la edición:" + lstediciones.get(myrv.getChildAdapterPosition(v)).getTituloIS(),Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
            myrv.setAdapter(adapator);

        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}