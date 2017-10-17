package roomSystem;

import java.util.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.Collator;
import org.w3c.dom.*;

public class RaumSystem {
	//ArrayList to work with objects of the system
	static ArrayList<Raum> raumListe = new ArrayList<>();
	//constructor of the system
	public RaumSystem(Document raumXML) {
			fillList(raumXML);
	}
	//a method that fills the content of the XML into a list
	private void fillList(Document raumXML) {
		//deletes all objects from the list
		for (int p = 0; p<raumListe.size(); p++) {
			raumListe.remove(p);
		}
		Raum raum;
	    NodeList nl = raumXML.getElementsByTagName("Raum");
	    //checks if there are elements in the XML
	    if (nl != null) {
	        int length = nl.getLength();
	        for (int i = 0; i < length; i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	Element el = (Element) nl.item(i);
	                if (el.getNodeName().contains("Raum")) {
	                    String nummer = el.getElementsByTagName("RaumNummer").item(0).getTextContent();
	                    String hoehe = el.getElementsByTagName("RaumHoehe").item(0).getTextContent();
	                    String laenge = el.getElementsByTagName("RaumLaenge").item(0).getTextContent();
	                    String breite = el.getElementsByTagName("RaumBreite").item(0).getTextContent();
	                    //new object is added to the list
	                    raum = new Raum(nummer, laenge, breite, hoehe);
	        			raumListe.add(raum);
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
	        Element root = doc.createElement("RaumListe");
	        doc.appendChild(root);
	        //add children nodes to the root
	        for(int i=0; i<raumListe.size(); i ++ ) {
	        	Element raum = doc.createElement("Raum");
	        	root.appendChild(raum);
	        	//adds elements to one object of the system
	            Element nummer = doc.createElement("RaumNummer");
	            nummer.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getRaumNummer())));
	            raum.appendChild(nummer);
	            
	            Element name = doc.createElement("RaumName");
	            name.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getName())));
	            raum.appendChild(name);
	            
	            Element hoehe = doc.createElement("RaumHoehe");
	            hoehe.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getHoehe())));
	            raum.appendChild(hoehe);
	            
	            Element breite = doc.createElement("RaumBreite");
	            breite.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getBreite())));
	            raum.appendChild(breite);
	            
	            Element laenge = doc.createElement("RaumLaenge");
	            laenge.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getLaenge())));
	            raum.appendChild(laenge);
	            
	            Element grund = doc.createElement("Grundflaeche");
	            grund.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getGrundflaeche())));
	            raum.appendChild(grund);
	            
	            Element status = doc.createElement("Belegt");
	            status.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getIsBelegt())));
	            raum.appendChild(status);
	            
	            Element person = doc.createElement("BelegtVon");
	            person.appendChild(doc.createTextNode(String.valueOf(raumListe.get(i).getBelegtVon())));
	            raum.appendChild(person);

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
	        String str = "Raeume.xml";
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
	public void raumBuchen (String raumNummer, String user) {
		int check = 0;
		for (int i=0; i<raumListe.size(); i++) {
			if (raumListe.get(i).getRaumNummer().equals(raumNummer) && raumListe.get(i).getIsBelegt() == false) {
				raumListe.get(i).setIsBelegt(true);
				raumListe.get(i).setBelegtVon(user);
				String booked = raumListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your booking action was successful! You have booked the room: " + booked + "!");
				check=1;
				sortieren(raumListe);
			}
		}
		for (int i=0; i<raumListe.size(); i++) {
			if (check==0 && raumListe.get(i).getRaumNummer().equals(raumNummer) && raumListe.get(i).getIsBelegt() == true) {
				String bookedBy = raumListe.get(i).getBelegtVon();
				JOptionPane.showMessageDialog(null,"Your booking action was not successful! The room is already booked by: " + bookedBy + "!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your booking action was not successful! Please verify that you have entered a correct room number!");
		}
	}
	//a method that cancels a booking of an object from the system
	public void raumBuchungStornieren(String raumNummer, String user) {
		int check = 0;
		for (int i=0; i<raumListe.size(); i++) {
			if (raumListe.get(i).getRaumNummer().equals(raumNummer) && raumListe.get(i).getIsBelegt() == true && user.equals(raumListe.get(i).getBelegtVon())) {
				raumListe.get(i).setIsBelegt(false);
				raumListe.get(i).setBelegtVon(null);
				String canceled = raumListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your cancelation action was successful! You have canceled the booking of: " + canceled + "!");
				check=1;
				sortieren(raumListe);
			}
		}
		for (int i=0; i<raumListe.size(); i++) {
			if (check==0 && raumListe.get(i).getRaumNummer().equals(raumNummer) && raumListe.get(i).getIsBelegt() == false) {
				JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! The room is currently not booked!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! Please verify that you have entered a correct room number!");
		}	
	}
	//a method that adds an object to the system
	public void raumHinzufuegen (String raumNummer, String laenge, String breite, String hoehe) {
		int check=0;
		for (int i=0; i<raumListe.size(); i++) {
			if (check==0 && raumListe.get(i).getRaumNummer().equals(raumNummer)) {
				JOptionPane.showMessageDialog(null,"You can't add a new room with that room number! The room number already exists");
				check=1;
			}
		}
		if (check==0) {
			//object is added to the array list 
			Raum raum = new Raum(raumNummer, laenge, breite, hoehe);
			raumListe.add(raum);
			JOptionPane.showMessageDialog(null,"The new room was successfully added to the room system!");
			//list is sorted new
			sortieren(raumListe);
		}
	}
	//a method that deletes an object from the system
	public void raumloeschen(String raumNummer, String user) {
		int check = 0;
		for (int i=0; i<raumListe.size(); i++) {
			if (raumListe.get(i).getRaumNummer().equals(raumNummer) && !user.equals(raumListe.get(i).getName())) {
				String roomName = raumListe.get(i).getName();
				if (raumListe.size() > 1) {
					raumListe.remove(i);
					JOptionPane.showMessageDialog(null,"Your delete action was successful! You have deleted: " + roomName + "!");
				}
				else if (raumListe.size() == 1) {
					JOptionPane.showMessageDialog(null,"Your delete action was not successful! You can't delete all the objects in the system.");
				}
				check=1;
				sortieren(raumListe);
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your delete action was not successful! Please verify that you have entered a correct room number!");
		}
	}
	//a method that sorts the objects and creates a new XML
	private void sortieren(ArrayList<Raum> list) {
		//sorts the objects
		Collator collator = Collator.getInstance(Locale.GERMAN);
		//list can't be empty
		if (!list.isEmpty()) {
		    Collections.sort(list, new Comparator<Raum>() {
		        @Override
		        public int compare(Raum r1, Raum r2) {
		        	//no nulls in the list!
		            return collator.compare(r1.getRaumNummer(), r2.getRaumNummer());
		        }
		       });
		   }
		//creates the XML from the list
		createXML();
		
	}
}
