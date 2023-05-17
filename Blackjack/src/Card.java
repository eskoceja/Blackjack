import java.util.Random;

//create cards with a suit and a rank
//contains methods needed to get value
//contains method to Sys-out
public class Card {
    private Suit suit;
    private Rank rank;

    //constructor
    public Card(Card card) {
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    //suit and rank to be created
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //return suit of card
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.rankValue;
    }

    public String toString() {
        return ("[" + rank + " of " + suit + "] (" + this.getValue() + ")");
    }
}
