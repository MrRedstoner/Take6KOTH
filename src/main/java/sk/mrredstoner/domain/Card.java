package sk.mrredstoner.domain;

import sk.mrredstoner.util.Utils;

public class Card {
	private final int number, cows;
	
	public Card(int number){
		this.number=number;
		this.cows=Utils.cows(number);
	}

	public int getNumber() {
		return number;
	}

	public int getCows() {
		return cows;
	}
}
