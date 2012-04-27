package org.source.comunicapa;

import org.source.comunicapa.fragment.SettingMainFragment;
import org.source.comunicapa.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class SettingPActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTheme(R.style.theme_action);
		setContentView(R.layout.layout_setting_main);

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
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		fragmentTransaction.replace(R.id.layout_setting_base_main, new SettingMainFragment());
		fragmentTransaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_menu, menu);
		menu.removeItem(R.id.setting);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// mViewPager.setVisibility(View.GONE);
		// mBaseLayout.setVisibility(View.VISIBLE);

		switch (item.getItemId()) {
		//
		// case R.id.search:
		// startActivity(new Intent(this, SearchPActivity.class));
		// return true;
		case R.id.mail:
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

	// private void setEditText() {
	//
	// mEditUsername = (EditText) findViewById(R.id.setting_username);
	// mEditUsername.setText(mPref.getString(Preferences.EMAIL_USERNAME, ""));
	//
	// mEditPassword = (EditText) findViewById(R.id.setting_password);
	// mEditPassword.setText(mPref.getString(Preferences.EMAIL_PASSWORD, ""));
	//
	// mEditSmtp = (EditText) findViewById(R.id.setting_smtp);
	// mEditSmtp.setText(mPref.getString(Preferences.EMAIL_SMTP, ""));
	//
	// mEditPop3 = (EditText) findViewById(R.id.setting_imap);
	// mEditPop3.setText(mPref.getString(Preferences.EMAIL_POP3, ""));
	//
	// }
	//
	// private void saveValue() {
	//
	// Editor edit = mPref.edit();
	//
	// edit.putString(Preferences.EMAIL_USERNAME,
	// mEditUsername.getText().toString());
	// edit.putString(Preferences.EMAIL_PASSWORD,
	// mEditPassword.getText().toString());
	// edit.putString(Preferences.EMAIL_SMTP, mEditSmtp.getText().toString());
	// edit.putString(Preferences.EMAIL_POP3, mEditPop3.getText().toString());
	//
	// edit.commit();
	//
	// }

}
