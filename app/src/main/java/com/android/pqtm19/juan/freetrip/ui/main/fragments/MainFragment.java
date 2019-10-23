package com.android.pqtm19.juan.freetrip.ui.main.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pqtm19.juan.freetrip.R;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.ui.main.adapters.TripListAdapter;
import com.android.pqtm19.juan.freetrip.ui.main.viewmodels.MainViewModel;

import java.util.List;
import java.util.UUID;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private final String CURRENT_UUID_ARG = "current id";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        boolean isTablet = getContext().getResources().getBoolean(R.bool.isTablet);
        final View view;

        if(!isTablet) {
            view = inflater.inflate(R.layout.main_fragment, container, false);
            displaySingleLayout(view);

        }else{
            view = inflater.inflate(R.layout.main_activity_tablet, container, false);
            displayMasterDetailLayout(view);
        }


        return view;
    }

    private void displaySingleLayout(View view){
        final View v = view;
        final TripListAdapter mTripAdapter = new TripListAdapter(getContext());
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_trip_list);
        mRecyclerView.setAdapter(mTripAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                mTripAdapter.setmTripList(trips);
            }
        });


        mTripAdapter.setOnItemClickListener(new TripListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UUID uuid) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(CURRENT_UUID_ARG, uuid);
                Navigation.findNavController(v).navigate(R.id.detailFragment, bundle);
            }
        });
    }

    private void displayMasterDetailLayout(View view) {
        final TripListAdapter mTripAdapter = new TripListAdapter(getContext());
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view_trip_list_tablet);
        mRecyclerView.setAdapter(mTripAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getAllTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                mTripAdapter.setmTripList(trips);
            }
        });


        mTripAdapter.setOnItemClickListener(new TripListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UUID uuid) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(CURRENT_UUID_ARG, uuid);
                NavHostFragment navHostFragment = (NavHostFragment)getChildFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_tablet);
                navHostFragment.getNavController().navigate(R.id.detailFragment, bundle);
            }
        });
    }
}
