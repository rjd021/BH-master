package com.boozehound.boozehound12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements AsyncResponse {
    private ArrayList<GetVenue> venuelist;
    private ListView lvVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button mapButton = (Button) findViewById(R.id.location);
        Button menuButton = (Button) findViewById(R.id.menu);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(MainMenu.this, this);
        taskRead.execute("http://ryandeal.me/getVenue.php");


        //Map button - open map page
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, MapActivity.class));
            }
        });
        //Map button - open map page
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, MapActivity.class));
            }
        });


    }

   public void OnClickVenue (View view) {

        Intent appInfo = new Intent(MainMenu.this, VenueActivity.class);
        startActivity(appInfo);
    }

    @Override
    public void processFinish(String s) {

        venuelist = new JsonConverter<GetVenue>().toArrayList(s, GetVenue.class);

        final BindDictionary<GetVenue> dict = new BindDictionary<GetVenue>();
    /*    dict.addStringField(R.id.VenueID, new StringExtractor<GetVenue>(){
            @Override
            public String getStringValue(GetVenue venue, int position){
                return "" + venue.VenueID;
            }
       });*/

        dict.addStringField(R.id.VenueName, new StringExtractor<GetVenue>() {
            @Override
            public String getStringValue(GetVenue venue, int position) {
                return venue.VenueName;
            }
        });
      /*  dict.addStringField(R.id.Phone, new StringExtractor<GetVenue>() {
            @Override
            public String getStringValue(GetVenue venue, int position) {
                return venue.Phone;
            }
        });*/


        FunDapter<GetVenue> adapter = new FunDapter<>
                (MainMenu.this, venuelist, R.layout.layout_main, dict);

        lvVenue = (ListView) findViewById(R.id.lvVenue);
        lvVenue.setAdapter(adapter);



        lvVenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GetVenue getVenue = venuelist.get(position);//using position int get correspondig data
                Log.d("log", getVenue.toString());

            }
        });

}







}










