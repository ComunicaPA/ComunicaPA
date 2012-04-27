package org.source.comunicapa.adapter;

import java.util.ArrayList;
import java.util.Collection;

import org.source.comunicapa.R;
import org.source.comunicapa.model.Amministrazione;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmministrazioneAdapter extends BaseAdapter {

	private ArrayList<Amministrazione> amministrazioneList;
	private Context mContext;

	public AmministrazioneAdapter(Context c) {
		super();
		this.mContext = c;
		amministrazioneList = new ArrayList<Amministrazione>();
	}

	@Override
	public int getCount() {

		return this.amministrazioneList.size();
	}

	@Override
	public Amministrazione getItem(int position) {

		return this.amministrazioneList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder;

		Amministrazione amm = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_amministrazione_item, parent, false);
			holder = new Holder();

			holder.indirizzo = (TextView) convertView.findViewById(R.id.amministrazione_indirizzo);
			holder.label = (TextView) convertView.findViewById(R.id.amministrazione_label);
			holder.website = (TextView) convertView.findViewById(R.id.amministrazione_website);

			convertView.setTag(holder);

		} else {

			holder = (Holder) convertView.getTag();

		}

		holder.label.setText(amm.getLabel());
		holder.indirizzo.setText(amm.getIndirizzo());
		holder.website.setText(amm.getHomepage());

		return convertView;
	}

	private class Holder {

		TextView label, website, indirizzo;

	}

	public void setAmministrazioneList(Collection<Amministrazione> amministrazioneList) {
		this.amministrazioneList.clear();
		this.amministrazioneList.addAll(amministrazioneList);
	}

}
