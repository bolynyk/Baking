package com.olynyk.baking.recipedetail.recipestep;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.olynyk.baking.R;
import com.olynyk.baking.domain.Step;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeStepFragment extends Fragment implements RecipeStepContract.View {

    private static final String ARGUMENT_RECIPE_STEP = "ARGUMENT_RECIPE_STEP";
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private RecipeStepContract.Presenter mPresenter;
    private TextView mRecipeStepDescription;
    private SimpleExoPlayer mSimpleExoPlayer;
    private PlayerView mPlayerView;
    private ImageView mImageView;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    public RecipeStepFragment() {

    }

    public static RecipeStepFragment newInstance(Step step) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_RECIPE_STEP, step);
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
        recipeStepFragment.setArguments(arguments);
        return recipeStepFragment;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (Util.SDK_INT > 23) {
//            initializePlayer(null);
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RecipeStepPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        mRecipeStepDescription = root.findViewById(R.id.recipe_step_long_description);

        mPlayerView = root.findViewById(R.id.recipe_step_video_view);
        mImageView = root.findViewById(R.id.recipe_step_image_view);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Step step = getArguments().getParcelable(ARGUMENT_RECIPE_STEP);
        mRecipeStepDescription.setText(step.getLongDescription());

        if (step.getImageUrl() != null && !step.getImageUrl().isEmpty()) {
            initializeImage(step.getImageUrl());
        } else {
            hideSystemUi();
            if ((Util.SDK_INT <= 23 || mSimpleExoPlayer == null)) {
                initializePlayer(step.getVideoUrl());
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer(String videoUrl) {
        mPlayerView.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.GONE);
        if (mSimpleExoPlayer == null) {
            // a factory to create an AdaptiveVideoTrackSelection
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            // using a DefaultTrackSelector with an adaptive video selection factory
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory), new DefaultLoadControl());

            mPlayerView.setPlayer(mSimpleExoPlayer);
        }

        MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
        mSimpleExoPlayer.prepare(mediaSource, true, false);
        mSimpleExoPlayer.setPlayWhenReady(playWhenReady);
        mSimpleExoPlayer.seekTo(currentWindow, playbackPosition);
    }

    private void initializeImage(String imageUrl) {
        mPlayerView.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(imageUrl)).into(mImageView);
    }

    private void releasePlayer() {
        if (mSimpleExoPlayer != null) {
            playbackPosition = mSimpleExoPlayer.getCurrentPosition();
            currentWindow = mSimpleExoPlayer.getCurrentWindowIndex();
            playWhenReady = mSimpleExoPlayer.getPlayWhenReady();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer")).
                createMediaSource(uri);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
