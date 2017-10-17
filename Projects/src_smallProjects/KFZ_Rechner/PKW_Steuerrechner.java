package KFZ_Rechner;

import java.util.Scanner;

/**@author Felix Goldmann & Robin Konopka */

public class PKW_Steuerrechner {
	
    private static Scanner reader;

	public static void main(String[] args) {
        reader = new Scanner(System.in);
        
        System.out.println("Please, enter the car type:");
        String type = reader.next();
        
        while (!type.equals("Benzin") && !type.equals("Diesel")) { //Checking if the input is valid
            System.out.println("Oops! Something went wrong. Please, tell us if youre car is a Benziner or a Diesel.");
            type = reader.next();
        }
        
        System.out.println("Please, enter the 'Hubraum':");
        int hubraum = reader.nextInt();
        
        System.out.println("Please, enter the 'Emissionswert':");
        int emissionswert = reader.nextInt();
        
        System.out.println("You need to pay: " + calculate(type, hubraum, emissionswert) + "€ for your 'PKW-Tax'.");
        //System.out.println(""+ calculate("Benzin", 4162, 332)); -> for the given example.
        //System.out.println(""+ calculate("Benzin", 1290, 101)); -> for the given example.
        
    }
    
    private static int calculate(String type, int hubraum, int emissionswert){ //Calculates the amount of taxes that must be paid.
        int grammUeber95 = 0;
        if(emissionswert>95) grammUeber95 = emissionswert-94;
        switch(type){
            case "Benzin":
                return (int) Math.floor(hubraum/100)*2 + grammUeber95*2;
            case "Diesel":
                return (int) ((int) Math.ceil(Math.floor(hubraum/100))*9.5 + grammUeber95*2);
        }
        return -1;
    }
    
}
