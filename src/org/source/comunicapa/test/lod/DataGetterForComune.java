package src.org.source.comunicapa.test.lod;

import java.util.ArrayList;
import java.util.List;

import lod.DataGetterCommons;
import model.Amministrazione;
import model.Comune;
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

/*** RICERCA PER COMUNE ***/
public class DataGetterForComune {

	public Comune searchForComune(String comune_input) {
		
		Comune comune = null;
		
		String queryString = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
							"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
							"select ?cod_com ?comune "+
							"where{ "+
							"	?cod_com rdf:type digitPA:Comune . "+
							"	?cod_com rdfs:label ?comune . "+
							"	FILTER (regex(str(?comune), \"^"+comune_input+"$\", \"i\")) . "+ 
							"}";
		try{
			Amministrazione amm;
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
			ResultSet resultsComuni = qexec.execSelect();
			String com=null, cod_com=null;
			
			if ( resultsComuni.hasNext() )
			{
				QuerySolution solutionComune = resultsComuni.nextSolution() ;
				
				if(solutionComune.getResource("cod_com") != null && solutionComune.getLiteral("comune") != null){
					com = solutionComune.getLiteral("comune").toString() ;
					
					comune = new Comune(com);
					
					for(Amministrazione a : searchAmministrazioniLocaliForComune(cod_com, com)){
						comune.addAmministrazione(a);
					}
					
				}
			}
		}
		catch(Exception e){
			
		}

		return comune;
	}
	
