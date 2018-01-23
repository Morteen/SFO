package com.example.morten.sfo_organiser;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOverSikt extends Fragment {

    ListView elevListView;
    private final int START=0;
    TextView trinn1,trinn2,trinn3,trinn4,visAlle,enFrem,enTilbake;
    OversiktAdapter adapter;
    private int sidetall =0;
    Dialog dialog;


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
           // new JsonStartTask().execute();
            visSide(START);

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




private void visSide(int side){

        if(side==0){
            adapter = new OversiktAdapter(getActivity(),MainActivity.JSONServerList);
            elevListView.setAdapter(adapter);
            elevListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {


                    Toast.makeText(getActivity(),"Onclick virker", Toast.LENGTH_SHORT).show();
                    visDialogBox(pos,MainActivity.JSONServerList);

                    return true;
                }


            });
        }else
        {
           final  ArrayList<Elev> valgteTrinnListe= new ArrayList<>();

            for(int i=0;i<MainActivity.JSONServerList.size();i++){
                if(MainActivity.JSONServerList.get(i).getTrinn()==side){
                    valgteTrinnListe.add(MainActivity.JSONServerList.get(i));
                }
            }

            adapter = new OversiktAdapter(getActivity(),valgteTrinnListe);
            elevListView.setAdapter(adapter);
            elevListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {


                    Toast.makeText(getActivity(),"Onclick virker", Toast.LENGTH_SHORT).show();
                    visDialogBox(pos,valgteTrinnListe);

                    return true;
                }


            });
        }

}
private void visDialogBox(int pos,ArrayList<Elev>temp){
    dialog = new Dialog(getActivity());

    dialog.setTitle(temp.get(pos).getFnavn()+" "+temp.get(pos).getEnavn());


    dialog.setContentView(R.layout.infodialog);
    Button ok = (Button) dialog.findViewById(R.id.tilbake);

    TextView tekst = (TextView) dialog.findViewById(R.id.infoText);
    tekst.setText(temp.get(pos).getInfo());
    dialog.show();
    ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
}



}



