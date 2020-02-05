package rocammo;

import java.util.ArrayList;

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
	
	public void add(int index) {
		if (index >= MAX_SLICES) {
			System.err.println("Error: remove: index: " + index + ", higher than MAX_SLICES: " + this.MAX_SLICES);
			return;
		}
		
		this.solution.add(index);
		this.score = calculateScore();
	}
	
	public void remove(int index) {
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
}

public class Main {
	public static void main(String args[]) {
		int arr[] = {1, 2, 3, 4, 5};
		
		/* SETUP */
		Pizza pizza = new Pizza(100, 5);
		pizza.fill(arr);
		
		/* WORKINGON */
		pizza.add(0);
		System.out.println(pizza.getScore());
		pizza.add(5);
		System.out.println(pizza.getScore());
		pizza.add(4);
		System.out.println(pizza.getScore());
		pizza.remove(7);
	}
}
