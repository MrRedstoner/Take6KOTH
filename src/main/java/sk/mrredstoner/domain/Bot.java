package sk.mrredstoner.domain;

import java.util.List;
import java.util.Set;

public interface Bot {
	/**Returns the name of the bot, same as its associated factory
	 * @return this bot's name*/
	String getName();
	
	/**Initializes the bot for a game. It shall be called exactly once before any other method (except {@link #getName()})
	 * @param cards the set of cards this bot was dealt for the game*/
	void giveCards(Set<Card>cards);
	
	/**Picks a card to play in the current turn. the Card returned should be one of those given in {@link #giveCards(Set)} that wasn't returned by any previous calls.
	 * @param board a view of the board's state at the start of this round
	 * @return the chosen card*/
	Card chooseCard(BoardState board);
	
	/**Informs the bot of all the Cards played in this round by all the bots, including itself.
	 * @param cards the cards used by others*/
	void playedCards(List<Card> cards);
	
	/**Picks a row where to place the Card returned by the last call to {@link #chooseCard(BoardState)}. Valid row numbers are 0 1 2 3
	 * @param board a view of the board's state at the time of choice
	 * @return the chosen row*/
	int pickRow(BoardState board);
	
	/**Informs the bot that it gained result cards after placing the card most recently returned by {@link #chooseCard(BoardState)}
	 * @param cards the cards that were gained*/
	void resultGain(Set<Card> cards);
	
	/**Informs the bot of the score it and the other competitors achieved. No further calls will be made on this Bot (except {@link #getName()}).
	 * @param score the bot's own score
	 * @param others the scores of the other bots*/
	void showScore(int score, List<Integer> others);
	
	/**Informs the bot that it has performed an invalid action. No further calls will be made on this Bot (except {@link #getName()}).*/
	void invalid();
}
