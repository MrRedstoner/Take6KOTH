package sk.mrredstoner.domain;

public class CardDeck {
	/**first element null to make number==index*/
	private final Card[]cards=new Card[105];
	
	public CardDeck(){
		for(int i=1;i<=104;i++){
			cards[i]=new Card(i);
		}
	}
	
	public Card getCard(int number){
		if(number<1 || number>104){
			throw new IllegalArgumentException();
		}
		
		return cards[number];
	}
}
