package com.example.morten.sfo_organiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class RegNyElev extends Fragment {

Button submit;
    EditText Fnavn, Enavn, tlf;
    CheckBox CBmandag, CBtirsdag, CBonsdag, CBtorsdag, Cbfredag;
    Elev nyElev;

    public RegNyElev() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      nyElev = new Elev();

        View view = inflater.inflate(R.layout.fragment_reg_ny_elev, container, false);

       submit=(Button) view.findViewById(R.id.submit);
        Fnavn = (EditText) view.findViewById(R.id.editFnavn);
        Enavn = (EditText) view.findViewById(R.id.editEnavn);
        tlf = (EditText) view.findViewById(R.id.editTlf);

        CBmandag = (CheckBox) view.findViewById(R.id.checkBoxMandag);
        CBtirsdag = (CheckBox) view.findViewById(R.id.checkBoxTirsdag);
        CBonsdag = (CheckBox) view.findViewById(R.id.checkBoxOnsdag);
        CBtorsdag = (CheckBox) view.findViewById(R.id.checkBoxTorsdag);
        Cbfredag = (CheckBox) view.findViewById(R.id.checkBoxFredag);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nyElev.setFnavn(Fnavn.getText().toString());
                nyElev.setEnavn(Enavn.getText().toString());
                nyElev.setTlfNr(tlf.getText().toString());
                if (CBmandag.isChecked()) {
                    nyElev.setSFO_dager(0, true);
                }
                if (CBtirsdag.isChecked()) {
                    nyElev.setSFO_dager(1, true);
                }
                if (CBonsdag.isChecked()) {
                    nyElev.setSFO_dager(2, true);
                }
                if (CBtorsdag.isChecked()) {
                    nyElev.setSFO_dager(3, true);
                }
                if (Cbfredag.isChecked()) {
                    nyElev.setSFO_dager(4, true);
                }

                MainActivity.elevListe.add(nyElev);
                Toast.makeText(getActivity(),nyElev.getFnavn()+""+MainActivity.elevListe.size(),Toast.LENGTH_LONG).show();
                Intent intent = new  Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }


}
