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
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOverSikt extends Fragment {

    ListView elevListView;
    TextView trinn1,trinn2,trinn3,trinn4,visAlle,enFrem,enTilbake;
    OversiktAdapter adapter;
    private int sidetall =0;


    public FragmentOverSikt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_oversikt_vol2, container, false);
        elevListView = (ListView) view.findViewById(R.id.elevListView);

        //Kode her
        if (MainActivity.erPaaNett) {
            Toast.makeText(getActivity(), "Appen er online", Toast.LENGTH_LONG).show();
            // Hente alle data jsonserveren
            new JsonStartTask().execute();
        }



        trinn1=(TextView)view.findViewById(R.id.trinn1);
        trinn2=(TextView)view.findViewById(R.id.trinn2);
        trinn3=(TextView)view.findViewById(R.id.trinn3);
        trinn4=(TextView)view.findViewById(R.id.trinn4);
        visAlle=(TextView)view.findViewById(R.id.visalle);
        enFrem=(TextView)view.findViewById(R.id.enFrem);
        enTilbake=(TextView)view.findViewById(R.id.entilbake);

        //Legger på click listnere for å gi sidetall
        enTilbake.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if(sidetall==0){
                                                 sidetall=0;
                                             }else{
                                                 sidetall--;
                                             }
                                             visSide(sidetall);
                                         }
                                     } );
        visAlle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=0;
                visSide(sidetall);
            }



        });


        trinn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sidetall=1;
                visSide(sidetall);
                 }



        });
        trinn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=2;
                visSide(sidetall);
            }
        });
        trinn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=3;
                visSide(sidetall);
            }
        });

        trinn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=4;
                visSide(sidetall);

            }
        });

        enFrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sidetall==4){
                    sidetall=4;
                }else{
                    sidetall++;
                }
                visSide(sidetall);
            }
        } );




        return view;
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

                 adapter = new OversiktAdapter(getActivity(), MainActivity.JSONServerList);
                elevListView.setAdapter(adapter);


            } else {

                Toast.makeText(getActivity(), "Fikk ikke hentet noen data", Toast.LENGTH_SHORT).show();

                progressDialog.cancel();

            }

        }


    }//Slutt på asynk classen



private void visSide(int side){
        if(side==0){
            adapter = new OversiktAdapter(getActivity(),MainActivity.JSONServerList);
            elevListView.setAdapter(adapter);
        }else
        {
            ArrayList<Elev> valgteTrinnListe= new ArrayList<>();

            for(int i=0;i<MainActivity.JSONServerList.size();i++){
                if(MainActivity.JSONServerList.get(i).getTrinn()==side){
                    valgteTrinnListe.add(MainActivity.JSONServerList.get(i));
                }
            }
            adapter = new OversiktAdapter(getActivity(),valgteTrinnListe);
            elevListView.setAdapter(adapter);
        }

}


}
