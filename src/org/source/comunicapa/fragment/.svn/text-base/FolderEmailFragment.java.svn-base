package org.source.comunicapa.fragment;

import org.source.comunicapa.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FolderEmailFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.layout_fragment_email_folder, null);

		LinearLayout draft = (LinearLayout) view.findViewById(R.id.layout_email_draft);

		draft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Bundle bundle = getArguments();
				// bundle.putBoolean(BundleTag.BUNDLE_DRAFT_EMAIL, true);
				// setArguments(bundle);

				showListFragment(true);

			}
		});

		LinearLayout sent = (LinearLayout) view.findViewById(R.id.layout_email_sent);
		sent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Bundle bundle = getArguments();
				// bundle.putBoolean(BundleTag.BUNDLE_DRAFT_EMAIL, false);
				// setArguments(bundle);
				showListFragment(false);

			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		((LinearLayout) getActivity().findViewById(R.id.base_layout)).setGravity(Gravity.CENTER);
	}

	private void showListFragment(boolean isDraft) {

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter,
				R.anim.fragment_slide_right_exit);

		fragmentTransaction.replace(R.id.base_layout, new ListEmailFragment(isDraft));
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}
