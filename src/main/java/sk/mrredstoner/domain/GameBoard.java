package sk.mrredstoner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import sk.mrredstoner.util.Utils;

public class GameBoard {
	private static final ToIntFunction<? super LinkedList<Card>> COW_SUM=l->l.stream().mapToInt(Card::getCows).sum();
	
	/**Holds the state of the board. LinkedList is used for getLast*/
	private final List<LinkedList<Card>> rows;
	
	/**Creates a new Game board, using the given deck, and cards as starts of rows
	 * @param deck the deck to use when necessary
	 * @param baseCards an array of length 4 containing the bases for rows
	 */
	public GameBoard(CardDeck deck, Card[]baseCards){
		assert baseCards.length==4;
		
		List<LinkedList<Card>>rows=new ArrayList<LinkedList<Card>>(4);
		for(int i=0;i<4;i++){
			LinkedList<Card>row=new LinkedList<Card>();
			row.add(baseCards[i]);
			rows.add(row);
		}
		
		this.rows=Collections.unmodifiableList(rows);
	}
	
	/**Gets a view of the state of the board at the time of call
	 * @return the state of the board*/
	public BoardState getState(){
		return new BoardState(
				rows.stream().map(LinkedList::getLast).mapToInt(Card::getNumber).toArray(),
				rows.stream().mapToInt(LinkedList::size).toArray(),
				rows.stream().mapToInt(COW_SUM).toArray());
	}
	
	/**Checks if a given card can be place directly or a row needs to be picked
	 * @return true if {@link #placeCard(Card)} should be called, false if {@link #placeCard(Card, int)}*/
	public boolean canPlaceDirectly(Card card){
		int num=card.getNumber();
		return rows.stream()
				.map(LinkedList::getLast)
				.mapToInt(Card::getNumber)
				.anyMatch(i->i<num);
	}
	
	/**Places a card on the board, returning any resulting cards (may be empty). {@link #canPlaceDirectly(Card)} should return true for the card
	 * @param card the card to place
	 * @return the set of resulting cards, may be empty
	 * */
	public Set<Card> placeCard(Card card){
		Utils.doAssert(canPlaceDirectly(card), card+" can't be placed directly");
		
		Set<Card>result=Collections.emptySet();
		int num=card.getNumber();
		
		List<Card> row=rows.stream()
				.filter(l->l.getLast().getNumber()<num)
				.max((l0,l1)->l0.getLast().compareTo(l1.getLast())).get();
		
		if(row.size()==5){
			result=row.stream().collect(Collectors.toSet());
			row.clear();
		}

		row.add(card);
		
		return result;
	}
	
	/**Places a card on the board, returning any resulting cards (may be empty). {@link #canPlaceDirectly(Card)} should return false for the card
	 * @param card the card to place
	 * @param row the 0-indexed number of the row where placement occurs
	 * @return the set of resulting cards, may be empty
	 * */
	public Set<Card> placeCard(Card card, int row){
		Utils.doAssert(!canPlaceDirectly(card), card+" must be placed directly");
		
		List<Card>row_=rows.get(row);
		Set<Card>result=row_.stream().collect(Collectors.toSet());
		row_.clear();
		row_.add(card);
		
		return result;
	}
}
