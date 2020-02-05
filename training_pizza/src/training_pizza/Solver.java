package training_pizza;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Solver {
	String text;

	public Solver(String path) {
		for (int i = 0; i < 100; i++) {
			readFile(path);
			writeToFile(i + ".txt");
		}
	}

	private void writeToFile(String string) {
		try {
			PrintStream fw = new PrintStream("out/" + string);
			fw.print(this.text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void readFile(String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				text += line;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
