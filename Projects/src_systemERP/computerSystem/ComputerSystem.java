package computerSystem;

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

public class ComputerSystem {
	//ArrayList to work with objects of the system
	static ArrayList<Computer> geraeteListe = new ArrayList<>();
	//constructor of the system
	public ComputerSystem(Document computerXML) {
			fillList(computerXML);
	}
	//a method that fills the content of the XML into a list
	private void fillList(Document computerXML) {
		//deletes all objects from the list
		for (int p = 0; p<geraeteListe.size(); p++) {
			geraeteListe.remove(p);
		}
		Computer computer;
	    NodeList nl = computerXML.getElementsByTagName("Geraet");
	    //checks if there are elements in the XML
	    if (nl != null) {
	        int length = nl.getLength();
	        for (int i = 0; i < length; i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	Element el = (Element) nl.item(i);
	                if (el.getNodeName().contains("Geraet")) {
	                    String nummer = el.getElementsByTagName("GeraeteNummer").item(0).getTextContent();
	                    String name = el.getElementsByTagName("GeraeteName").item(0).getTextContent();
	                    String typ = el.getElementsByTagName("GeraeteTyp").item(0).getTextContent();
	                    String preis = el.getElementsByTagName("AnschaffungsPreis").item(0).getTextContent();
	                    String dauer = el.getElementsByTagName("Nutzungsdauer").item(0).getTextContent();
	                    //new object is added to the list
	                    computer = new Computer(nummer, name, typ, preis, dauer);
	        			geraeteListe.add(computer);
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
	        Element root = doc.createElement("GeraeteListe");
	        doc.appendChild(root);
	        //add children nodes to the root
	        for(int i=0; i<geraeteListe.size(); i ++ ) {
	        	Element computer = doc.createElement("Geraet");
	        	root.appendChild(computer);
	        	//adds elements to one object of the system
	            Element nummer = doc.createElement("GeraeteNummer");
	            nummer.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getInventarNummer())));
	            computer.appendChild(nummer);
	            
	            Element name = doc.createElement("GeraeteName");
	            name.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getName())));
	            computer.appendChild(name);
	            
	            Element typ = doc.createElement("GeraeteTyp");
	            typ.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getTyp())));
	            computer.appendChild(typ);
	            
	            Element IPv4 = doc.createElement("Ipv4Addresse");
	            IPv4.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getIPv4Addresse())));
	            computer.appendChild(IPv4);
	            
	            Element subnet = doc.createElement("Subnetzmaske");
	            subnet.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getSubNetMask())));
	            computer.appendChild(subnet);
	            
	            Element gateway = doc.createElement("Gateway");
	            gateway.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getGateway())));
	            computer.appendChild(gateway);
	            
	            Element mac = doc.createElement("MacAddresse");
	            mac.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getMacAddresse())));
	            computer.appendChild(mac);
	            
	            Element price = doc.createElement("AnschaffungsPreis");
	            price.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getAPreis())));
	            computer.appendChild(price);
	            
	            Element dauer = doc.createElement("Nutzungsdauer");
	            dauer.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getNDauer())));
	            computer.appendChild(dauer);

	            Element status = doc.createElement("Belegt");
	            status.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getIsBelegt())));
	            computer.appendChild(status);
	            
	            Element person = doc.createElement("BelegtVon");
	            person.appendChild(doc.createTextNode(String.valueOf(geraeteListe.get(i).getBelegtVon())));
	            computer.appendChild(person);

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
	        String str = "Computer.xml";
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
	public void geraetBuchen (String geraeteNummer, String user) {
		int check = 0;
		for (int i=0; i<geraeteListe.size(); i++) {
			if (geraeteListe.get(i).getInventarNummer().equals(geraeteNummer) && geraeteListe.get(i).getIsBelegt() == false) {
				geraeteListe.get(i).setIsBelegt(true);
				geraeteListe.get(i).setBelegtVon(user);
				String booked = geraeteListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your booking action was successful! You have booked the device: " + booked + "!");
				check=1;
				sortieren(geraeteListe);
			}
		}
		for (int i=0; i<geraeteListe.size(); i++) {
			if (check==0 && geraeteListe.get(i).getInventarNummer().equals(geraeteNummer) && geraeteListe.get(i).getIsBelegt() == true) {
				String bookedBy = geraeteListe.get(i).getBelegtVon();
				JOptionPane.showMessageDialog(null,"Your booking action was not successful! The device is already booked by: " + bookedBy + "!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your booking action was not successful! Please verify that you have entered a correct device number!");
		}
	}
	//a method that cancels a booking of an object from the system
	public void geraetBuchungStornieren(String geraeteNummer, String user) {
		int check = 0;
		for (int i=0; i<geraeteListe.size(); i++) {
			if (geraeteListe.get(i).getInventarNummer().equals(geraeteNummer) && geraeteListe.get(i).getIsBelegt() == true && user.equals(geraeteListe.get(i).getBelegtVon())) {
				geraeteListe.get(i).setIsBelegt(false);
				geraeteListe.get(i).setBelegtVon(null);
				String canceled = geraeteListe.get(i).getName();
				JOptionPane.showMessageDialog(null,"Your cancelation action was successful! You have canceled the booking of the device: " + canceled + "!");
				check=1;
				sortieren(geraeteListe);
			}
		}
		for (int i=0; i<geraeteListe.size(); i++) {
			if (check==0 && geraeteListe.get(i).getInventarNummer().equals(geraeteNummer) && geraeteListe.get(i).getIsBelegt() == false) {
				JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! The device is currently not booked!");
				check=2;
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your cancelation action was not successful! Please verify that you have entered a correct device number!");
		}
	}
	//a method that adds an object to the system
	public void geraetHinzufuegen (String geraeteTyp, String name, String inventarNummer, String aPreis, String nDauer) {
		int check=0;
		for (int i=0; i<geraeteListe.size(); i++) {
			if (check==0 && geraeteListe.get(i).getInventarNummer().equals(inventarNummer)) {
				JOptionPane.showMessageDialog(null,"You can't add a new device with that device number! The device number already exists");
				check=1;
			}
		}
		if (check==0) {
			//object is added to the array list
			Computer computer = new Computer(inventarNummer,name, geraeteTyp, aPreis, nDauer);
			geraeteListe.add(computer);
			JOptionPane.showMessageDialog(null,"The new device was successfully added to the device system!");
			//list is sorted new
			sortieren(geraeteListe);
		}
		
	}
	//a method that deletes an object from the system
	public void geraetloeschen(String inventarNummer, String user) {
		int check = 0;
		for (int i=0; i<geraeteListe.size(); i++) {
			if (geraeteListe.get(i).getInventarNummer().equals(inventarNummer) && !user.equals(geraeteListe.get(i).getName())) {
				String staffName = geraeteListe.get(i).getName();
				if (geraeteListe.size() > 1) {
					geraeteListe.remove(i);
					JOptionPane.showMessageDialog(null,"Your delete action was successful! You have deleted the device: " + staffName + "!");
				}
				else if (geraeteListe.size() == 1) {
					JOptionPane.showMessageDialog(null,"Your delete action was not successful! You can't delete all the objects in the system.");
				}
				check=1;
				sortieren(geraeteListe);
			}
		}
		if (check==0) {
			JOptionPane.showMessageDialog(null,"Your delete action was not successful! Please verify that you have entered a correct device number!");
		}
	}
	//a method that sorts the objects and creates a new XML
	private void sortieren(ArrayList<Computer> list) {
		//sorts the objects
		Collator collator = Collator.getInstance(Locale.GERMAN);
		//list can't be empty
		if (!list.isEmpty()) {
		    Collections.sort(list, new Comparator<Computer>() {
		        @Override
		        public int compare(Computer c1, Computer c2) {
		            //no nulls in the list!
		            return collator.compare(c1.getInventarNummer(), c2.getInventarNummer());
		        }
		       });
		   }
		//creates the XML from the list
		createXML();		
	}
}
