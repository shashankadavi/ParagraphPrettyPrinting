import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Printing {
	// input words
	String[] words;
	// cost from i to j words
	Line[][] lineCost;
	// max length
	public static int lengthOfLine;
	// number of words
	int noOfWords;
	// infinity
	static int infinite;
	// line break at indices
	int[] lineBreaks;
	// final cost from 1 to jth word
	int[] finalCost;

	/**
	 * constructor
	 */
	public Printing(String[] w, int n) {
		lineCost = new Line[n][n];
		this.words = w;
		this.noOfWords = n;
		lineBreaks = new int[n];
		finalCost = new int[n + 1];

		init();
		start();
		finish();
		// print the result
		result(lineBreaks, noOfWords - 1);
	}

	/**
	 * this method initializes all the lines having cost infinite
	 */
	public void init() {
		for (int i = 0; i < noOfWords; i++) {
			finalCost[i + 1] = infinite;
			for (int j = 0; j < noOfWords; j++) {
				// no word has been filled, length left = max
				lineCost[i][j] = new Line();
			}
		}
	}

	/**
	 * this method fills the array lineCost, lineCost[i][j] represents the cost
	 * of fitting all words from i to j into a line
	 */
	public void start() {
		for (int i = 0; i < noOfWords; i++) {
			for (int j = i; j < noOfWords; j++) {

				for (int k = i; k <= j; k++) {
					// if adding word does not make it out of range, keep adding
					// to this line
					if (!lineCost[i][j].add(words[k])) {
						break;
					}
					;
				}
			}
		}

	}

	/**
	 * this method calculates the cost after each word and line break
	 */

	public void finish() {

		for (int j = 0; j < noOfWords; j++) {
			for (int i = 0; i <= j; i++) {
				// if the previous cost is not infinite, fit the word
				if (finalCost[i] != infinite) {
					// if line cost is not currently infinite
					if (lineCost[i][j].cost != infinite) {
						// if it minimizes our line cost
						if (finalCost[i] + lineCost[i][j].cost < finalCost[j + 1]) {
							// keep track of the line
							lineBreaks[j] = i;
							// update the cost
							finalCost[j + 1] = finalCost[i];
							finalCost[j + 1] = finalCost[j + 1]
									+ lineCost[i][j].cost;

						}
					}
				}
			}
		}
	}

	/**
	 * this method prints the words and their line break
	 */
	public void result(int[] print, int n) {
		// until the line number is not zero, recurse
		if (print[n] != 0) {
			result(print, print[n] - 1);
		}
		// print all the words in that line
		for (int i = print[n]; i <= n; i++) {
			System.out.print(words[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		lengthOfLine = sc.nextInt();
		infinite = lengthOfLine * lengthOfLine * lengthOfLine;
		int n = sc.nextInt();
		String[] w = new String[n];

		for (int i = 0; i < n; i++) {
			w[i] = sc.next();
		}
		// Initializing the object
		Printing p = new Printing(w, n);

		sc.close();
		// We used the following code to generate the DP table
		/*
		 * File f=new File("output.csv"); // FileOutputStream o=new
		 * FileOutputStream(f); PrintWriter out=new PrintWriter(f);
		 * 
		 * out.println();
		 * 
		 * 
		 * 
		 * out.println("Matrix of word");
		 * 
		 * for(int i=0;i<n;i++){ for(int j=0;j<n;j++){
		 * out.print(p.lineCost[i][j]+","); } out.println(); } out.println();
		 * out.println(); out.println("cost");
		 * 
		 * for(int i=0;i<n;i++){ out.print(p.finalCost[i]+",");
		 * 
		 * } out.println(); out.println();
		 * 
		 * out.println("line breaks");
		 * 
		 * for(int i=0;i<n;i++){ out.print(p.lineBreaks[i]+",");
		 * 
		 * } out.println(); out.println(); out.close();
		 */
	}

}
