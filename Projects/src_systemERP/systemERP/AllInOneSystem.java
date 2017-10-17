package systemERP;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import computerSystem.ComputerSystem;
import roomSystem.RaumSystem;
import vehicleSystem.FahrzeugeSystem;
import workerSystem.MitarbeiterSystem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.Toolkit;

	/**@author Robin Konopka */

public class AllInOneSystem {

	public JFrame frmAllinonesystem;
	private MitarbeiterSystem mSystem;
	private RaumSystem rSystem;
	private FahrzeugeSystem fSystem;
	private ComputerSystem cSystem;

	/**
	 * Create the application.
	 */
	public AllInOneSystem(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML, Document XML, String type, String user) {
		createNeededSystem(raumXML, fahrzeugeXML, computerXML, type);
		mSystem = new MitarbeiterSystem(mitarbeiterXML);
		initialize(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, XML, type, user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML, Document XML, String type, String user) {
		frmAllinonesystem = new JFrame();
		frmAllinonesystem.setIconImage(Toolkit.getDefaultToolkit().getImage(AllInOneSystem.class.getResource("/resources/bear.png")));
		frmAllinonesystem.setTitle(type + " System");
		frmAllinonesystem.setBounds(100, 100, 350, 270);
		frmAllinonesystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAllinonesystem.getContentPane().setLayout(null);
		
		//checks if the type is 'Staff', if not this button is not visible
		if (type.equals("Staff")) {
			/**
			 * PrintAsList-Button: this button is used to print out a list of staff members within certain staff group
			 */
			JButton btnNewButton = new JButton("Print as List");
			btnNewButton.setToolTipText("prints a list of staff members (within the chosen group) on the console");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
					printList();
				}
			});
			btnNewButton.setBounds(170, 150, 130, 23);
			frmAllinonesystem.getContentPane().add(btnNewButton);
		}
		
		/**
		 * PrintAsXML-Button: this button is used to print out the XML file of the current system, which one it is, is determined by the type variable
		 */
		JButton btnNewButton_1 = new JButton("Print as XML");
		btnNewButton_1.setToolTipText("prints the XML of the current system on the console");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				printAsXML(type);
			}
		});
		btnNewButton_1.setBounds(30, 150, 130, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_1);
		
		/**
		 * Booking-Button: this button is used to book an object from the current system, which one it is, is determined by the type variable
		 */
		JButton btnNewButton_2 = new JButton("Booking " + type);
		btnNewButton_2.setToolTipText("user can book an object from the current system");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				booking(type,user);
			}
		});
		btnNewButton_2.setBounds(30, 50, 130, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_2);
		
		/**
		 * Add-Button: this button is used to add an object to the current system, which one it is, is determined by the type variable
		 */
		JButton btnNewButton_3 = new JButton("Add " + type);
		btnNewButton_3.setToolTipText("adds an object to the current system");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				addItem(type,user);
			}
		});
		btnNewButton_3.setBounds(30, 100, 130, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_3);
		
		/**
		 * Cancel-Button: this button is used to cancel a booking of an object from the current system, which one it is, is determined by the type variable
		 */
		JButton btnNewButton_4 = new JButton("Cancel Booking");
		btnNewButton_4.setToolTipText("user can cancel a booking from the current system");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				cancelBooking(type,user);
			}
		});
		btnNewButton_4.setBounds(170, 50, 130, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_4);
		
		/**
		 * Delete-Button: this button is used to delete an object from the current system, which one it is, is determined by the type variable
		 */
		JButton btnNewButton_5 = new JButton("Delete " + type);
		btnNewButton_5.setToolTipText("removes an object from the current system");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				removeItem(type,user);
			}
		});
		btnNewButton_5.setBounds(170, 100, 130, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_5);
		
		/**
		 * Back-Button: a button that is used to go back to the 'SystemOverview'
		 */
		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.setToolTipText("button that is used to lead the user back to the system overview");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/** actionPerformed is done when the button was pressed*/
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SystemOverview window = new SystemOverview(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, user);
							window.frmSystemOverview.setVisible(true);
							frmAllinonesystem.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_6.setBounds(132, 198, 65, 23);
		frmAllinonesystem.getContentPane().add(btnNewButton_6);
		
		/**
		 * ChooseAction-Label: a JLabel that tells the user to choose an action
		 */
		JLabel lblChooseAnAction = new JLabel("Choose an action you would like to perform");
		lblChooseAnAction.setToolTipText("Grab her right by the pussay. - Donald J. Trump 2016");
		lblChooseAnAction.setFont(new Font( "Times New Roman", Font.PLAIN, 16));
		lblChooseAnAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAnAction.setBounds(30, 11, 270, 28);
		frmAllinonesystem.getContentPane().add(lblChooseAnAction);
	}

	/**
	 * createNeededSystem: a private method that creates an object of one system class, except staff(is needed for all)
	 */
	private void createNeededSystem (Document raumXML, Document fahrzeugeXML, Document computerXML, String type) {
		switch (type) {
			case "Room":
				rSystem = new RaumSystem(raumXML);
				break;
			case "Vehicle":
				fSystem = new FahrzeugeSystem(fahrzeugeXML);
				break;
			case "Device":
				cSystem = new ComputerSystem(computerXML);
				break;
		}
	}
	
	/**
	 * printOutXML: a private method that tries to transform a document into a stream, so it can be printed on the console
	 */
	private void printOutXML(String xmlName) {
		
		try {
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = parser.parse(new InputSource("D:\\Schule\\Programs\\src_systemERP\\resources\\" + xmlName));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Source source = new DOMSource(document);
			Result output = new StreamResult(System.out);
			transformer.transform(source, output);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * printAsXML: a private method that determines which file is used for the printOutXML method, by comparing the type with strings
	 */
	private void printAsXML(String type) {
		try {
			switch (type) {
				case "Room":
					printOutXML("Raeume.xml");
					break;
				case "Vehicle":
					printOutXML("Fahrzeuge.xml");
					break;
				case "Staff":
					printOutXML("Mitarbeiter.xml");
					break;
				case "Device":
					printOutXML("Computer.xml");
					break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * printList: a private method that asks the user to determine which group filter should be used for the sortByGroup method in the staff system class
	 */
	private void printList() {
		String group = JOptionPane.showInputDialog("Please enter your group of staff you want to print: ");
		mSystem.sortByGroup(group);
	}
	
	/**
	 * booking: a private method that books an object from the current system, it calls the 'booking' method in the current system
	 */
	private void booking(String type, String user) {
		int check;
		//determines which system is used by comparing type with strings
		switch (type) {
			case "Room":
				//user is asked to give important information for a booking process
				String raumnummer = JOptionPane.showInputDialog("Please enter the room number of the room you would like to book: ");
				String name = JOptionPane.showInputDialog("Please enter your name to verify the booking: ");
				check=0;
				if (name.equals(user)) { //system checks if the verifying name is the same as the users name
					rSystem.raumBuchen(raumnummer, name); //'booking' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your booking action");
				}
				break;
			case "Vehicle":
				//user is asked to give important information for a booking process
				String fahrzeugNummer = JOptionPane.showInputDialog("Please enter the vehicle number of the vehicle you would like to book: ");
				String name1 = JOptionPane.showInputDialog("Please enter your name to verify the booking: ");
				check=0;
				if (name1.equals(user)) { //system checks if the verifying name is the same as the users name
					fSystem.fahrzeugBuchen(fahrzeugNummer, name1); //'booking' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your booking action");
				}
				break;
			case "Staff":
				//user is asked to give important information for a booking process
				String mitarbeiterNummer = JOptionPane.showInputDialog("Please enter the personal number of the staff you would like to book: ");
				String name2 = JOptionPane.showInputDialog("Please enter your name to verify the booking: ");
				check=0;
				if (name2.equals(user)) { //system checks if the verifying name is the same as the users name
					mSystem.mitarbeiterBuchen(mitarbeiterNummer, name2); //'booking' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your booking action");
				}
				break;
			case "Device":
				//user is asked to give important information for a booking process
				String inventarNummer = JOptionPane.showInputDialog("Please enter the item number of the device you would like to book: ");
				String name3 = JOptionPane.showInputDialog("Please enter your name to verify the booking: ");
				check=0;
				if (name3.equals(user)) { //system checks if the verifying name is the same as the users name
					cSystem.geraetBuchen(inventarNummer, name3); //'booking' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your booking action");
				}
		}
	}
	
	/**
	 * cancelBooking: a private method that cancels a booking of an object from the current system, it calls the 'cancelBooking' method in the current system
	 */
	private void cancelBooking(String type, String user) {
		int check;
		switch (type) {
			case "Room":
				//user is asked to give important information for a cancel process
				String raumnummer = JOptionPane.showInputDialog("Please enter the room number of the room you would like to cancel: ");
				String name = JOptionPane.showInputDialog("Please enter your name to verify the booking cancelation: ");
				check=0;
				if (name.equals(user)) { //system checks if the verifying name is the same as the users name
					rSystem.raumBuchungStornieren(raumnummer, name); //'cancel' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your cancel action");
				}
				break;
			case "Vehicle":
				//user is asked to give important information for a cancel process
				String fahrzeugNummer = JOptionPane.showInputDialog("Please enter the vehicle number of the vehicle you would like to cancel: ");
				String name1 = JOptionPane.showInputDialog("Please enter your name to verify the booking cancelation: ");	
				check=0;
				if (name1.equals(user)) { //system checks if the verifying name is the same as the users name
					fSystem.fahrzeugBuchungStornieren(fahrzeugNummer, name1); //'cancel' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your cancel action");
				}
				break;
			case "Staff":
				//user is asked to give important information for a cancel process
				String mitarbeiterNummer = JOptionPane.showInputDialog("Please enter the personal number of the staff you would like to cancel: ");
				String name2 = JOptionPane.showInputDialog("Please enter your name to verify the booking cancelation: ");
				check=0;
				if (name2.equals(user)) { //system checks if the verifying name is the same as the users name
					mSystem.mitarbeiterBuchungStornieren(mitarbeiterNummer, name2); //'cancel' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your cancel action");
				}
				break;
			case "Device":
				//user is asked to give important information for a cancel process
				String inventarNummer = JOptionPane.showInputDialog("Please enter the item number of the device you would like to cancel: ");
				String name3 = JOptionPane.showInputDialog("Please enter your name to verify the booking cancelation: ");
				check=0;
				if (name3.equals(user)) { //system checks if the verifying name is the same as the users name
					cSystem.geraetBuchungStornieren(inventarNummer, name3); //'cancel' method of the current system is called
					check=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
				}
				if (check==0) { //if the names are not equal the user will get a message that tells him about it
					JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your cancel action");
				}
				break;
		}
	}
	
	/**
	 * removeItem: a private method that deletes an object from the current system, it calls the 'deleteObject' method in the current system
	 */
	private void removeItem(String type, String user) {
		int check1=0;
		if (mSystem.isAdmin(user) == true) {
			check1=1;
			int check2;
			switch (type) {
				case "Room":
					//user is asked to give important information for a deleting process
					String raumnummer = JOptionPane.showInputDialog("Please enter the room number of the room you would like to delete: ");
					String name = JOptionPane.showInputDialog("Please enter your name to verify the deleting process: ");
					check2=0;
					if (name.equals(user)) { //system checks if the verifying name is the same as the users name
						rSystem.raumloeschen(raumnummer, name); //'deleting' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your delete action");
					}
					break;
				case "Vehicle":
					//user is asked to give important information for a deleting process
					String fahrzeugNummer = JOptionPane.showInputDialog("Please enter the vehicle number of the vehicle you would like to delete: ");
					String name1 = JOptionPane.showInputDialog("Please enter your name to verify the deleting process: ");
					check2=0;
					if (name1.equals(user)) { //system checks if the verifying name is the same as the users name 
						fSystem.fahrzeugloeschen(fahrzeugNummer, name1); //'deleting' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your delete action");
					}
					break;
				case "Staff":
					//user is asked to give important information for a deleting process
					String mitarbeiterNummer = JOptionPane.showInputDialog("Please enter the personal number of the staff you would like to delete: ");
					String name2 = JOptionPane.showInputDialog("Please enter your name to verify the deleting process: ");
					check2=0;
					if (name2.equals(user)) { //system checks if the verifying name is the same as the users name
						mSystem.mitarbeiterloeschen(mitarbeiterNummer, name2); //'deleting' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your delete action");
					}
					break;
				case "Device":
					//user is asked to give important information for a deleting process
					String inventarNummer = JOptionPane.showInputDialog("Please enter the item number of the device you would like to delete: ");
					String name3 = JOptionPane.showInputDialog("Please enter your name to verify the deleting process: ");
					check2=0;
					if (name3.equals(user)) { //system checks if the verifying name is the same as the users name
						cSystem.geraetloeschen(inventarNummer, name3); //'deleting' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your delete action");
					}
					break;
			}
		}
		if (check1 == 0) {
			JOptionPane.showMessageDialog(null,"You dont have the administrative rights to remove Objects from this System!");
		}
	}
	
	/**
	 * addItem: a private method that adds an object to the current system, it calls the 'addObject' method in the current system
	 */
	private void addItem(String type, String user) {
		int check1=0;
		if (mSystem.isAdmin(user) == true) { //system checks if the user is an administrator, by calling the 'isAdmin' method in the staff system
			check1=1; //to prevent the system from showing a message even if a the user is an administrator this variable is set to '1'
			int check2;
			switch (type) {
				case "Room":
					//user is asked to give important information for an adding process
					String raumNummer = JOptionPane.showInputDialog("Please enter the room number of the room you would like to add: ");
					String laenge = JOptionPane.showInputDialog("Please enter the length of the room you would like to add: ");
					String breite = JOptionPane.showInputDialog("Please enter the width of the room you would like to add: ");
					String hoehe = JOptionPane.showInputDialog("Please enter the height of the room you would like to add: ");
					String name = JOptionPane.showInputDialog("Please enter your name to verify the adding process: ");
					check2=0;
					if (name.equals(user)) { //system checks if the verifying name is the same as the users name
						rSystem.raumHinzufuegen(raumNummer, laenge, breite, hoehe); //'adding' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your adding action");
					}
					break;
				case "Vehicle":
					//user is asked to give important information for an adding process
					String fahrzeugNummer = JOptionPane.showInputDialog("Please enter the vehicle number of the vehicle you would like to add: ");
					String fahrzeugTyp = JOptionPane.showInputDialog("Please enter the type of the vehicle you would like to add: ");
					String fahrzeugName = JOptionPane.showInputDialog("Please enter the name of the vehicle you would like to add: ");
					String name1 = JOptionPane.showInputDialog("Please enter your name to verify the adding process: ");
					check2=0;
					if (name1.equals(user)) { //system checks if the verifying name is the same as the users name
						fSystem.fahrzeugHinzufuegen(fahrzeugTyp, fahrzeugName, fahrzeugNummer); //'adding' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your adding action");
					}
					break;
				case "Staff":
					//user is asked to give important information for an adding process
					String mitarbeiterNummer = JOptionPane.showInputDialog("Please enter the personal number of the staff you would like to add: ");
					String mitarbeiterName = JOptionPane.showInputDialog("Please enter the username of the staff you would like to add: ");
					String gruppe = JOptionPane.showInputDialog("Please enter the group of the staff you would like to add: ");
					String birth = JOptionPane.showInputDialog("Please enter the birthdate of the staff you would like to add: ");
					String name2 = JOptionPane.showInputDialog("Please enter your name to verify the adding process: ");
					check2=0;
					if (name2.equals(user)) { //system checks if the verifying name is the same as the users name
						mSystem.mitarbeiterHinzufuegen(gruppe, mitarbeiterName, mitarbeiterNummer, birth); //'adding' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your adding action");
					}
					break;
				case "Device":
					//user is asked to give important information for an adding process
					String inventarNummer = JOptionPane.showInputDialog("Please enter the item number of the device you would like to add: ");
					String geraeteTyp = JOptionPane.showInputDialog("Please enter the type of the device you would like to add: ");
					String geraeteName = JOptionPane.showInputDialog("Please enter the name of the device you would like to add: ");
					String aPreis = JOptionPane.showInputDialog("Please enter the price of the device you would like to add: ");
					String nDauer = JOptionPane.showInputDialog("Please enter the estimated time of usage for the device you would like to add: ");
					String name3 = JOptionPane.showInputDialog("Please enter your name to verify the adding process: ");
					check2=0;
					if (name3.equals(user)) { //system checks if the verifying name is the same as the users name
						cSystem.geraetHinzufuegen(geraeteTyp, geraeteName, inventarNummer, aPreis, nDauer); //'adding' method of the current system is called
						check2=1; //to prevent the system from showing a message even if a process has worked this variable is set to '1'
					}
					if (check2==0) { //if the names are not equal the user will get a message that tells him about it
						JOptionPane.showMessageDialog(null,"Your username does not match the name you chose for your adding action");
					}
					break;
			}
		}
		if (check1 == 0) { //if the user is not an administrator the system will show a message that tells him about it
			JOptionPane.showMessageDialog(null,"You dont have the administrative rights to add Objects to this System!");
		}
	}
}	
