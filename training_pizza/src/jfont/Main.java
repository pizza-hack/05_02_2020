package jfont;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

	public static final String PATH_1 = "in/a_example.in";
	public static final String PATH_2 = "in/b_small.in";
	public static final String PATH_3 = "in/c_medium.in";
	public static final String PATH_4 = "in/d_quite_big.in";
	public static final String PATH_5 = "in/e_also_big.in";
	
	public static void main(String args[]) throws IOException {
		int[] i1 = readFile(PATH_1);
		int[] i2 = readFile(PATH_2);
		int[] i3 = readFile(PATH_3);
		int[] i4 = readFile(PATH_4);
		int[] i5 = readFile(PATH_5);
		
	}

	public static int[] readFile(String path) throws IOException {
		int[] index;
		ArrayList<Integer> list = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();

		String[] header = line.split(" ");
		int scoreToReach = Integer.parseInt(header[0]);
		int arraySize = Integer.parseInt(header[1]);

		index = new int[arraySize];

		line = br.readLine();

		String[] sizes = line.split(" ");

		for (int i = 0; i < sizes.length; i++) {
			index[i] = Integer.parseInt(sizes[i]);
			list.add(index[i]);
		}
		int total = IntStream.of(index).sum();
		System.out.println("Score to reach: "+scoreToReach);
		System.out.println("Suma todos: "+total);
		System.out.println("Diff: "+(total- scoreToReach));
		System.out.println(Arrays.toString(index));
		System.out.println();
		
		//SumSet.sum_up(list,(total- scoreToReach));
		
		
		return index;

	}
}
