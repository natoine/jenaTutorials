package tuto.store;

import tuto.WriteRDFXML;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class StoreRDF {

	public static void main(String[] args)
	{
		// Make a TDB-backed dataset
		  String directory = "db" ;
		  Dataset dataset = TDBFactory.createDataset(directory) ;
		  
		  dataset.begin(ReadWrite.WRITE) ;
		  // Get model inside the transaction
		  Model model = dataset.getDefaultModel() ;
		  //model.write(System.out, "RDF/XML-ABBREV");
		  model = WriteRDFXML.johnSmith2(model);
		  model.write(System.out, "RDF/XML-ABBREV");
		  dataset.commit();
		  dataset.end() ;
		 // model.write(System.out, "RDF/XML-ABBREV");
	}
}
