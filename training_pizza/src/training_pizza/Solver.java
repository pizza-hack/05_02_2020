package training_pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Solver extends Thread {
	
	String text;
	String name;
	String path;
	long startTime;
	int times;
	
	public Solver(String path,String name,int times) {
		this.name = name;
		this.path = path;
		this.times = times;
		this.startTime = System.currentTimeMillis();
	}
	
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		System.out.println("Empiezo!: "+this.startTime);
		for(int i=0;i<this.times;i++) {
			readFile(path);
			writeToFile(i+".txt");
		}
		long timeSpent = System.currentTimeMillis() - this.startTime;
		System.out.println(this.name+" - "+timeSpent+" - "+timeSpent/(float)this.times);
	}
	private void writeToFile(String string) {
		
		try {
			PrintStream fw = new PrintStream(new File("out/"+name+"/"+string));
			
			fw.print(this.text);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void readFile(String path) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			
			while((line = br.readLine()) != null) {
				text += line;	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
