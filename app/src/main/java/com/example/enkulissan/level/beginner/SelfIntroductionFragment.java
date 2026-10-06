package com.example.enkulissan.level.beginner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.enkulissan.R;
import com.example.enkulissan.level.CurvedLineView;

public class SelfIntroductionFragment extends Fragment {

    private int stepCounter = 0;

    public SelfIntroductionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_self_introduction, container, false);
        LinearLayout roadmapContainer = view.findViewById(R.id.roadmap_containers);

        String[] topics = {
                "Words",
                "Sentences",
                "Present Tense",
                "Past Tense",
                "Future Tense"
        };

        boolean[] actives = {true, true, true, false, false}; // example active states

        int[] icons = {
                R.drawable.afric,         // replace with your actual drawables
                R.drawable.african,
                R.drawable.african,
                R.drawable.african,
                R.drawable.african
        };

        for (int i = 0; i < topics.length; i++) {
            boolean isLeft = (stepCounter % 2 == 0);
            addZigzagTopic(inflater, roadmapContainer, topics[i], icons[i], actives[i], isLeft);

            if (i < topics.length - 1) {
                CurvedLineView curvedLine = new CurvedLineView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 120);
                curvedLine.setLayoutParams(params);
                curvedLine.setLeftToRight(isLeft);

                if (i == 0) {
                    curvedLine.startGlowing();
                }

                roadmapContainer.addView(curvedLine);
            }
            stepCounter++;
        }

        return view;
    }

    private void addZigzagTopic(LayoutInflater inflater, LinearLayout container,
                                String title, int imageResId, boolean isActive, boolean isLeft) {

        View item = inflater.inflate(R.layout.item_roadmap_zigzag, container, false);

        ImageView icon = item.findViewById(R.id.icon_image);
        TextView topicTitle = item.findViewById(R.id.topic_title);
        Space spaceLeft = item.findViewById(R.id.space_left);
        Space spaceRight = item.findViewById(R.id.space_right);

        LinearLayout leftExtra = item.findViewById(R.id.left_extra_container);
        LinearLayout rightExtra = item.findViewById(R.id.right_extra_container);

        ImageView speechIconLeft = item.findViewById(R.id.speech_icon_left);
        ImageView seeIconLeft = item.findViewById(R.id.see_icon_left);
        ImageView readIconLeft = item.findViewById(R.id.read_icon_left);

        ImageView speechIconRight = item.findViewById(R.id.speech_icon_right);
        ImageView seeIconRight = item.findViewById(R.id.see_icon_right);
        ImageView readIconRight = item.findViewById(R.id.read_icon_right);

        icon.setImageResource(imageResId);
        topicTitle.setText(title);

        if (!isActive) {
            icon.setAlpha(0.3f);
            topicTitle.setAlpha(0.3f);
        }

        // Show small circles for all main topics listed here
        if ("Words".equals(title) || "Sentences".equals(title) ||
                "Present Tense".equals(title) || "Past Tense".equals(title) ||
                "Future Tense".equals(title)) {

            if (isLeft) {
                leftExtra.setVisibility(View.VISIBLE);
                rightExtra.setVisibility(View.GONE);

                speechIconLeft.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "Speech icon clicked (Left)", Toast.LENGTH_SHORT).show();
                });
                seeIconLeft.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "See icon clicked (Left)", Toast.LENGTH_SHORT).show();
                });
                readIconLeft.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "Read icon clicked (Left)", Toast.LENGTH_SHORT).show();
                });

            } else {
                rightExtra.setVisibility(View.VISIBLE);
                leftExtra.setVisibility(View.GONE);

                speechIconRight.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "Speech icon clicked (Right)", Toast.LENGTH_SHORT).show();
                });
                seeIconRight.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "See icon clicked (Right)", Toast.LENGTH_SHORT).show();
                });
                readIconRight.setOnClickListener(v -> {
                    Toast.makeText(getContext(), "Read icon clicked (Right)", Toast.LENGTH_SHORT).show();
                });
            }
        } else {
            leftExtra.setVisibility(View.GONE);
            rightExtra.setVisibility(View.GONE);
        }

        if (isLeft) {
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
                Toast.makeText(getContext(), "Clicked: " + title, Toast.LENGTH_SHORT).show();
                // TODO: Replace with real navigation to sub-topic fragment or content
            } else {
                Toast.makeText(getContext(), "Locked. Complete earlier topics.", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(item);
    }
}
