package com.android.pqtm19.juan.freetrip.ui.main.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pqtm19.juan.freetrip.R;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.ui.main.viewmodels.DetailViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetailFragment extends Fragment {

    private final String CURRENT_UUID_ARG = "current id";
    private DetailViewModel mViewModel;
    private List<Trip> mTripList = new ArrayList<>();
    private TextView nameTrip, countryTrip;

    public static DetailFragment newInstance() { return new DetailFragment(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        nameTrip = view.findViewById(R.id.textView_trip_detail_name);
        countryTrip = view.findViewById(R.id.textView_trip_detail_country);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            final UUID currentUUID = (UUID) getArguments().getSerializable(CURRENT_UUID_ARG);

            mViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
                @Override
                public void onChanged(List<Trip> trips) {
                    mTripList = trips;

                    for(Trip trip : mTripList ){
                        if(currentUUID.toString().equals(trip.getUUID().toString())){
                            nameTrip.setText(trip.getName());
                            countryTrip.setText(trip.getCountry());
                            Snackbar.make(view, currentUUID.toString(),
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            });

        }
    }
}
