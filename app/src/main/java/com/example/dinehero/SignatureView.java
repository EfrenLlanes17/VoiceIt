package com.example.dinehero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SignatureView extends View {
    private Path path;
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint bitmapPaint;

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF000000);  // Black color
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(8f); // Thickness of the stroke
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.drawPath(path, paint);

        float signatureLineY = getHeight() * 0.8f; // You can adjust the position as needed
        Paint linePaint = new Paint();
        linePaint.setColor(0xFF000000);  // Black color
        linePaint.setStrokeWidth(4f);    // Line thickness
        canvas.drawLine(50, signatureLineY, getWidth() - 50, signatureLineY, linePaint);

        Paint textPaint = new Paint();
        textPaint.setColor(0xFF000000);  // Black color
        textPaint.setTextSize(40f);      // Size of the "X"
        textPaint.setAntiAlias(true);

        // Draw the "X" before the line
        canvas.drawText("X", 20, signatureLineY + 10, textPaint);  // Position of the "X"

        // Draw the signature line
        canvas.drawLine(100, signatureLineY, getWidth() - 50, signatureLineY, linePaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touchStart(float x, float y) {
        ProfileActivity.isSignedQ(true);
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        path.lineTo(mX, mY);
        canvas.drawPath(path, paint);
        path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }

    public Bitmap getSignatureBitmap() {
        return bitmap;
    }

    public void clearSignature() {
        ProfileActivity.isSignedQ(false);

        path.reset();
        canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        invalidate();
    }
}
