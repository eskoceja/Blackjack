import java.util.ArrayList;

//A hand of cards to play with
public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public Card getCard(int idx) {
        return hand.get(idx);
    }

    //takes single card from top of the deck and adds it to hand, removing it form prev deck
    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.takeCard());
    }

    public int calculatedValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card: hand) { //for each hand in this card
            value += card.getValue(); //add card value to hand
            if (card.getValue() == 11) { //count how many aces have been added
                aceCount++;
            }
        }
        //scenario with multiple aces; go back and set each ace to 1 or until get back under 21, if possible
        if (value > 21 && aceCount > 0) {
            while (aceCount > 0 && value > 21) {
                aceCount--;
                value -= 10;
            }
        }
        return value;
    }

    public String toString() {
        String output = "";
        for (Card card : hand) {
            output += card + " - ";
        }
        return output;
    }

    //add a single card to hand
    public void discardHandToDeck(Deck discardDeck) {
        discardDeck.addCards(hand); //copy cards from discardDeck
        hand.clear(); //clear the hand
    }

}
