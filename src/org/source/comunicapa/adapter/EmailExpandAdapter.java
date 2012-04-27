package org.source.comunicapa.adapter;

import java.util.ArrayList;
import java.util.List;

import org.source.comunicapa.R;
import org.source.comunicapa.SendEmailActivity;
import org.source.comunicapa.constants.IntentTag;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EmailExpandAdapter extends BaseExpandableListAdapter {

	private ArrayList<String> emailList;
	private Context mContext;

	private List<DataSetObserver> observers;

	private String emailType;

	public EmailExpandAdapter(Context context, String emailType) {
		this.mContext = context;
		this.emailList = new ArrayList<String>();
		this.observers = new ArrayList<DataSetObserver>();
	}

	public void setEmails(List<String> emails) {

		this.emailList.clear();
		if (emails != null) {
			this.emailList.addAll(emails);

			for (DataSetObserver data : this.observers) {
				data.onInvalidated();
			}
		}
	}

	@Override
	public boolean areAllItemsEnabled() {

		return true;
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {

		return emailList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {
			
			convertView = (LinearLayout) ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_emailchild_item,
					null);

		}

		final String email = emailList.get(childPosition);
		TextView text = (TextView) convertView.findViewById(R.id.text_email_child);
		text.setText(email);

		ImageView emailImage = (ImageView) convertView.findViewById(R.id.image_email_child);

			@Override
			public void onClick(View v) {

				Intent intentEmail = new Intent(mContext, SendEmailActivity.class);
				intentEmail.putExtra(IntentTag.EMAIL_ADDRESS, email);
				mContext.startActivity(intentEmail);

			}
		});

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		return emailList.size();
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {

		return 1;
	}

	@Override
	public long getCombinedGroupId(long groupId) {

		return 1;
	}

	@Override
	public String getGroup(int groupPosition) {

		return emailType;
	}

	@Override
	public int getGroupCount() {

		return 1;
	}

	@Override
	public long getGroupId(int groupPosition) {

		return 1;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		if (convertView == null) {
			// convertView =
			// LayoutInflater.from(mContext).inflate(R.layout.layout_emailgroup_item,
			// parent, false);
			convertView = (LinearLayout) ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_emailgroup_item,
					null);

		}

		TextView text = (TextView) convertView.findViewById(R.id.text_emailgroup_label);
		text.setText(text.getText().toString().replace("%d", emailType));

		return convertView;
	}

	@Override
	public boolean hasStableIds() {

		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {

		return true;
	}

	@Override
	public boolean isEmpty() {

		return (this.emailList.size() == 1) ? true : false;

	}

}
