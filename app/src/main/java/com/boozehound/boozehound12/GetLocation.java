package com.boozehound.boozehound12;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryan on 4/29/2017.
 */

public class GetLocation {

    @SerializedName("VenueID")
    public int VenueID;
    @SerializedName("City")
    public String City;
    @SerializedName("State")
    public String State;
    @SerializedName("ZIP")
    public int  ZIP;
    @SerializedName("Longitude")
    public String Longitude;
    @SerializedName("Latitude")
    public String Latitude;
}
