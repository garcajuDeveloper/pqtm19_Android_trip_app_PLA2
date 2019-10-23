package com.android.pqtm19.juan.freetrip.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.pqtm19.juan.freetrip.data.entities.Trip;

import java.util.List;
import java.util.UUID;

@Dao
public interface TripDAO {

    @Query("Select * from trip_table")
    LiveData<List<Trip>> getTrips();

    @Query("Select * from trip_table where id=:id ")
    Trip getTripById(UUID id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();
}
