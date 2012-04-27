package org.source.comunicapa.adapter.item;

import android.support.v4.app.Fragment;

public class DashBoardItem {

	private int title;
	private int icon;
	private Fragment fragment;

	public DashBoardItem(int title, int icon, Fragment fragment) {
		this.title = title;
		this.icon = icon;
		this.fragment = fragment;
	}

	public int getTitle() {
		return title;
	}

	public int getIcon() {
		return icon;
	}

	public Fragment getFragment() {
		return fragment;
	}

}
