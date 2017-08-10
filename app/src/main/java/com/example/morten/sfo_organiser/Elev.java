package com.example.morten.sfo_organiser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by morten on 02.08.2017.
 */

public class Elev {
    private String Fnavn;
    private String Enavn;
    private String tlfNr;
    private boolean[]SFO_dager= new boolean[5] ;

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
    }

    public String getMerknader() {
        return Merknader;
    }


    public void setMerknader(String merknader) {
        Merknader = merknader;
    }
    //Boolean mandag, tirsdag,onsdag,torsdag,fredag=false;
    //List<Boolean> Uka= Arrays.asList(mandag, tirsdag, onsdag, torsdag,fredag);
    List<String> Uka= Arrays.asList("mandag", "tirsdag", "onsdag", "torsdag","fredag");

    private String Merknader;


    public boolean getSFO_dager(int ukedagNr) {
        return SFO_dager[ukedagNr];
    }

    public void setSFO_dager(int ukedagNr,boolean SFO_dager) {
        this.SFO_dager[ukedagNr] = SFO_dager;
    }

    private List<String>Dager;


    public String getFnavn() {
        return Fnavn;
    }

    public void setFnavn(String fnavn) {
        Fnavn = fnavn;
    }

    public String getEnavn() {
        return Enavn;
    }

    public void setEnavn(String enavn) {
        Enavn = enavn;
    }



    public void setDag(String dag) {
        dag = dag;
    }
}
