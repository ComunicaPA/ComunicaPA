package org.source.comunicapa.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Comune {
	private String comune;
	private String cap;
	private Map<String, Amministrazione> amministrazioni;

	public Comune(String comune) {
		this.comune = comune;
		this.amministrazioni = new HashMap<String, Amministrazione>();
	}

	// TBD: questo costruttore forse non serve
	/*
	 * public Comune(String comune, String cap) { this.comune = comune; this.cap
	 * = cap; this.amministrazioni = new HashMap<String, Amministrazione>(); }
	 */

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void addAmministrazione(Amministrazione amm) {
		this.amministrazioni.put(amm.getCodice(), amm);
	}

	public boolean contains(String amm) {
		return this.amministrazioni.containsKey(amm);
	}

	public Amministrazione getAmministrazione(String amm) {
		if (this.amministrazioni.containsKey(amm))
			return this.amministrazioni.get(amm);
		else
			return null;
	}

	public Collection<Amministrazione> getAmministrazioni() {

		return (Collection<Amministrazione>) this.amministrazioni.values();
	}

	@Override
	public String toString() {

		if (getCap() != null) {
			return getComune() + " - CAP: " + getCap();
		} else {
			return getComune();
		}

	}
}
