package com.example.enkulissan.tools;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class TranslateFragment extends Fragment {

    private EditText etInput;
    private Button btnTranslate;
    private TextView tvTranslated;
    private Translator translator;

    public TranslateFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        etInput = view.findViewById(R.id.et_input);
        btnTranslate = view.findViewById(R.id.btn_translate);
        tvTranslated = view.findViewById(R.id.tv_translated);

        // Disable translate button until model is downloaded
        btnTranslate.setEnabled(false);

        // Setup translator options: English -> Amharic
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage("am")  // Use "am" for Amharic
                .build();

        translator = Translation.getClient(options);

        // Download model with relaxed conditions (allow download on any network)
        DownloadConditions conditions = new DownloadConditions.Builder()
                .build();

        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(getContext(), "Amharic model ready", Toast.LENGTH_SHORT).show();
                    btnTranslate.setEnabled(true);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Model download failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    btnTranslate.setEnabled(false);
                });

        btnTranslate.setOnClickListener(v -> {
            String inputText = etInput.getText().toString().trim();
            if (TextUtils.isEmpty(inputText)) {
                etInput.setError("Please enter text");
                return;
            }

            // Translate only if model ready
            translator.translate(inputText)
                    .addOnSuccessListener(translatedText -> tvTranslated.setText(translatedText))
                    .addOnFailureListener(e -> tvTranslated.setText("Error: " + e.getMessage()));
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (translator != null) {
            translator.close();
        }
    }
}
