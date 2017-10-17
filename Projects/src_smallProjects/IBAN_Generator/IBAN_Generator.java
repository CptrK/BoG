package IBAN_Generator;

import java.math.BigInteger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**@author Felix Goldmann & Robin Konopka */

public class IBAN_Generator {
	
	public static final String[] textfield = { "generate IBAN", "validate IBAN","exit programm" };
	
	public static void main (String[] args){
		JTextField accountNumber = new JTextField();
		JTextField bankcode = new JTextField();
		
		JFrame frame = new JFrame("IBAN");
		String type = (String) JOptionPane.showInputDialog(frame, "Choose your Option:", "What would you like to do?", JOptionPane.PLAIN_MESSAGE, null, textfield, textfield[0]);
		//Opens a dialog where you can choose multiple options
		
		if(type.equals("validate IBAN")){ //Opens a dialog where you can validate your IBAN by writing it in a textfield
			String iban = (String) JOptionPane.showInputDialog("Please, enter your IBAN.");
			if (validateIban(iban)){ //When the IBAN is correct you'll get the message below
				JOptionPane.showMessageDialog(null, "Your IBAN is correct!");
				String optV = (String) JOptionPane.showInputDialog("Do you want to continue your process? \n \n 'Yes' or 'No'?");
				//You're ask if you want to continue using this process
				if (optV.equals("Yes")) { //If 'Yes' is chosen you will get back to the start menu
					IBAN_Generator.main(args);
				}
			}
			else{ //When the IBAN is incorrect you'll get the message below
				JOptionPane.showMessageDialog(null,"Oops! Something went wrong, please check your IBAN and retry the validation.");
				String optV = (String) JOptionPane.showInputDialog("Do you want to retry validate your IBAN? \n \n 'Yes' or 'No'?");
				//You're ask if you want to retry validate your IBAN
				if (optV.equals("Yes")) { //If 'Yes' is chosen you will get back to the start menu
					IBAN_Generator.main(args);
				}
			}
		}
		else if(type.equals("generate IBAN")){ //Opens a dialog where you can create your IBAN by writing your account number and bank code in textfields
			Object[] daten = {"Account number: ", accountNumber ,"Bank code:",bankcode};
			JOptionPane pane = new JOptionPane( daten, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			pane.createDialog(null,  "Eingaben").setVisible(true);
				if(bankcode.getText().length() != 8 ){ //When the bank code does not have eight digits you'll get the message below
					JOptionPane.showMessageDialog(null,"Your bank code is either too short or too long. \nRemember the bank code is an eight digit code.");
					String optG = (String) JOptionPane.showInputDialog("Do you want to retry generating your IBAN? \n \n 'Yes' or 'No'?");
					//You're ask if you want to retry generating your IBAN
					if (optG.equals("Yes")) { //If 'Yes' is chosen you will get back to the start menu
						IBAN_Generator.main(args);
					}
				}
				else if(accountNumber.getText().length() > 10 || accountNumber.getText().length() < 5){ //When the bank code does not have between five and ten digits you'll get the message below
					JOptionPane.showMessageDialog(null,"Your account number is either too short or too long. \nRemember the account number is an five to ten digit code.");
					String optG = (String) JOptionPane.showInputDialog("Do you want to retry generating your IBAN? \n \n 'Yes' or 'No'?");
					//You're ask if you want to retry generating your IBAN
					if (optG.equals("Yes")) { //If 'Yes' is chosen you will get back to the start menu
						IBAN_Generator.main(args);
					}
				}
				else { //When your input for both fields is valid you'll receive the message below 
					JOptionPane.showMessageDialog(null, "Your IBAN is: " + convertToIban(accountNumber.getText(), bankcode.getText()));
					//You'll also get your IBAN as an output in the Console (makes it easier to check validation)
					System.out.println("Your IBAN is: " + convertToIban(accountNumber.getText(), bankcode.getText()));
					String optG = (String) JOptionPane.showInputDialog("Do you want to continue your process? \n \n 'Yes' or 'No'?");
					//You're ask if you want to continue using this process
					if (optG.equals("Yes")) { //If 'Yes' is chosen you will get back to the start menu
						IBAN_Generator.main(args);
					}
		    }
	    }
		else { //Opens a dialog where you can choose to exit the program
			String optE = (String) JOptionPane.showInputDialog("Are you sure, you want to exit the program? \n \n 'Yes' or 'No'?");
			//You're ask if you're sure to exit the program
			if (optE.equals("Yes")) {
			}
			else{ //If 'No' is chosen you will get back to the start menu
				IBAN_Generator.main(args);
			}
		}
}
	
	public static String convertToIban(String accountNumber, String bankcode){ //Generates an IBAN with the given account number and bank code
		String IBAN = new String();
			while(accountNumber.length()<10){ //Fills left side of the account number with zeros until it has 10 digits
				accountNumber = "0"+accountNumber;
			}
		IBAN = bankcode + accountNumber + "131400";
		int checkDigit = calculateCheckDigit(IBAN);
		IBAN = "";
		IBAN = "DE" + checkDigit + bankcode + accountNumber; //The actual IBAN is created
		return IBAN;
	}
	
	public static boolean validateIban(String IBAN){ //Checks if the given IBAN is equal to the generated one
		String bankcode = new String();
		String accountNumber = new String();
		for (int i = 0; i < IBAN.length(); i++ ){
			if (i >=  4 && i <= 11){ //Checks if the digits 5 till 12 (of IBAN) equal the bank code
				bankcode = bankcode + IBAN.charAt(i);
			}
			else if(i >= 12 ){ //Checks if the digits 13 to 22 (of IBAN) equal the account number
				accountNumber = accountNumber + IBAN.charAt(i);
			}
		}
		if(IBAN.equals(convertToIban(accountNumber, bankcode))){ //Actual comparison of the two IBANs'
			return true;
		}
		else {
			return false;
		}
	}
	
	private static int calculateCheckDigit(String IBAN){ //an (for me) arbitrarily calculation that has an actual check digit as result
		int checkDigit = 0;
		BigInteger tempIBAN = new BigInteger(IBAN);
		BigInteger divisor = new BigInteger("97");
		divisor = tempIBAN.remainder(divisor);
		checkDigit = divisor.intValue();
		checkDigit = 98 - checkDigit;
		return checkDigit;
	}

}