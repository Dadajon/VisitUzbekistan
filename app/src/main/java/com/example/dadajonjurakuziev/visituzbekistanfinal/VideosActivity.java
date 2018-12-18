package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideosActivity extends YouTubeBaseActivity {
    private static final String TAG = "VideosActivity";
    YouTubePlayerView youTubePlayerView;
    Button startBtn;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting YouTubePlayerView.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        youTubePlayerView = findViewById(R.id.videoPlayer);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                Log.d(TAG, "onInitializationSuccess: Done initializing.");
//                youTubePlayer.loadVideo("W4hTJybfU7s");
////                List<String> videoList = new ArrayList<>();
////                videoList.add("pu4rJertDJQ");
////                videoList.add("W4hTJybfU7s");
////                youTubePlayer.loadVideos(videoList);

                if (null == youTubePlayer) return;

                // Start buffering
                if (!b) {
                    youTubePlayer.cueVideo("pu4rJertDJQ");
                }

                // Add listeners to YouTubePlayer instance
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason arg0) {
                    }

                    @Override
                    public void onLoaded(String arg0) {
                    }

                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onVideoStarted() {
                    }

                });

                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onBuffering(boolean arg0) {
                    }

                    @Override
                    public void onPaused() {
                    }

                    @Override
                    public void onPlaying() {
                    }

                    @Override
                    public void onSeekTo(int arg0) {
                    }

                    @Override
                    public void onStopped() {
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Failed to initializing");
            }
        };

        //AutoPlay
        youTubePlayerView.initialize(PlayerConfig.getApiKey(), mOnInitializedListener);

//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: Initializing YouTube Player.");
//                Toast.makeText(VideosActivity.this, "Click on Play button", Toast.LENGTH_SHORT).show();
//                youTubePlayerView.initialize(PlayerConfig.getApiKey(), mOnInitializedListener);
//            }
//        });

    }
}