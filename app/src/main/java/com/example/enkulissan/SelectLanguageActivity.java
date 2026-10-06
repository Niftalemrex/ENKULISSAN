package com.example.enkulissan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.enkulissan.fragments.HomeActivity;

public class SelectLanguageActivity extends AppCompatActivity {

    private LinearLayout languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        languageList = findViewById(R.id.language_list);

        for (int i = 0; i < languageList.getChildCount(); i++) {
            View child = languageList.getChildAt(i);
            child.setOnClickListener(v -> {
                TextView languageText = (TextView) ((LinearLayout) v).getChildAt(1);
                String selectedLanguage = languageText.getText().toString();

                Toast.makeText(this, "Selected: " + selectedLanguage, Toast.LENGTH_SHORT).show();

                // Navigate to HomeActivity or your host activity
                Intent intent = new Intent(SelectLanguageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
