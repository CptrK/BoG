package BMI;

import java.util.Scanner;

/**@author Felix Goldmann & Robin Konopka */

public class BMI_Rechner {

		private static Scanner reader;

		public static void main(String[] args) { 
			reader = new Scanner(System.in);
			
			System.out.println("Please, enter your height(in cm):");
	        double height = reader.nextDouble() / 100; //Entered height in meters
	        
	        System.out.println("Please, enter your weight(in kg):");
	        double weight = reader.nextDouble(); //Entered weight in kg
			
			double bmi = weight / (height * height); //Calculates the 'bmi' with entered informations
			
			System.out.println ("Your BodyMassIndex is: " + bmi + "."); //Output of the 'bmi'
			
			if (bmi < 20) { //Determination in which kind of weight category you are
				System.out.println ("With a BodyMassIndex of " + bmi + " you are underweight.");	
			}
			else if (bmi > 20 && bmi < 25) {
				System.out.println ("With a BodyMassIndex of " + bmi + " you have a normal weight.");
			}
			else if (bmi > 25 && bmi < 30) {
				System.out.println ("With a BodyMassIndex of " + bmi + " you are slightly overweight.");
			}
			else {
				System.out.println ("With a BodyMassIndex of " + bmi + " you are overweight.");
			}
		}
		
	}
