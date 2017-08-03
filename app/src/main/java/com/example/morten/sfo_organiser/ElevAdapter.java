package com.example.morten.sfo_organiser;

import android.content.Context;
import android.graphics.Color;
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
    List<String> Uka= Arrays.asList("mandag", "tirsdag", "onsdag", "torsdag","fredag");




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


            viewHolder.tvElevFornavn = (TextView) convertView.findViewById(R.id.fNavn);
            viewHolder.mandag = (TextView) convertView.findViewById(R.id.mandag);
            viewHolder.tirsdag = (TextView) convertView.findViewById(R.id.tTirsdag);
            viewHolder.onsdag = (TextView) convertView.findViewById(R.id.onsdag);
            viewHolder.torsdag = (TextView) convertView.findViewById(R.id.torsdag);
            viewHolder.fredag = (TextView) convertView.findViewById(R.id.fredag);




            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Elev currentElev = Elever.get(position);
        //Setter verdien til  turmaal objektet  i denne posisjonen i riktig textview
        viewHolder.tvElevFornavn.setText(currentElev.getFnavn());
        if( currentElev.getDag().equals(Uka.get(0))){
        viewHolder.mandag.setBackgroundColor(Color.GREEN);
        }
        if( currentElev.getDag().equals(Uka.get(1))){
            viewHolder.tirsdag.setBackgroundColor(Color.GREEN);
        }
        if( currentElev.getDag().equals(Uka.get(2))){
            viewHolder.onsdag.setBackgroundColor(Color.GREEN);
        }
        if( currentElev.getDag().equals(Uka.get(3))){
            viewHolder.torsdag.setBackgroundColor(Color.GREEN);
        }
        if( currentElev.getDag().equals(Uka.get(4))){
            viewHolder.fredag.setBackgroundColor(Color.GREEN);
        }

        //viewHolder.tvStartHoyde.setText(Integer.toString(currentMaal.getHoyde()) + " meter");



        return convertView;
    }



    private static class ViewHolder {
        public TextView tvElevFornavn;
        public TextView mandag;
        public TextView tirsdag;
        public TextView onsdag;
        public TextView torsdag;
        public TextView fredag;



    }

}
