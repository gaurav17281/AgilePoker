package com.ga.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.ga.agile.Constants;

public class SharedPrefUtil {

	public static void saveBackgroundColor(Context context, int backgroundColor) {
		SharedPreferences.Editor prefEdit = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE).edit();
		prefEdit.remove(Constants.BG_COLOR);
		prefEdit.putInt(Constants.BG_COLOR, backgroundColor);
		prefEdit.commit();
	}

	public static void saveForegroundColor(Context context, int foregroundColor) {
		SharedPreferences.Editor prefEdit = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE).edit();
		prefEdit.remove(Constants.FG_COLOR);
		prefEdit.putInt(Constants.FG_COLOR, foregroundColor);
		prefEdit.commit();
	}

	public static void saveCardTimeDelay(Context context, int timeDelay) {
		SharedPreferences.Editor prefEdit = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE).edit();
		prefEdit.remove(Constants.CARD_TIME_DELAY);
		prefEdit.putInt(Constants.CARD_TIME_DELAY, timeDelay);
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
		backgroundColor = pref.getInt(Constants.BG_COLOR, backgroundColor);
		return backgroundColor;
	}

	public static int getCardTimeDelay(Context context) {
		int cardTimeDelay = Constants.DEFAULT_POKER_CARD_MIN_LIFE_TIME;
		SharedPreferences pref = context.getSharedPreferences(
				Constants.AGILE_POKER_PREF, Context.MODE_PRIVATE);
		cardTimeDelay = pref.getInt(Constants.CARD_TIME_DELAY, cardTimeDelay);
		return cardTimeDelay;
	}
}
