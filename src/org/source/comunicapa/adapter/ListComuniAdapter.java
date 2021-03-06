package org.source.comunicapa.adapter;

import java.util.ArrayList;
import java.util.List;

import org.source.comunicapa.R;
import org.source.comunicapa.model.Comune;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListComuniAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Comune> comuni;

	public ListComuniAdapter(Context context) {

		this.mContext = context;
		comuni = new ArrayList<Comune>();
	}

	@Override
	public int getCount() {

		return comuni.size();
	}

	@Override
	public Comune getItem(int arg0) {

		return comuni.get(arg0);
	}

	@Override
	public long getItemId(int position) {

		return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ItemHolder item;
		Comune comune = this.comuni.get(position);
		if (convertView == null) {

			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_comune_item, parent, false);

			item = new ItemHolder();
			item.cap = (TextView) convertView.findViewById(R.id.text_comune_cap);

			convertView.setTag(item);

		} else {

			item = (ItemHolder) convertView.getTag();
		}

		item.comune.setText(comune.getComune());
		item.cap.setText(comune.getCap());

		return convertView;
	}

	public void setComuni(List<Comune> comuni) {

		this.comuni.clear();
		this.comuni.addAll(comuni);
	}

	private class ItemHolder {

		TextView comune, cap;
	}
}
