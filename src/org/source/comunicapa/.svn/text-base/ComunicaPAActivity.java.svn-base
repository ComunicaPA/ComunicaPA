package org.source.comunicapa;

import org.source.comunicapa.adapter.TabsAdapter;
import org.source.comunicapa.fragment.DashboardFragment;
import org.source.comunicapa.service.CheckEmailFolderService;
import org.source.comunicapa.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ComunicaPAActivity extends FragmentActivity {

	ViewPager mViewPager;
	LinearLayout mBaseLayout;
	TabsAdapter mTabsAdapter;

	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.theme_action);
		setContentView(R.layout.main);
		getActionBar().hide();

		Typeface typeface = Utils.getTypeface(this, "organica.ttf");

		((TextView) findViewById(R.id.splash_text)).setTypeface(typeface);

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

		// ActionBar bar = getSupportActionBar();

		// bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ActionBar.Tab tab1 =
		// bar.newTab().setText(getString(R.string.rubrica));
		//
		// ActionBar.Tab tab2 =
		// bar.newTab().setText(getString(R.string.cartelle));

		// mViewPager = (ViewPager) findViewById(R.id.pager);
		mBaseLayout = (LinearLayout) findViewById(R.id.base_layout);

		// mTabsAdapter = new TabsAdapter(getSupportFragmentManager());

		// mTabsAdapter = new TabsAdapter(this, getSupportActionBar(),
		// mViewPager);
		// mTabsAdapter.addTab(tab2, CartelleFragment.class);
		// mTabsAdapter.addTab(tab1, RubricaFragment.class);

		startService(new Intent(this, CheckEmailFolderService.class));

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		fragmentTransaction.replace(R.id.base_layout, new DashboardFragment());
		fragmentTransaction.commit();

		closeSplash();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_menu, menu);

		return true;

		// menu.add("Save").setIcon(R.drawable.mail).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		//
		// menu.add("Setting").setIcon(R.drawable.setting).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		// //
		// menu.add("Refresh").setIcon(R.drawable.ic_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// mViewPager.setVisibility(View.GONE);
		// mBaseLayout.setVisibility(View.VISIBLE);

		switch (item.getItemId()) {

		// case R.id.search:
		// startActivity(new Intent(this, SearchPActivity.class));
		// break;
		case R.id.mail:
			Intent intentEmail = new Intent(this, SendEmailActivity.class);
			startActivity(intentEmail);
			return true;
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, ComunicaPAActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.setting:
			Intent intentSetting = new Intent(this, SettingPActivity.class);
			startActivity(intentSetting);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

		// getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		// FragmentTransaction transaction =
		// getSupportFragmentManager().beginTransaction();
		//
		// // Replace whatever is in the fragment_container view with this
		// // fragment,
		// // and add the transaction to the back stack
		//
		// transaction.replace(R.id.base_layout, f);
		// transaction.addToBackStack(null);
		//
		// // Commit the transaction
		// transaction.commit();

	}

	private void closeSplash() {
		handler.postDelayed(new Runnable() {
			public void run() {
				Animation animation = AnimationUtils.loadAnimation(ComunicaPAActivity.this, R.anim.fadeout);
				animation.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						if (findViewById(R.id.layout_spalsh) != null) {
							findViewById(R.id.layout_spalsh).setVisibility(View.GONE);
							getActionBar().show();
						}

					}
				});
				if (findViewById(R.id.layout_spalsh) != null) {

					findViewById(R.id.layout_spalsh).startAnimation(animation);
				}
			}

		}, 2200);

	}

}