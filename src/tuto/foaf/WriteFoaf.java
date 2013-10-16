package tuto.foaf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class WriteFoaf 
{
	public static final String NS_FOAF = "http://xmlns.com/foaf/0.1/";

	public static Model writeProfile()
	{
		Model model = ModelFactory.createDefaultModel();
		String personURI    = "http://www.natoine.fr#me";
		String strgivenName    = "Antoine";
		String strfamilyName   = "Seilles";
		String strnickName 	= "Natoine";
		String strfullName     = strgivenName + " " + strfamilyName;
		
		//ajout d un namespace
		model.setNsPrefix("foaf", NS_FOAF);
		//creation des proprietes
		Property firstname = model.createProperty( NS_FOAF + "firstName" );
		Property familyName = model.createProperty( NS_FOAF + "familyName" );
		//Property nick = model.createProperty( NS_FOAF + "nick" );
		Property name = model.createProperty( NS_FOAF + "name" );
		
		//TODO
		//need to create a foaf:Person not a generic resource
		//Resource person = model.createResource(personURI);
		
		Resource person = model.createResource(personURI, FOAF.PERSON);
		
		person.addProperty(firstname, strgivenName);
		person.addProperty(familyName, strfamilyName);
		//person.addProperty(nick, strnickName);
		person.addProperty(FOAF.nick, strnickName);
		person.addProperty(name, strfullName);
		
		return model;
	}
	
	public static void main(String[] args)
	{
		Model model = writeProfile();
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
