package com.android.pqtm19.juan.freetrip.ui.main.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);

        return fragment;
    }

    private void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar mCalendar = Calendar.getInstance();

        int startingDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        int startingDayMonth = mCalendar.get(Calendar.MONTH);
        int startingDayYear = mCalendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), listener, startingDayYear, startingDayMonth, startingDay );
    }
}
