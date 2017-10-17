package vehicleSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class FahrzeugeSystem {
	//ArrayList to work with objects of the system
	static ArrayList<Fahrzeug> fahrzeugListe = new ArrayList<>();
	//constructor of the system
	public FahrzeugeSystem(Document fahrzeugXML)  {
			fillList(fahrzeugXML);
	}
	//a method that fills the content of the XML into a list
	private void fillList(Document fahrzeugXML)  {
		//deletes all objects from the list
		for (int p = 0; p<fahrzeugListe.size(); p++) {
			fahrzeugListe.remove(p);
		}
		Fahrzeug fahrzeug;
	    NodeList nl = fahrzeugXML.getElementsByTagName("Fahrzeug");
	    //checks if there are elements in the XML
	    if (nl != null) {
	        int length = nl.getLength();
	        for (int i = 0; i < length; i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	Element el = (Element) nl.item(i);
	                if (el.getNodeName().contains("Fahrzeug")) {
	                    String fahrzeugNummer = el.getElementsByTagName("FahrzeugNummer").item(0).getTextContent();
	                    String name = el.getElementsByTagName("FahrzeugName").item(0).getTextContent();
	                    String typ = el.getElementsByTagName("FahrzeugTyp").item(0).getTextContent();
	                    //new object is added to the list
	                    fahrzeug = new Fahrzeug(typ,name,fahrzeugNummer);
	        			fahrzeugListe.add(fahrzeug);
	            	}
	            }
	        }
	    }
	}
	// a method that creates a XML file from the object list in the system
	public void createXML() {
		//tries to build a document from the list content
		try {
	        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();
	        //creates root node
	        Element root = doc.createElement("FahrzeugListe");
	        doc.appendChild(root);
	        //add children nodes to the root
	        for(int i=0; i<fahrzeugListe.size(); i ++ ) {
	        	Element fahrzeug = doc.createElement("Fahrzeug");
	        	root.appendChild(fahrzeug);
	        	//adds elements to one object of the system
	            Element nummer = doc.createElement("FahrzeugNummer");
	            nummer.appendChild(doc.createTextNode(String.valueOf(fahrzeugListe.get(i).getFahrzeugNummer())));
	            fahrzeug.appendChild(nummer);
	            
	            Element name = doc.createElement("FahrzeugName");
	            name.appendChild(doc.createTextNode(String.valueOf(fahrzeugListe.get(i).getName())));
	            fahrzeug.appendChild(name);
	            
	            Element typ = doc.createElement("FahrzeugTyp");
	            typ.appendChild(doc.createTextNode(String.valueOf(fahrzeugListe.get(i).getFahrzeugTyp())));
	            fahrzeug.appendChild(typ);

	            Element status = doc.createElement("Belegt");
	            status.appendChild(doc.createTextNode(String.valueOf(fahrzeugListe.get(i).getIsBelegt())));
	            fahrzeug.appendChild(status);
	            
	            Element person = doc.createElement("BelegtVon");
	            person.appendChild(doc.createTextNode(String.valueOf(fahrzeugListe.get(i).getBelegtVon())));
	            fahrzeug.appendChild(person);

	        }


	        //save XML
	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();

	        //format XML
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

	        aTransformer.setOutputProperty(
	                "{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

	        DOMSource source = new DOMSource(doc);
	        File dir = new File("D:\\Schule\\Programs\\src_systemERP\\resources");
	        String str = "Fahrzeuge.xml";
	        try {
	            FileWriter fos = new FileWriter(new File(dir, str));
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);

	        } catch (IOException e) {

	            e.printStackTrace();
	        }

	    } catch (TransformerException ex) {
	        System.out.println("Error outputting document");

	    } catch (ParserConfigurationException ex) {
	        System.out.println("Error building document");
	    }
		
	}
	//a method that books an object from the system
	public void fahrzeugBuchen (String fahrzeugNummer, String user) {
		int check = 0;
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer) && fahrzeugListe.get(i).getIsBelegt() == false) {
				fahrzeugListe.get(i).setIsBelegt(true);
				fahrzeugListe.get(i).setBelegtVon(user);
				String booked = fahrzeugListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your booking action was successful! You have booked the vehicle: " + booked + "!");
				check=1;
				sortieren(fahrzeugListe);
			}
		}
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (check==0 && fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer) && fahrzeugListe.get(i).getIsBelegt() == true) {
				String bookedBy = fahrzeugListe.get(i).getBelegtVon();
				JOptionPane.showMessageDialog(null,"Your booking action was not successful! The vehicle is already booked by: " + bookedBy + "!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your booking action was not successful! Please verify that you have entered a correct vehicle number!");
		}
	}
	//a method that cancels a booking of an object from the system
	public void fahrzeugBuchungStornieren(String fahrzeugNummer, String user) {
		int check = 0;
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer) && fahrzeugListe.get(i).getIsBelegt() == true && user.equals(fahrzeugListe.get(i).getBelegtVon())) {
				fahrzeugListe.get(i).setIsBelegt(false);
				fahrzeugListe.get(i).setBelegtVon(null);
				String canceled = fahrzeugListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your cancelation action was successful! You have canceled the booking of the vehicle: " + canceled + "!");
				check=1;
				sortieren(fahrzeugListe);
			}
		}
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (check==0 && fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer) && fahrzeugListe.get(i).getIsBelegt() == false) {
				JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! The vehicle is currently not booked!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! Please verify that you have entered a correct vehicle number!");
		}
	}
	//a method that adds an object to the system
	public void fahrzeugHinzufuegen (String fahrzeugTyp, String name, String fahrzeugNummer) {
		int check=0;
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (check==0 && fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer)) {
				JOptionPane.showMessageDialog(null,"You can't add a new vehicle member with vehicle staff number! The vehicle number already exists");
				check=1;
			}
		}
		if (check==0) {
			//object is added to the array list 
			Fahrzeug fahrzeug = new Fahrzeug(fahrzeugTyp,name,fahrzeugNummer);
			fahrzeugListe.add(fahrzeug);
			JOptionPane.showMessageDialog(null,"The new vehicle was successfully added to the vehicle system!");
			//list is sorted new
			sortieren(fahrzeugListe);
		}	
	}
	//a method that deletes an object from the system
	public void fahrzeugloeschen(String fahrzeugNummer, String user) {
		int check = 0;
		for (int i=0; i<fahrzeugListe.size(); i++) {
			if (fahrzeugListe.get(i).getFahrzeugNummer().equals(fahrzeugNummer) && !user.equals(fahrzeugListe.get(i).getName())) {
				String vehicleName = fahrzeugListe.get(i).getName();
				if (fahrzeugListe.size() > 1) {
					fahrzeugListe.remove(i);
					JOptionPane.showMessageDialog(null,"Your delete action was successful! You have deleted the vehicle: " + vehicleName + "!");
				}
				else if (fahrzeugListe.size() == 1) {
					JOptionPane.showMessageDialog(null,"Your delete action was not successful! You can't delete all the objects in the system.");
				}
				check=1;
				sortieren(fahrzeugListe);
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your delete action was not successful! Please verify that you have entered a correct vehicle number!");
		}
	}
	//a method that sorts the objects and creates a new XML
	private void sortieren(ArrayList<Fahrzeug> list) {
		//sorts the objects
		Collator collator = Collator.getInstance(Locale.GERMAN);
		//list can't be empty
		if (!list.isEmpty()) {
		    Collections.sort(list, new Comparator<Fahrzeug>() {
		        @Override
		        public int compare(Fahrzeug f1, Fahrzeug f2) {
		        	//no nulls in the list!
		            return collator.compare(f1.getFahrzeugNummer(), f2.getFahrzeugNummer());
		        }
		       });
		   }
		//creates the XML from the list
		createXML();	
	}
}
