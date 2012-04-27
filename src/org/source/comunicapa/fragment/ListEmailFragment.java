package org.source.comunicapa.fragment;

import java.util.ArrayList;

import org.source.comunicapa.R;
import org.source.comunicapa.SendEmailActivity;
import org.source.comunicapa.adapter.EmailAdapter;
import org.source.comunicapa.constants.IntentTag;
import org.source.comunicapa.datastore.EmailStore;
import org.source.comunicapa.model.Email;
import org.source.comunicapa.utils.Utils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListEmailFragment extends Fragment {

	private ListView mList;
	private EmailAdapter listAdapter;
	private boolean isDraft;
	private LinearLayout mLoadingLayout;
	private TextView textEmailEmpty;
	private LinearLayout baseLayout;

	public ListEmailFragment(boolean isDraft) {
		super();
		this.isDraft = isDraft;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.layout_fragment_email_list, null);

		mList = (ListView) view.findViewById(R.id.listview_email);
		mLoadingLayout = (LinearLayout) view.findViewById(R.id.layout_email_loading);
		textEmailEmpty = (TextView) view.findViewById(R.id.text_email_empty);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		baseLayout = ((LinearLayout) getActivity().findViewById(R.id.base_layout));
		listAdapter = new EmailAdapter(getActivity());

		mList.setAdapter(listAdapter);

		mList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Utils.log("LongCLICK " + arg2);

				return true;
			}
		});

		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				Utils.log("CLICK " + position);

				Email email = listAdapter.getItem(position);

				if (isDraft) {

					Intent intent = new Intent(getActivity(), SendEmailActivity.class);
					intent.putExtra(IntentTag.EMAIL_ADDRESS, email.getAddress());
					intent.putExtra(IntentTag.EMAIL_BODY, email.getBody());
					intent.putExtra(IntentTag.EMAIL_SUBJECT, email.getSubject());
					intent.putExtra(IntentTag.EMAIL_ID, email.getId());
					startActivity(intent);

				} else {

					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit,
							R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);

					fragmentTransaction.replace(R.id.base_layout, new ViewEmailFragment(email));
					fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
				}

			}
		});

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		new GettingEmailTask().execute(isDraft);
	}

	private class GettingEmailTask extends AsyncTask<Boolean, Void, ArrayList<Email>> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			mList.setVisibility(View.GONE);
			mLoadingLayout.setVisibility(View.VISIBLE);
			textEmailEmpty.setVisibility(View.GONE);
			baseLayout.setGravity(Gravity.CENTER);

		}

		@Override
		protected ArrayList<Email> doInBackground(Boolean... params) {

			EmailStore store = new EmailStore(getActivity());

			if (params[0]) {

				return store.getDraft();
			} else {
				return store.getEmail();
			}

		}

		@Override
		protected void onPostExecute(ArrayList<Email> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Utils.log(" NUMEBER ELEMENT " + result.size());

			listAdapter.setEmails(result);

			if (result.size() > 0) {
				mList.setVisibility(View.VISIBLE);
				mLoadingLayout.setVisibility(View.GONE);
				textEmailEmpty.setVisibility(View.GONE);
				baseLayout.setGravity(Gravity.NO_GRAVITY);

			} else {

				mList.setVisibility(View.GONE);
				mLoadingLayout.setVisibility(View.GONE);
				textEmailEmpty.setVisibility(View.VISIBLE);
			}
		}

	}

}
