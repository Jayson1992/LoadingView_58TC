package com.example.loadingview_58tc;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    PathView mPathView;
    ShadowView mShadowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPathView = findViewById(R.id.pathView);
        mShadowView = findViewById(R.id.shadowView);
        findViewById(R.id.start).setOnClickListener(v -> {
            mPathView.start();
            mShadowView.start();
            initMoveAnim();
            initScaleAnim();
        });
        findViewById(R.id.stop).setOnClickListener(v -> {
            mPathView.stop();
            mShadowView.stop();
        });

    }

    private void initMoveAnim() {
        ValueAnimator downAnim = ValueAnimator.ofInt(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55, getResources().getDisplayMetrics()));
        downAnim.setRepeatCount(ValueAnimator.INFINITE);
        downAnim.setRepeatMode(ValueAnimator.REVERSE);
        downAnim.setTarget(mPathView);
        downAnim.addUpdateListener(animation -> mPathView.setTranslationY((Integer) animation.getAnimatedValue()));
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 360);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnim.setTarget(mPathView);
        rotateAnim.addUpdateListener(animation -> mPathView.setRotation((Float) animation.getAnimatedValue()));
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(700);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.play(downAnim).with(rotateAnim);
        animSet.start();
    }

    private void initScaleAnim() {
        ValueAnimator enlargeAnim = ValueAnimator.ofFloat(0, 1);
        enlargeAnim.setRepeatCount(ValueAnimator.INFINITE);
        enlargeAnim.setRepeatMode(ValueAnimator.REVERSE);
        enlargeAnim.setTarget(mShadowView);
        enlargeAnim.addUpdateListener(animation -> {
            mShadowView.setScaleX((Float) animation.getAnimatedValue());
            mShadowView.setScaleY((Float) animation.getAnimatedValue());
        });
        ValueAnimator reduceAnim = ValueAnimator.ofFloat(1, 0);
        reduceAnim.setRepeatCount(ValueAnimator.INFINITE);
        reduceAnim.setRepeatMode(ValueAnimator.REVERSE);
        reduceAnim.setTarget(mShadowView);
        reduceAnim.addUpdateListener(animation -> {
            mShadowView.setScaleX((Float) animation.getAnimatedValue());
            mShadowView.setScaleY((Float) animation.getAnimatedValue());
        });
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(700);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.play(enlargeAnim).with(reduceAnim);
        animSet.start();
    }
}