package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.adapter.AmministrazioneAdapter;
import org.source.comunicapa.model.Amministrazione;
import org.source.comunicapa.model.Comune;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ComuneFragment extends Fragment {

	private Comune comune;

	private ListView amministrazioniList;

	private TextView comuneText, capText;

	public ComuneFragment(Comune comune) {
		this.comune = comune;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_comune_fragment_info, container, false);

		amministrazioniList = (ListView) view.findViewById(R.id.list_amministrazioni);
		comuneText = (TextView) view.findViewById(R.id.text_comune_name);
		capText = (TextView) view.findViewById(R.id.text_cap_name);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		comuneText.setText(comune.getComune());

		if (comune.getCap() != null) {
			capText.setText(capText.getText().toString().replace("%d", comune.getCap()));

		} else {

			capText.setVisibility(View.GONE);
		}

		final AmministrazioneAdapter adapter = new AmministrazioneAdapter(getActivity());
		adapter.setAmministrazioneList(comune.getAmministrazioni());

		amministrazioniList.setAdapter(adapter);

		amministrazioniList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				Amministrazione amm = adapter.getItem(position);

				AmministrazioneFragment fragment = new AmministrazioneFragment(amm);

				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter,
						R.anim.fragment_slide_right_exit);

				fragmentTransaction.replace(R.id.layout_search_container, fragment);
				fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

				fragmentTransaction.commit();

			}
		});
	}
}
