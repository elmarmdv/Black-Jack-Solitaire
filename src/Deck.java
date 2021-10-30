import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cardsInDeck;

	Deck() {

		cardsInDeck = new ArrayList<Card>();
		String[] rankArray = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		char[] suitArray = { 'C', 'D', 'H', 'S' };

		for (int i = 0; i < rankArray.length; i++) { // first loop - ranks
			for (int j = 0; j < suitArray.length; j++) { // second (nested) loop - suits

				// create new card with corresponding rank (rankArray[i]) and suit
				// (suitArray[j])
				Card newCard = new Card(rankArray[i], suitArray[j]);

				// store this card in the (unsorted) deck
				cardsInDeck.add(newCard);

//				System.out.printf("Card %d-%d: %s%c%n", i, j, rankArray[i], suitArray[j]); // TEST 1 - print loop
				// indices and cards.
			}
		}

		// SHUFFLES THE DECK!
		Collections.shuffle(cardsInDeck);

		// TEST FOR SHUFFLE
		for (Card elem : cardsInDeck) { // alternative of the first one, for enhanced loop
			System.out.println(elem.getName());
		}
	}

	public Card drawCard() {
		// draws and returns a new card
		Card nextCard = this.cardsInDeck.get(0);
		return nextCard;
	}

	public void removeCard() {
		this.cardsInDeck.remove(0);
	}

}
