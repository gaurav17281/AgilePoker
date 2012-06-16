package com.ga.agile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AgilePokerCard extends Activity implements OnClickListener {
	private static final String LOG_TAG = "AgilePokerCard";
	private String mCardText = null;
	private RelativeLayout mLayoutContainer;
	private Runnable mRunnable;
	private Handler mHandler;
	private TextView mTimerText;
	private int mExpiredTime = 0;
	private int mCardLifeTime = Constants.POKER_CARD_LIFE_TIME;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poker_card);
		initData();
		initViews();
	}

	private void initViews() {
		mLayoutContainer = (RelativeLayout) findViewById(R.id.card_container);
		mLayoutContainer.setOnClickListener(this);

		TextView textView = null;
		textView = (TextView) findViewById(R.id.textTopLeft);
		if (null != textView) {
			textView.setText(mCardText);
		}

		textView = null;
		textView = (TextView) findViewById(R.id.textTopRight);
		if (null != textView) {
			textView.setText(mCardText);
		}

		textView = null;
		textView = (TextView) findViewById(R.id.textCenter);
		if (null != textView) {
			textView.setText(mCardText);
		}

		CustomTextView customTextView = null;
		customTextView = (CustomTextView) findViewById(R.id.textBottomLeft);
		if (null != customTextView) {
			customTextView.setText(mCardText);
		}

		customTextView = null;
		customTextView = (CustomTextView) findViewById(R.id.textBottomRight);
		if (null != customTextView) {
			customTextView.setText(mCardText);
		}
		mTimerText = (TextView) findViewById(R.id.textTimerText);
	}

	private void initData() {
		mCardText = getIntent().getExtras().getString(Constants.KEY_CARD_TEXT);
		if (null == mCardText || mCardText.length() <= 0) {
			finishCardActivity();
		}

		mHandler = new Handler();
		mRunnable = new Runnable() {
			public void run() {
				Log.d(LOG_TAG, "Runnable: run()" + mExpiredTime);
				int remainingTime = mCardLifeTime - mExpiredTime;
				if (remainingTime <= 0) {
					finishCardActivity();
				} else {
					updateTimer(remainingTime);
				}
			}
		};
		mHandler.postDelayed(mRunnable, Constants.POKER_CARD_UPDATE_DELAY);
	}

	private void updateTimer(int remainingTime) {
		if (null != mTimerText) {
			mTimerText
					.setText(getString(R.string.remaining_time, remainingTime));
			mExpiredTime++;
			mHandler.postDelayed(mRunnable, Constants.POKER_CARD_UPDATE_DELAY);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.card_container) {
			mHandler.removeCallbacks(mRunnable);
			mExpiredTime = 0;
			mHandler.postDelayed(mRunnable, Constants.POKER_CARD_UPDATE_DELAY);
			Toast.makeText(this,
					getString(R.string.timer_reset, mCardLifeTime),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		if (null != mHandler && null != mRunnable) {
			mHandler.removeCallbacks(mRunnable);
		}
		super.onBackPressed();
	}

	private void finishCardActivity() {
		this.finish();
	}

	public int getCardLifeTime() {
		return mCardLifeTime;
	}

	public void setCardLifeTime(int cardLifeTime) {
		this.mCardLifeTime = cardLifeTime;
	}
}