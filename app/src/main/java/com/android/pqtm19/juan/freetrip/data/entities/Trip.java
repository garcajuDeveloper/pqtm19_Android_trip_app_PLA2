package com.android.pqtm19.juan.freetrip.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Trip(String name, String country) {
        this.mUUID = UUID.randomUUID();
        this.mName = name;
        this.mCountry = country;
    }

    @NonNull
    public UUID getUUID() { return mUUID; }

    public void setUUID(@NonNull UUID mUUID) { this.mUUID = mUUID; }

    @NonNull
    public String getName() { return mName; }

    public void setName(@NonNull String mName) { this.mName = mName; }

    public String getCountry() { return mCountry; }

    public void setCountry(String mCountry) { this.mCountry = mCountry; }
}
