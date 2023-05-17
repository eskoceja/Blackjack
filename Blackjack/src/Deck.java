import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//Deck of cards
public class Deck {
    private ArrayList<Card> deck; //Arraylist to hold deck of cards

    //empty deck of cards
    public Deck() {
        deck = new ArrayList<>();
    }

    //Create standard deck of cards
    public Deck(boolean makeDeck) {
        deck = new ArrayList<>();

        if(makeDeck) {
            //go through all suits
            for(Suit suit: Suit.values()){
                //go through all ranks
                for(Rank rank : Rank.values()) {
                    //add new card containing each iterations suit and rank
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }

    //arraylist containing all cards
    public ArrayList<Card> getCards() {
        return deck;
    }
    //Card being added to deck
    public void addCard(Card card) {
        deck.add(card);
    }
//    public void shuffle() {
//        ArrayList<Card> shuffled = new ArrayList<>();
//
//        while(deck.size() > 0) {
//            int cardToPull = (int)(Math.random()*(deck.size()-1));
//            shuffled.add(deck.get(cardToPull));
//            deck.remove(cardToPull);
//        }
//        deck = shuffled;
//    }
//    short way to shuffle
    public void shuffle() {
        Collections.shuffle(deck, new Random());
    }

    //card take from deck
    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0)); //take a copy of the first card from deck
        deck.remove(0); //remove card from deck
        return cardToTake; //give card back
    }

    //returns value to string
    public String toString() {
        String output = "";
        for (Card card : deck) {
            output += card;
            output += "\n";
        }
        return output;
    }

    //return true if deck still has cards left
    public boolean hasCards() {
        if(deck.size() > 0){
            return true;
        } else {
            return false;
        }
    }

    //empties out deck
    public void emptyDeck() {
        deck.clear();
    }

    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    //takes cards from discarded deck and places them in deck, shuffled; clears old deck
    public void reloadDeckFromDiscard(Deck discard) {
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile and shuffling deck.");
    }

    //return number of cards left in the deck
    public int cardsLeft() {
        return deck.size();
    }

}
