package com.boozehound.boozehound12;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Ryan on 4/1/2017.
 */

public class VenueActivity extends AppCompatActivity implements AsyncResponse {
    private ArrayList<GetOffers> offerlist;
    private ListView lvOffers;
    public String result;
    Spinner classSelect,daySelect;
    ArrayAdapter adapterSpinner, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(VenueActivity.this, this);
        taskRead.execute("http://ryandeal.me/getSpecials.php");

    }





    @Override
    public void processFinish(String s) {

        setContentView(R.layout.activity_venue);

        //btn to go to map view
        Button mapButton = (Button) findViewById(R.id.location);
        //btn to go back to list view
        Button backArrow = (Button) findViewById(R.id.arrowBack);
        //btn to call venue
        Button call = (Button) findViewById(R.id.phone);

        //Map button - open map page
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VenueActivity.this, MapActivity.class));
            }
        });
        //Map button - open map page

        //back arrow btn - open main page
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(VenueActivity.this, MainMenu.class));
            }
        });
        //press btn to call venue
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:8179753682"));
                startActivity(callIntent);
            }
        });


        //day of the week spinner
        Spinner spinner = (Spinner) findViewById(R.id.dayOfThWeek);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(this,
                //*****days_array in values/strings******
                R.array.days_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinadapter);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                spinner.setSelection(0);
                break;
            case Calendar.MONDAY:
                spinner.setSelection(1);
                break;
            case Calendar.TUESDAY:
                spinner.setSelection(2);
                break;
            case Calendar.WEDNESDAY:
                spinner.setSelection(3);
                break;
            case Calendar.THURSDAY:
                spinner.setSelection(4);
                break;
            case Calendar.FRIDAY:
                spinner.setSelection(5);
                break;
            case Calendar.SATURDAY:
                spinner.setSelection(6);
                break;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    SundaySpecials();
                } else if (position == 1) {
                    MondaySpecials();
                } else if (position == 2) {
                    TuesdaySpecials();
                } else if (position == 3) {
                    WednesdaySpecials();
                } else if (position == 4) {
                    ThursdaySpecials();
                } else if (position == 5) {
                    FridaySpecials();
                } else if (position == 6) {
                    SaturdaySpecials();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        offerlist = new JsonConverter<GetOffers>().toArrayList(s, GetOffers.class);

        BindDictionary<GetOffers> dict = new BindDictionary<GetOffers>();

/*
        dict.addStringField(R.id.DrinkID, new StringExtractor<GetOffers>() {
            @Override
            public String getStringValue(GetOffers offers, int position) {
                return "" + offers.DrinkID;
            }
        });*/
        dict.addStringField(R.id.Price, new StringExtractor<GetOffers>() {
            @Override
            public String getStringValue(GetOffers offers, int position) {
                return offers.Price;
            }
        });


        FunDapter<GetOffers> adapter = new FunDapter<>
                (VenueActivity.this, offerlist, R.layout.layout_venue_list, dict);

        lvOffers = (ListView) findViewById(R.id.lvSpecials);
        lvOffers.setAdapter(adapter);
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
//Methods to show specials for specific days

    public void SundaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello SUNDAY",Toast.LENGTH_SHORT).show();

    }
    public void MondaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello MONDAY",Toast.LENGTH_SHORT).show();

    }
    public void TuesdaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello TUESDAY",Toast.LENGTH_SHORT).show();

    }
    public void WednesdaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello vWEDNESDAY",Toast.LENGTH_SHORT).show();

    }
    public void ThursdaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello THURSDAY",Toast.LENGTH_SHORT).show();

    }
    public void FridaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello FRIDAY",Toast.LENGTH_SHORT).show();

    }
    public void SaturdaySpecials(){
        Toast.makeText(getApplicationContext(),"Hello SATURDAY",Toast.LENGTH_SHORT).show();

    }


}










