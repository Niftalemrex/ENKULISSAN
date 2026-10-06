package com.example.enkulissan.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;

public class DayMonthFragment extends Fragment {

    public DayMonthFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_month, container, false);

        GridView gridWeekdays = view.findViewById(R.id.grid_weekdays);
        GridView gridMonths = view.findViewById(R.id.grid_months);

        DayMonthAdapter weekdaysAdapter = new DayMonthAdapter(getContext(), true);
        DayMonthAdapter monthsAdapter = new DayMonthAdapter(getContext(), false);

        gridWeekdays.setAdapter(weekdaysAdapter);
        gridMonths.setAdapter(monthsAdapter);

        return view;
    }
}
