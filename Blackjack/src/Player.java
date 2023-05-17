import java.util.Scanner;
//is player
public class Player extends Person{
    Scanner input = new Scanner(System.in);
    private int money;
    private int bet;

    //new player
    public Player() {
        super.setName("Player");
        this.input = new Scanner(System.in);
        this.money = 200;
        this.bet = 0;
    }

    //getters and setters for betting $$$
    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    //player makes decision to hit or stand
    public void makeDecision(Deck deck, Deck discard) {
        int decision = 0;
        boolean getNum = true;

        while(getNum) {
            try{
                System.out.println("Would you like to: \n" +
                        "1) Hit or \n" +
                        "2) Stand");
                decision = input.nextInt();
                getNum = false;
            } catch (Exception e){
                System.out.println("Invalid");
                input.next();
            }
        }
        System.out.println("You selected: " + decision);

        if(decision == 1) {
            this.hit(deck, discard);
            if(this.getHand().calculatedValue()>20) {
                return;
            } else {
                this.makeDecision(deck, discard);
            }
        } else {
            System.out.println("You stand.");
        }

    }

    //placing bet for a min of $10
    public void placeBet() {
        boolean validBet = false;
        while(!validBet) {
            try {
                System.out.println("Place your bet (Minimum bet $10)");
                int betAmount = input.nextInt();
                if(betAmount >= 10 && betAmount <= money){
                    this.bet = betAmount;
                    validBet = true;
                } else {
                    System.out.println("Invalid bet amount");
                }
            } catch (Exception e) {
                System.out.println("Invalid bet amount");
                input.next();
            }
        }
        System.out.println("You placed a bet of $" + bet);
    }

}
