package com.ga.agile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ga.color.AmbilWarnaDialog;
import com.ga.color.AmbilWarnaDialog.OnAmbilWarnaListener;

public class AgilePokerHome extends Activity implements OnClickListener {

	private static final String LOG_TAG = "AgilePokerHome";

	private EditText mEditText = null;
	private Button mButton = null;
	private Button mFGColorButton = null;
	private Button mBGColorButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poker_card_list);
		initViews();
	}

	private void initViews() {
		mEditText = (EditText) findViewById(R.id.edittext);
		if (null != mButton) {
			mButton.requestFocus();
		}

		mButton = (Button) findViewById(R.id.button);
		if (null != mButton) {
			mButton.setOnClickListener(this);
		}

		mFGColorButton = (Button) findViewById(R.id.color_fg_button);
		if (null != mFGColorButton) {
			mFGColorButton.setOnClickListener(this);
		}

		mBGColorButton = (Button) findViewById(R.id.color_bg_button);
		if (null != mBGColorButton) {
			mBGColorButton.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button) {
			openCard();
		} else if (v.getId() == R.id.color_fg_button) {
			openColorPicker(Constants.FG_COLOR_SELECTOR);
		} else if (v.getId() == R.id.color_bg_button) {
			openColorPicker(Constants.BG_COLOR_SELECTOR);
		}
	}

	private void openColorPicker(int colorSeletor) {
		int currentColor = 0xffffffff;
		Bundle b = new Bundle();
		b.putLong(Constants.KEY_COLOR_CODE, currentColor);
		b.putLong(Constants.KEY_WHICH_MODE, colorSeletor);
		AmbilWarnaDialog dialog = new AmbilWarnaDialog(AgilePokerHome.this, b,
				new OnAmbilWarnaListener() {
					@Override
					public void onCancel(AmbilWarnaDialog dialog) {
						Log.d(LOG_TAG, "onCancel() Color Picker");
					}

					@Override
					public void onOk(AmbilWarnaDialog dialog, Bundle bundle) {
						Log.d(LOG_TAG,
								"onOk() Mode = "
										+ bundle.getInt(Constants.KEY_WHICH_MODE)
										+ "Selected = "
										+ bundle.getInt(Constants.KEY_COLOR_CODE));
					}
				});
		dialog.show();
	}

	private void openCard() {
		String cardText = mEditText.getText().toString();
		if (cardText.length() > 0) {
			Intent intent = new Intent();
			intent.setClass(this, AgilePokerCard.class);
			intent.putExtra(Constants.KEY_CARD_TEXT, cardText);
			startActivity(intent);
		}
	}
}