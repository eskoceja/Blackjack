//is the game being played!
public class Game {
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private int wins, losses, pushes;

    public Game() {
//        wins = 0;
//        losses = 0;
//        pushes = 0;

        deck = new Deck(true); //creates deck with 52 cards
        discarded = new Deck(); //creates empty deck

        dealer = new Dealer(); //new dealer
        player = new Player(); //new player- YOU!

        deck.shuffle(); //shuffle deck
        startRound(); //begin the game!
    }

    //game round: display score, give cards, check for blackjack, ask player to makeDecision(), and display $$$
    private void startRound() {

        //displaying score
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... \n" +
                    "Wins: " + wins + "\n" +
                    "Losses: " + losses + "\n" +
                    "Pushes: " + pushes);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        //checking that deck has at least 4 cards left
        if(deck.cardsLeft() < 4){
            deck.reloadDeckFromDiscard(discarded);
        }

        player.placeBet(); // player places bet

        //dealer gets 2 cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        //player gets 2 cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //display dealer and players hand
        dealer.printFirstHand();
        player.printHand();

        //check if dealer has blackjack to begin with
        if (dealer.hasBlackjack()) {
            dealer.printHand();

            //check if player also has blackjack
            if (player.hasBlackjack()) {
                System.out.println("You both have 21 - Push.");
                pushes++;
                player.setMoney(player.getMoney() + player.getBet()); //return amt to player
                startRound();
            } else {
                System.out.println("Dealer has Blackjack. You lose.");
                dealer.printHand();
                losses++;
                player.setMoney(player.getMoney() - player.getBet()); //deduct from player balance
                startRound();
            }

        }

        //check if player has blackjack to begin with
        if (player.hasBlackjack()) {
            System.out.println("You have Blackjack! You win!");
            wins++;
            player.setMoney(player.getMoney() + (player.getBet() * 2)); //double and add money to balance
            startRound();
        }

        //player decides to hit or stand
        player.makeDecision(deck, discarded);

        //check if busted
        if (player.getHand().calculatedValue() > 21) {
            System.out.println("You have gone over 21.");
            losses++;
            player.setMoney(player.getMoney() - player.getBet()); //deduct money from player
            startRound();
        }

        //dealers turn
        dealer.printHand();

        while (dealer.getHand().calculatedValue() < 17) {
            dealer.hit(deck, discarded);
        }

        //check who wins
        if (dealer.getHand().calculatedValue() > 21) {
            System.out.println("Dealer busts");
            wins++;
            player.setMoney(player.getMoney() + (player.getBet() * 2)); //add money to balance
        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            System.out.println("You lose.");
            losses++;
            player.setMoney(player.getMoney() - player.getBet()); //take money from balance
        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            System.out.println("You win!");
            wins++;
            player.setMoney(player.getMoney() + (player.getBet() * 2)); //add money to balance
        } else {
            System.out.println("Push.");
            player.setMoney(player.getMoney() + player.getBet()); //return money to player
        }

        System.out.println("Your current balance is: $" + player.getMoney()); //display balance
        startRound(); //begin new round

    }

}
