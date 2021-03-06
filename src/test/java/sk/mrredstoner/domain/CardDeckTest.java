package sk.mrredstoner.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class CardDeckTest {
	private static CardDeck deck;
	
	@BeforeClass
	public static void makeDeck(){
		deck=new CardDeck();
	}
	
	@Test
	public void checkCardCows(){
		int[] counts={-1, //throwaway to get the indices matching
				1, 1, 1, 1, 2, 1, 1, 1, 1, 3,
				5, 1, 1, 1, 2, 1, 1, 1, 1, 3,
				1, 5, 1, 1, 2, 1, 1, 1, 1, 3,
				1, 1, 5, 1, 2, 1, 1, 1, 1, 3,
				1, 1, 1, 5, 2, 1, 1, 1, 1, 3,
				1, 1, 1, 1, 7, 1, 1, 1, 1, 3,
				1, 1, 1, 1, 2, 5, 1, 1, 1, 3,
				1, 1, 1, 1, 2, 1, 5, 1, 1, 3,
				1, 1, 1, 1, 2, 1, 1, 5, 1, 3,
				1, 1, 1, 1, 2, 1, 1, 1, 5, 3,
				1, 1, 1, 1
				};
		for(int i=1;i<=104;i++){
			assertEquals( "Failure on "+i, i, deck.getCard(i).getNumber());
			assertEquals( "Failure on "+i, counts[i], deck.getCard(i).getCows());
		}
	}
	
	@Test(expected=RuntimeException.class)
	public void throwsOnLowIndex(){
		deck.getCard(0);
	}
	
	@Test(expected=RuntimeException.class)
	public void throwsOnHighIndex(){
		deck.getCard(105);
	}
	
	@Test
	public void checkSortability(){
		Card[]cards=deck.getShuffledDeck();
		Arrays.sort(cards);
		assertArrayEquals(deck.getUnshuffledDeck(), cards);
	}
}
