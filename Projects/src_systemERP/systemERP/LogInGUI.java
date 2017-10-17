package systemERP;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import org.w3c.dom.Document;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import workerSystem.MitarbeiterSystem;

	/**@author Robin Konopka */ 

public class LogInGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JFrame frmErpSystem;
	private JPasswordField passwordField;
	private JLabel lblWelcomeToThe;
	private JTextField textField;
	private MitarbeiterSystem mSystem;

	/**
	 * Create the application.
	 */
	public LogInGUI(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		mSystem = new MitarbeiterSystem(mitarbeiterXML);
		initialize(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		frmErpSystem = new JFrame();
		frmErpSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(LogInGUI.class.getResource("/resources/illuminati.png")));
		frmErpSystem.setResizable(false);
		frmErpSystem.setTitle("ERP System - Log In");
		frmErpSystem.setBounds(100, 100, 450, 250);
		frmErpSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * LogIn-Button, a Button that can be used to Log into the ERP-system if the LogIn data is correct
		 */
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setBounds(109, 180, 220, 35);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				//temporary variables to check if the LogIn data is correct
				String name = textField.getText();
				char [] pwd = passwordField.getPassword();
				String psswrd = new String(pwd);
				int check = 0;
				//system is going through the worker list
				for (int i=0; i<mSystem.mitarbeiterListe.size(); i++) {
					//system searches for an Object of worker with the same name and password
					if (mSystem.mitarbeiterListe.get(i).getName().equals(name) && mSystem.mitarbeiterListe.get(i).getLogin().equals(psswrd) ) {
						//system checks if the password is "init00" (the initial password), if so it forces the user to change it
						check=1;
						if (psswrd.equals("init00")) {
							//the password change menu is opened up
							openPasswordChangeMenu(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
						}
						else {
							//the system overview is opened up
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										SystemOverview window = new SystemOverview(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML, name);
										window.frmSystemOverview.setVisible(true);
										frmErpSystem.setVisible(false);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
					}
				}
				if (check==0) {
					// if there is no worker object with the same name and password in the worker list, a message appears on the screen
					JOptionPane.showMessageDialog(null,"Your username or password is wrong. Please, try again!");
				}
			}
		});
		frmErpSystem.getContentPane().setLayout(null);
		frmErpSystem.getContentPane().add(btnNewButton);
		
		/**
		 * Password-Field: a field where the user can enter his password, written characters are hidden as dots
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 120, 251, 27);
		frmErpSystem.getContentPane().add(passwordField);
		
		/**
		 * Welcome-Message: a JLabel that greets the user to the operating system
		 */
		lblWelcomeToThe = new JLabel("Welcome to the ERP System - Please enter your Log In Data");
		lblWelcomeToThe.setToolTipText("created by Felix Goldmann and Robin Konopka");
		lblWelcomeToThe.setBounds(0, 0, 444, 60);
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		frmErpSystem.getContentPane().add(lblWelcomeToThe);
		
		/**
		 * User-Field: a field where the user can enter his user name
		 */
		textField = new JTextField();
		textField.setBounds(157, 83, 251, 27);
		frmErpSystem.getContentPane().add(textField);
		textField.setColumns(10);
		
		/**
		 * Password-Label: a JLabel next to the 'Password-Field', it tells the user that the field next to it, is the 'Password-Field'
		 */
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setToolTipText("If you haven't logged in before, your password is init00");
		lblPassword.setBounds(71, 120, 116, 27);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frmErpSystem.getContentPane().add(lblPassword);
		
		/**
		 * User-Label: a JLabel next to the 'User-Field', it tells the user that the field next to it, is the 'User-Field'
		 */
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setToolTipText("Your Username is build as followed: firstname.lastname");
		lblUsername.setBounds(71, 83, 116, 27);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frmErpSystem.getContentPane().add(lblUsername);
		
		/**
		 * Dew-Button: a Button that leads the user to the password change menu
		 */
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Opens the password change menu directly");
		ImageIcon icon = new ImageIcon(LogInGUI.class.getResource("/resources/buttonpic.png"));
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 50, 20,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		btnNewButton_1.setIcon(icon);
		btnNewButton_1.setBounds(345, 192, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				//the password change menu is opened up
				openPasswordChangeMenu(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
			}
		});
		frmErpSystem.getContentPane().add(btnNewButton_1);
		
	}
	
	/**
	 * openPasswordChangeMenu: a private method that invokes an 'eventQueue', it runs the 'LogHelpGUI' makes it visible and sets this GUI to invisible
	 */
	private void openPasswordChangeMenu (Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogHelpGUI window = new LogHelpGUI(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
					window.frmPasswordChangeMenu.setVisible(true);
					frmErpSystem.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
