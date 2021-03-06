package org.source.comunicapa.fragment;

import java.util.List;

import org.source.comunicapa.R;
import org.source.comunicapa.adapter.ListComuniAdapter;
import org.source.comunicapa.model.Comune;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListaComuniFragment extends ListFragment {

	private List<Comune> list;

	public ListaComuniFragment(List<Comune> list) {
		this.list = list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_fragment_comuni, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		ListComuniAdapter adapter = new ListComuniAdapter(getActivity());
		adapter.setComuni(list);

		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		ComuneFragment fragment = new ComuneFragment(this.list.get(position));

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		

		fragmentTransaction.replace(R.id.layout_search_container, fragment);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

		fragmentTransaction.commit();

	}

}
