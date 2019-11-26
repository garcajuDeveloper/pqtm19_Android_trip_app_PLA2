package com.android.pqtm19.juan.freetrip.ui.main.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pqtm19.juan.freetrip.R;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;
import com.android.pqtm19.juan.freetrip.ui.main.utils.DatePickerFragment;
import com.android.pqtm19.juan.freetrip.ui.main.viewmodels.DetailViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetailFragment extends Fragment /*implements DatePickerFragment.Callbacks*/ {

    private final String CURRENT_UUID_ARG = "current id";
    private final String ARG_DIALOG_FRAGMENT = "date_picker_fragment";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private DetailViewModel mViewModel;
    private List<Trip> mTripList = new ArrayList<>();
    private TextView nameTrip, countryTrip, dateTrip;
    private ImageButton datePickerButton;

    public static DetailFragment newInstance() { return new DetailFragment(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        nameTrip = view.findViewById(R.id.text_input_edit_text_title);
        countryTrip = view.findViewById(R.id.text_input_edit_text_country);
        dateTrip = view.findViewById(R.id.edit_text_trip_date);

        datePickerButton = view.findViewById(R.id.button_date_picker);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_trip_menu_item:

                Trip mTrip = new Trip("", "");
                mTrip = updatedTrip(mTrip);

                try{
                    mTrip.setUUID((UUID) getArguments().getSerializable(CURRENT_UUID_ARG));
                    mViewModel.updateTrip(mTrip);

                }catch (NullPointerException exception){
                    mViewModel.insertTrip(mTrip);
                }

                break;

            case R.id.delete_trip_menu_item:

                mViewModel.deleteTrip((UUID) getArguments().getSerializable(CURRENT_UUID_ARG));

                break;
        }

        Navigation.findNavController(getActivity(),R.id.nav_host_fragment)
                .navigate(R.id.mainFragment);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setNewDate();

        if(getArguments() != null){
            final UUID currentUUID = (UUID) getArguments().getSerializable(CURRENT_UUID_ARG);

            mViewModel.getAllTrips().observe(this, trips -> {
                mTripList = trips;

                for(Trip trip : mTripList ){
                    if(currentUUID.toString().equals(trip.getUUID().toString())){
                       onShowTripInfo(trip);
                    }
                }
            });
        }
    }

    private void onShowTripInfo(Trip trip){
        nameTrip.setText(trip.getName());
        countryTrip.setText(trip.getCountry());
        dateTrip.setText(formatter.format(trip.getDate()));
    }

    private Trip updatedTrip(Trip trip){
        trip.setName(nameTrip.getText().toString());
        trip.setCountry(countryTrip.getText().toString());

        try {
            trip.setDate(formatter.parse(dateTrip.getText().toString()));
        }catch (ParseException excepcion){
            Toast.makeText(getContext(), "Can't create a date!", Toast.LENGTH_LONG).show();
        }

        return trip;
    }

    private void setNewDate(){ datePickerButton.setOnClickListener(view -> showDatePickerDialog()); }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            String dateText = day +"/" + (month + 1) + "/" + year;
            dateTrip.setText(dateText);
        });

        newFragment.show(getActivity().getSupportFragmentManager(), ARG_DIALOG_FRAGMENT);
    }
}
