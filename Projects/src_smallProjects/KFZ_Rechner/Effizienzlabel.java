package KFZ_Rechner;

import javax.swing.JOptionPane;
import javax.swing.*;

/**@author Felix Goldmann & Robin Konopka */

public class Effizienzlabel {
	
	public static void main (String[] args){
		
		JTextField emissions = new JTextField();
		JTextField weight = new JTextField();
		
		Object[] textfield = { "CO2-emission in g/km: ", emissions, "Empty weight in kg: ", weight}; //Sets values for 'textfield'
		
		JOptionPane pane = new JOptionPane( textfield, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION); pane.createDialog(null,  "Enter your informations:").setVisible(true);
		//Opens a dialog where you can set your own informations
		
		double M = Integer.parseInt(weight.getText()); //Converts your set string for 'weight' into an integer
		double R = 36.59079 + 0.08987 * M; //Calculation of a reference value for the emission with empty weight
		
		double CO2_PKW = Integer.parseInt(emissions.getText()); //Converts your set string for 'emissions' into an integer
		double CO2_diff = (CO2_PKW - R) * 100 / R; //Calculation of the actual emissions with the deviation of the reference value
		
		if (CO2_diff <= -37){//Determination for what kind of label your car is qualified
			System.out.println("You have the 'Effizienzlabel' A+. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= -36.99 && CO2_diff <= -28){
			System.out.println("You have the 'Effizienzlabel' A. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= -27.99 && CO2_diff <= -19){
			System.out.println("You have the 'Effizienzlabel' B. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= -18.99 && CO2_diff <= -10){
			System.out.println("You have the 'Effizienzlabel' C. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= -9.99 && CO2_diff <= -1){
			System.out.println("You have the 'Effizienzlabel' D. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= -0.99 && CO2_diff <= 8){
			System.out.println("You have the 'Effizienzlabel' E. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else if (CO2_diff >= 8.01 && CO2_diff <= 17){
			System.out.println("You have the 'Effizienzlabel' F. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		else{
			System.out.println("You have the 'Effizienzlabel' G. \nThis is a consequence caused by your data: \nCO2-Emission: " + CO2_PKW + " \nEmpty weight: " + M);
		}
		
	} 

}
