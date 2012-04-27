package src.org.source.comunicapa.test.lod;

import java.util.ArrayList;
import java.util.List;

import model.Impostazioni;
import model.Servizio;
import model.UfficioProtocollo;
import model.UnitaOrganizzativa;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

/*** FUNZIONI COMMON PER I SERVIZI ON-DEMAND ***/
public class DataGetterCommons {
	public List<Servizio> searchServiziForAmministrazione(String cod_amm) {
		List<Servizio> servizi = new ArrayList<Servizio>();
		String queryString = "prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"prefix rdfs: <http://w3.org/2000/01/rdf-schema#> "+ 
							"prefix rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#>  "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/>  "+
							"select ?servizio ?nome_servizio ?note ?telefono ?sito_web ?pec ?email ?cecpac "+
							"where  "+
							"{  "+
							"   <"+cod_amm+"> rdf:type digitPA:Amministrazione . "+
							"	<"+cod_amm+"> digitPA:eroga ?servizio .  "+
							"	?servizio rdfs:label ?nome_servizio .  "+
							"	OPTIONAL { ?servizio rdfs:comment ?note } . "+ 
							"	OPTIONAL { ?servizio foaf:phone ?telefono } .  "+
							"	OPTIONAL { ?servizio foaf:homepage ?sito_web } .  "+
							"	OPTIONAL { ?servizio digitPA:PEC ?pec } .  "+
							"	OPTIONAL { ?servizio foaf:mbox ?email } .  "+
							"	OPTIONAL { ?servizio digitPA:CEC-PAC ?cecpac } . "+ 
							"} "+
							"order by ?servizio";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
		
		ResultSet resultsServizi  = qexec.execSelect();
		String cod_serv, servizio;
		Servizio serv = null;
		
		for ( ; resultsServizi.hasNext() ; )
		{
			QuerySolution solutionServizio = resultsServizi.nextSolution() ;
			
			if(solutionServizio.getResource("servizio") != null && solutionServizio.getLiteral("nome_servizio") != null){
				String sito_web=null, descrizione=null, pec = null, email = null, cecpac=null, telefono = null;
				servizio = solutionServizio.getLiteral("nome_servizio").toString();
				cod_serv = solutionServizio.getResource("servizio").toString();				
				
				serv = new Servizio(cod_serv);
				serv.setNome(servizio);
				
				servizi.add(serv);
				
				if(solutionServizio.getLiteral("note") != null){
					descrizione = solutionServizio.getLiteral("note").toString() ;
					serv.setDescrizione(descrizione);
				}
				
				if(solutionServizio.getLiteral("sito_web") != null){
					sito_web = solutionServizio.getLiteral("sito_web").toString() ;
					serv.setHomepage(sito_web);
				}
				
				if(solutionServizio.getLiteral("telefono") != null){
					telefono = solutionServizio.getLiteral("telefono").toString() ;
					serv.setTelefono(telefono);
				}
				
				if(solutionServizio.getLiteral("pec") != null){
					pec = solutionServizio.getLiteral("pec").toString() ;
					serv.setPec(pec);
				}
			
			}
		}
		return servizi;					
	}
		
