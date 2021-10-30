
public class Card {
	private String rank;
	private char suit;
	private int score;

	Card(String r, char s) {
		this.rank = r;
		this.suit = s;

		if (r.equals("J") || r.equals("Q") || r.equals("K")) {
			this.score = 10;
		} else if (r.equals("A")) {
			this.score = 11; // we will re-evaluate Ace's score in the end when scoring
		} else {
			this.score = Integer.valueOf(r);
		}
	}

	public String getName() {
		// concatenates rank and suit to return a string containing the overall "name"
		// of the card
		String cardName = this.rank + this.suit;
		return cardName;
	}

	public int getScore() {
		return this.score;
	}
}
