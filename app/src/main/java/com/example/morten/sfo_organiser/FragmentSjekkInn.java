package com.example.morten.sfo_organiser;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSjekkInn extends Fragment {

ElevAdapter adapter;
ListView sjekkInnListView;


    public FragmentSjekkInn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_sjekk_inn, container, false);
        //Kode her
        sjekkInnListView=(ListView)view.findViewById(R.id.sjekkInnListView);
        if (MainActivity.erPaaNett) {

            // Hente alle data jsonserveren
            new JsonSjekkInnTask().execute();
        }

        return view;
    }




    class JsonSjekkInnTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        public final static int MY_REQUEST_LOCATION = 1;


        String elevData = null;

        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public JsonSjekkInnTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getActivity());
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


                Toast.makeText(getActivity(), "JsonData:" + result, Toast.LENGTH_SHORT).show();


                try {


                    MainActivity.JSONServerList = Elev.lagElevListe(result);




                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JsonExeption", e.getMessage());
                }
                JSONArray mArray;
                try {
                    mArray = new JSONArray(result);
                    for (int i = 0; i < mArray.length(); i++) {
                        JSONObject mJsonObject = mArray.getJSONObject(i);
                        Log.d("OutPut fra ", mJsonObject.getString("fname"));
                        Elev elev = new Elev(mJsonObject);
                        Log.d("JsonTOElev", elev.toString());


                        MainActivity.JSONServerList.add(elev);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter = new ElevAdapter(getActivity(), MainActivity.JSONServerList);
                sjekkInnListView.setAdapter(adapter);


            } else {

                Toast.makeText(getActivity(), "Fikk ikke hentet noen data", Toast.LENGTH_SHORT).show();

                progressDialog.cancel();

            }

        }


    }//Slutt på asynk classen



}
