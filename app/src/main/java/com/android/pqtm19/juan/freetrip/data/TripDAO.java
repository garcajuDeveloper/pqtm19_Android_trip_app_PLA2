package com.android.pqtm19.juan.freetrip.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.pqtm19.juan.freetrip.data.entities.Trip;

import java.util.List;
import java.util.UUID;

@Dao
public interface TripDAO {

    @Query("Select * from trip_table")
    LiveData<List<Trip>> getTrips();

    @Insert
    void insertTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("Delete from trip_table where id=:uuid")
    void deleteTrip(UUID uuid);

    @Query("Delete from trip_table")
    void deleteAll();
}
