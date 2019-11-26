package com.android.pqtm19.juan.freetrip.ui.main.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pqtm19.juan.freetrip.R;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.ui.main.adapters.TripListAdapter;
import com.android.pqtm19.juan.freetrip.ui.main.viewmodels.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private final String CURRENT_UUID_ARG = "current id";
    private FloatingActionButton fabAddTrip;

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
            setFabAddTrip(view);
            displaySingleLayout(view);

        }else{
            view = inflater.inflate(R.layout.main_activity_tablet, container, false);
            setFabAddTrip(view);
            displayMasterDetailLayout(view);
        }


        return view;
    }

    private void setFabAddTrip(View view){
        fabAddTrip = view.findViewById(R.id.fab_add_trip);
        fabAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.detailFragment);
            }
        });
    }

    private void displaySingleLayout(View view){
        final View v = view;
        final TripListAdapter mTripAdapter = new TripListAdapter(getContext());
        final RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_trip_list);
        mRecyclerView.setAdapter(mTripAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getAllTrips().observe(this, trips -> mTripAdapter.setmTripList(trips));

        mTripAdapter.setOnItemClickListener(uuid -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CURRENT_UUID_ARG, uuid);
            Navigation.findNavController(v).navigate(R.id.detailFragment, bundle);
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
                    case ItemTouchHelper.RIGHT:
                        mViewModel.deleteTrip(mTripAdapter.getUUIDTripAt(viewHolder.getAdapterPosition()));
                        mTripAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        Snackbar.make(mRecyclerView, "Trip deleted!", Snackbar.LENGTH_LONG).show();
                        break;
                }
            }
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState,
                                    boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, mRecyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();
                super.onChildDraw(c, mRecyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
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