	public List<UfficioProtocollo> searchUfficiProtocolloForComuneNotInAmm(String cod_com){
		List<UfficioProtocollo> uffici_protocollo = new ArrayList<UfficioProtocollo>(); 
		Query query = null;
		QueryExecution qexec = null;
		
		String cod_amm=null, amministrazione = null, sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null, telefono = null, fax = null, cod_up, ufficio_protocollo, last_cod_up=null; 
		UfficioProtocollo up = null;
		
		String queryString = "prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
							"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
							"prefix geonames: <http://www.geonames.org/ontology#> "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
							"select ?cod_amm ?cod_up ?amm ?up ?indirizzo ?telefono ?sito_web ?fax "+
							"where "+
							"{ "+
							"	?cod_amm rdf:type digitPA:Amministrazione . "+
							"	?cod_up rdf:type digitPA:UfficioProtocolloAOO . "+
							"	?cod_up rdfs:label ?up . "+
							"	?cod_amm rdfs:label ?amm . "+
							"	?cod_up digitPA:afferisce ?cod_amm . "+
							"	?cod_up geonames:locatedIn <"+cod_com+"> . "+
							"	?cod_amm geonames:locatedIn ?com_amm . "+
							"	FILTER( <"+cod_com+"> != ?com_amm) . "+
							"	OPTIONAL { ?cod_up foaf:homepage ?sito_web } . "+
							"	OPTIONAL { ?cod_up digitPA:indirizzo ?indirizzo } . "+
							"	OPTIONAL { ?cod_up foaf:phone ?telefono } . "+
							"	OPTIONAL { ?cod_up digitPA:fax ?fax } . "+
							"} ";
		try{
			
		
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
			ResultSet resultsUfficiProtocollo = qexec.execSelect();
			
			for ( ; resultsUfficiProtocollo.hasNext() ; )
			{
				QuerySolution solutionUfficioProtocollo = resultsUfficiProtocollo.nextSolution() ;
				if(solutionUfficioProtocollo.getResource("cod_up") != null && solutionUfficioProtocollo.getLiteral("up") != null){
					
					cod_amm = solutionUfficioProtocollo.getResource("cod_amm").toString();
					amministrazione = solutionUfficioProtocollo.getLiteral("amm").toString() ;
					ufficio_protocollo = solutionUfficioProtocollo.getLiteral("up").toString();
					cod_up = solutionUfficioProtocollo.getResource("cod_up").toString();
						
					if(cod_up != last_cod_up){
						up = new UfficioProtocollo(cod_up, ufficio_protocollo);
						
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
					
					if(solutionUfficioProtocollo.getLiteral("pec") != null){
						pec = solutionUfficioProtocollo.getLiteral("pec").toString() ;
						up.addPec(pec);
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
		}
		catch(Exception e){
			System.out.println("Errore: "+e.getMessage());
		}
		return uffici_protocollo;		
	}
	
	public List<UnitaOrganizzativa> searchUnitaOrganizzativeForComuneNotInAmm(String cod_com){
		List<UnitaOrganizzativa> unita_organizzative = new ArrayList<UnitaOrganizzativa>(); 
		Query query = null;
		QueryExecution qexec = null;
		
		String cod_amm=null, amministrazione = null, sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null, telefono = null, fax = null, cod_uo, unita_organizzativa, last_cod_uo=null; 
		// Cerco le Unità Organizzative del Comune		
		String queryString = "prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"prefix rdfs: <http://w3.org/2000/01/rdf-schema#> "+
							"prefix rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#> "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
							"prefix geonames: <http://www.geonames.org/ontology#> "+
							"select ?cod_amm ?cod_uo ?amm ?uo ?indirizzo ?telefono ?sito_web ?fax "+
							"where "+
							"{ "+
							"	?cod_amm rdf:type digitPA:Amministrazione . "+
							"	?cod_uo rdf:type digitPA:UnitaOrganizzativa . "+
							"	?cod_uo rdfs:label ?uo . "+
							"	?cod_uo digitPA:afferisce ?cod_amm . "+
							"	?cod_uo geonames:locatedIn <http://spcdata.digitpa.gov.it/Comune/E742> . "+
							"	?cod_amm geonames:locatedIn ?com_amm . "+
							"	FILTER( <http://spcdata.digitpa.gov.it/Comune/E742> != ?com_amm) . "+
							"	OPTIONAL { ?cod_uo foaf:homepage ?sito_web } . "+
							"	OPTIONAL { ?cod_uo digitPA:indirizzo ?indirizzo } . "+
							"	OPTIONAL { ?cod_uo foaf:phone ?telefono } . "+
							"	OPTIONAL { ?cod_uo digitPA:fax ?fax } . "+
							"} "+
							"ORDER BY ?cod_amm desc(?cod_uo) ";
		try{
			
			
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
			ResultSet resultsUnitaOrganizzative = qexec.execSelect();
			
			for ( ; resultsUnitaOrganizzative.hasNext() ; )
			{
				QuerySolution solutionUnitaOrganizzativa = resultsUnitaOrganizzative.nextSolution() ;
				if(solutionUnitaOrganizzativa.getResource("cod_uo") != null && solutionUnitaOrganizzativa.getLiteral("uo") != null){
					
					cod_amm = solutionUnitaOrganizzativa.getResource("cod_amm").toString();
					amministrazione = solutionUnitaOrganizzativa.getLiteral("amm").toString() ;
					unita_organizzativa = solutionUnitaOrganizzativa.getLiteral("uo").toString();
					cod_uo = solutionUnitaOrganizzativa.getResource("cod_uo").toString();
						
					if(cod_uo != last_cod_uo){
						uo = new UnitaOrganizzativa(cod_uo, unita_organizzativa);
						uo.setLabelAmministrazione(amministrazione);	
						
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
						
						if(solutionUnitaOrganizzativa.getLiteral("fax") != null){
							fax = solutionUnitaOrganizzativa.getLiteral("fax").toString() ;
							uo.setTelefono(fax);
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
					
					if(solutionUnitaOrganizzativa.getLiteral("cecpac") != null){
						pec = solutionUnitaOrganizzativa.getLiteral("cecpac").toString() ;
						uo.addCecpac(cecpac);
					}
				}
			
			}
		}
		catch(Exception e){
			System.out.println("Errore: "+e.getMessage());
		}
		return unita_organizzative;
	}
		
	
	private List<Amministrazione> searchAmministrazioniLocaliForComune(String cod_com, String comune){
		String sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null;
		List<Amministrazione> amministrazioni = new ArrayList<Amministrazione>();
		DataGetterCommons dgc = new DataGetterCommons();
		
		String queryString = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
							"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
							"prefix geonames: <http://www.geonames.org/ontology#> "+
							"prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"select ?cod_amm ?amministrazione ?sito_web ?pec ?email ?cecpac  "+
							"where{ "+
							"	?cod_amm rdf:type digitPA:Amministrazione . "+
							"	?cod_amm geonames:locatedIn <"+cod_com +"> . "+	
							"	?cod_amm rdfs:label ?amministrazione . "+
							"	?cod_amm digitPA:categoria ?cod_cat . "+
							"	OPTIONAL { ?cod_amm foaf:homepage ?sito_web } . "+
							"	OPTIONAL { ?cod_amm digitPA:PEC ?pec } . "+
							"	OPTIONAL { ?cod_amm foaf:mbox ?email } . "+
							"	OPTIONAL { ?cod_amm digitPA:CEC-PAC ?cecpac } . "+
							"} "+
							"ORDER BY ?cod_amm ";
		try{
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
			ResultSet resultsAmministrazioni = qexec.execSelect();
			String cod_amm, amministrazione, last_cod_amm = null;
			Amministrazione amm = null;
			for ( ; resultsAmministrazioni.hasNext() ; )
			{
				QuerySolution solutionAmministrazione = resultsAmministrazioni.nextSolution() ;
				if(solutionAmministrazione.getResource("cod_amm") != null && solutionAmministrazione.getLiteral("amministrazione") != null){
					
					cod_amm = solutionAmministrazione.getResource("cod_amm").toString();
					amministrazione = solutionAmministrazione.getLiteral("amministrazione").toString() ;
					
					if(cod_amm != last_cod_amm){
						amm = new Amministrazione(cod_amm, amministrazione);
						amm.setComune(comune);
						amministrazioni.add(amm);
						
						if(solutionAmministrazione.getLiteral("indirizzo") != null){
							indirizzo = solutionAmministrazione.getLiteral("indirizzo").toString() ;
							amm.setIndirizzo(indirizzo);
						}
						
						if(solutionAmministrazione.getLiteral("sito_web") != null){
							sito_web = solutionAmministrazione.getLiteral("sito_web").toString() ;
							amm.setHomepage(sito_web);
						}
						
					}
					
			
					
				}
			}
		}
		catch(Exception e){
			System.out.println("Errore: "+e.getMessage());
		}
		return amministrazioni;
	}
}
