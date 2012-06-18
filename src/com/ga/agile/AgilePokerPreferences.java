package com.ga.agile;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.NumberPicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ga.color.AmbilWarnaDialog;
import com.ga.color.AmbilWarnaDialog.OnAmbilWarnaListener;
import com.ga.pref.SharedPrefUtil;

public class AgilePokerPreferences extends Activity implements
		OnItemClickListener, OnClickListener {

	private static final String LOG_TAG = "AgilePokerPreferences";
	private ListView mPrefListView;
	private PrefListAdapter mPrefListAdapter;
	private ArrayList<String> mPrefList;
	private Dialog mDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.poker_pref_layout);
		initViews();
		super.onCreate(savedInstanceState);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initViews() {
		if (null == mPrefList) {
			mPrefList = new ArrayList(Arrays.asList(getResources()
					.getStringArray(R.array.pref_list)));
		}

		if (null == mPrefListAdapter) {
			mPrefListAdapter = new PrefListAdapter(this, mPrefList);
		}

		mPrefListView = (ListView) findViewById(R.id.poker_pref_list);
		if (null != mPrefListView) {
			mPrefListView.setAdapter(mPrefListAdapter);
			mPrefListView.setOnItemClickListener(this);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		switch (pos) {
		case 0:
			openColorPicker(Constants.FG_COLOR_SELECTOR);
			break;
		case 1:
			openColorPicker(Constants.BG_COLOR_SELECTOR);
			break;
		case 2:
			openCardTimeDialog();
			break;
		default:
			break;

		}
	}

	private void openCardTimeDialog() {
		if (null == mDialog) {
			mDialog = new Dialog(this);
		}
		mDialog.setContentView(R.layout.number_picker_dialog);
		mDialog.setTitle(R.string.choose_card_delay_time);
		mDialog.findViewById(R.id.dialog_cancel).setOnClickListener(this);
		mDialog.findViewById(R.id.dialog_okay).setOnClickListener(this);
		NumberPicker numberPicker = (NumberPicker) mDialog
				.findViewById(R.id.card_delay_picker);
		if (null != numberPicker) {
			numberPicker.setMinValue(Constants.DEFAULT_POKER_CARD_MIN_LIFE_TIME);
			numberPicker.setMaxValue(Constants.DEFAULT_POKER_CARD_MAX_LIFE_TIME);
			int timeDelay = SharedPrefUtil.getCardTimeDelay(this);
			if (timeDelay <= 0 ) {
				timeDelay = Constants.DEFAULT_POKER_CARD_MIN_LIFE_TIME;
			}
			numberPicker.setValue(timeDelay);
		}
		mDialog.show();
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
		AmbilWarnaDialog dialog = new AmbilWarnaDialog(
				AgilePokerPreferences.this, b, new OnAmbilWarnaListener() {
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
									AgilePokerPreferences.this, selectedColor);
						} else if (colorSeletor == Constants.FG_COLOR_SELECTOR) {
							SharedPrefUtil.saveForegroundColor(
									AgilePokerPreferences.this, selectedColor);
						}
					}
				});
		dialog.show();
	}

	private void saveCardDelayTime() {
		NumberPicker numberPicker = (NumberPicker) mDialog
				.findViewById(R.id.card_delay_picker);
		if (null != numberPicker) {
			int timeDelay = numberPicker.getValue();
			SharedPrefUtil.saveCardTimeDelay(this, timeDelay);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_okay:
			saveCardDelayTime();
		case R.id.dialog_cancel:
		default:
			mDialog.dismiss();
			break;
		}
	}
}
