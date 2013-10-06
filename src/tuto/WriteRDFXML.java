package tuto;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

public class WriteRDFXML 
{
	
	public static Model johnSmith(Model model)
	{
		// some definitions
		String personURI    = "http://somewhere/JohnSmith";
		String fullName     = "John Smith";

		// create an empty Model
		//Model model = ModelFactory.createDefaultModel();

		// create the resource
		Resource johnSmith = model.createResource(personURI);

		// add the property
		johnSmith.addProperty(VCARD.FN, fullName);
		
		return model;
	}

	public static Model johnSmith2(Model model)
	{
	
	// some definitions
	String personURI    = "http://somewhere/JohnSmith";
	String givenName    = "John";
	String familyName   = "Smith";
	String fullName     = givenName + " " + familyName;

	// create the resource
	//   and add the properties cascading style
	Resource johnSmith
	  = model.createResource(personURI)
	         .addProperty(VCARD.FN, fullName)
	         .addProperty(VCARD.N,
	                      model.createResource()
	                           .addProperty(VCARD.Given, givenName)
	                           .addProperty(VCARD.Family, familyName));
	return model;
	}
	
	public static void printStatements(Model model)
	{
		// list the statements in the Model
		StmtIterator iter = model.listStatements();

		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) 
		{
		    Statement stmt      = iter.nextStatement();  // get next statement
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    System.out.print(subject.toString());
		    System.out.print(" " + predicate.toString() + " ");
		    if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {
		        // object is a literal
		        System.out.print(" \"" + object.toString() + "\"");
		    }

		    System.out.println(" .");
		} 
	}
	
	public static void main(String args[])
	{
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		//model = johnSmith2(model);
		//printStatements(model);
		
		//XML version
		// some definitions
		String personURI    = "http://somewhere/JohnSmith";
		String givenName    = "John";
		String familyName   = "Smith";
		String fullName     = givenName + " " + familyName;

		// create the resource
		//   and add the properties cascading style
		Resource johnSmith
		  = model.createResource(personURI)
		         .addProperty(VCARD.FN, fullName)
		         .addProperty(VCARD.N,
		                      model.createResource()
		                           .addProperty(VCARD.Given, givenName)
		                           .addProperty(VCARD.Family, familyName));
		//model.write ecrit en XML/RDF
		//mais attention il donne des URI aux blanknodes
		//model.write(System.out);
		//preferer :
		model.write(System.out, "RDF/XML-ABBREV");
		//model.write(System.out, "N-TRIPLES");
		//model.write(System.out, "TURTLE");
		
	}
	
}