package com.ga.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.ga.agile.Constants;

public class SharedPrefUtil {

	public static void saveBackgroundColor(Context context, int backgroundColor) {
		SharedPreferences.Editor prefEdit = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE).edit();
		prefEdit.putInt(Constants.BG_COLOR, backgroundColor);
		prefEdit.commit();
	}

	public static void saveForegroundColor(Context context, int foregroundColor) {
		SharedPreferences.Editor prefEdit = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE).edit();
		prefEdit.putInt(Constants.FG_COLOR, foregroundColor);
		prefEdit.commit();
	}

	public static int getForegroundColor(Context context) {
		int foregroundColor = Constants.DEFAULT_FG_COLOR;
		SharedPreferences pref = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE);
		foregroundColor = pref.getInt(Constants.FG_COLOR, foregroundColor);
		return foregroundColor;
	}

	public static int getBackgroundColor(Context context) {
		int backgroundColor = Constants.DEFAULT_BG_COLOR;
		SharedPreferences pref = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE);
		backgroundColor = pref.getInt(Constants.FG_COLOR, backgroundColor);
		return backgroundColor;
	}

}
