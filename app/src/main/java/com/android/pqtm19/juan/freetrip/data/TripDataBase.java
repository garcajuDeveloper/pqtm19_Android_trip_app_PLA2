package com.android.pqtm19.juan.freetrip.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.data.utils.UUIDTypeConverter;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
@TypeConverters(UUIDTypeConverter.class)
public abstract class TripDataBase extends RoomDatabase {

    public abstract TripDAO tripDAO();

    private static volatile TripDataBase DB_INSTANCE;

    public static TripDataBase getDataBase(final Context context){
        if( DB_INSTANCE == null) {
            synchronized (TripDataBase.class) {
                if( DB_INSTANCE == null) {
                    DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDataBase.class, "trip_database")
                            .addCallback(sRoomDataBaseCallback)
                            .build();
                }
            }
        }
        return DB_INSTANCE;
    }

    static RoomDatabase.Callback sRoomDataBaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateTripDataBaseASync(DB_INSTANCE).execute();
        }
    };

    private static class PopulateTripDataBaseASync extends AsyncTask<Void, Void, Void>{

        private final TripDAO mTripDAO;

        PopulateTripDataBaseASync(TripDataBase dataBase){
            mTripDAO = dataBase.tripDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mTripDAO.deleteAll();

            Trip tripOne = new Trip("Honey Moon at Punta Cana",
                    "Republica Dominicana");
            mTripDAO.insert(tripOne);

            Trip tripTwo = new Trip("Honey Moon at Rome", "Italy");
            mTripDAO.insert(tripTwo);

            Trip tripThree = new Trip("Hollydays at Seville", "Spain");
            mTripDAO.insert(tripThree);

            Trip tripFour = new Trip("Marriage Aniversary in Andorra La Vella",
                    "Andorra");
            mTripDAO.insert(tripFour);

            Trip tripFive = new Trip("Scape days in Madrid", "Spain");
            mTripDAO.insert(tripFive);
            return null;
        }
    }
}
