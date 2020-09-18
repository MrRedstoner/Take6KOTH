package sk.mrredstoner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameBoard {
	@SuppressWarnings("unused")
	private final List<List<Card>> rows;
	
	/**Creates a new Game board, using the given deck, and cards as starts of rows
	 * @param deck the deck to use when necessary
	 * @param baseCards an array of length 4 containing the bases for rows
	 */
	public GameBoard(CardDeck deck, Card[]baseCards){
		assert baseCards.length==4;
		
		List<List<Card>>rows=new ArrayList<List<Card>>(4);
		for(int i=0;i<4;i++){
			List<Card>row=new LinkedList<Card>();
			row.add(baseCards[i]);
			rows.add(row);
		}
		
		this.rows=Collections.unmodifiableList(rows);
	}
}
