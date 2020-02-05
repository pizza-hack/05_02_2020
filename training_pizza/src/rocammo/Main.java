package rocammo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
			for (int i = MAX_SLICES - decrease, j = 0; sum <= diff && i >= 0; i--, j++) {
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
	public static final String PATH_1 = "in/a_example.in";
	public static final String PATH_2 = "in/b_small.in";
	public static final String PATH_3 = "in/c_medium.in";
	public static final String PATH_4 = "in/d_quite_big.in";
	public static final String PATH_5 = "in/e_also_big.in";

	public static void main(String args[]) throws IOException {
		Pizza p1 = readFile(PATH_1);
		Pizza p2 = readFile(PATH_2);
		Pizza p3 = readFile(PATH_3);
		Pizza p4 = readFile(PATH_4);
		Pizza p5 = readFile(PATH_5);

		p3.solve();
		System.out.println("score: " + p3.getScore());

		System.out.println(p3); // TODO VOLCAR A FICHERO
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
}
