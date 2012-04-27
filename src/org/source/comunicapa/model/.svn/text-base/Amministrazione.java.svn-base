package org.source.comunicapa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Amministrazione {
	private String codice;
	private String label;
	private String comune;
	private String homepage;
	private String telefono;
	private String indirizzo;
	private List<String> email;
	private List<String> pec;
	private List<String> cecpac;
	// private String acronimo;
	// private String codice_fiscale;
	private List<Servizio> servizi;

	private Map<String, UfficioProtocollo> uffici_protocollo;
	private Map<String, UnitaOrganizzativa> unita_organizzative;

	/*
	 * public Amministrazione() { this.servizi = null; this.uffici_protocollo =
	 * null; this.unita_organizzative = null; this.email = null; this.cecpac =
	 * null; this.pec = null; }
	 */
	public Amministrazione(String codice, String label) {
		this.codice = codice;
		this.label = label;
		this.servizi = null;
		this.uffici_protocollo = null;
		this.unita_organizzative = null;
		this.email = null;
		this.cecpac = null;
		this.pec = null;
	}

	/*
	 * public Amministrazione(String codice) { this.codice = codice;
	 * this.servizi = null; this.uffici_protocollo = null;
	 * this.unita_organizzative = null; this.email = null; this.cecpac = null;
	 * this.pec = null; }
	 */

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/*
	 * public String getTelefono() { return telefono; }
	 * 
	 * public void setTelefono(String telefono) { this.telefono = telefono; }
	 */

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	/*
	 * public String getAcronimo() { return acronimo; }
	 * 
	 * public void setAcronimo(String acronimo) { this.acronimo = acronimo; }
	 */

	/*
	 * public String getCodiceFiscale() { return codice_fiscale; }
	 * 
	 * public void setCodiceFiscale(String codiceFiscale) { codice_fiscale =
	 * codiceFiscale; }
	 */

	public List<Servizio> getServizi() {
		return servizi;
	}

	public Collection<UfficioProtocollo> getUfficiProtocollo() {
		if (this.uffici_protocollo != null)
			return (Collection<UfficioProtocollo>) this.uffici_protocollo.values();
		else
			return null;
	}

	public Collection<UnitaOrganizzativa> getUnitaOrganizzative() {
		if (this.unita_organizzative != null)
			return (Collection<UnitaOrganizzativa>) this.unita_organizzative.values();
		else
			return null;
	}

	public void addServizio(Servizio s) {
		if (this.servizi == null)
			this.servizi = new ArrayList<Servizio>();
		this.servizi.add(s);
	}

	public void addUfficioProtocollo(UfficioProtocollo u) {
		if (this.uffici_protocollo == null)
			this.uffici_protocollo = new HashMap<String, UfficioProtocollo>();
		this.uffici_protocollo.put(u.getCodice(), u);
	}

	public void addUnitaOrganizzativa(UnitaOrganizzativa u) {
		if (this.unita_organizzative == null)
			this.unita_organizzative = new HashMap<String, UnitaOrganizzativa>();
		this.unita_organizzative.put(u.getCodice(), u);
	}

	public boolean containsUfficioProtocollo(String up) {
		return this.uffici_protocollo.containsKey(up);
	}

	public boolean containsUnitaOrganizzative(String uo) {
		return this.unita_organizzative.containsKey(uo);
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}
}
