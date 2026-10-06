package com.example.enkulissan.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.enkulissan.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import java.util.*;

public class VideoFragment extends Fragment {

    static class VideoItem {
        String title, channel, videoId;

        VideoItem(String title, String channel, String videoId) {
            this.title = title;
            this.channel = channel;
            this.videoId = videoId;
        }
    }

    public VideoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        LinearLayout list = root.findViewById(R.id.video_list);
        List<VideoItem> videos = getVideoList();

        for (VideoItem video : videos) {
            View card = getLayoutInflater().inflate(R.layout.item_video_card, list, false);

            TextView title = card.findViewById(R.id.video_title);
            TextView channel = card.findViewById(R.id.video_channel);
            ImageView thumbnail = card.findViewById(R.id.video_thumbnail);
            YouTubePlayerView playerView = card.findViewById(R.id.youtube_player);

            ImageButton likeBtn = card.findViewById(R.id.btn_like);
            ImageButton commentBtn = card.findViewById(R.id.btn_comment);

            // Set video info
            title.setText(video.title);
            channel.setText(video.channel);

            String thumbUrl = "https://img.youtube.com/vi/" + video.videoId + "/0.jpg";
            Picasso.get().load(thumbUrl).into(thumbnail);

            getLifecycle().addObserver(playerView);

            // Play video on thumbnail click
            thumbnail.setOnClickListener(v -> {
                thumbnail.setVisibility(View.GONE);
                playerView.setVisibility(View.VISIBLE);
                playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(video.videoId, 0f);
                    }
                });
            });

            // Like toggle
            likeBtn.setOnClickListener(v -> {
                boolean selected = !v.isSelected();
                v.setSelected(selected);
                likeBtn.setImageResource(selected ? R.drawable.ic_like_filled : R.drawable.ic_like_outline);
            });

            // Comment placeholder
            commentBtn.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Comment feature coming soon!", Toast.LENGTH_SHORT).show();
            });

            list.addView(card);
        }
    }

    private List<VideoItem> getVideoList() {
        List<VideoItem> list = new ArrayList<>();
        list.add(new VideoItem("Alphabet Part 1", "Enkulissan", "4UPobZEpVlE"));
        list.add(new VideoItem("Alphabet Part 2", "Enkulissan", "jw3BOUYHOLM"));
        list.add(new VideoItem("Basic Amharic Greetings", "Enkulissan", "73zIlJyKqh4"));
        return list;
    }
}
