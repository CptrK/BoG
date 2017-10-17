package systemERP;

import java.awt.EventQueue;
import java.io.*;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

	/**@author Robin Konopka */ 

public class ERP {
	
	//Test to see if the ERP-System works
	public static void main(String[] args) throws IOException {
		//open up all the XML resources in inputStreams
		InputStream raeumeStream = openResource("/resources/Raeume.xml");
		InputStream fahrzeugeStream = openResource("/resources/Fahrzeuge.xml");
		InputStream mitarbeiterStream = openResource("/resources/Mitarbeiter.xml");
		InputStream geraeteStream = openResource("/resources/Computer.xml");
		
		//convert those inputStreams to documents
		Document raeume = convertedStreamToDocument(raeumeStream);
		Document fahrzeuge = convertedStreamToDocument(fahrzeugeStream);
		Document mitarbeiter = convertedStreamToDocument(mitarbeiterStream);
		Document geraete = convertedStreamToDocument(geraeteStream);
			
		//run the system with documents
		run(raeume,mitarbeiter,fahrzeuge,geraete);
			
	}
	
	/**
	 * run: a private method that starts up the GUI, it invokes an 'eventQueue' and makes 'LogInGUI' visible
	 */
	private static void run (Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) throws IOException { //Main-Methode
		//running LogInGUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LogInGUI window = new LogInGUI(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
				window.frmErpSystem.setVisible(true);
				
			}
		});
	}
	
	/**
	 * openResource: a private method that returns an InputStream, which is taken from the path that is specified when it is called
	 */
	private static InputStream openResource(final String name) {
		InputStream stream = ERP.class.getResourceAsStream(name);
		//checks if the stream has content to prevent further errors, if no content is available system throws a runtime exception 
		if (stream == null) {
			throw new RuntimeException("Testfile not found: " + name);
		}
		return stream;
	}
	
	/**
	 * convertedStreamToDocument: a private method that returns a document after it was converted from an InputStream
	 */
	private static Document convertedStreamToDocument(InputStream input) throws IOException {
		
		try { //system tries to convert stream into document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	DocumentBuilder db = dbf.newDocumentBuilder(); 
        	Document doc = db.parse(input);
        	return doc;
		} catch (Exception e) {
			throw new IOException("The inputfile couldn't be converted into an document!");
		}
		
	}
	
}
