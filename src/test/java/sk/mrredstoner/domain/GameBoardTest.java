package sk.mrredstoner.domain;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameBoardTest {
	private static CardDeck deck;
	private static Card card(int number){
		return deck.getCard(number);
	}
	
	@BeforeClass
	public static void makeDeck(){
		deck=new CardDeck();
	}
	
	private void assertBoardState(BoardState state, int tc0, int tc1, int tc2, int tc3, int ca0, int ca1, int ca2, int ca3, int co0, int co1, int co2, int co3){
		assertEquals(tc0, state.getTopCard(0));
		assertEquals(tc1, state.getTopCard(1));
		assertEquals(tc2, state.getTopCard(2));
		assertEquals(tc3, state.getTopCard(3));
		
		assertEquals(ca0, state.getCardCount(0));
		assertEquals(ca1, state.getCardCount(1));
		assertEquals(ca2, state.getCardCount(2));
		assertEquals(ca3, state.getCardCount(3));
		
		assertEquals(co0, state.getCowCount(0));
		assertEquals(co1, state.getCowCount(1));
		assertEquals(co2, state.getCowCount(2));
		assertEquals(co3, state.getCowCount(3));
	}
	
	@SafeVarargs
	public final GameBoard makeBoard(int...cards){
		return makeBoard(card(cards[0]),card(cards[1]),card(cards[2]),card(cards[3]));
	}
	
	@SafeVarargs
	public final GameBoard makeBoard(Card...cards){
		return new GameBoard(deck, cards);
	}
	
	@Test
	public void testBaseState() {
		GameBoard gb=makeBoard(1,22,10,55);
		assertNotNull(gb);
		assertBoardState(gb.getState(),
				1, 22, 10, 55,
				1,  1,  1,  1,
				1,  5,  3,  7);
	}

	@Test
	public void testRegularAdd() {
		GameBoard gb=makeBoard(1,22,10,55);
		Card add;
		Set<Card>result;
		
		assertNotNull(gb);
		assertBoardState(gb.getState(),
				1, 22, 10, 55,
				1,  1,  1,  1,
				1,  5,  3,  7);
		
		add=card(11);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertTrue(result.isEmpty());
		assertBoardState(gb.getState(),
				1, 22, 11, 55,
				1,  1,  2,  1,
				1,  5,  8,  7);
		
		add=card(12);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertTrue(result.isEmpty());
		assertBoardState(gb.getState(),
				1, 22, 12, 55,
				1,  1,  3,  1,
				1,  5,  9,  7);

		add=card(14);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertTrue(result.isEmpty());
		assertBoardState(gb.getState(),
				1, 22, 14, 55,
				1,  1,  4,  1,
				1,  5, 10,  7);

		add=card(15);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertTrue(result.isEmpty());
		assertBoardState(gb.getState(),
				1, 22, 15, 55,
				1,  1,  5,  1,
				1,  5, 12,  7);

		add=card(20);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertEquals(IntStream.of(10,11,12,14,15).mapToObj(deck::getCard).collect(Collectors.toSet()), result);
		assertBoardState(gb.getState(),
				1, 22, 20, 55,
				1,  1,  1,  1,
				1,  5,  3,  7);
	}
	
	@Test
	public void testRowAdd() {
		GameBoard gb=makeBoard(60,22,10,55);
		Card add;
		Set<Card>result;
		
		assertNotNull(gb);
		assertBoardState(gb.getState(),
				60, 22, 10, 55,
				1,  1,  1,  1,
				3,  5,  3,  7);
		
		add=card(11);
		
		assertTrue(gb.canPlaceDirectly(add));
		result=gb.placeCard(add);
		assertTrue(result.isEmpty());
		assertBoardState(gb.getState(),
				60, 22, 11, 55,
				1,  1,  2,  1,
				3,  5,  8,  7);
		
		add=card(9);
		
		assertFalse(gb.canPlaceDirectly(add));
		result=gb.placeCard(add, 2);
		assertEquals(IntStream.of(10,11).mapToObj(deck::getCard).collect(Collectors.toSet()), result);
		assertBoardState(gb.getState(),
				60, 22, 9, 55,
				1,  1,  1,  1,
				3,  5,  1,  7);
	}
	
	@Test(expected=RuntimeException.class)
	public void testShouldBeRegularAdd() {
		GameBoard gb=makeBoard(1,22,10,55);
		Card add;
		
		assertNotNull(gb);
		assertBoardState(gb.getState(),
				1, 22, 10, 55,
				1,  1,  1,  1,
				1,  5,  3,  7);
		
		add=card(11);
		
		assertTrue(gb.canPlaceDirectly(add));
		gb.placeCard(add, 0);
	}

	@Test(expected=RuntimeException.class)
	public void testShouldBeRowAdd() {
		GameBoard gb=makeBoard(60,22,10,55);
		Card add;
		
		assertNotNull(gb);
		assertBoardState(gb.getState(),
				60, 22, 10, 55,
				1,  1,  1,  1,
				3,  5,  3,  7);
		
		add=card(9);
		
		assertFalse(gb.canPlaceDirectly(add));
		gb.placeCard(add);
	}
}
