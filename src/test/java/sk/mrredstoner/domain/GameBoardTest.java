package sk.mrredstoner.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

//TODO make actual tests
public class GameBoardTest {
	private static CardDeck deck;
	
	@BeforeClass
	public static void makDeck(){
		deck=new CardDeck();
	}
	
	@Test
	public void test() {
		GameBoard gb=new GameBoard(deck, Arrays.copyOf(deck.getUnshuffledDeck(), 4));
		assertNotNull(gb);
	}

}
