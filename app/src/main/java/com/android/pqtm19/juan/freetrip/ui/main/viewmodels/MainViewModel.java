package com.android.pqtm19.juan.freetrip.ui.main.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.pqtm19.juan.freetrip.repository.TripRepository;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;

import java.util.List;
import java.util.UUID;

public class MainViewModel extends AndroidViewModel {

    private TripRepository mTripRepository;
    private LiveData<List<Trip>> mTripList;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mTripRepository = new TripRepository(application);
        mTripList = mTripRepository.getTripList();
    }

    public LiveData<List<Trip>> getAllTrips(){ return mTripList; }

    public void deleteTrip(UUID uuid){ mTripRepository.deleteTrip(uuid); }
}
