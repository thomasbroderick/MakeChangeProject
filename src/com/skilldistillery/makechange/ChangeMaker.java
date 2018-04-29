package com.skilldistillery.makechange;

import java.util.Scanner;

public class ChangeMaker {

	public void run() {
		askInput();
//		calcChange();
//		issueChange();

	}

	public void issueChange() {
		// TODO Write method that takes the array output from calcChange and converts it
		// into the final output

	}

	public void calcChange() {
		// TODO Write method that takes the change amount from the askInput method and
		// creates an array of quotients that correspond to the various monetary amounts

	}

	public double askInput() {
		// TODO Write method that asks the user for input and determines if there is
		// change to be issued
		Scanner kb = new Scanner(System.in);
		double amtOwed = 0.0, amtTendered = 0.0, changeDue = 0.0;
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
						if (amtTendered == amtOwed) {
							System.out.println("That's the exact amount owed.  Have a nice day!");
							return 0.0;
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
