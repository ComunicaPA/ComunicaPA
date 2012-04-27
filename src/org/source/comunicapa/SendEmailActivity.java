package org.source.comunicapa;

import org.source.comunicapa.constants.IntentTag;
import org.source.comunicapa.datastore.EmailStore;
import org.source.comunicapa.model.Email;
import org.source.comunicapa.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendEmailActivity extends FragmentActivity {

	private EditText mEditEmail, mEditSubject, mEditBody;
	private Button mButtonSend, mButtonDelete, mButtonDraft;

	private EmailStore mStore;

	private int mEmailId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTheme(R.style.theme_action);

		setContentView(R.layout.layout_email);

		Typeface typeface = Utils.getTypeface(this, "organica.ttf");

		this.getActionBar().setDisplayShowCustomEnabled(true);
		this.getActionBar().setDisplayShowTitleEnabled(false);

		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.titleview, null);

		// if you need to customize anything else about the text, do it here.
		// I'm using a custom TextView with a custom font in my layout xml so
		// all I need to do is set title
		TextView tx = (TextView) v.findViewById(R.id.actionbar_text);

		tx.setTypeface(typeface);

		// assign the view to the actionbar
		this.getActionBar().setCustomView(v);

		mStore = new EmailStore(this);

		setEditText(getIntent());

		mButtonSend = (Button) findViewById(R.id.button_send);

		mButtonSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new SendAsyncTask().execute(mEditEmail.getText().toString(), mEditSubject.getText().toString(), mEditBody.getText().toString());
			}
		});

		mButtonDraft = (Button) findViewById(R.id.button_draft);
		mButtonDraft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new SaveDraftAsyncTask().execute(mEditEmail.getText().toString(), mEditSubject.getText().toString(), mEditBody.getText().toString());
			}
		});

		mButtonDelete = (Button) findViewById(R.id.button_delete);
		mButtonDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				mEditEmail.setText("");
				mEditSubject.setText("");
				mEditBody.setText("");

				if (mEmailId > 0) {
					new DeleteDraftAsyncTask().execute(mEmailId);
				}
			}
		});

	}

	private void setEditText(Intent intent) {

		mEditEmail = (EditText) findViewById(R.id.edit_email);
		mEditEmail.setText(intent.getStringExtra(IntentTag.EMAIL_ADDRESS));

		mEditBody = (EditText) findViewById(R.id.edit_body_email);
		mEditBody.setText(intent.getStringExtra(IntentTag.EMAIL_BODY));

		mEditSubject = (EditText) findViewById(R.id.edit_subject_email);
		mEditSubject.setText(intent.getStringExtra(IntentTag.EMAIL_SUBJECT));

		mEmailId = intent.getIntExtra(IntentTag.EMAIL_ID, -1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_menu, menu);
		menu.removeItem(R.id.mail);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// mViewPager.setVisibility(View.GONE);
		// mBaseLayout.setVisibility(View.VISIBLE);

		switch (item.getItemId()) {

		// case R.id.search:
		// startActivity(new Intent(this, SearchPActivity.class));
		// return true;
		case R.id.setting:
			Intent intentSetting = new Intent(this, SettingPActivity.class);
			startActivity(intentSetting);
			return true;

		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, ComunicaPAActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * AsyncTask to send email params[0] = email address params[1] = email
	 * subject params[2] = email body
	 * 
	 * @author matteo
	 * 
	 */
	private class SendAsyncTask extends AsyncTask<String, Void, Boolean> {
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			Toast.makeText(getBaseContext(), R.string.email_sending_email, Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Boolean doInBackground(String... params) {

			boolean isSaved = false;
			Email email = new Email();

			email.setTime(System.currentTimeMillis());
			email.setAddress(params[0]);
			email.setSubject(params[1]);
			email.setBody(params[2]);

			// XXX
			/**
			 * Add SENDING EMAIL ACTION
			 */

			// delete draft item and save email one.
			if (mEmailId >= 0) {

				mStore.deleteItem(mEmailId);
			}

			isSaved = mStore.saveEmail(email);

			return isSaved;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			super.onPostExecute(result);

			if (result) {
				Toast.makeText(getBaseContext(), R.string.email_sent_email_ok, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), R.string.email_sent_email_fault, Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * AsyncTask to save email in local db params[0] = email address, params[1]
	 * = email subject, params[2] = email body.
	 * 
	 * @author matteo
	 * 
	 */
	private class SaveDraftAsyncTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			Toast.makeText(getBaseContext(), R.string.email_saving_email, Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Boolean doInBackground(String... params) {

			boolean isSaved = false;
			Email email = new Email();

			email.setAddress(params[0]);
			email.setSubject(params[1]);
			email.setBody(params[2]);
			email.setTime(System.currentTimeMillis());
			if (mEmailId == -1) { // primo salvataggio

				isSaved = mStore.saveDraft(email);

			} else { // update salvataggio

				email.setId(mEmailId);

				isSaved = mStore.updateDraft(email);

			}
			return isSaved;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			super.onPostExecute(result);

			if (result) {
				Toast.makeText(getBaseContext(), R.string.email_saved_draft_ok, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), R.string.email_saved_draft_fault, Toast.LENGTH_SHORT).show();
			}
		}

	}

	/**
	 * AsyncTask to delete draft in local db params[0] = id draft;
	 * 
	 * @author matteo
	 * 
	 */
	private class DeleteDraftAsyncTask extends AsyncTask<Integer, Void, Boolean> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

		}

		@Override
		protected Boolean doInBackground(Integer... params) {

			return mStore.deleteItem(params[0].intValue());
		}

		@Override
		protected void onPostExecute(Boolean result) {

			super.onPostExecute(result);

		}

	}

}
