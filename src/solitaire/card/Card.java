package solitaire.card;

public class Card {
	
	private static Card[][] cards = new Card[Suit.values().length][Rank.values().length];
	private Rank rank;
	private Suit suit;
	
	public enum Rank{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
	
	public enum Suit{
		CLUBS, DIAMONDS, SPADES, HEARTS;
	}
	
	public Card( Rank rank,  Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	public static Card getCard(Rank rank, Suit suit) {
		if(cards[suit.ordinal()][rank.ordinal()] == null) {
			cards[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
		}
		
		return cards[suit.ordinal()][rank.ordinal()];
	}
	
	public static Card flyWeightFactory(Rank rank, Suit suit) {
		if(cards[suit.ordinal()][rank.ordinal()]== null) {
			cards[suit.ordinal()][rank.ordinal()] = new Card(rank,suit);
		}
		return cards[suit.ordinal()][rank.ordinal()];
	}
	
	public String getIDString() {
		return Integer.toString(getSuit().ordinal() * Rank.values().length + getRank().ordinal());
	}
	
	public static Card get(String pID) {
		assert pID != null;
		int id = Integer.parseInt(pID);
		return flyWeightFactory(Rank.values()[id % Rank.values().length],Suit.values()[id/Rank.values().length]);
	}
	
	@Override
	public String toString() {
		return  rank + "of" + suit;
	}
	
	
}
