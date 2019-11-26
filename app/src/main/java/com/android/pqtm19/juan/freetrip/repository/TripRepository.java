package com.android.pqtm19.juan.freetrip.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.android.pqtm19.juan.freetrip.data.TripDAO;
import com.android.pqtm19.juan.freetrip.data.TripDataBase;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;

import java.util.List;
import java.util.UUID;

public class TripRepository {

    private TripDAO mTripDao;
    private LiveData<List<Trip>> mTripList;

    public TripRepository(Application application){
        TripDataBase dataBaseConector = TripDataBase.getDataBase(application);
        mTripDao = dataBaseConector.tripDAO();
        mTripList = mTripDao.getTrips();
    }

    public LiveData<List<Trip>> getTripList() {
        return mTripList;
    }

    public void updateTrip(Trip trip){ new UpdateTripAsyncTask(mTripDao).execute(trip); }

    public void insertTrip(Trip trip){ new InsertTripAsyncTask(mTripDao).execute(trip); }

    public void deleteTrip(UUID uuid) { new DeleteTripAsyncTask(mTripDao).execute(uuid); }

}
