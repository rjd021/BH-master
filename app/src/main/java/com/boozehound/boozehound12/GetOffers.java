package com.boozehound.boozehound12;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryan on 4/29/2017.
 */

public class GetOffers {

    @SerializedName("HappyHourID")
    public int HappyHourID;
    @SerializedName("DrinkID")
    public String DrinkID;
    @SerializedName("Price")
    public String Price;
    @SerializedName("VenueName")
    public String VenueName;
}
