package com.example.android.gridview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView myGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGrid = (GridView) findViewById(R.id.gridView);

        myGrid.setAdapter(new Adaptador(this));
        myGrid.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MyDialog.class);
        ViewHolder holder = (ViewHolder) view.getTag();
        Country temp = (Country) holder.myCountry.getTag();
        intent.putExtra("countryImage", temp.imgageId);
        intent.putExtra("countryName", temp.countryName);
        startActivity(intent);
    }
}
class Country{

    int imgageId;
    String countryName;
    Country(int imageId, String countryName){

        this.imgageId = imageId;
        this.countryName = countryName;
    }
}
class ViewHolder{

    ImageView myCountry;
    ViewHolder(View v){
        myCountry = (ImageView) v.findViewById(R.id.imageView);
    }
}
class Adaptador extends BaseAdapter{

    ArrayList<Country> list;
    Context context;
    Adaptador(Context context) // necesary for the resources
    {
        this.context = context;
        list = new ArrayList<Country>();
        Resources res = context.getResources();
        String[] tempCountryNames = res.getStringArray(R.array.country_names);
        int[] countryImages ={R.drawable.andorra_texture, R.drawable.argentina_texture, R.drawable.armenia_texture,
                R.drawable.austria_texture, R.drawable.belgium_texture, R.drawable.bolivia_texture, R.drawable.botswana_texture,
                R.drawable.brazil_texture, R.drawable.bulgaria_texture, R.drawable.cameroon_texture};
        for(int i = 0; i < 10; i++){
            Country tempCountry = new Country(countryImages[i], tempCountryNames[i]);
            list.add(tempCountry);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if(row == null){ //we are creating stuff for the first time

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, parent, false); //this method is the one that takes the xml file and get a java object
            holder = new ViewHolder(row);
            row.setTag(holder); //This is a method to store something inside a View Object

        } else{ //we are reusing stuff

            holder = (ViewHolder) row.getTag();
        }
        //now we put the values
        Country temp = list.get(position);
        holder.myCountry.setImageResource(temp.imgageId);
        holder.myCountry.setTag(temp);
        return row;
    }
}