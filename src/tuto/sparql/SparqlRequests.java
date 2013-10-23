package tuto.sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


public class SparqlRequests 
{

	public static void localSparql()
	{
		Model model = ModelFactory.createDefaultModel() ;
		String queryString = " .... " ;
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		try {
			ResultSet results = qexec.execSelect() ;
			for ( ; results.hasNext() ; )
			{
				QuerySolution soln = results.nextSolution() ;
				RDFNode x = soln.get("varName") ;       // Get a result variable by name.
				Resource r = soln.getResource("VarR") ; // Get a result variable - must be a resource
				Literal l = soln.getLiteral("VarL") ;   // Get a result variable - must be a literal
			}
		}
		finally { qexec.close() ; }
	}
	
	public static void remoteSparql()
	{
		Model model = ModelFactory.createDefaultModel();
		String queryString = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX igeo:<http://rdf.insee.fr/def/geo#>"
				+ "SELECT ?region WHERE {"
				+ "?region rdf:type igeo:Region ."
//				+ "?region igeo:nom \"Bourgogne\" ."
				+ "}" ;
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://rdf.insee.fr/sparql", query);
		ResultSet results = qexec.execSelect() ;
		while(results.hasNext())
		{
			QuerySolution soln = results.nextSolution();
			System.out.println(soln.toString());
			System.out.println(soln.get("?region"));
		}
		qexec.close();
	}
	
	public static void main(String[] args)
	{
		remoteSparql();
	}

}
