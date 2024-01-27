package fr.jux.bettercolloscope;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout main_table = (TableLayout) findViewById(R.id.main_table);
        main_table.setStretchAllColumns(true);
        init_tables(main_table);
        connexion_e_colle();
    }

    private static final String TAG = "connexion";

    public void init_tables(TableLayout main_table) {
        //TODO : CREATE TABLES FROM HTML RAW DATA
    }

    public void connexion_e_colle() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(1, "https://e-colle.supwallon.fr/app_mobile/connect", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
                Toast.makeText(getApplicationContext(), "Erreur lors de la connexion à e-colle", Toast.LENGTH_LONG).show();
            }
        }

        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("username", "jules.mercier");
                headers.put("password", "XXXXX");
                return headers;
            }
        };
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);

    }
}

