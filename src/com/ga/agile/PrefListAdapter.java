package com.ga.agile;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ga.pref.SharedPrefUtil;

public class PrefListAdapter extends ArrayAdapter<String> {

	@SuppressWarnings("unused")
	private static final String LOG_TAG = "GenericListAdapter";
	private final Context mContext;
	private final ArrayList<String> mPrefList;

	public PrefListAdapter(Context context, ArrayList<String> prefList) {
		super(context, R.layout.list_item_layout, prefList);
		this.mContext = context;
		this.mPrefList = prefList;
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	static class GenericViewHolder {
		public TextView mRow1Text;
		public TextView mPrefTypeText;
		public LinearLayout mPrefTypeColor;
		public int pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GenericViewHolder gViewholder = null;
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.list_item_layout, null, true);
			gViewholder = new GenericViewHolder();
			gViewholder.mRow1Text = (TextView) rowView
					.findViewById(R.id.preference_name);
			gViewholder.mPrefTypeText = (TextView) rowView
					.findViewById(R.id.preference_type_text);
			gViewholder.mPrefTypeColor = (LinearLayout) rowView
					.findViewById(R.id.preference_type_color);
			gViewholder.mRow1Text.setText(mPrefList.get(position));
			rowView.setTag(gViewholder);
		}
		gViewholder = (GenericViewHolder) rowView.getTag();
		gViewholder.pos = position;

		// 1 == fg color
		// 2 == bg color
		// 3 == card timer
		if (0 == position) {
//			gViewholder.mPrefTypeText.setVisibility(View.GONE);
//			gViewholder.mPrefTypeColor.setBackgroundColor(SharedPrefUtil
//					.getForegroundColor(mContext));
		} else if (1 == position) {
//			gViewholder.mPrefTypeText.setVisibility(View.GONE);
//			gViewholder.mPrefTypeColor.setBackgroundColor(SharedPrefUtil
//					.getBackgroundColor(mContext));
		} else if (2 == position) {
//			gViewholder.mPrefTypeColor.setVisibility(View.GONE);
//			gViewholder.mPrefTypeText.setText(SharedPrefUtil
//					.getCardTimeDelay(mContext));
		}

		gViewholder = (GenericViewHolder) rowView.getTag();
		gViewholder.pos = position;
		return rowView;
	}
}
