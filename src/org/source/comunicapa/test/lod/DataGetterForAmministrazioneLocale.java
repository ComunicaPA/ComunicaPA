package src.org.source.comunicapa.test.lod;

import java.util.ArrayList;
import java.util.List;

import lod.DataGetterCommons;
import model.Amministrazione;
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

/*** RICERCA PER AMMINISTRAZIONE LOCALE ***/
public class DataGetterForAmministrazioneLocale {

	public List<Amministrazione> searchForAmministrazioneLocale(String amministrazione_input) {
		String sito_web=null, indirizzo=null, pec = null, email = null, cecpac=null;
		List<Amministrazione> amministrazioni = new ArrayList<Amministrazione>();
		DataGetterCommons dgc = new DataGetterCommons();
		
		String queryString = "prefix rdfs: <http://w3.org/2000/01/rdf-schema#> "+
							"prefix digitPA: <http://spcdata.digitpa.gov.it/> "+
							"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
							"prefix geonames: <http://geonames.org/ontology#> "+
							"prefix foaf: <http://xmlns.com/foaf/0.1/> "+
							"select ?cod_amm ?amministrazione ?comune ?sito_web ?indirizzo ?pec ?email ?cecpac "+
							"where{ "+
							"	?cod_amm rdf:type digitPA:Amministrazione . "+
							"	?cod_com rdf:type digitPA:Comune . "+
							"	?cod_amm geonames:locatedIn ?cod_com . "+	
							"	?cod_amm rdfs:label ?amministrazione . "+
							"	?cod_com rdfs:label ?comune . "+
							"	?cod_amm digitPA:categoria ?cod_cat . "+
							"	?cod_cat digitPA:tipologia_amministrazione \"L\" . "+
							"	FILTER (regex(str(?amministrazione), \""+amministrazione_input+"\", \"i\")) . "+ 
							"	OPTIONAL { ?cod_amm foaf:homepage ?sito_web } . "+
							"	OPTIONAL { ?cod_amm digitPA:indirizzo ?indirizzo } . "+ 
							"	OPTIONAL { ?cod_amm digitPA:PEC ?pec } .  "+
							"	OPTIONAL { ?cod_amm foaf:mbox ?email } . "+
							"	OPTIONAL { ?cod_amm digitPA:CEC-PAC ?cecpac } . "+
							"} "+
							"order by ?cod_amm";
		try{
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(Impostazioni.endpoint, query);
			ResultSet resultsAmministrazioni = qexec.execSelect();
			String cod_amm, amministrazione, comune, last_cod_amm = null;
			Amministrazione amm = null;
			for ( ; resultsAmministrazioni.hasNext() ; )
			{
				QuerySolution solutionAmministrazione = resultsAmministrazioni.nextSolution() ;
				if(solutionAmministrazione.getResource("cod_amm") != null && solutionAmministrazione.getLiteral("amministrazione") != null){
					
					cod_amm = solutionAmministrazione.getResource("cod_amm").toString();
					amministrazione = solutionAmministrazione.getLiteral("amministrazione").toString();
					comune = solutionAmministrazione.getLiteral("comune").toString();
					
					if(cod_amm != last_cod_amm){
						amm = new Amministrazione(cod_amm, amministrazione);
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
					
					if(solutionAmministrazione.getLiteral("pec") != null){
						pec = solutionAmministrazione.getLiteral("pec").toString() ;
					}
					
					if(solutionAmministrazione.getLiteral("email") != null){
						email = solutionAmministrazione.getLiteral("email").toString() ;
						amm.addEmail(email);
					}
					
					if(solutionAmministrazione.getLiteral("cecpac") != null){
						pec = solutionAmministrazione.getLiteral("cecpac").toString() ;
						amm.addCecpac(cecpac);
					}
			
				}
			}
		}
		catch(Exception e){
			
		}
		return amministrazioni;
	}
}
