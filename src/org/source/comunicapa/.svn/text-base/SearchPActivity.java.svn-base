package org.source.comunicapa;

import java.util.List;

import org.source.comunicapa.constants.IntentTag;
import org.source.comunicapa.constants.SearchType;
import org.source.comunicapa.fragment.ListaComuniFragment;
import org.source.comunicapa.model.Comune;
import org.source.comunicapa.test.lod.DataGetter2;
import org.source.comunicapa.utils.Constants;
import org.source.comunicapa.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchPActivity extends FragmentActivity {

	private SearchAsyncTask task;
	private LinearLayout progressSearchLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setTheme(R.style.theme_action);
		setContentView(R.layout.layout_search);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
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

		progressSearchLayout = (LinearLayout) findViewById(R.id.layout_search_running);

		task = new SearchAsyncTask();
		task.execute(getIntent());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_menu, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

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

	private class SearchAsyncTask extends AsyncTask<Intent, Void, List<Comune>> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			progressSearchLayout.setVisibility(View.VISIBLE);

		}

		@Override
		protected List<Comune> doInBackground(Intent... params) {

			String searchText = params[0].getStringExtra(IntentTag.SEARCH_TEXT);

			switch (params[0].getIntExtra(IntentTag.SEARCH_TYPE, 1)) {

			case SearchType.SEARCH_BY_CENTRAL_ADMIN:
				return new DataGetter2().searchForComune(searchText);
			case SearchType.SEARCH_BY_LOCAL_ADMIN:

				return new DataGetter2().searchForComune(searchText);
			case Constants.SEARCH_BY_CITY:

				return new DataGetter2().searchForComune(searchText);
				// return new DataGetter().searchForComune(searchText);
			default:
				return null;
			}

		}

		@Override
		protected void onPostExecute(List<Comune> result) {

			super.onPostExecute(result);

			progressSearchLayout.setVisibility(View.GONE);

			ListaComuniFragment fragment = new ListaComuniFragment(result);

			FragmentManager fragmentManager = getSupportFragmentManager();

			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			// fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
			// R.anim.fragment_slide_left_exit,
			// R.anim.fragment_slide_right_enter,
			// R.anim.fragment_slide_right_exit);

			fragmentTransaction.replace(R.id.layout_search_container, fragment);

			// fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			// fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			task.cancel(true);

		}

		return super.onKeyDown(keyCode, event);
	}
}
