package tuto.foaf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class FOAF 
{

	protected static final String uri = "http://xmlns.com/foaf/0.1/";
	
	public static String getURI() {
        return uri;
  }

  private static Model m = ModelFactory.createDefaultModel();
  
  public static final Resource PERSON = m.createResource(uri + "Person" );
  public static final Property nick = m.createProperty(uri, "nick" );
	
}
