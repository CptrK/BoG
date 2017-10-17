package workerSystem;

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

public class MitarbeiterSystem {
	//ArrayList to work with objects of the system
	public ArrayList<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
	//ArrayList to work print out staff members of a certain group
	static ArrayList<Mitarbeiter> sortedGroupListe = new ArrayList<>();
	//constructor of the system
	public MitarbeiterSystem(Document mitarbeiterXML) {
			fillList(mitarbeiterXML);
	}
	//a method that fills the content of the XML into a list
	public void fillList(Document mitarbeiterXML) {
		//deletes all objects from the list
		for (int p = 0; p<mitarbeiterListe.size(); p++) {
			mitarbeiterListe.remove(p);
		}
		Mitarbeiter mitarbeiter;
	    NodeList nl = mitarbeiterXML.getElementsByTagName("Mitarbeiter");
	    //checks if there are elements in the XML
	    if (nl != null) {
	        int length = nl.getLength();
	        for (int i = 0; i < length; i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	Element el = (Element) nl.item(i);
	                if (el.getNodeName().contains("Mitarbeiter")) {
	                    String nummer = el.getElementsByTagName("MitarbeiterNummer").item(0).getTextContent();
	                    String name = el.getElementsByTagName("MitarbeiterName").item(0).getTextContent();
	                    String birth = el.getElementsByTagName("Geburtsdatum").item(0).getTextContent();
	                    String gruppe = el.getElementsByTagName("Gruppe").item(0).getTextContent();
	                    String login = el.getElementsByTagName("LogIn").item(0).getTextContent();
	                    //new object is added to the list
	        			mitarbeiter = new Mitarbeiter(nummer, name, birth, gruppe, login);
	        			mitarbeiterListe.add(mitarbeiter);
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
	        Element root = doc.createElement("MitarbeiterListe");
	        doc.appendChild(root);
	        //add children nodes to the root
	        for(int i=0; i<mitarbeiterListe.size(); i ++ ) {
	        	Element mitarbeiter = doc.createElement("Mitarbeiter");
	        	root.appendChild(mitarbeiter);
	        	//adds elements to one object of the system
	            Element nummer = doc.createElement("MitarbeiterNummer");
	            nummer.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getMitarbeiterNummer())));
	            mitarbeiter.appendChild(nummer);
	            
	            Element name = doc.createElement("MitarbeiterName");
	            name.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getName())));
	            mitarbeiter.appendChild(name);
	            
	            Element typ = doc.createElement("Gruppe");
	            typ.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getGruppe())));
	            mitarbeiter.appendChild(typ);
	            	            
	            Element login = doc.createElement("LogIn");
	            login.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getLogin())));
	            mitarbeiter.appendChild(login);
	            
	            Element email = doc.createElement("EmailAddresse");
	            email.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getEmail())));
	            mitarbeiter.appendChild(email);
	                      
	            Element name1 = doc.createElement("Vorname");
	            name1.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getVorName())));
	            mitarbeiter.appendChild(name1);
	            
	            Element name2 = doc.createElement("Nachname");
	            name2.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getNachName())));
	            mitarbeiter.appendChild(name2);
	            
	            Element date = doc.createElement("Geburtsdatum");
	            date.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getBirthdate())));
	            mitarbeiter.appendChild(date);
	            
	            Element status = doc.createElement("Belegt");
	            status.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getIsBelegt())));
	            mitarbeiter.appendChild(status);
	            
	            Element person = doc.createElement("BelegtVon");
	            person.appendChild(doc.createTextNode(String.valueOf(mitarbeiterListe.get(i).getBelegtVon())));
	            mitarbeiter.appendChild(person);

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
	        String str = "Mitarbeiter.xml";
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
	public void mitarbeiterBuchen (String mitarbeiterNummer, String user) {
		int check = 0;
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer) && mitarbeiterListe.get(i).getIsBelegt() == false) {
				mitarbeiterListe.get(i).setIsBelegt(true);
				mitarbeiterListe.get(i).setBelegtVon(user);
				String booked = mitarbeiterListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your booking action was successful! You have booked staff member: " + booked + "!");
				check=1;
				sortieren(mitarbeiterListe);
			}
		}
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (check==0 && mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer) && mitarbeiterListe.get(i).getIsBelegt() == true) {
				String bookedBy = mitarbeiterListe.get(i).getBelegtVon();
				JOptionPane.showMessageDialog(null,"Your booking action was not successful! The staff member is already booked by: " + bookedBy + "!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your booking action was not successful! Please verify that you have entered a correct staff number!");
		}
	}
	//a method that cancels a booking of an object from the system
	public void mitarbeiterBuchungStornieren(String mitarbeiterNummer, String user) {
		int check = 0;
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer) && mitarbeiterListe.get(i).getIsBelegt() == true && user.equals(mitarbeiterListe.get(i).getBelegtVon())) {
				mitarbeiterListe.get(i).setIsBelegt(false);
				mitarbeiterListe.get(i).setBelegtVon(null);
				String canceled = mitarbeiterListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your cancelation action was successful! You have canceled the booking with staff member: " + canceled + "!");
				check=1;
				sortieren(mitarbeiterListe);
			}
		}
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (check==0 && mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer) && mitarbeiterListe.get(i).getIsBelegt() == false) {
				JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! The staff member is currently not booked!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! Please verify that you have entered a correct staff number!");
		}
	}
	//a method that adds an object to the system
	public void mitarbeiterHinzufuegen (String gruppe, String name, String mitarbeiterNummer, String birth)  {
		int check=0;
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (check==0 && mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer)) {
				JOptionPane.showMessageDialog(null,"You can't add a new staff member with that staff number! The staff number already exists");
				check=1;
			}
			if (check==0 && mitarbeiterListe.get(i).getName().equals(name)) {
				JOptionPane.showMessageDialog(null,"You can't add a new staff member with that staff name! The staff name already exists");
				check=1;
			}
		}
		if (check==0) {
			//object is added to the array list
			Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterNummer, name, birth, gruppe, "init00");
			mitarbeiterListe.add(mitarbeiter);
			JOptionPane.showMessageDialog(null,"The new staff was successfully added to the staff system!");
			//list is sorted new
			sortieren(mitarbeiterListe);
		}
	}
	//a method that deletes an object from the system
	public void mitarbeiterloeschen(String mitarbeiterNummer, String user) {
		int check = 0;
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (mitarbeiterListe.get(i).getMitarbeiterNummer().equals(mitarbeiterNummer) && !user.equals(mitarbeiterListe.get(i).getName())) {
				String staffName = mitarbeiterListe.get(i).getName();
				mitarbeiterListe.remove(i);
				check=1;
				JOptionPane.showMessageDialog(null,"Your delete action was successful! You have deleted the staff member: " + staffName + "!");
				sortieren(mitarbeiterListe);
			}
		}
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (user.equals(mitarbeiterListe.get(i).getName())) {
				JOptionPane.showMessageDialog(null,"Your delete action was not successful! You can't delete your user when you are logged in!");
				check=1;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your delete action was not successful! Please verify that you have entered a correct staff number!");
		}
	}
	//a method checks if the current user has administrative rights
	public boolean isAdmin(String user) {
		boolean isAdmin = false;
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (mitarbeiterListe.get(i).getName().equals(user)) {
				if (mitarbeiterListe.get(i).getAdmin() == true) {
					isAdmin = true;
				}
			}
		}
		return isAdmin;
	}
	//a method that sorts the objects and creates a new XML
	private void sortieren(ArrayList<Mitarbeiter> list) {
		//sorts the objects
		Collator collator = Collator.getInstance(Locale.GERMAN);
		//list can't be empty
		if (!list.isEmpty()) {
		    Collections.sort(list, new Comparator<Mitarbeiter>() {
		        @Override
		        public int compare(Mitarbeiter m1, Mitarbeiter m2) {
		        	//no nulls in the list!
		            return collator.compare(m1.getMitarbeiterNummer(), m2.getMitarbeiterNummer());
		        }
		       });
		   }
			//creates the XML from the list
			createXML();
		
	}
	//a method that sorts a new array list with objects that have a specific group value
	public void sortByGroup(String group) {
		for (int i=0; i<mitarbeiterListe.size(); i++) {
			if (mitarbeiterListe.get(i).getGruppe().equals(group)) {
				sortedGroupListe.add(mitarbeiterListe.get(i));
				sortieren(sortedGroupListe);
			}
		}
		//prints the new list on console
		printGroupList(group);
	}
	//a method that prints a list on the console, which list is determined by group value
	public void printGroupList(String group) {
		System.out.println(" \n \nThe following staff is currently in the group '" + group + "': \n");
		for (int i=0; i<sortedGroupListe.size(); i++) {
			String name = sortedGroupListe.get(i).getName();
			System.out.println(name + "\n");
		}
	}
}
