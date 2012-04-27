package org.source.comunicapa.model;

import java.util.ArrayList;
import java.util.List;

public class UfficioProtocollo {
	private String codice_amministrazione;
	private String label_amministrazione;
	private String codice;
	private String label;
	private String homepage;
	private String comune;
	private List<String> email;
	private List<String> pec;
	private List<String> cecpac;
	private String telefono;
	private String indirizzo;
	// private String fax;
	private List<Servizio> servizi;

	public UfficioProtocollo(String codice, String label) {
		this.codice = codice;
		this.label = label;
		this.servizi = null;
		this.email = null;
		this.cecpac = null;
		this.pec = null;
	}

	public String getCodice() {
		return codice;
	}

	public String getLabel() {
		return label;
	}

	/*
	 * public void setLabel(String label) { this.label = label; }
	 */
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public List<String> getEmail() {
		return email;
	}

	public void addEmail(String email) {
		if (this.email == null)
			this.email = new ArrayList<String>();
		if (!this.email.contains(email))
			this.email.add(email);
	}

	public List<String> getPec() {
		return pec;
	}

	public void addPec(String pec) {
		if (this.pec == null)
			this.pec = new ArrayList<String>();
		if (!this.pec.contains(pec))
			this.pec.add(pec);
	}

	public List<String> getCecpac() {
		return cecpac;
	}

	public void addCecpac(String cecpac) {
		if (this.cecpac == null)
			this.cecpac = new ArrayList<String>();
		if (!this.cecpac.contains(cecpac))
			this.cecpac.add(cecpac);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	/*
	 * public String getFax() { return fax; }
	 * 
	 * public void setFax(String fax) { this.fax = fax; }
	 */

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void addServizio(Servizio s) {
		if (this.servizi == null)
			this.servizi = new ArrayList<Servizio>();
		this.servizi.add(s);
	}

	public String getCodiceAmministrazione() {
		return codice_amministrazione;
	}

	public void setCodiceAmministrazione(String codiceAmministrazione) {
		codice_amministrazione = codiceAmministrazione;
	}

	public String getLabelAmministrazione() {
		return label_amministrazione;
	}

	public void setLabelAmministrazione(String labelAmministrazione) {
		label_amministrazione = labelAmministrazione;
	}
}
