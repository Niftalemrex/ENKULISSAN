package com.example.enkulissan.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;

public class NumberFragment extends Fragment {

    public NumberFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        GridView gridView = view.findViewById(R.id.grid_number);

        NumberAdapter adapter = new NumberAdapter(getContext());
        gridView.setAdapter(adapter);

        return view;
    }
}
