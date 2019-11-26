package com.android.pqtm19.juan.freetrip.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID mUUID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "country")
    private String mCountry;

    @NonNull
    @ColumnInfo(name = "date")
    private Date mDate;

    public Trip(String name, String country) {
        this.mUUID = UUID.randomUUID();
        this.mName = name;
        this.mCountry = country;
        this.mDate = new Date();
    }

    @NonNull
    public UUID getUUID() { return mUUID; }

    public void setUUID(@NonNull UUID mUUID) { this.mUUID = mUUID; }

    @NonNull
    public String getName() { return mName; }

    public void setName(@NonNull String mName) { this.mName = mName; }

    public String getCountry() { return mCountry; }

    public void setCountry(String mCountry) { this.mCountry = mCountry; }

    @NonNull
    public Date getDate() { return mDate; }

    public void setDate(@NonNull Date date) { this.mDate = date; }
}
