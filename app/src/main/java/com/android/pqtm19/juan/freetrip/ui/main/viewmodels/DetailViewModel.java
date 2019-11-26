package com.android.pqtm19.juan.freetrip.ui.main.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.repository.TripRepository;

import java.util.List;
import java.util.UUID;


public class DetailViewModel extends AndroidViewModel {

    private TripRepository mTripRepository;
    private LiveData<List<Trip>> mAlltrips;

    public DetailViewModel(@NonNull Application application){
        super(application);
        mTripRepository = new TripRepository(application);
        mAlltrips = mTripRepository.getTripList();
    }

    public LiveData<List<Trip>> getAllTrips() { return mAlltrips; }

    public void updateTrip(Trip trip){ mTripRepository.updateTrip(trip); }

    public void insertTrip(Trip trip){ mTripRepository.insertTrip(trip); }

    public void deleteTrip(UUID uuid){ mTripRepository.deleteTrip(uuid); }
}
