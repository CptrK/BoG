package systemERP;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import org.w3c.dom.Document;

	/**@author Robin Konopka */ 

public class SystemOverview {

	public JFrame frmSystemOverview;

	/**
	 * Create the application.
	 */
	public SystemOverview(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML, String user) {
		initialize(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML, String user) {
		frmSystemOverview = new JFrame();
		frmSystemOverview.setIconImage(Toolkit.getDefaultToolkit().getImage(SystemOverview.class.getResource("/resources/illuminati.png")));
		frmSystemOverview.setTitle("Systems");
		frmSystemOverview.setBounds(100, 100, 205, 300);
		frmSystemOverview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemOverview.getContentPane().setLayout(null);
		
		/**
		 * RoomSystem-Button: a Button that leads the user to the 'AllInOneSystem' GUI with the type 'Room'
		 */
		JButton btnNewButton = new JButton("Room System");
		btnNewButton.setToolTipText("this button leads the user to the Room System");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { /** actionPerformed is done when the button was pressed*/
				openAllInOneSystem(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, raumXML, "Room", user);
			}
		});
		btnNewButton.setBounds(10, 40, 170, 30);
		frmSystemOverview.getContentPane().add(btnNewButton);
		
		/**
		 * VehicleSystem-Button: a Button that leads the user to the 'AllInOneSystem' GUI with the type 'Vehicle'
		 */
		JButton btnNewButton_1 = new JButton("Vehicle System");
		btnNewButton_1.setToolTipText("this button leads the user to the Vehicle System");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				openAllInOneSystem(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, fahrzeugeXML, "Vehicle", user);
			}
		});
		btnNewButton_1.setBounds(10, 81, 170, 30);
		frmSystemOverview.getContentPane().add(btnNewButton_1);
		
		/**
		 * StaffSystem-Button: a Button that leads the user to the 'AllInOneSystem' GUI with the type 'Staff'
		 */
		JButton btnNewButton_2 = new JButton("Staff System");
		btnNewButton_2.setToolTipText("this button leads the user to the Staff System");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				openAllInOneSystem(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, mitarbeiterXML, "Staff", user);
			}
		});
		btnNewButton_2.setBounds(10, 122, 170, 30);
		frmSystemOverview.getContentPane().add(btnNewButton_2);
		
		/**
		 * DeviceSystem-Button: a Button that leads the user to the 'AllInOneSystem' GUI with the type 'Device'
		 */
		JButton btnNewButton_3 = new JButton("Device System");
		btnNewButton_3.setToolTipText("this button leads the user to the Device System");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				openAllInOneSystem(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, computerXML, "Device", user);
			}
		});
		btnNewButton_3.setBounds(10, 163, 170, 30);
		frmSystemOverview.getContentPane().add(btnNewButton_3);
		
		/**
		 * LogOut-Button: a Button that lets the user log out, user is send to the 'LogInGUI'
		 */
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setToolTipText("Button that is used to log out the System - user is asked before logging out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you want to log out the system?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					openLogInMenu(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
				}
			}
		});
		btnLogOut.setBounds(50, 228, 89, 23);
		frmSystemOverview.getContentPane().add(btnLogOut);
		
		
		/**
		 * Choose-Message: a JLabel that tells the user to choose a system of choice
		 */
		JLabel lblChooseASystem = new JLabel("Choose a System");
		lblChooseASystem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChooseASystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseASystem.setBounds(10, 11, 170, 18);
		frmSystemOverview.getContentPane().add(lblChooseASystem);
	}
	
	/**
	 * openLogInMenu: a private method that invokes an 'eventQueue', it runs the 'LogInGUI' makes it visible and sets this GUI to invisible
	 */
	private void openLogInMenu (Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LogInGUI window = new LogInGUI(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
				window.frmErpSystem.setVisible(true);
				frmSystemOverview.setVisible(false);
			}
		});
	}
	
	/**
	 * openAllInOneSystem: a private method that invokes an 'eventQueue', it runs the 'AllInOneSystem' GUI makes it visible and sets this GUI to invisble
	 */
	private void openAllInOneSystem(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML, Document XML, String type, String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllInOneSystem window = new AllInOneSystem(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, XML, type, user);
					window.frmAllinonesystem.setVisible(true);
					frmSystemOverview.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
