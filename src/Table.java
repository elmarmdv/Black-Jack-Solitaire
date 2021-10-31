import java.util.Arrays;

public class Table {
	// Keeps track of, updates, prints what is currently on the game table

	private Card[] positions = new Card[20]; // array that will store cards in corresponding positions
	private boolean[] positionEmpty = new boolean[20]; // array that stores whether each slot is empty

	Table() {
		// constructor for a new game table with all slots empty
		for (int i = 0; i < positionEmpty.length; i++) {
			positionEmpty[i] = true;
		}
	}

	public void printTable() {
		// prints the current state of the game table

		int index = 0; // to keep track of positions
		int slotLength;// a "bookkeeping" variable to set the correct spacing between slots

		for (int i = 0; i <= 1; i++) { // for the first two rows
			for (int j = 0; j < 5; j++) {
				if (positionEmpty[index]) {
					// if position is empty, print out its index
					System.out.print(index + 1);
					// a "bookkeeping" variable to set the correct spacing between slots
					slotLength = 1 + ((index + 1) / 10);
				} else {
					// if position is occupied, print out the card name
					String cardName = positions[index].getName();
					System.out.print(cardName);
					// a "bookkeeping" variable to set the correct spacing between slots
					slotLength = cardName.length();
				}
				index++;

				// a "bookkeeping" loop to make sure spacing is consistent despite different
				// slot (character) lengths
				for (int k = 0; k < 5 - slotLength; k++) {
					System.out.print(" ");
				}
			}
			System.out.println(); // prints a new line after each row is complete
		}
		for (int i = 2; i <= 3; i++) { // for the last two rows
			System.out.print("     "); // prints space before the first position on row
			for (int j = 1; j < 4; j++) {
				if (positionEmpty[index]) {
					// if position is empty, print out its index
					System.out.print(index + 1);
					// a "bookkeeping" variable to set the correct spacing between slots
					slotLength = 1 + ((index + 1) / 10);
				} else {
					// if position is occupied, print out the card name
					String cardName = positions[index].getName();
					System.out.print(cardName);
					// a "bookkeeping" variable to set the correct spacing between slots
					slotLength = cardName.length();
				}
				index++;
				// a "bookkeeping" loop to make sure spacing is consistent despite different
				// slot (character) lengths
				for (int k = 0; k < 5 - slotLength; k++) {
					System.out.print(" ");
				}
			}
			System.out.println(); // prints a new line after each row is complete
		}
	}

	public boolean isSlotEmpty(int s) {
		// checks if the slot chosen by user is empty
		return positionEmpty[s - 1];
	}

	public void updateSlot(int s, Card c) {
		// updates the position chosen by user with a new card
		positions[s - 1] = c;
		positionEmpty[s - 1] = false;
	}

	public boolean allPositionsFilled() {
		// checks if all 16 "scored" positions are full and returns a boolean
		for (int i = 0; i < 16; i++) {
			if (positionEmpty[i]) {
				// as soon as the method finds one empty slot, return false
				return false;
			}
		}
		// returns true (all positions ARE full) if no empty position is found
		return true;
	}

	public int scoreGame() {
		// scores the game when all 16 positions are full

		// array of scores for each of the 9 hands
		int[][] handPoints = new int[9][5];

		int index = 0; // index of "slots"

		// loop to make a table of scores
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < 5; j++) {
				// gets the score of the card positioned at the index
				int cardScore = positions[index].getScore();
				// update scores for corresponding two hands
				handPoints[i][j] = cardScore; // horizontal
				handPoints[4 + j][i] = cardScore; // vertical
				index++;
			}
		}
		for (int i = 2; i <= 3; i++) {
			for (int j = 1; j < 4; j++) {
				// gets the score of the card posit1ioned at the index
				int cardScore = positions[index].getScore();

				// update scores for corresponding two hands
				handPoints[i][j] = cardScore; // horizontal
				handPoints[4 + j][i] = cardScore; // vertical
				index++;
			}
		}

		int[] handSums = new int[9]; // stores sums of card points (not scores) for each hand
		int[] handScores = new int[9]; // stores final scores of each hand

		// a loop to calculate individual hand sums and scores
		for (int i = 0; i < handPoints.length; i++) {
			// sort the array to have aces (11s) in the end
			Arrays.sort(handPoints[i]);
			for (int j = 0; j < handPoints[i].length; j++) {
				if (handPoints[i][j] == 11) { // if hand has aces
					// checking which value of ace should be used:
					// if the sum will be more than 21 considering ..
					// all leftover 11s as 1 (array is sorted!), then choose 1
					// otherwise choose 11
					if (21 - handSums[i] >= (11 + 4 - j)) {
						handSums[i] += 11;
					} else {
						handSums[i] += 1;
					}
				} else { // if not aces, then just add points
					handSums[i] += handPoints[i][j];
				}
//				System.out.print(handPoints[i][j] + " "); // TEST (prints sorted card points in each hand)
			}

			// calculating actual hand scores from their sums
			if (handSums[i] > 21) {
				handScores[i] = 0; // bust!
			} else if (handSums[i] == 21) {
				if (i == 4 || i == 8) { // hands 4 and 8 are the ones with 2 cards
					handScores[i] = 10; // blackjack!
				} else {
					handScores[i] = 7;
				}
			} else if (handSums[i] <= 20 && handSums[i] >= 17) {
				handScores[i] = handSums[i] - 15;
			} else {
				handScores[i] = 1;
			}
//			System.out.println("(Hand " + i + ") = " + handSums[i] + " -> " + handScores[i]); // TEST (prints total sum and score of each hand)
		}

		// calculate the overall total score
		int totalScore = 0;
		for (int i = 0; i < handScores.length; i++) {
			totalScore += handScores[i];
		}

		// return total score
		return totalScore;
	}
}
