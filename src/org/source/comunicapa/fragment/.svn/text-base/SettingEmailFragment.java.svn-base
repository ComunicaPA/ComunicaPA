package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.constants.EmailType;
import org.source.comunicapa.constants.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SettingEmailFragment extends Fragment {

	private int type;
	private SharedPreferences mPref;
	private EditText mEditUsername;
	private EditText mEditPassword;
	private EditText mEditSmtp;
	private EditText mEditPop3;
	private EditText mEditImapPort, mEditSmtpPort;
	private CheckBox mCheckBoxSSL;
	private Button saveButton;

	public SettingEmailFragment(int type) {
		this.type = type;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.layout_setting, container, false);

		mEditUsername = (EditText) v.findViewById(R.id.setting_username);

		mEditPassword = (EditText) v.findViewById(R.id.setting_password);

		mEditSmtp = (EditText) v.findViewById(R.id.setting_smtp);

		mEditPop3 = (EditText) v.findViewById(R.id.setting_imap);
		mCheckBoxSSL = (CheckBox) v.findViewById(R.id.setting_ssl);
		mEditSmtpPort = (EditText) v.findViewById(R.id.setting_porta_smatp);
		mEditImapPort = (EditText) v.findViewById(R.id.setting_porta_imap);

		saveButton = (Button) v.findViewById(R.id.setting_save);
		return v;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		mPref = getActivity().getSharedPreferences(Preferences.SHARED_NAME, Context.MODE_PRIVATE);

		setEditText();

		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveValue();

			}
		});
	}

	private void setEditText() {

		if (this.type == EmailType.NORMAL_TYPE) {
			mEditUsername.setText(mPref.getString(Preferences.EMAIL_USERNAME, ""));
			mEditPassword.setText(mPref.getString(Preferences.EMAIL_PASSWORD, ""));
			mEditSmtp.setText(mPref.getString(Preferences.EMAIL_SMTP, ""));
			mEditPop3.setText(mPref.getString(Preferences.EMAIL_IMAP, ""));

			mCheckBoxSSL.setChecked(mPref.getBoolean(Preferences.EMAIL_SSL, false));
			mEditSmtpPort.setText(mPref.getString(Preferences.EMAIL_SMTP_PORT, ""));
			mEditImapPort.setText(mPref.getString(Preferences.EMAIL_IMAP_PORT, ""));

		} else {

			mEditUsername.setText(mPref.getString(Preferences.PEC_USERNAME, ""));
			mEditPassword.setText(mPref.getString(Preferences.PEC_PASSWORD, ""));
			mEditSmtp.setText(mPref.getString(Preferences.PEC_SMTP, ""));
			mEditPop3.setText(mPref.getString(Preferences.PEC_IMAP, ""));
			mCheckBoxSSL.setChecked(mPref.getBoolean(Preferences.PEC_SSL, false));
			mEditSmtpPort.setText(mPref.getString(Preferences.PEC_SMTP_PORT, ""));
			mEditImapPort.setText(mPref.getString(Preferences.PEC_IMAP_PORT, ""));
		}

	}

	private void saveValue() {

		Editor edit = mPref.edit();

		if (this.type == EmailType.NORMAL_TYPE) {
			edit.putString(Preferences.EMAIL_USERNAME, mEditUsername.getText().toString());
			edit.putString(Preferences.EMAIL_PASSWORD, mEditPassword.getText().toString());
			edit.putString(Preferences.EMAIL_SMTP, mEditSmtp.getText().toString());
			edit.putString(Preferences.EMAIL_IMAP, mEditPop3.getText().toString());
			edit.putBoolean(Preferences.EMAIL_SSL, mCheckBoxSSL.isChecked());
			edit.putString(Preferences.EMAIL_SMTP_PORT, mEditSmtpPort.getText().toString());
			edit.putString(Preferences.EMAIL_IMAP_PORT, mEditImapPort.getText().toString());
		} else {

			edit.putString(Preferences.PEC_USERNAME, mEditUsername.getText().toString());
			edit.putString(Preferences.PEC_PASSWORD, mEditPassword.getText().toString());
			edit.putString(Preferences.PEC_SMTP, mEditSmtp.getText().toString());
			edit.putString(Preferences.PEC_IMAP, mEditPop3.getText().toString());
			edit.putBoolean(Preferences.PEC_SSL, mCheckBoxSSL.isChecked());
			edit.putString(Preferences.PEC_SMTP_PORT, mEditSmtpPort.getText().toString());
			edit.putString(Preferences.PEC_IMAP_PORT, mEditImapPort.getText().toString());
		}
		edit.commit();

	}

}