	public List<Servizio> searchServiziForUnitaOrganizzativa(String codUo) {
		List<Servizio> servizi = new ArrayList<Servizio>();
		String queryString = "prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"prefix rdfs: <http://w3.org/2000/01/rdf-schema#> "+ 
							"prefix rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#>  "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/>  "+
							"select ?servizio ?nome_servizio ?note ?telefono ?sito_web ?pec ?email ?cecpac "+
							"where  "+
							"{  "+
							"   <"+codUo+"> rdf:type digitPA:UnitaOrganizzativa . "+
							"	<"+codUo+"> digitPA:eroga ?servizio .  "+
							"	?servizio rdfs:label ?nome_servizio .  "+
							"	OPTIONAL { ?servizio rdfs:comment ?note } . "+ 
							"	OPTIONAL { ?servizio foaf:phone ?telefono } .  "+
							"	OPTIONAL { ?servizio foaf:homepage ?sito_web } .  "+
							"	OPTIONAL { ?servizio digitPA:PEC ?pec } .  "+
							"	OPTIONAL { ?servizio foaf:mbox ?email } .  "+
							"	OPTIONAL { ?servizio digitPA:CEC-PAC ?cecpac } . "+ 
							"} "+
							"order by ?servizio";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
		
		ResultSet resultsServizi  = qexec.execSelect();
		String cod_serv, servizio;
		Servizio serv = null;
		
		for ( ; resultsServizi.hasNext() ; )
		{
			QuerySolution solutionServizio = resultsServizi.nextSolution() ;
			
			if(solutionServizio.getResource("servizio") != null && solutionServizio.getLiteral("nome_servizio") != null){
				String sito_web=null, descrizione=null, pec = null, email = null, cecpac=null, telefono = null;
				servizio = solutionServizio.getLiteral("nome_servizio").toString();
				cod_serv = solutionServizio.getResource("servizio").toString();				
				
				serv = new Servizio(cod_serv);
				serv.setNome(servizio);
				
				servizi.add(serv);
				
				if(solutionServizio.getLiteral("note") != null){
					descrizione = solutionServizio.getLiteral("note").toString() ;
					serv.setDescrizione(descrizione);
				}
				
				if(solutionServizio.getLiteral("sito_web") != null){
					sito_web = solutionServizio.getLiteral("sito_web").toString() ;
					serv.setHomepage(sito_web);
				}
				
				if(solutionServizio.getLiteral("telefono") != null){
					telefono = solutionServizio.getLiteral("telefono").toString() ;
					serv.setTelefono(telefono);
				}
				
				if(solutionServizio.getLiteral("pec") != null){
					pec = solutionServizio.getLiteral("pec").toString() ;
					serv.setPec(pec);
				}
				
				if(solutionServizio.getLiteral("email") != null){
					email = solutionServizio.getLiteral("email").toString() ;
					serv.setEmail(email);
				}
				
				if(solutionServizio.getLiteral("cecpac") != null){
					pec = solutionServizio.getLiteral("cecpac").toString() ;
					serv.setCecpac(cecpac);
				}
			}
		}
		return servizi;	
	}

	//restituisce i servizi di un ufficio protocollo
	public List<Servizio> searchServiziForUfficioProtocollo(String codUp) {
		List<Servizio> servizi = new ArrayList<Servizio>();
		String queryString = "prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"prefix rdfs: <http://w3.org/2000/01/rdf-schema#> "+ 
							"prefix rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#>  "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/>  "+
							"select ?servizio ?nome_servizio ?note ?telefono ?sito_web ?pec ?email ?cecpac "+
							"where  "+
							"{  "+
							"   <"+codUp+"> rdf:type digitPA:UfficioProtocolloAOO . "+
							"	<"+codUp+"> digitPA:eroga ?servizio .  "+
							"	?servizio rdfs:label ?nome_servizio .  "+
							"	OPTIONAL { ?servizio rdfs:comment ?note } . "+ 
							"	OPTIONAL { ?servizio foaf:phone ?telefono } .  "+
							"	OPTIONAL { ?servizio foaf:homepage ?sito_web } .  "+
							"	OPTIONAL { ?servizio digitPA:PEC ?pec } .  "+
							"	OPTIONAL { ?servizio foaf:mbox ?email } .  "+
							"	OPTIONAL { ?servizio digitPA:CEC-PAC ?cecpac } . "+ 
							"} "+
							"order by ?servizio";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
		
		ResultSet resultsServizi  = qexec.execSelect();
		String cod_serv, servizio;
		Servizio serv = null;
		
		for ( ; resultsServizi.hasNext() ; )
		{
			QuerySolution solutionServizio = resultsServizi.nextSolution() ;
			
			if(solutionServizio.getResource("servizio") != null && solutionServizio.getLiteral("nome_servizio") != null){
				String sito_web=null, descrizione=null, pec = null, email = null, cecpac=null, telefono = null;
				servizio = solutionServizio.getLiteral("nome_servizio").toString();
				cod_serv = solutionServizio.getResource("servizio").toString();				
				
				serv = new Servizio(cod_serv);
				serv.setNome(servizio);
				
				servizi.add(serv);
				
				if(solutionServizio.getLiteral("note") != null){
					descrizione = solutionServizio.getLiteral("note").toString() ;
					serv.setDescrizione(descrizione);
				}
				
				if(solutionServizio.getLiteral("sito_web") != null){
					sito_web = solutionServizio.getLiteral("sito_web").toString() ;
					serv.setHomepage(sito_web);
				}
				
				if(solutionServizio.getLiteral("telefono") != null){
					telefono = solutionServizio.getLiteral("telefono").toString() ;
					serv.setTelefono(telefono);
				}
				
				if(solutionServizio.getLiteral("pec") != null){
					pec = solutionServizio.getLiteral("pec").toString() ;
					serv.setPec(pec);
				}
				
				if(solutionServizio.getLiteral("email") != null){
					email = solutionServizio.getLiteral("email").toString() ;
					serv.setEmail(email);
				}
				
				if(solutionServizio.getLiteral("cecpac") != null){
					pec = solutionServizio.getLiteral("cecpac").toString() ;
					serv.setCecpac(cecpac);
				}
			}
		}
		return servizi;	
	}
	
