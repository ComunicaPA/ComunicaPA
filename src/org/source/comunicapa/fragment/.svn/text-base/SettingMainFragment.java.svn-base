package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.constants.EmailType;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingMainFragment extends Fragment {

	private TextView setPec;
	private TextView setEmail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.layout_fragment_setting_base, container, false);

		setPec = (TextView) v.findViewById(R.id.setting_pec_email);
		setEmail = (TextView) v.findViewById(R.id.setting_account_email);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		setPec.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showSettingFragment(EmailType.PEC_TYPE);

			}
		});

		setEmail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showSettingFragment(EmailType.NORMAL_TYPE);

			}
		});
	}

	private void showSettingFragment(int type) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter,
				R.anim.fragment_slide_right_exit);

		fragmentTransaction.replace(R.id.layout_setting_base_main, new SettingEmailFragment(type));
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}
