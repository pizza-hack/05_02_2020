package training_pizza;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hllo world");
		//long t0 = System.currentTimeMillis();
		Solver s1 = new Solver("in/a_example.txt","1",100);
		s1.start();
		Solver s2 = new Solver("in/b_lovely_landscapes.txt","2",1);
		s2.start();
		Solver s3 = new Solver("in/c_memorable_moments.txt","3",1);
		s3.start();

		//System.out.println(System.currentTimeMillis() - t0);
	}
}