	public List<UnitaOrganizzativa> searchUnitaOrganizzativeForAmministrazione(String cod_amm) {
		List<UnitaOrganizzativa> unita_organizzative = new ArrayList<UnitaOrganizzativa>();
		String queryString = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
								"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
								"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
								"prefix geonames: <http://www.geonames.org/ontology#> "+
								"prefix foaf: <http://xmlns.com/foaf/0.1/> "+
								"select ?cod_uo ?unita_organizzativa ?comune ?sito_web ?telefono ?indirizzo ?pec ?email ?cecpac "+
								"where{ "+
								"	?cod_uo rdf:type digitPA:UnitaOrganizzativa . "+
								"	?cod_uo rdfs:label ?unita_organizzativa . "+
								"	?cod_uo geonames:locatedIn ?cod_com . "+
								"	?cod_com rdfs:label ?comune . "+
								"	?cod_uo	 digitPA:afferisce <"+cod_amm+"> . "+
								"	<"+cod_amm+"> rdf:type digitPA:Amministrazione . "+
								"	OPTIONAL { ?cod_uo foaf:homepage ?sito_web } . "+
								"	OPTIONAL { ?cod_uo digitPA:indirizzo ?indirizzo } . "+ 
								"	OPTIONAL { ?cod_uo digitPA:PEC ?pec } .  "+
								"	OPTIONAL { ?cod_uo foaf:mbox ?email } . "+
								"	OPTIONAL { ?cod_uo digitPA:CEC-PAC ?cecpac } . "+
								"	OPTIONAL { ?cod_uo foaf:phone ?telefono  } . "+
								"} "+
								"ORDER BY ?cod_uo";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
		
		ResultSet resultsUnitaOrganizzative  = qexec.execSelect();
		String cod_uo, unita_organizzativa, last_cod_uo=null;
		UnitaOrganizzativa uo = null;
		for ( ; resultsUnitaOrganizzative.hasNext() ; )
		{
			QuerySolution solutionUnitaOrganizzativa = resultsUnitaOrganizzative.nextSolution() ;
			
			if(solutionUnitaOrganizzativa.getResource("cod_uo") != null && solutionUnitaOrganizzativa.getLiteral("unita_organizzativa") != null){
				String comune = null, sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null, telefono = null;
				unita_organizzativa = solutionUnitaOrganizzativa.getLiteral("unita_organizzativa").toString();
				cod_uo = solutionUnitaOrganizzativa.getResource("cod_uo").toString();
				comune = solutionUnitaOrganizzativa.getLiteral("comune").toString();

				if(last_cod_uo != cod_uo){
					uo = new UnitaOrganizzativa(cod_uo, unita_organizzativa);
					uo.setComune(comune);
					uo.setCodiceAmministrazione(cod_amm);
		
					unita_organizzative.add(uo);
					
					if(solutionUnitaOrganizzativa.getLiteral("indirizzo") != null){
						indirizzo = solutionUnitaOrganizzativa.getLiteral("indirizzo").toString() ;
						uo.setIndirizzo(indirizzo);
					}
					
					if(solutionUnitaOrganizzativa.getLiteral("sito_web") != null){
						sito_web = solutionUnitaOrganizzativa.getLiteral("sito_web").toString() ;
						uo.setHomepage(sito_web);
					}
					
					if(solutionUnitaOrganizzativa.getLiteral("telefono") != null){
						telefono = solutionUnitaOrganizzativa.getLiteral("telefono").toString() ;
						uo.setTelefono(telefono);
					}
					
				}
				if(solutionUnitaOrganizzativa.getLiteral("pec") != null){
					pec = solutionUnitaOrganizzativa.getLiteral("pec").toString() ;
					uo.addPec(pec);
				}
				
				if(solutionUnitaOrganizzativa.getLiteral("email") != null){
					email = solutionUnitaOrganizzativa.getLiteral("email").toString() ;
					uo.addEmail(email);
				}

			}
		}
		return unita_organizzative;
	}
	
