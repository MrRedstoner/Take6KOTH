package sk.mrredstoner.domain;

import java.util.Arrays;

/**An immutable view of the state of the board. Contains the numbers of cards at the top, the numbers of cards in each row and the numbers of cows in each row*/
public class BoardState {
	private final int[] topCards, cardCounts, cowCounts;
	
	/**Constructs a new view
	 * @param topCards an array of length 4 containing the top cards for rows
	 * @param cardCounts an array of length 4 containing the numbers of cards for rows
	 * @param cowCounts an array of length 4 containing the numbers of cows for rows
	 * */
	public BoardState(int[] topCards, int[] cardCounts, int[] cowCounts){
		assert topCards.length==4;
		assert cardCounts.length==4;
		assert cowCounts.length==4;
		
		this.topCards=Arrays.copyOf(topCards, 4);
		this.cardCounts=Arrays.copyOf(cardCounts, 4);
		this.cowCounts=Arrays.copyOf(cowCounts, 4);
	}
	
	/**returns the number of the top card in the given row
	 * @param row 0-indexed number of the row
	 * @return the number of the top card
	 * */
	public int getTopCard(int row){
		return topCards[row];
	}
	
	/**returns the number of cards in the given row
	 * @param row 0-indexed number of the row
	 * @return the number of cards
	 * */
	public int getCardCount(int row){
		return cardCounts[row];
	}
	
	/**returns the number of cows in the given row
	 * @param row 0-indexed number of the row
	 * @return the number of cows
	 * */
	public int getCowCount(int row){
		return cowCounts[row];
	}
	
	/**Returns a debug-convenient textual representation*/
	@Override
	public String toString(){
		return String.format("State top %s card %s cow %s", Arrays.toString(topCards), Arrays.toString(cardCounts), Arrays.toString(cowCounts));
	}
}
