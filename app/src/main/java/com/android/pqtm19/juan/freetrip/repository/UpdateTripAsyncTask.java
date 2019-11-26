package com.android.pqtm19.juan.freetrip.repository;

import android.os.AsyncTask;

import com.android.pqtm19.juan.freetrip.data.TripDAO;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;

class UpdateTripAsyncTask extends AsyncTask<Trip, Void, Void> {

    private TripDAO mAsyncTripDao;

    public UpdateTripAsyncTask(TripDAO tripDAO) { mAsyncTripDao = tripDAO; }

    @Override
    protected Void doInBackground(Trip... params) {
        mAsyncTripDao.updateTrip(params[0]);
        return null;
    }
}
