package sk.mrredstoner.domain;

import java.util.Objects;

import sk.mrredstoner.util.Utils;

public final class Card {
	private final int number, cows;
	
	/**Creates a new Card with the sepcified number, automatically calculating the number of cows*/
	public Card(int number){
		assert 1<=number && number<=104;
		
		this.number=number;
		this.cows=Utils.cows(number);
	}

	/**returns the number of this card*/
	public int getNumber() {
		return number;
	}

	/**returns the number of cows on this card*/
	public int getCows() {
		return cows;
	}
	
	@Override
	public boolean equals(Object other){
		if(other==this)return true;
		if(other==null)return false;
		return other instanceof Card && ((Card)other).number == number;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(number);
	}
	
	/**Returns a debug-convenient textual representation*/
	@Override
	public String toString(){
		return String.format("Card %d[%d]", number, cows);
	}
}
