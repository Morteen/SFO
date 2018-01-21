package com.example.morten.sfo_organiser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by morten on 02.08.2017.
 */

public class Elev {
    private String Fnavn;
    private String Enavn;
    private String tlf;
    private String info;
    private String klasse;
    private int trinn;
    int id;
    private boolean added;
    private boolean[]SFO_dager= new boolean[5] ;

    public Elev() {

    }

    public Elev(String fnavn, String enavn) {
        Fnavn = fnavn;
        Enavn = enavn;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public int getTrinn() {
        return trinn;
    }

    public void setTrinn(int trinn) {
        this.trinn = trinn;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }



    public String getTlfNr() {
        return tlf;
    }

    public void setTlfNr(String tlf) {
        this.tlf = tlf;
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

    static final String TABELL_NAVN = "Elever";
    static final String KOL_NAVN_fnavn = "fname";
    static final String KOL_NAVN_enavn = "ename";
    static final String KOL_NAVN_info = "info";
    static final String KOL_NAVN_klasse = "klasse";
    static final String KOL_NAVN_trinn = "trinn";
    static  final String KOL_NAVN_tlf="tlf";
    static final String  KOL_NAVN_ID="id";

    // Konstruktør som bygger Elevobjekt basert på et JSONObject-objekt
    public Elev(JSONObject jsonElev) throws JSONException {
        this.setFnavn(jsonElev.optString(KOL_NAVN_fnavn));
        this.setEnavn(jsonElev.optString(KOL_NAVN_enavn));
        this.setTlfNr(jsonElev.optString(KOL_NAVN_tlf));
        this.setInfo(jsonElev.optString(KOL_NAVN_info));
        this.setTrinn(Integer.valueOf(jsonElev.optString(KOL_NAVN_trinn)));
        this.setKlasse(jsonElev.optString(KOL_NAVN_klasse));
        this.id=jsonElev.optInt(KOL_NAVN_ID);




    }



    /***
     *  Metode som lager en ArrayList med Elev-objekter basert på en streng med JSONdata
     * @param jsonElevString
     * @return En liste med turmaål
     * @throws JSONException
     * @throws NullPointerException
     */
    public static ArrayList<Elev> lagElevListe(String jsonElevString)
            throws JSONException, NullPointerException {
        ArrayList<Elev> elevListe = new ArrayList<Elev>();
        JSONObject jsonData = new JSONObject(jsonElevString);
        JSONArray jsonElevTabell = jsonData.optJSONArray(TABELL_NAVN);
        for (int i = 0; i < jsonElevTabell.length(); i++) {
            JSONObject jsonElev = (JSONObject) jsonElevTabell.get(i);
            Elev thisElev = new Elev(jsonElev);
            elevListe.add(thisElev);
        }
        return elevListe;
    }



    /***
     * Lager et Json objekt fra et Elev objekt
     * @return SONObject
     */
    public JSONObject toJSONObject() {
        JSONObject jsonElev = new JSONObject();
        try {
            jsonElev.put(KOL_NAVN_fnavn, this.getFnavn());
            jsonElev.put(KOL_NAVN_enavn, this.getEnavn());
            jsonElev.put(KOL_NAVN_info, this.getInfo());
            jsonElev.put(KOL_NAVN_klasse, this.getKlasse());
            jsonElev.put(KOL_NAVN_trinn, this.getTrinn());
            jsonElev.put(KOL_NAVN_tlf, this.getTlfNr());



        } catch (JSONException e) {
            return null;
        }
        return jsonElev;
    }


   /* public Elev(JSONObject jsonElev) throws JSONException {
        this.setFnavn(jsonElev.optString(KOL_NAVN_fnavn));
        this.setEnavn(jsonElev.optString(KOL_NAVN_enavn));
        this.setTlfNr(jsonElev.optString(KOL_NAVN_tlf));
        this.setTrinn(jsonElev.optInt(KOL_NAVN_trinn));
        this.setKlasse(jsonElev.getString(KOL_NAVN_klasse));
        this.setInfo(jsonElev.getString(KOL_NAVN_info));





    }*/

public String  toString(){
    return this.getFnavn()+" "+this.getEnavn();
}


}




