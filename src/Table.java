import java.util.Arrays;

public class Table {
	// keeps track, updates, prints what is currently on the game table
	private Card[][] table = new Card[4][5];
	private Card[] positions = new Card[20];
	private boolean[] positionEmpty = new boolean[20];

	Table() {
		// constructs a new table at the beginning of the game with all positions empty
		for (int i = 0; i < positionEmpty.length; i++) {
			positionEmpty[i] = true;
		}

//		int index = 0;
//		// assigns the memory slots in the "table" grid array to whatever will be/is
//		// stored in positions array (1-16)
//		for (int i = 0; i <= 1; i++) {
//			for (int j = 0; j < table[i].length; j++) {
//				table[i][j] = positions[index];
//				index++;
//			}
//		}
//		for (int i = 2; i <= 3; i++) {
//			for (int j = 1; j < table[i].length - 1; j++) {
//				table[i][j] = positions[index];
//				index++;
//			}
//		}

	}

	public void printTable() {

		// these are just to test what happens when one of the grid values is filled
		// with a sample card
//		Card testCard = new Card("4", 'S');
//		positions[3] = testCard;
//		positionEmpty[3] = false;

		int index = 0;

		// a "bookkeeping" variable just to set the correct spacing between slots
		int slotLength;
		// prints out the current state of cards/positions on the table
		// will print the number of the slot if it's empty
		// if slot has a card, will print that card
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (positionEmpty[index] == true) {
					// if position is empty, print out its index
					System.out.print(index + 1);
					// a "bookkeeping" variable just to set the correct spacing between slots
					slotLength = 1 + ((index + 1) / 10);
				} else {
					// if position has a card, print out the card name
					String cardName = positions[index].getName();
					System.out.print(cardName);
					// a "bookkeeping" variable just to set the correct spacing between slots
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
		for (int i = 2; i <= 3; i++) {
			System.out.print("     "); // prints space between positions
			for (int j = 1; j < table[i].length - 1; j++) {
				if (positionEmpty[index] == true) {
					// if position is empty, print out its index
					System.out.print(index + 1);
					// a "bookkeeping" variable just to set the correct spacing between slots
					slotLength = 1 + ((index + 1) / 10);
				} else {
					// if position has a card, print out the card name
					String cardName = positions[index].getName();
					System.out.print(cardName);
					// a "bookkeeping" variable just to set the correct spacing between slots
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
		// checks if the slot chosen by user (to put a card in) is empty
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
				// as soon as the method finds one empty slot, it returns false
				return false;
			}
		}
		// returns true (all positions ARE full) if no empty position is found
		return true;
	}

	public int scoreGame() {
		// score table that will just store the scores for each slot
		int[][] scoreTable = new int[4][5];
		// array of scores for each of the 9 hands
		int[][] handPoints = new int[9][5];

//		// First, let's set scores for all hands as zero
//		for (int i = 0; i < handScores.length; i++) {
//			handScores[i][????] = 0;
//		}

		int index = 0; // index of "slots"
		// loop to make a table of scores
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < table[i].length; j++) {
				// gets the score of the card positioned at a specific index
				int cardScore = positions[index].getScore();
				// store the score in the score-table
				scoreTable[i][j] = cardScore;
//				// update scores for corresponding two hands
				handPoints[i][j] = cardScore; // horizontal
				handPoints[4 + j][i] = cardScore; // vertical
				index++;
			}
		}
		for (int i = 2; i <= 3; i++) {
			for (int j = 1; j < table[i].length - 1; j++) {
				// gets the score of the card posit1ioned at a specific index
				int cardScore = positions[index].getScore();
				// store the score in the score-table
				scoreTable[i][j] = cardScore;
				// update scores for corresponding two hands
				handPoints[i][j] = cardScore; // horizontal
				handPoints[4 + j][i] = cardScore; // vertical
				index++;
			}
		}
		// hard-code scores for "empty slots" below the arms of "T"
//		System.out.println("Slot 2,0: " + scoreTable[2][0]);

		int[] handSums = new int[9]; // stores sums of card points for each hand
		int[] handScores = new int[9]; // stores final scores of each hand
		// a loop to calculate individual hand sums and scores
		for (int i = 0; i < handPoints.length; i++) {
			Arrays.sort(handPoints[i]); // sort the array to have aces (11s) in the end
			for (int j = 0; j < handPoints[i].length; j++) {
				if (handPoints[i][j] == 11) {
					// checking which value of ace should be used
					if (21 - handSums[i] > (11 + 4 - j)) {
						handSums[i] += 11;
					} else {
						handSums[i] += 1;
					}
				} else {
					handSums[i] += handPoints[i][j];
				}

				System.out.print(handPoints[i][j] + " "); // TEST

			}
			// calculating actual hand scores from their sums/indices
			if (handSums[i] > 21) {
				handScores[i] = 0;
			} else if (handSums[i] == 21) {
				if (i == 4 || i == 8) { // hands 4 and 8 are the ones with 2 cards
					handScores[i] = 10;
				} else {
					handScores[i] = 7;
				}
			} else if (handSums[i] <= 20 && handSums[i] >= 17) {
				handScores[i] = handSums[i] - 15;
			} else {
				handScores[i] = 1;
			}

			System.out.println(" = " + handSums[i] + " -> " + handScores[i]); // TEST
		}

		// calculate the overall total score
		int totalScore = 0;
		for (int i = 0; i < handScores.length; i++) {
			totalScore += handScores[i];
		}

		// Voila!
		return totalScore;
	}
}
