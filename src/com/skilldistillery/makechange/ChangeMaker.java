package com.skilldistillery.makechange;

import java.util.Scanner;

public class ChangeMaker {
	public double changeDue = 0.0;
	int [] moneyInts = {0, 0, 0, 0, 0, 0, 0};
	
	
	public void run() {
		askInput();
		calcChange(changeDue);
//		issueChange(moneyInts);

	}

	public void issueChange() {
		// TODO Write method that takes the array output from calcChange and converts it
		// into the final output

	}

	public int[] calcChange(double d) {
		// TODO Write method that takes the change amount from the askInput method and
		// creates an array of quotients that correspond to the various monetary amounts
		double [] moneyTypes = {10.0, 5.0, 1.0, 0.25, 0.1, 0.05, 0.01};
		double remainder = 0.0;
		System.out.println("Right now d is: " + d);
		for (int i = 0; i < moneyTypes.length; i++) {
			remainder = d % moneyTypes[i];
			System.out.println("Right now remainder is: " + remainder);
			int mI = (int)(d / moneyTypes[i]);
			System.out.println("Right now mI is: " + mI);
			d = remainder;
			moneyInts[i] = mI;
//			System.out.println("Number going into the array is: " + mI);
		}
		if (remainder > 0.005) {
			moneyInts [6] += 1;
			System.out.println(moneyInts[6]);
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
		if (amtOwed <= 0) {
			System.out.println("Looks like this one's on the house.");
			return 0.0;
		} else {
			System.out.print("How much do you pay? ");
			amtTendered = kb.nextDouble();
			if (amtTendered < amtOwed) {
				do {
					System.out.println("Looks like you're a little short.");
					System.out.println("Do you have more money on you? (Y/N)");
					String choice = kb.next();
					if (choice.equals("Y") || choice.equalsIgnoreCase("yes")) {
						System.out.println("How much more do you pay?");
						double moreTendered = kb.nextDouble();
						amtTendered += moreTendered;
						System.out.println("Right now amt tendered is " + amtTendered);
						if (amtTendered == amtOwed) {
							System.out.println("That's the exact amount owed.  Have a nice day!");
							return 0.0;
						}
						else if (amtTendered > amtOwed) {
							changeDue = amtTendered - amtOwed;
						}
					} else {
						System.out.println("Sorry friend, maybe next time.");
						return 0.0;
					}
				} while (amtTendered < amtOwed);
			
			} else if (amtTendered == amtOwed) {
				System.out.println("That's the exact amount owed.  Have a nice day!");
				return 0.0;
			}

			else {
				changeDue = amtTendered - amtOwed;
			}

			kb.close();
		}
		System.out.println(changeDue); //for testing
		return changeDue;

	}

}
