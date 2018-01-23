package com.example.morten.sfo_organiser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by morten on 02.08.2017.
 */

public class ElevAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Elev> Elever;
    LayoutInflater mInflater;
    List<String> Uka = Arrays.asList("mandag", "tirsdag", "onsdag", "torsdag", "fredag");


    public ElevAdapter(Context mContext, ArrayList<Elev> Elever) {
        this.mContext = mContext;
        this.Elever = Elever;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Elever.size();
    }

    @Override
    //Finner Elver basert på posisjon i listen
    public Object getItem(int position) {
        return Elever.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {

            //XML fil-navnet
            convertView = mInflater.inflate(R.layout.elevliste, null);
            viewHolder = new ViewHolder();


            viewHolder.tvfNavn = (TextView) convertView.findViewById(R.id.fNavn);
            //viewHolder.tveNavn = (TextView) convertView.findViewById(R.id.eNavn);
            //viewHolder.tvKlasse = (TextView) convertView.findViewById(R.id.klasse);
            viewHolder.tvTrinn = (TextView) convertView.findViewById(R.id.trinn);




            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Elev currentElev = Elever.get(position);
        //Setter verdien til  turmaal objektet  i denne posisjonen i riktig textview
        viewHolder.tvfNavn.setText(currentElev.getFnavn()+" "+currentElev.getEnavn());
        //viewHolder.tveNavn.setText(currentElev.getEnavn());
        viewHolder.tvTrinn.setText(Integer.toString(currentElev.getTrinn())+" "+currentElev.getKlasse());
        //viewHolder.tvKlasse.setText();




        return convertView;
    }


    private static class ViewHolder {
        public TextView tvfNavn;
        public TextView tveNavn;
        public TextView tvTrinn;
        public TextView tvKlasse;



    }

}
