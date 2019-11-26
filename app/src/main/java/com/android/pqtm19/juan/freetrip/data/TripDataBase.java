package com.android.pqtm19.juan.freetrip.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.data.utils.DateConverter;
import com.android.pqtm19.juan.freetrip.data.utils.UUIDTypeConverter;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
@TypeConverters({UUIDTypeConverter.class, DateConverter.class})
public abstract class TripDataBase extends RoomDatabase {

    public abstract TripDAO tripDAO();

    private static volatile TripDataBase DB_INSTANCE;

    public static TripDataBase getDataBase(final Context context){
        if( DB_INSTANCE == null) {
            synchronized (TripDataBase.class) {
                if( DB_INSTANCE == null) {
                    DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDataBase.class, "trip_database")
                            .build();
                }
            }
        }
        return DB_INSTANCE;
    }
}
