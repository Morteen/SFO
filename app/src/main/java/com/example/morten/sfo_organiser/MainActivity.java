package com.example.morten.sfo_organiser;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Elev> elevListe;
    Elev e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








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

        elevListe= new ArrayList();
        elevListe.add(e1);
        elevListe.add(e2);
        elevListe.add(e3);

        ElevAdapter adapter = new ElevAdapter(getApplicationContext(), elevListe);
        startListView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main,menu);
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
}
