package com.example.enkulissan.level;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CurvedLineView extends View {

    private Paint paint;
    private Path path;
    private boolean leftToRight = true;

    // Glow animation
    private float phase = 0f;
    private boolean glowing = false;
    private ValueAnimator animator;

    public CurvedLineView(Context context) {
        super(context);
        init();
    }

    public CurvedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF1B4332); // Base green color
        paint.setStrokeWidth(6f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        path = new Path();

        // Glow animation setup
        animator = ValueAnimator.ofFloat(0, 60);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(animation -> {
            phase = (float) animation.getAnimatedValue();
            invalidate();
        });
    }

    public void setLeftToRight(boolean leftToRight) {
        this.leftToRight = leftToRight;
        invalidate();
    }

    public void startGlowing() {
        glowing = true;
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    public void stopGlowing() {
        glowing = false;
        if (animator.isRunning()) {
            animator.cancel();
            phase = 0f;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        int width = getWidth();
        int height = getHeight();

        if (leftToRight) {
            path.moveTo(0, height);
            path.cubicTo(width * 0.25f, height * 0.7f,
                    width * 0.75f, height * 0.3f,
                    width, 0);
        } else {
            path.moveTo(width, height);
            path.cubicTo(width * 0.75f, height * 0.7f,
                    width * 0.25f, height * 0.3f,
                    0, 0);
        }

        if (glowing) {
            // Dim base path
            paint.setColor(Color.parseColor("#441B4332"));
            paint.setStrokeWidth(6f);
            paint.setPathEffect(null);
            canvas.drawPath(path, paint);

            // Animated glowing dash on top
            paint.setColor(Color.parseColor("#AA44FF44"));
            paint.setStrokeWidth(8f);
            PathEffect dash = new DashPathEffect(new float[]{20, 40}, phase);
            paint.setPathEffect(dash);
            canvas.drawPath(path, paint);
        } else {
            // Simple curve without glow
            paint.setColor(0xFF1B4332);
            paint.setStrokeWidth(6f);
            paint.setPathEffect(null);
            canvas.drawPath(path, paint);
        }
    }
}
