package com.example.loadingview_58tc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {
    private int measureWidth, measureHeight, delayTime = 700;
    private Path mSquarePath;
    private Paint mPaint;
    private Handler handler = new Handler(msg -> {
        if (0 == msg.what) {
            invalidate();
            delayTime = 1400;
        }
        return true;
    });
    private int type = 0;
    private boolean isStart;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSquarePath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void start() {
        isStart = true;
        invalidate();
    }

    public void stop() {
        isStart = false;
        invalidate();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureHeight = getMeasuredHeight();
        measureWidth = getMeasuredWidth();
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isStart) {
            if (0 == type) {
                mPaint.setColor(Color.parseColor("#36D088"));
                mSquarePath.moveTo(measureWidth / 2, 0);
                mSquarePath.lineTo((float) (measureWidth / 2 - (measureWidth / 2) * Math.cos(Math.PI / 6)), (float) (measureWidth / 2 * 1.5));
                mSquarePath.lineTo((float) (measureWidth / 2 + (measureWidth / 2) * Math.cos(Math.PI / 6)), (float) (measureWidth / 2 * 1.5));
                canvas.drawPath(mSquarePath, mPaint);
                type = 1;
            } else if (1 == type) {
                mPaint.setColor(Color.parseColor("#EC6E58"));
                canvas.drawRect(0, 0, measureWidth, measureHeight, mPaint);
                type = 2;
            } else {
                mPaint.setColor(Color.parseColor("#68A0F3"));
                canvas.drawCircle(measureWidth / 2, measureWidth / 2, measureWidth / 2, mPaint);
                type = 0;
            }
            handler.sendEmptyMessageDelayed(0, delayTime);
        }
    }
}
