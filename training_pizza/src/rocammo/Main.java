package rocammo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

class Pizza {
	private int MAX_SCORE;
	private int MAX_SLICES;

	private int[] slices;

	private ArrayList<Integer> solution;
	private int score;

	public Pizza(int MAX_SCORE, int MAX_SLICES) {
		this.MAX_SCORE = MAX_SCORE;
		this.MAX_SLICES = MAX_SLICES;

		this.slices = new int[MAX_SLICES];

		this.solution = new ArrayList<Integer>();
		this.score = 0;
	}

	public void fill(int[] arr) {
		for (int i = 0; i < MAX_SLICES; i++) {
			this.slices[i] = arr[i];
		}
	}

	public void solve() {
		int max_achieved = calculateMaxScore(), diff = MAX_SCORE - max_achieved;
		if (diff < 0)
			diff = diff * -1;
		System.out.println("max_score: " + MAX_SCORE + ", max_achieved: " + max_achieved + ", diff: " + diff);

		int sum = 0;
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		boolean condition = false;
		int decrease = 1;
		while (!condition) {
			for (int i = MAX_SLICES - decrease; sum <= diff && i >= 0; i--) {
				if (sum + this.slices[i] <= diff) {
					indexes.add(i);
					sum += this.slices[i];
				} else {
					continue;
				}
			}
			if (sum == diff) {
				for (int i = 0; i < MAX_SLICES; i++) {
					add(i);
				}
				for (int i = 0; i < indexes.size(); i++) {
					remove(indexes.get(i));
				}
				condition = true;
			}

			indexes.clear();
			sum = 0;
			decrease++;
		}
	}

	private void add(int index) {
		if (index >= MAX_SLICES) {
			System.err.println("Error: add: index: " + index + ", higher than MAX_SLICES: " + this.MAX_SLICES);
			return;
		}

		if (this.solution.contains(index)) {
			System.err.println("Error: add: index: " + index + ", is already taken");
			return;
		}

		this.solution.add(index);
		this.score = calculateScore();
	}

	private void remove(int index) {
		if (index >= MAX_SLICES) {
			System.err.println("Error: remove: index: " + index + ", higher than MAX_SLICES: " + this.MAX_SLICES);
			return;
		}

		this.solution.remove(index);
		this.score = calculateScore();
	}

	private int calculateScore() {
		int i, _score;
		for (i = 0, _score = 0; i < this.solution.size(); i++) {
			_score += this.slices[this.solution.get(i)];
		}
		return _score;
	}

	public int getScore() {
		return score;
	}

	private int calculateMaxScore() {
		int i, _max_achieved;
		for (i = 0, _max_achieved = 0; i < this.MAX_SLICES; i++) {
			_max_achieved += this.slices[i];
		}
		return _max_achieved;
	}

	@Override
	public String toString() {
		String indexes = this.solution.size() + "\n";

		Iterator<Integer> itr = this.solution.iterator();
		while (itr.hasNext()) {
			indexes += itr.next() + " ";
		}

		return indexes;
	}
}

public class Main {
	public static final String IN_1 = "in/a_example.in";
	public static final String IN_2 = "in/b_small.in";
	public static final String IN_3 = "in/c_medium.in";
	public static final String IN_4 = "in/d_quite_big.in";
	public static final String IN_5 = "in/e_also_big.in";
	
	public static final String OUT_1 = "out/a_example.out";
	public static final String OUT_2 = "out/b_small.out";
	public static final String OUT_3 = "out/c_medium.out";
	public static final String OUT_4 = "out/d_quite_big.out";
	public static final String OUT_5 = "out/e_also_big.out";

	public static void main(String args[]) throws IOException {
		Pizza p1 = readFile(IN_1);
		Pizza p2 = readFile(IN_2);
		Pizza p3 = readFile(IN_3);
		Pizza p4 = readFile(IN_4);
		Pizza p5 = readFile(IN_5);

		p1.solve();
		p2.solve();
		p3.solve();
		p4.solve();
		p5.solve();
		
		writeFile(OUT_1, p1);
		writeFile(OUT_2, p2);
		writeFile(OUT_3, p3);
		writeFile(OUT_4, p4);
		writeFile(OUT_5, p5);
	}

	public static Pizza readFile(String path) throws IOException {
		int[] index;
		ArrayList<Integer> list = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(path));

		String line = br.readLine();
		String[] header = line.split(" ");
		int scoreToReach = Integer.parseInt(header[0]);
		int arraySize = Integer.parseInt(header[1]);

		Pizza pizza = new Pizza(scoreToReach, arraySize);

		index = new int[arraySize];
		line = br.readLine();
		String[] sizes = line.split(" ");
		for (int i = 0; i < sizes.length; i++) {
			index[i] = Integer.parseInt(sizes[i]);
			list.add(index[i]);
		}

		pizza.fill(index);

		br.close();

		return pizza;
	}
	
	public static void writeFile(String path, Pizza pizza) throws FileNotFoundException {
		PrintStream fw = new PrintStream(new File(path));
		fw.println(pizza);
		fw.close();
	}
}
