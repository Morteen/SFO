package com.example.morten.sfo_organiser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* Fragment start = new StartFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentTransaction replace = ft.replace(R.id.main, start);
        ft.addToBackStack(null);
        ft.commit();*/



        /*startListView = (ListView) findViewById(R.id.startList);
        tmListe = Turmaal.lagTurListe(result);
        if (myLocation != null) {
            Turmaal.sorterListe(tmListe, myLocation);
        }*/
        Elev e1,e2,e3;
        e1=new Elev();
        e1.setDag("mandag");
        e1.setSFO_dager(0,true);
        e1.setSFO_dager(4,true);
        e2= new Elev();
        e2.setSFO_dager(1,true);
        e2.setSFO_dager(2,true);

        e3= new Elev();
        e1.setFnavn("Morten");

        e2.setFnavn("Gry");
        e3.setFnavn("Maria");
        e3.setSFO_dager(3,true);
        e3.setSFO_dager(0,true);
        ListView startListView = (ListView)findViewById(R.id.startListView) ;

        ArrayList<Elev> elevListe = new ArrayList();
        elevListe.add(e1);
        elevListe.add(e2);

        elevListe.add(e3);
        ElevAdapter adapter = new ElevAdapter(getApplicationContext(), elevListe);
        startListView.setAdapter(adapter);

    }
}
