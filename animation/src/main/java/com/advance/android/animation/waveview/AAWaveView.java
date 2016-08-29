package com.advance.android.animation.waveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.advance.android.animation.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述:
 * 日期: 2016-08-29
 * 时间: 19:07
 * ——————————————————————————————————
 */
public class AAWaveView extends View {
    private Context mContext;
    private Paint mPaint;
    private Interpolator mInterpolator = new LinearInterpolator();
    private boolean mIsRunning;
    private float mMinRadius;//最小波纹半径(初始半径)
    private float mMaxRadius;//最大波纹半径(结束半径)
    private long mDuration = 1500;//波纹从创建到消失时间
    private int mSpeed = 1000;   // 波纹的创建速度，每500ms创建一个
    private int mColor = Color.RED;

    private boolean mMaxRadiusSet;
    private float mMaxRadiusRate = 0.9f;   // 如果没有设置mMaxRadius，可mMaxRadius = 最小长度 * mMaxRadiusRate;

    private List<Circle> mCircleList = new ArrayList<Circle>();
    private long mLastCreateTime;
    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                newCircle();
                postDelayed(mCreateCircle, mSpeed);
            }
        }
    };

    public AAWaveView(Context context) {
        this(context, null);
    }

    public AAWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AAWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.HDWave);
        mMinRadius = a.getFloat(R.styleable.HDWave_minRadius, mMinRadius);
        mMaxRadius = a.getFloat(R.styleable.HDWave_maxRadius, mMaxRadius);
        mDuration = a.getInt(R.styleable.HDWave_duration, (int) mDuration);
        mSpeed = a.getInt(R.styleable.HDWave_speed, mSpeed);
        mColor = a.getColor(R.styleable.HDWave_color, mColor);

        mMaxRadiusSet = a.getBoolean(R.styleable.HDWave_maxRadiusSet, mMaxRadiusSet);
        mMaxRadiusRate = a.getFloat(R.styleable.HDWave_maxRadiusRate, mMaxRadiusRate);
        if (mMaxRadius != 0) {
            mMaxRadiusSet = true;
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        setStyle(Paint.Style.STROKE);


    }

    /**
     * 开始
     */
    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        mIsRunning = false;
        mCircleList.clear();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (!mMaxRadiusSet) {
            mMaxRadius = Math.min(w, h) * mMaxRadiusRate / 2.0f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<Circle> iterator = mCircleList.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, circle.getCurrentRadius(), mPaint);
            } else {
                iterator.remove();
            }
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    public void setMinRadius(float minRadius) {
        mMinRadius = minRadius;
    }

    public void setMaxRadius(float maxRadius) {
        mMaxRadius = maxRadius;
        if (maxRadius != 0) {
            mMaxRadiusSet = true;
        }
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void setMaxRadiusSet(boolean maxRadiusSet) {
        mMaxRadiusSet = maxRadiusSet;
    }

    public void setMaxRadiusRate(float maxRadiusRate) {
        mMaxRadiusRate = maxRadiusRate;
    }

    private void newCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle();
        mCircleList.add(circle);
        invalidate();
        mLastCreateTime = currentTime;
    }

    private class Circle {
        private long mCreateTime;

        public Circle() {
            this.mCreateTime = System.currentTimeMillis();
        }

        public int getAlpha() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        public float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return mMinRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mMinRadius);
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        if (mInterpolator == null) {
            mInterpolator = new LinearInterpolator();
        }
    }
}
