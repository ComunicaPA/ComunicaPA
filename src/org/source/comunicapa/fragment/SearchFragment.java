package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.SearchPActivity;
import org.source.comunicapa.constants.IntentTag;
import org.source.comunicapa.test.lod.DataGetter;
import org.source.comunicapa.test.main.MainMock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		MainMock mock = new MainMock();
		DataGetter getter = new DataGetter();

		mock.stampaPerComune(getter.searchForComune("Avola"));

		View view = inflater.inflate(R.layout.layout_fragment_search, null);

		final EditText searchText = (EditText) view.findViewById(R.id.edit_search_text);
		final RadioGroup searchtype = (RadioGroup) view.findViewById(R.id.radio_search_type);

		Button searchButton = (Button) view.findViewById(R.id.button_search);

		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), SearchPActivity.class);

				intent.putExtra(IntentTag.SEARCH_TEXT, searchText.getEditableText().toString());
				intent.putExtra(IntentTag.SEARCH_TYPE, searchtype.getCheckedRadioButtonId());

				startActivity(intent);

			}
		});

		return view;
	}
}
