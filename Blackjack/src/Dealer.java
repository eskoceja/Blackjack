//is dealer
public class Dealer extends Person{
    public Dealer() {
        super.setName("Dealer");
    }
    public void printFirstHand() {
        System.out.println("The dealer's hand looks like this: \n" +
                super.getHand().getCard(0) + " --- The second card is face down.");
    }

}
