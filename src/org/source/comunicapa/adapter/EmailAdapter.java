package org.source.comunicapa.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.source.comunicapa.R;
import org.source.comunicapa.model.Email;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class EmailAdapter extends BaseAdapter {

	private ArrayList<Email> mListEmail;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private Context mContext;

	public EmailAdapter(Context context) {
		this.mContext = context;
		this.mListEmail = new ArrayList<Email>();
	}

	@Override
	public int getCount() {

		return mListEmail.size();
	}

	@Override
	public Email getItem(int arg0) {

		return mListEmail.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup root) {

		EmailClassHolder holder;
		Email email = mListEmail.get(pos);

		Date date = new Date(email.getTime());

		if (convertView == null) {

			convertView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_email_item, root, false);
			holder = new EmailClassHolder();

			holder.addressText = (TextView) convertView.findViewById(R.id.text_email_emailaddress);
			holder.dateText = (TextView) convertView.findViewById(R.id.text_email_date);
			holder.checkboxSelected = (CheckBox) convertView.findViewById(R.id.checkbox_email_selected);
			holder.timeText = (TextView) convertView.findViewById(R.id.text_email_time);

		} else {
			holder = (EmailClassHolder) convertView.getTag();
		}

		holder.addressText.setText(email.getAddress());
		holder.subjectText.setText(email.getSubject());
		holder.dateText.setText(dateFormat.format(date));
		holder.timeText.setText(timeFormat.format(date));

		return convertView;
	}

	public void setEmails(ArrayList<Email> list) {

		this.mListEmail = list;
		notifyDataSetChanged();
	}

	private class EmailClassHolder {

		CheckBox checkboxSelected;
		TextView dateText, addressText, subjectText, timeText;

	}

}
