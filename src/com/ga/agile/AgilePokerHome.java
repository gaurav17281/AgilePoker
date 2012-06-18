package com.ga.agile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ga.color.AmbilWarnaDialog;
import com.ga.color.AmbilWarnaDialog.OnAmbilWarnaListener;
import com.ga.pref.SharedPrefUtil;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		new MenuInflater(this).inflate(R.menu.options_menu, menu);
		menu.findItem(R.id.preferences).setIcon(R.drawable.icon_settings)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (R.id.preferences == item.getItemId()) {
			openPreferences();
		}
		return super.onOptionsItemSelected(item);
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
		if (colorSeletor == Constants.BG_COLOR_SELECTOR) {
			currentColor = SharedPrefUtil.getBackgroundColor(this);
		} else if (colorSeletor == Constants.FG_COLOR_SELECTOR) {
			currentColor = SharedPrefUtil.getForegroundColor(this);
		}

		Bundle b = new Bundle();
		b.putInt(Constants.KEY_COLOR_CODE, currentColor);
		b.putInt(Constants.KEY_WHICH_MODE, colorSeletor);
		AmbilWarnaDialog dialog = new AmbilWarnaDialog(AgilePokerHome.this, b,
				new OnAmbilWarnaListener() {
					@Override
					public void onCancel(AmbilWarnaDialog dialog) {
						Log.d(LOG_TAG, "onCancel() Color Picker");
					}

					@Override
					public void onOk(AmbilWarnaDialog dialog, Bundle bundle) {
						int colorSeletor = bundle
								.getInt(Constants.KEY_WHICH_MODE);
						int selectedColor = bundle
								.getInt(Constants.KEY_COLOR_CODE);
						Log.d(LOG_TAG, "onOk() Mode = " + colorSeletor
								+ "Selected = " + selectedColor);
						if (colorSeletor == Constants.BG_COLOR_SELECTOR) {
							SharedPrefUtil.saveBackgroundColor(
									AgilePokerHome.this, selectedColor);
						} else if (colorSeletor == Constants.FG_COLOR_SELECTOR) {
							SharedPrefUtil.saveForegroundColor(
									AgilePokerHome.this, selectedColor);
						}
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

	private void openPreferences() {
		Intent intent = new Intent();
		intent.setClass(this, AgilePokerPreferences.class);
		startActivity(intent);
	}
}