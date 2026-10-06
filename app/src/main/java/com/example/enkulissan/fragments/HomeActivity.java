package com.example.enkulissan.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;

public class HomeActivity extends AppCompatActivity {

    // Tab layout containers
    LinearLayout tabHome, tabDictionary, tabVideo, tabProfile, tabGame;

    // Tab labels
    TextView tabHomeLabel, tabDictionaryLabel, tabVideoLabel, tabProfileLabel, tabGameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize layouts
        tabHome = findViewById(R.id.tab_home);
        tabDictionary = findViewById(R.id.tab_dictionary);
        tabVideo = findViewById(R.id.tab_video);
        tabProfile = findViewById(R.id.tab_profile);
        tabGame = findViewById(R.id.tab_game);

        // Initialize labels
        tabHomeLabel = findViewById(R.id.tab_home_label);
        tabDictionaryLabel = findViewById(R.id.tab_dictionary_label);
        tabVideoLabel = findViewById(R.id.tab_video_label);
        tabProfileLabel = findViewById(R.id.tab_profile_label);
        tabGameLabel = findViewById(R.id.tab_game_label);

        // Load default tab
        loadFragment(new HomeFragment());
        highlightTab(tabHomeLabel);

        // Tab click listeners
        tabHome.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
            highlightTab(tabHomeLabel);
        });

        tabDictionary.setOnClickListener(v -> {
            loadFragment(new DictionaryFragment());
            highlightTab(tabDictionaryLabel);
        });

        tabVideo.setOnClickListener(v -> {
            loadFragment(new VideoFragment());
            highlightTab(tabVideoLabel);
        });

        tabProfile.setOnClickListener(v -> {
            loadFragment(new ProfileFragment());
            highlightTab(tabProfileLabel);
        });

        tabGame.setOnClickListener(v -> {
            loadFragment(new GameFragment());
            highlightTab(tabGameLabel);
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void highlightTab(TextView selectedTabLabel) {
        // Reset all tab label colors
        tabHomeLabel.setTextColor(Color.GRAY);
        tabDictionaryLabel.setTextColor(Color.GRAY);
        tabVideoLabel.setTextColor(Color.GRAY);
        tabProfileLabel.setTextColor(Color.GRAY);
        tabGameLabel.setTextColor(Color.GRAY);

        // Highlight the selected tab
        selectedTabLabel.setTextColor(Color.BLUE);
    }
}
