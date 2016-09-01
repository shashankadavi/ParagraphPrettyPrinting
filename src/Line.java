public class Line {
	// current cost
	int cost;
	// length left to fill into a line
	int left;

	/**
	 * constructor
	 */

	public Line() {
		left = Printing.lengthOfLine;
	}

	/**
	 * if adding a word into the line goes out of range, make the cost infinite,
	 * else update the cost
	 */
	public boolean add(String a) {
		if (a.length() + 1 > left && left != Printing.lengthOfLine) {
			cost = Printing.infinite;
			left = Printing.lengthOfLine;
			return false;
		} else {
			if (left == Printing.lengthOfLine) {
				left++;
			}
			left = left - a.length() - 1;
			cost = left * left;
			return true;
		}
	}

	/**
	 * this method prints the current state of collection of the words (used for
	 * debugging)
	 */
	public String toString() {
		return "" + left;
	}
}
