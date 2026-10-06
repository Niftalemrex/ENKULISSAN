package com.example.enkulissan.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;
import com.example.enkulissan.level.beginner.SelfIntroductionFragment;

public class BeginnerFragment extends Fragment {

    private int stepCounter = 0;

    public BeginnerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beginner, container, false);
        LinearLayout roadmapContainer = view.findViewById(R.id.roadmap_container);

        String[] titles = {
                "Self-introduction", "Greetings & Farewells", "Numbers & Dates",
                "Family & Friends", "Daily Activities", "Basic Needs",
                "Colors & Shapes", "Simple Questions"
        };

        int[] icons = {
                R.drawable.african, R.drawable.african, R.drawable.ic_calendar_week,
                R.drawable.ic_like_filled, R.drawable.ic_streak, R.drawable.ic_novice_badge,
                R.drawable.icon, R.drawable.ic_xp
        };

        boolean[] actives = {true, true, true, true, false, false, false, false};

        for (int i = 0; i < titles.length; i++) {
            addZigzagTopic(inflater, roadmapContainer, titles[i], icons[i], actives[i]);

            // Add curved line between topics except last
            if (i < titles.length - 1) {
                CurvedLineView curvedLine = new CurvedLineView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 120);
                curvedLine.setLayoutParams(params);

                curvedLine.setLeftToRight(i % 2 == 0);

                if (i == 0) {
                    curvedLine.startGlowing();
                }

                roadmapContainer.addView(curvedLine);
            }
        }

        return view;
    }

    private void addZigzagTopic(LayoutInflater inflater, LinearLayout container,
                                String title, int imageResId, boolean isActive) {

        View item = inflater.inflate(R.layout.item_roadmap_zigzag, container, false);

        ImageView icon = item.findViewById(R.id.icon_image);
        TextView topicTitle = item.findViewById(R.id.topic_title);
        Space spaceLeft = item.findViewById(R.id.space_left);
        Space spaceRight = item.findViewById(R.id.space_right);

        icon.setImageResource(imageResId);
        topicTitle.setText(title);

        if (!isActive) {
            icon.setAlpha(0.3f);
            topicTitle.setAlpha(0.3f);
        }

        if (stepCounter % 2 == 0) {
            spaceLeft.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f));
            spaceRight.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f));
        } else {
            spaceLeft.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f));
            spaceRight.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f));
        }

        item.setOnClickListener(v -> {
            if (isActive) {
                if ("Self-introduction".equals(title)) {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new SelfIntroductionFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Opening: " + title, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Locked. Complete earlier topics.", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(item);
        stepCounter++;
    }
}
