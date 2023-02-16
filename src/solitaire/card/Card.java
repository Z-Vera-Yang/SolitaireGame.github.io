package solitaire.card;

public class Card {
private static Card[][] cards = new Card[SUIT.values().length][Rank.values().length];
private Rank rank;
private SUIT suit;

public Card(Rank rank, SUIT suit) {
	this.rank = rank;
	this.suit = suit;
}

public Rank getRank() {
	return rank;
}

public SUIT getSuit() {
	return suit;
}
public static Card getCard(Rank rank, SUIT suit) {
	
	if(cards[suit.ordinal()][rank.ordinal()] == null) {
		cards[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
	}
		return cards[suit.ordinal()][rank.ordinal()];
}
}
