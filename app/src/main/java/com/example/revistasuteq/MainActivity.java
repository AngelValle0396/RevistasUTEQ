package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.revistasuteq.WebServices.Asynchtask;
import com.example.revistasuteq.WebServices.WebService;
import com.example.revistasuteq.adaptador.AdaptadorRevista;
import com.example.revistasuteq.modelo.Revista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MainActivity extends AppCompatActivity implements Asynchtask
{
    private RequestQueue queue;
    ArrayList<Revista> lstRevistas;
    RecyclerView myrv;
    AdaptadorRevista adapator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue=Volley.newRequestQueue(MainActivity.this);

        myrv = (RecyclerView)findViewById(R.id.rwrevistas);
        myrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        handleSSLHandshake();
        //LgVolley();
        llamadoWS();
    }
    private void llamadoWS(){
        String url ="https://revistas.uteq.edu.ec/ws/journals.php";
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,
                datos, MainActivity.this, MainActivity.this);
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
    private void LgVolley(){
       final String urllg="https://revistas.uteq.edu.ec/ws/journals.php";
        /*HashMap<String, String> params = new HashMap<String, String>();

        JsonObjectRequest req = new JsonObjectRequest(urllg, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonlista= new JSONArray(response);
                            lstRevistas = Revista.JsonObjectsBuild(jsonlista);
                            adapator= new AdaptadorRevista(getApplicationContext(), lstRevistas);
                            myrv.setAdapter(adapator);

                        }catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG);
            }
        });*/
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, urllg, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonlista= new JSONArray(response);
                        lstRevistas = Revista.JsonObjectsBuild(jsonlista);
                        adapator= new AdaptadorRevista(getApplicationContext(), lstRevistas);
                        myrv.setAdapter(adapator);

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

    @Override
    public void processFinish(String result) throws JSONException {
        try {
            JSONArray jsonlista= new JSONArray(result);
            lstRevistas = Revista.JsonObjectsBuild(jsonlista);
            adapator= new AdaptadorRevista(getApplicationContext(), lstRevistas);
            myrv.setAdapter(adapator);

        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}