package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.adapter.DashboardAdapter;
import org.source.comunicapa.adapter.item.DashBoardItem;

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
import android.widget.GridView;
import android.widget.LinearLayout;

public class DashboardFragment extends Fragment {

	private GridView mGrid;

	private DashBoardItem[] objects = new DashBoardItem[] { new DashBoardItem(R.string.dash_search, R.drawable.dash_search, new SearchFragment()),
			new DashBoardItem(R.string.dash_email, R.drawable.dash_email, new FolderEmailFragment()),
			new DashBoardItem(R.string.dash_rubrica, R.drawable.dash_rubrica, new RubricaFragment()),
			new DashBoardItem(R.string.dash_info, R.drawable.dash_info, new InfoFragment())

	};

	private LinearLayout baseLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.layout_fragment_dashboard, null);

		mGrid = (GridView) layout.findViewById(R.id.dashboard_grid_view);

		return layout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		DashboardAdapter adapter = new DashboardAdapter(getSupportActivity().getBaseContext(), R.layout.layout_dashboard_entry, objects);

		mGrid.setAdapter(adapter);

		baseLayout = (LinearLayout) getActivity().findViewById(R.id.base_layout);
		baseLayout.setGravity(Gravity.CENTER);

		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter,
						R.anim.fragment_slide_right_exit);

				fragmentTransaction.replace(R.id.base_layout, objects[position].getFragment());
				fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

			}

		};
		mGrid.setOnItemClickListener(listener);

		super.onActivityCreated(savedInstanceState);
	}
}
