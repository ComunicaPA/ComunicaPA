package org.source.comunicapa.fragment;

import org.source.comunicapa.R;
import org.source.comunicapa.adapter.EmailExpandAdapter;
import org.source.comunicapa.model.Amministrazione;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class AmministrazioneFragment extends Fragment {

	private Amministrazione amministrazione;

	private ExpandableListView listEmail, listPec, listCecpac;

	private ImageView addContactButton;

	public AmministrazioneFragment(Amministrazione amministrazione) {

		this.amministrazione = amministrazione;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_amministrazione_fragment, container, false);

		TextView label = (TextView) view.findViewById(R.id.text_amministrazione);
		label.setText(this.amministrazione.getLabel());

		TextView indirizzo = (TextView) view.findViewById(R.id.text_amministrazione_indirizzo);
		indirizzo.setText(this.amministrazione.getIndirizzo() + " " + this.amministrazione.getComune());

		TextView homepage = (TextView) view.findViewById(R.id.text_amministrazione_homepage);

		if (this.amministrazione.getHomepage() != null) {
			homepage.setText(this.amministrazione.getHomepage().trim());
			homepage.setVisibility(View.VISIBLE);
		} else {
			homepage.setVisibility(View.GONE);

		}
		TextView phone = (TextView) view.findViewById(R.id.text_amministrazione_homepage);
		if (this.amministrazione.getTelefono() != null) {

			phone.setText(this.amministrazione.getTelefono().trim());
			phone.setVisibility(View.VISIBLE);
		} else {
			phone.setVisibility(View.GONE);
		}
		listEmail = (ExpandableListView) view.findViewById(R.id.expand_listview_amministrazione_email);
		listPec = (ExpandableListView) view.findViewById(R.id.expand_listview_amministrazione_pec);
		listCecpac = (ExpandableListView) view.findViewById(R.id.expand_listview_amministrazione_cecpac);
		addContactButton = (ImageView) view.findViewById(R.id.button_add_administration_contact);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		if (this.amministrazione.getCecpac() == null || this.amministrazione.getCecpac().size() == 0) {
			System.out.println("CECPAC == 0");
		} else {
			System.out.println("CECPAC == " + this.amministrazione.getCecpac().size());
		}

		if (this.amministrazione.getEmail() == null || this.amministrazione.getEmail().size() == 0) {
			System.out.println("EMAIL == 0");
		} else {
			System.out.println("Email == " + this.amministrazione.getEmail().size());
		}

		if (this.amministrazione.getPec() == null || this.amministrazione.getPec().size() == 0) {
			System.out.println("PEC == 0");
		} else {
			System.out.println("PEC == " + this.amministrazione.getPec().size());
		}

		EmailExpandAdapter emailAdapter = new EmailExpandAdapter(getActivity(), "email");
		emailAdapter.setEmails(this.amministrazione.getEmail());

		if (emailAdapter.getChildrenCount(0) == 0) {
			listEmail.setVisibility(View.GONE);
		} else {
			listEmail.setVisibility(View.VISIBLE);
			listEmail.setAdapter(emailAdapter);
		}

		EmailExpandAdapter cepacAdapter = new EmailExpandAdapter(getActivity(), "CeCPac");
		cepacAdapter.setEmails(this.amministrazione.getCecpac());

		if (emailAdapter.getChildrenCount(0) == 0) {
			listCecpac.setVisibility(View.GONE);
		} else {
			listCecpac.setVisibility(View.VISIBLE);
			listCecpac.setAdapter(cepacAdapter);
		}

		EmailExpandAdapter pecAdapter = new EmailExpandAdapter(getActivity(), "Pec");
		pecAdapter.setEmails(this.amministrazione.getPec());

		if (emailAdapter.getChildrenCount(0) == 0) {
			listPec.setVisibility(View.GONE);
		} else {
			listPec.setVisibility(View.VISIBLE);
			listPec.setAdapter(pecAdapter);
		}

		addContactButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}
}
