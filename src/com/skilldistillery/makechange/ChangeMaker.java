package com.skilldistillery.makechange;

import java.util.Scanner;

public class ChangeMaker {
	//Declaring and instantiating the variables that will need to be passed from method to method
	public double changeDue = 0.0;
	int[] moneyInts = { 0, 0, 0, 0, 0, 0, 0 };

	public void run() {
		askInput();
		if (changeDue > 0) {
		calcChange(changeDue);
		issueChange(moneyInts);
		}
	}

	public void issueChange(int[] a) {
		// TODO Write method that takes the array output from calcChange and converts it
		// into the final output
		String[] denoms = { "$10", "$5", "$1", "quarter(s)", "dime(s)", "nickel(s)", "pennies" };
		String output = "Your change comes to: ";
		
		//for loop iterates over the calcChange array and matches it up with the denoms array if the value is greater than 0
		for (int i = 0; i < a.length; i++) { 
			if (a[i] != 0) {
				output += a[i] + " " + denoms[i] + "\t";
			} else {
				continue;
			}
		}
		System.out.println(output);
	}

	public int[] calcChange(double d) {
		// TODO Write method that takes the change amount from the askInput method and
		// creates an array of quotients that correspond to the various monetary amounts
		double[] moneyTypes = { 10.0, 5.0, 1.0, 0.25, 0.1, 0.05, 0.01 };
		double remainder = 0.0;

		//for loop that takes the double amount from changeDue and iterates it over the moneyTypes array, doing division and modulus operations to determine how many of each amount is due back and how much to pass to the next iteration of the loop
		for (int i = 0; i < moneyTypes.length; i++) {
			remainder = d % moneyTypes[i];
			int mI = (int) (d / moneyTypes[i]);
			d = remainder;
			moneyInts[i] = mI;
		}
		//if statement to account for weirdness in the double division that can affect the pennies calculation
		if (remainder > 0.005) {
			moneyInts[6] += 1;
		}
		return moneyInts;
	}

	public double askInput() {
		// TODO Write method that asks the user for input and determines if there is
		// change to be issued
		Scanner kb = new Scanner(System.in);
		double amtOwed = 0.0, amtTendered = 0.0;
		System.out.print("How much do you owe? ");
		amtOwed = kb.nextDouble();
		
		//if statement in case the user inputs a negative amount owed
		if (amtOwed <= 0) {
			System.out.println("Looks like this one's on the house.");
			return 0.0;
		} else {
			System.out.print("How much do you pay? ");
			amtTendered = kb.nextDouble();
			
			//do-while loop that runs while the user has not inputted enough to cover what is owed
			if (amtTendered < amtOwed) {
				do {
					System.out.println("Looks like you're a little short.");
					System.out.println("Do you have more money on you? (Y/N)");
					String choice = kb.next();
					if (choice.equals("Y") || choice.equalsIgnoreCase("yes")) {
						System.out.println("How much more do you pay?");
						double moreTendered = kb.nextDouble();
						amtTendered += moreTendered;
						if (amtTendered == amtOwed) {
							System.out.println("That's the exact amount owed.  Have a nice day!");
							return 0.0;
						} else if (amtTendered > amtOwed) {
							changeDue = amtTendered - amtOwed;
						}
					} else {
						System.out.println("Sorry friend, maybe next time.");
						return 0.0;
					}
				} while (amtTendered < amtOwed);
				
				//output if exact change is provided or if change is due
			} else if (amtTendered == amtOwed) {
				System.out.println("That's the exact amount owed.  Have a nice day!");
				return 0.0;
			}
			else {
				changeDue = amtTendered - amtOwed;
			}

		}
		kb.close();
		return changeDue;
		
	}

}
