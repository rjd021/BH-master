package com.boozehound.boozehound12;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.extractors.StringExtractor;

/**
 * Created by Ryan on 4/24/2017.
 */

public class ProfileActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BindDictionary<Product> dict = new BindDictionary<Product>();
        dict.addStringField(R.id.description,
                new StringExtractor<Product>() {

                    @Override
                    public String getStringValue(Product item, int position) {
                        return item.description;
                    }
                });
        }
}
