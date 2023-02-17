package solitaire.card;

public class Card {
	
	private static Card[][] cards = new Card[Suit.values().length][Rank.values().length];
	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
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

	@Override
	public String toString() {
		return suit + "" + rank;
	}
	
	
}
