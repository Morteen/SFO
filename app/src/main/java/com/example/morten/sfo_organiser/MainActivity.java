package com.example.morten.sfo_organiser;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Elev> elevListe, JSONServerList;
    Elev e1, e2, e3;
 static ElevAdapter elevAdapter;
  static OversiktAdapter oversiktAdapter;
    static boolean erPaaNett;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JSONServerList = new ArrayList<Elev>();

      //Adapter for å bla i fragmentene
        ViewPager pager = (ViewPager) findViewById(R.id.pager2);
        PageAdapter pAdpter = new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(pAdpter);


        erPaaNett = isOnline();
        if(isOnline()){
            new JsonStartTask().execute();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //Sender brukeren til registrering av en ny elev
        if (id == R.id.action_Legg_til) {

            Fragment addOne = new RegNyElev();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main, addOne);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();

        }
        return super.onOptionsItemSelected(item);
    }

    class JsonStartTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        public final static int MY_REQUEST_LOCATION = 1;


        String elevData = null;

        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public JsonStartTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Vent litt nå da..");
            progressDialog.setTitle("Kobler opp...");
            progressDialog.show();
            progressDialog.setProgressStyle(3);
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {


            // HttpURLConnection connection= null;
            BufferedReader reader = null;
            String line = "";
            String result = "";
            InputStream is = null;

            String url = "http://192.168.10.141:3000/Elever";
            HttpURLConnection connection = null;
            try {
                URL actorUrl = new URL(url);

                connection = (HttpURLConnection) actorUrl.openConnection();
                connection.connect();
                int status = connection.getResponseCode();
                Log.d("connection", "status " + status);

                is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String responseString;
                StringBuilder sb = new StringBuilder();
                while ((responseString = reader.readLine()) != null) {
                    sb = sb.append(responseString);
                }
                elevData = sb.toString();

                Log.d("connection", elevData);

//Log.D("Resultat fra JSON server"+)
                return elevData;


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();

            } catch (NullPointerException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();

            } finally {

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                connection.disconnect();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String result) {


            if (result != null) {

                progressDialog.cancel();





              /*  try {


                    JSONServerList = Elev.lagElevListe(result);



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JsonExeption", e.getMessage());
                }*/
                JSONArray mArray;
                try {
                    mArray = new JSONArray(result);
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mJsonObject = mArray.getJSONObject(i);
                        Log.d("OutPut fra ", mJsonObject.getString("fname"));
                        Elev elev = new Elev(mJsonObject);
                        Log.d("JsonTOElev", elev.toString());


                        JSONServerList.add(elev);

                    }
                    Toast.makeText(getApplicationContext(), "JsonListsize:" + JSONServerList.size(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            } else {

                Toast.makeText(getApplicationContext(), "Fikk ikke hentet noen data", Toast.LENGTH_SHORT).show();

                progressDialog.cancel();

            }

        }


    }//Slutt på asynk classen

    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
