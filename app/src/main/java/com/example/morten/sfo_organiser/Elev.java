package com.example.morten.sfo_organiser;

import java.util.List;

/**
 * Created by morten on 02.08.2017.
 */

public class Elev {
    private String Fnavn;
    private String Enavn;
    private String Dag;



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

    public String getDag() {
        return Dag;
    }

    public void setDag(String dag) {
        dag = dag;
    }
}
