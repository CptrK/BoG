package systemERP;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import org.w3c.dom.Document;
import workerSystem.MitarbeiterSystem;
import java.awt.Toolkit;

	/**@author Robin Konopka */ 

public class LogHelpGUI {

	public JFrame frmPasswordChangeMenu;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JLabel lblUsername;
	private JLabel lblOldPassword;
	private JLabel lblNewPassword;
	private MitarbeiterSystem mSystem;

	/**
	 * Create the application.
	 */
	public LogHelpGUI(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		mSystem = new MitarbeiterSystem(mitarbeiterXML);
		initialize(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		frmPasswordChangeMenu = new JFrame();
		frmPasswordChangeMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(LogHelpGUI.class.getResource("/resources/buttonpic.png")));
		frmPasswordChangeMenu.setTitle("Password Change Menu");
		frmPasswordChangeMenu.setBounds(100, 100, 450, 250);
		frmPasswordChangeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordChangeMenu.getContentPane().setLayout(null);
		
		/**
		 * OldPassword-Field: a field where the user can enter his old password, written characters are hidden as dots
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 95, 161, 20);
		frmPasswordChangeMenu.getContentPane().add(passwordField);
		
		/**
		 * NewPassword-Field: a field where the user can enter his new password, written characters are hidden as dots
		 */
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(200, 120, 161, 20);
		frmPasswordChangeMenu.getContentPane().add(passwordField_1);
		
		/**
		 * User-Field: a field where the user can enter his user name
		 */
		textField = new JTextField();
		textField.setBounds(200, 64, 161, 20);
		frmPasswordChangeMenu.getContentPane().add(textField);
		textField.setColumns(10);
		
		/**
		 * Back-Button: a button that is used to go back to the 'LogInGUI'
		 */
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setToolTipText("This button is used to get back, to the LogIn menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				//the LogInMenu is opened up
				openLogInMenu(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
			}
		});
		btnNewButton.setBounds(236, 178, 125, 23);
		frmPasswordChangeMenu.getContentPane().add(btnNewButton);
		
		/**
		 * ChangePassword-Button: a button that is used to change the password and return to the 'LogInMenu' after that, if the LogIn data is correct
		 */
		JButton btnNewButton_1 = new JButton("Change Password");
		btnNewButton_1.setToolTipText("When you have entered all your informations, this button is used to change your password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { /** actionPerformed is done when the button was pressed*/
				//temporary variables to check if the LogIn data is correct
				String name = textField.getText();
				char [] old = passwordField.getPassword();
				String psswrd = new String(old);
				char [] nyu = passwordField_1.getPassword();
				String newPsswrd = new String(nyu);
				int check = 0;
				//system is going through the worker list
				for (int i=0; i<mSystem.mitarbeiterListe.size(); i++) {
					//system searches for an Object of worker with the same name and password
					if (mSystem.mitarbeiterListe.get(i).getName().equals(name) && mSystem.mitarbeiterListe.get(i).getLogin().equals(psswrd) ) {
						check=1;
						//system checks if the old password is the same as the new one
						if (!psswrd.equals(newPsswrd)) {
							//the password is changed and the LogInMenu is opened, also a message is displayed to tell the user that the password was changed
							mSystem.mitarbeiterListe.get(i).setLogin(newPsswrd);
							mSystem.createXML();
							JOptionPane.showMessageDialog(null,"Your password was succesfully changed! System restart so changes are safed.");
							frmPasswordChangeMenu.setVisible(false);
						}
						else {
							//if old and new password are identical a message is displayed
							JOptionPane.showMessageDialog(null,"The old and the new password can't be identical!");
						}
					}
				}
				if (check==0) {
					// if there is no worker object with the same name and password in the worker list, a message appears on the screen
					JOptionPane.showMessageDialog(null,"Your username or password is wrong. Please, try again!");
				}
			}
		});
		btnNewButton_1.setBounds(65, 178, 125, 23);
		frmPasswordChangeMenu.getContentPane().add(btnNewButton_1);
		
		/**
		 * Enter-Label: a JLabel that asks the user to enter his LogIn data and a new password
		 */
		JLabel lblPleaseEnterYour = new JLabel("Please enter your LogIn data and choose your new password.");
		lblPleaseEnterYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterYour.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPleaseEnterYour.setBounds(10, 11, 414, 42);
		frmPasswordChangeMenu.getContentPane().add(lblPleaseEnterYour);
		
		/**
		 * User-Label: a JLabel next to the 'User-Field', it tells the user that the field next to it, is the 'User-Field'
		 */
		lblUsername = new JLabel("Username:");
		lblUsername.setToolTipText("Your Username is build as followed: firstname.lastname");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUsername.setBounds(36, 64, 154, 20);
		frmPasswordChangeMenu.getContentPane().add(lblUsername);
		
		/**
		 * OldPassword-Label: a JLabel next to the 'OldPassword-Field', it tells the user that the field next to it, is the 'OldPassword-Field'
		 */
		lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setToolTipText("If you haven't logged in before, your password is init00");
		lblOldPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOldPassword.setBounds(36, 95, 154, 20);
		frmPasswordChangeMenu.getContentPane().add(lblOldPassword);
		
		/**
		 * NewPassword-Label: a JLabel next to the 'NewPassword-Field', it tells the user that the field next to it, is the 'NewPassword-Field'
		 */
		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setToolTipText("Here you should consider to choose a safe password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewPassword.setBounds(36, 120, 154, 20);
		frmPasswordChangeMenu.getContentPane().add(lblNewPassword);
		
	}
	
	/**
	 * openLogInMenu: a private method that invokes an 'eventQueue', it runs the 'LogInGUI' makes it visible and sets this GUI to invisible
	 */
	private void openLogInMenu (Document raumXML, Document mitarbeiterXML, Document fahrzeugeXML, Document computerXML) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LogInGUI window = new LogInGUI(raumXML, mitarbeiterXML, fahrzeugeXML, computerXML);
				window.frmErpSystem.setVisible(true);
				frmPasswordChangeMenu.setVisible(false);
			}
		});
	}
	
}
