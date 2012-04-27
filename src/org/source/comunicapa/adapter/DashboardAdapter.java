package org.source.comunicapa.adapter;

import org.source.comunicapa.R;
import org.source.comunicapa.adapter.item.DashBoardItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DashboardAdapter extends ArrayAdapter<DashBoardItem> {

	private Context context;
	private DashBoardItem[] entries;

	public DashboardAdapter(Context context, int text, DashBoardItem[] objects) {
		super(context, text, objects);

		this.context = context;
		entries = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		TextView titleTextView;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View item = inflater.inflate(R.layout.layout_dashboard_entry, null, false);

			titleTextView = (TextView) item.findViewById(R.id.text_dashboard_entry);
			titleTextView.setCompoundDrawablesWithIntrinsicBounds(0, entries[position].getIcon(), 0, 0);

			convertView = titleTextView;
		} else {
			titleTextView = (TextView) convertView;
		}
		return titleTextView;
	}

	public int getCount() {
		return entries.length;
	}

	public long getItemId(int position) {
		return 1;
	}

}
