package sk.mrredstoner.domain;

public interface BotFactory {
	/**Returns the name of the bot this factory makes.
	 * @return name of the associated bot*/
	String getBotName();
	
	/**Produces a new instance of the associated Bot
	 * @return a new Bot*/
	Bot getNewInstance();
}
