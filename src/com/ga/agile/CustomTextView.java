package com.ga.agile;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class CustomTextView extends TextView {
	final boolean mTopDown;
	private int mRotateAngle = 90;

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		final int gravity = getGravity();
		if (Gravity.isVertical(gravity)
				&& (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {
			setGravity((gravity & Gravity.HORIZONTAL_GRAVITY_MASK)
					| Gravity.TOP);
			mTopDown = false;
		} else
			mTopDown = true;
	}

	public void setRotateAngle(int rotateAngle) {
		mRotateAngle = rotateAngle;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		TextPaint textPaint = getPaint();
		textPaint.setColor(getCurrentTextColor());
		textPaint.drawableState = getDrawableState();

		canvas.save();

		if (mTopDown) {
			canvas.translate(getWidth(), 0);
			canvas.rotate(mRotateAngle);
		} else {
			canvas.translate(0, getHeight());
			canvas.rotate(-1 * mRotateAngle);
		}

		canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

		getLayout().draw(canvas);
		canvas.restore();

	}

	/*
	 * @Override protected void onDraw(Canvas canvas) { TextPaint textPaint =
	 * getPaint(); textPaint.setTextSize(44); int cx = this.getMeasuredWidth() /
	 * 2; int cy = this.getMeasuredHeight() / 2; textPaint.setColor(Color.RED);
	 * getLayout().draw(canvas);
	 * 
	 * canvas.save(); canvas.scale(1f, -0.5f, cx, cy);
	 * textPaint.setColor(Color.GRAY); canvas.drawText("Hello", cx, cy,
	 * textPaint); super.onDraw(canvas); canvas.restore(); }
	 */
}