	public List<UfficioProtocollo> searchUfficiProtocolloForAmministrazione(String cod_amm) {
		List<UfficioProtocollo> uffici_protocollo = new ArrayList<UfficioProtocollo>(); 
		Query query = null;
		QueryExecution qexec = null;
		
		String queryString = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
								"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
								"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
								"prefix geonames: <http://www.geonames.org/ontology#> "+
								"prefix foaf: <http://xmlns.com/foaf/0.1/> "+
								"select ?cod_up ?ufficio_protocollo ?indirizzo ?comune ?sito_web ?telefono ?pec ?email ?cecpac "+
								"where{ "+
								"	?cod_up rdf:type digitPA:UfficioProtocolloAOO . "+
								"	?cod_up geonames:locatedIn ?cod_com . "+
								"	?cod_com rdfs:label ?comune . "+
								"	?cod_up rdfs:label ?ufficio_protocollo . "+
								"	?cod_up	 digitPA:afferisce <"+cod_amm+"> . "+
								"	<"+cod_amm+"> rdf:type digitPA:Amministrazione .  "+
								"	OPTIONAL { ?cod_up foaf:homepage ?sito_web } . "+
								"	OPTIONAL { ?cod_up digitPA:indirizzo ?indirizzo } . "+ 
								"	OPTIONAL { ?cod_up digitPA:PEC ?pec } . "+
								"	OPTIONAL { ?cod_up foaf:mbox ?email } . "+
								"	OPTIONAL { ?cod_up digitPA:CEC-PAC ?cecpac } . "+
								"	OPTIONAL { ?cod_up foaf:phone ?telefono } . "+
								"} ";

		query = QueryFactory.create(queryString);
		qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
		ResultSet resultsUfficiProtocollo = qexec.execSelect();
		String cod_up, ufficio_protocollo, last_cod_up=null;
		String comune=null, sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null, telefono = null;
		UfficioProtocollo up = null;
		for ( ; resultsUfficiProtocollo.hasNext() ; )
		{
			QuerySolution solutionUfficioProtocollo = resultsUfficiProtocollo.nextSolution() ;
			if(solutionUfficioProtocollo.getResource("cod_up") != null && solutionUfficioProtocollo.getLiteral("ufficio_protocollo") != null){
				
				ufficio_protocollo = solutionUfficioProtocollo.getLiteral("ufficio_protocollo").toString();
				cod_up = solutionUfficioProtocollo.getResource("cod_up").toString();
				comune = solutionUfficioProtocollo.getLiteral("comune").toString();
				
				if(cod_up != last_cod_up){
					up = new UfficioProtocollo(cod_up, ufficio_protocollo);
					
					up.setComune(comune);
					up.setCodiceAmministrazione(cod_amm);	
					
					uffici_protocollo.add(up);
					
					if(solutionUfficioProtocollo.getLiteral("indirizzo") != null){
						indirizzo = solutionUfficioProtocollo.getLiteral("indirizzo").toString() ;
						up.setIndirizzo(indirizzo);
					}
					
					if(solutionUfficioProtocollo.getLiteral("sito_web") != null){
						sito_web = solutionUfficioProtocollo.getLiteral("sito_web").toString() ;
						up.setHomepage(sito_web);
					}
					
					if(solutionUfficioProtocollo.getLiteral("telefono") != null){
						telefono = solutionUfficioProtocollo.getLiteral("telefono").toString() ;
						up.setTelefono(telefono);
					}
					
				}
				
				
				if(solutionUfficioProtocollo.getLiteral("email") != null){
					email = solutionUfficioProtocollo.getLiteral("email").toString() ;
					up.addEmail(email);
				}
				
				if(solutionUfficioProtocollo.getLiteral("cecpac") != null){
					pec = solutionUfficioProtocollo.getLiteral("cecpac").toString() ;
					up.addCecpac(cecpac);
				}

			}
		}
		return uffici_protocollo;
	}
}
