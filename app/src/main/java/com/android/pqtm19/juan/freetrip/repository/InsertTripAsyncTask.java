package com.android.pqtm19.juan.freetrip.repository;

import android.os.AsyncTask;

import com.android.pqtm19.juan.freetrip.data.TripDAO;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;

class InsertTripAsyncTask extends AsyncTask<Trip, Void, Void> {
    private TripDAO mAsyncTripDao;

    public InsertTripAsyncTask(TripDAO tripDAO) { mAsyncTripDao = tripDAO; }

    @Override
    protected Void doInBackground(Trip... params) {
        mAsyncTripDao.insertTrip(params[0]);
        return null;
    }
}
