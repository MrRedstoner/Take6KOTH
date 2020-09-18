package sk.mrredstoner.domain;

import java.util.Arrays;

import sk.mrredstoner.util.Utils;

/**Represents a deck of cards that makes up the game, acting as a cache/factory for them*/
public class CardDeck {
	/**first element null to make number==index*/
	private final Card[]cards=new Card[105];
	
	/**Constructs a full deck of cards*/
	public CardDeck(){
		for(int i=1;i<=104;i++){
			cards[i]=new Card(i);
		}
	}
	
	/**Fetches the Card instance identified by the number
	 * @param number the number of the card
	 * @return the Card whose getNumber() == number
	 * @throws IllegalArgumentException if number is outside the valid range
	 * */
	public Card getCard(int number){
		if(number<1 || number>104){
			throw new IllegalArgumentException();
		}
		
		return cards[number];
	}
	
	/**Returns an unshuffled array of all cards
	 * @return a new array containing 104 Cards, in ascending order
	 * */
	public Card[] getUnshuffledDeck(){
		return Arrays.copyOfRange(cards, 1, cards.length);
	}
	
	/**Returns a shuffled array of all cards
	 * @return a new array containing 104 Cards, in random order
	 * */
	public Card[] getShuffledDeck(){
		return Utils.shuffle(getUnshuffledDeck());
	}
}
