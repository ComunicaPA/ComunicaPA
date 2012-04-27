package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.SendEmailActivity;
import org.source.comunicapa.constants.IntentTag;
import org.source.comunicapa.datastore.EmailStore;
import org.source.comunicapa.model.Email;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewEmailFragment extends Fragment {

	private Email email;

	private TextView addressText, subjectText, bodyText;
	private Button forwardButton, deleteButton;

	public ViewEmailFragment(Email email) {
		this.email = email;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.layout_fragment_email_view, container, false);

		addressText = (TextView) view.findViewById(R.id.email_address_text);
		subjectText = (TextView) view.findViewById(R.id.email_subject_text);
		bodyText = (TextView) view.findViewById(R.id.email_body_text);

		forwardButton = (Button) view.findViewById(R.id.email_forward_button);
		forwardButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), SendEmailActivity.class);
				intent.putExtra(IntentTag.EMAIL_SUBJECT, "FW: " + email.getSubject());
				intent.putExtra(IntentTag.EMAIL_BODY, "-- \n " + email.getBody());
				startActivity(intent);
			}
		});

		deleteButton = (Button) view.findViewById(R.id.email_delete_button);
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new DeleteSentEmailTask().execute(email);
			}
		});

		((LinearLayout) getActivity().findViewById(R.id.base_layout)).setGravity(Gravity.NO_GRAVITY);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		addressText.setText(this.email.getAddress());
		subjectText.setText(this.email.getSubject());
		bodyText.setText(this.email.getBody());

	}

	private class DeleteSentEmailTask extends AsyncTask<Email, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			Toast.makeText(getActivity(), R.string.email_deleting_email, Toast.LENGTH_SHORT).show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Email... params) {
			EmailStore store = new EmailStore(getActivity());
			return store.deleteEmail(params[0]);

		}

		@Override
		protected void onPostExecute(Boolean result) {

			super.onPostExecute(result);
			if (result) {
				Toast.makeText(getActivity(), R.string.email_deleted_email_ok, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), R.string.email_deleted_email_fault, Toast.LENGTH_LONG).show();
			}

		}
	}
}
