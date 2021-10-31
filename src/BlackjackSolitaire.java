import java.util.Scanner;

public class BlackjackSolitaire {
	private Deck gameDeck;
	private Table gameTable;

	public void play() {
		// creates and shuffles the deck
		gameDeck = new Deck();
		// creates a new game table with empty slots
		gameTable = new Table();
		// initiates a new card draw/play
		newPlay();
	}

	public void newPlay() {

		// prints current state of the game table
		gameTable.printTable();

		// initiate a scanner for user input
		Scanner scnr = new Scanner(System.in);
		// draw a new card from the deck
		Card cardDrawn = gameDeck.drawCard();

		System.out.println("Card drawn: " + cardDrawn.getName());
		System.out.println("Select a slot for the card (1-20): ");
		int slotChosen = scnr.nextInt(); // store user input of a chosen slot in a variable

		// check if the position entered by user is valid
		if (slotChosen < 1 || slotChosen > 20) {
			System.out.println("Invalid slot! Please choose slot within 1-20!!!");
			// prompt input again
			newPlay();
			// check if the position entered by user is empty
		} else if (gameTable.isSlotEmpty(slotChosen)) {
			// set Card to new position
			gameTable.updateSlot(slotChosen, cardDrawn);
			// remove card from deck
			gameDeck.removeCard();

			// check if all 16 scoring slots are filled: if yes, initiate scoring; if not,
			// deal another card.
			if (gameTable.allPositionsFilled()) {
				int finalScore = gameTable.scoreGame();
				System.out.println("Game over! You scored " + finalScore + " points.");
			} else {
				newPlay();
			}
		} else {
			// print "Slot already full! Choose different slot!"
			System.out.println("Slot " + slotChosen + " is already full!!! Choose a different slot!");
			// prompt input again
			newPlay();
		}
		scnr.close();
	}
}
