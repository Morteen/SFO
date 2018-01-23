package com.example.morten.sfo_organiser;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSjekkInn extends Fragment {

    ElevAdapter adapter;

    ListView sjekkInnListView;
    private TextView sjekkInnTilbake,sjekkInnVisalle,sjekkInnTrinn1,sjekkInnTrinn2,sjekkInnTrinn3,sjekkInnTrinn4,sjekkInnEnFrem;
 private int sidetall=0;

    public FragmentSjekkInn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_sjekk_inn, container, false);
        //Kode her
        sjekkInnListView = (ListView) view.findViewById(R.id.sjekkInnListView);
        setTextview(view);
        new setlistAsynk().execute();
        sjekkInnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {


                Toast.makeText(getActivity(),"Onclick virker", Toast.LENGTH_SHORT).show();

                return true;
            }


        });




        return view;
    }


    class setlistAsynk extends AsyncTask<String, Void, String> {


        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public setlistAsynk() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {


            if (MainActivity.JSONServerList.size() < 1) {
                return "Får ikke lastet data";
            } else {
                return null;
            }


        }


        @Override
        protected void onPostExecute(String result) {


            if (result != null) {

                Toast.makeText(getActivity(), result + MainActivity.JSONServerList.size(), Toast.LENGTH_SHORT).show();
                adapter = new ElevAdapter(getActivity(), MainActivity.JSONServerList);
                sjekkInnListView.setAdapter(adapter);

            } else {

                Toast.makeText(getActivity(), "Alt ok", Toast.LENGTH_SHORT).show();

                adapter = new ElevAdapter(getActivity(), MainActivity.JSONServerList);
                sjekkInnListView.setAdapter(adapter);

            }

        }


    }//Slutt på asynk classen

    private void setTextview(View view){
        sjekkInnTilbake=(TextView)view.findViewById(R.id.sjekkInnTilbake);
        sjekkInnVisalle=(TextView)view.findViewById(R.id.sjekkInnVisalle);
        sjekkInnTrinn1=(TextView)view.findViewById(R.id.sjekkInnTrinn1);
        sjekkInnTrinn2=(TextView)view.findViewById(R.id.sjekkInnTrinn2);
        sjekkInnTrinn3=(TextView)view.findViewById(R.id.sjekkInnTrinn3);
        sjekkInnTrinn4=(TextView)view.findViewById(R.id.sjekkInnTrinn4);
        sjekkInnEnFrem=(TextView)view.findViewById(R.id.sjekkInnEnFrem);


        //Legger på click listnere for å gi sidetall
        sjekkInnTilbake.setOnClickListener(new View.OnClickListener() {
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
        sjekkInnVisalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=0;
                visSide(sidetall);
            }



        });


        sjekkInnTrinn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=1;
                visSide(sidetall);
            }



        });
        sjekkInnTrinn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=2;
                visSide(sidetall);
            }
        });
        sjekkInnTrinn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=3;
                visSide(sidetall);
            }
        });

        sjekkInnTrinn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidetall=4;
                visSide(sidetall);

            }
        });

        sjekkInnEnFrem.setOnClickListener(new View.OnClickListener() {
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




    }


    private void visSide(int side) {
        if (side == 0) {
            adapter = new ElevAdapter(getActivity(), MainActivity.JSONServerList);
            sjekkInnListView.setAdapter(adapter);
            sjekkInnListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"Onclick virker", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            ArrayList<Elev> valgteTrinnListe = new ArrayList<>();

            for (int i = 0; i < MainActivity.JSONServerList.size(); i++) {
                if (MainActivity.JSONServerList.get(i).getTrinn() == side) {
                    valgteTrinnListe.add(MainActivity.JSONServerList.get(i));
                }
            }
            adapter = new ElevAdapter(getActivity(), valgteTrinnListe);
            sjekkInnListView.setAdapter(adapter);
            sjekkInnListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {


                    Toast.makeText(getActivity(),"Onclick virker", Toast.LENGTH_SHORT).show();
                    //visDialogBox(pos,MainActivity.JSONServerList);

                    return true;
                }


            });




        }
    }

}