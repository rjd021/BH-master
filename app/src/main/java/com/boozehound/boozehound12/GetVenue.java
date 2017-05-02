package com.boozehound.boozehound12;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryan on 4/29/2017.
 */

public class GetVenue {
    @SerializedName("VenueID")
    public int VenueID;
    @SerializedName("VenueName")
    public String VenueName;
    @SerializedName("Phone")
    public String Phone;
}
