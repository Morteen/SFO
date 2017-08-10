package com.example.morten.sfo_organiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;


public class RegNyElev extends Fragment {


EditText Fnavn,Enavn,tlf;
    CheckBox CBmandag,CBtirsdag,CBonsdag,CBtorsdag,Cbfredag;
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

      Elev nyElev= new Elev();

       View view=inflater.inflate(R.layout.fragment_reg_ny_elev, container, false);
        Fnavn=(EditText)view.findViewById(R.id.editFnavn);
        nyElev.setFnavn(Fnavn.getText().toString());
        Enavn=(EditText)view.findViewById(R.id.editEnavn);
        nyElev.setEnavn(Enavn.getText().toString());
        tlf=(EditText)view.findViewById(R.id.editTlf);
        nyElev.setTlfNr(tlf.getText().toString());


        CBmandag=(CheckBox)view.findViewById(R.id.checkBoxMandag);
        if(CBmandag.isChecked()){
       nyElev.setSFO_dager(0,true);
        }
        CBtirsdag=(CheckBox)view.findViewById(R.id.checkBoxTirsdag);
        if(CBtirsdag.isChecked()){
            nyElev.setSFO_dager(1,true);
        }
        CBonsdag=(CheckBox)view.findViewById(R.id.checkBoxOnsdag);
        if(CBonsdag.isChecked()){
            nyElev.setSFO_dager(2,true);
        }
        CBtorsdag=(CheckBox)view.findViewById(R.id.checkBoxTorsdag);
        if(CBtorsdag.isChecked()){
            nyElev.setSFO_dager(3,true);
        }
        Cbfredag=(CheckBox)view.findViewById(R.id.checkBoxFredag);
        if(Cbfredag.isChecked()){
            nyElev.setSFO_dager(4,true);
        }
        return view;
    }








}
