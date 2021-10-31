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
				// create new card with corresponding rank and suit
				Card newCard = new Card(rankArray[i], suitArray[j]);
				// store this card in the (unsorted) deck
				cardsInDeck.add(newCard);
			}
		}
		// shuffles the deck
		Collections.shuffle(cardsInDeck);
	}

	public Card drawCard() {
		// draws and returns the top card on the deck
		Card nextCard = this.cardsInDeck.get(0);
		return nextCard;
	}

	public void removeCard() {
		// removes the top card on the deck
		this.cardsInDeck.remove(0);
	}
}
