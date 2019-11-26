package com.android.pqtm19.juan.freetrip.repository;

import android.os.AsyncTask;

import com.android.pqtm19.juan.freetrip.data.TripDAO;

import java.util.UUID;

class DeleteTripAsyncTask extends AsyncTask<UUID, Void, Void> {
    private TripDAO mAsyncTripDao;

    public DeleteTripAsyncTask(TripDAO tripDao) { mAsyncTripDao = tripDao; }

    @Override
    protected Void doInBackground(UUID... params) {
        mAsyncTripDao.deleteTrip(params[0]);
        return null;
    }
}
