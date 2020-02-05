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
//		int[] i1 = readFile(PATH_1);
//		int[] i2 = readFile(PATH_2);
//		int[] i3 = readFile(PATH_3);
		int[] i4 = readFile(PATH_4);
//		int[] i5 = readFile(PATH_5);
		
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
		
		SumSetIndex.sum_up(index,(total- scoreToReach));
		
		
		return index;

	}
	
	public static void sol4() {
		int[] sol4 = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 188, 218, 335};
		
		
	}
	
}
