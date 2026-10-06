package com.example.enkulissan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;
import com.example.enkulissan.level.BeginnerFragment;
import com.example.enkulissan.level.IntermediateFragment;
import com.example.enkulissan.level.AdvancedFragment;
import com.example.enkulissan.tools.AlphabetFragment;
import com.example.enkulissan.tools.NumberFragment;
import com.example.enkulissan.tools.TranslateFragment;
import com.example.enkulissan.tools.DayMonthFragment;  // import your new fragment

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find Buttons inside CardViews
        Button btnBeginner = view.findViewById(R.id.btn_beginner);
        Button btnIntermediate = view.findViewById(R.id.btn_intermediate);
        Button btnAdvanced = view.findViewById(R.id.btn_advanced);

        CardView cardAlphabet = view.findViewById(R.id.card_alphabet);
        CardView cardNumber = view.findViewById(R.id.card_number);
        CardView cardTranslate = view.findViewById(R.id.card_translate);

        // New: DayMonth Card
        CardView cardDayMonth = view.findViewById(R.id.card_day_month);

        // Set click listeners on buttons
        btnBeginner.setOnClickListener(v -> navigateTo(new BeginnerFragment()));
        btnIntermediate.setOnClickListener(v -> navigateTo(new IntermediateFragment()));
        btnAdvanced.setOnClickListener(v -> navigateTo(new AdvancedFragment()));

        // Set click listeners on tool cards
        cardAlphabet.setOnClickListener(v -> navigateTo(new AlphabetFragment()));
        cardNumber.setOnClickListener(v -> navigateTo(new NumberFragment()));
        cardTranslate.setOnClickListener(v -> navigateTo(new TranslateFragment()));

        // Set click listener on DayMonth card
        cardDayMonth.setOnClickListener(v -> navigateTo(new DayMonthFragment()));

        return view;
    }

    private void navigateTo(Fragment fragment) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
