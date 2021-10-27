package main.java;

public class Main {

	/* Implement a parallel array sum, and compare performance with single thread sum. 
	 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
	 * Make an array of 200 million random numbers between 1 and 10. 
	 * Compute the sum in parallel using multiple threads. 
	 * Then compute the sum with only one thread, and display the sum and times for both cases.
	 * */
	protected static int arraySize = 200000000;
	protected static double[] randomArray = null;
	private static SumParallel sumParallel;
	private static SumParallelThread spt1;
	private static SumParallelThread spt2;
	private static SumParallelThread spt3;
	private static SumParallelThread spt4;
	private static SumParallelThread spt5;
	private static SumParallelThread spt6;
	private static SumParallelThread spt7;
	private static SumParallelThread spt8;
	private static SumSingle sumSingle;
	private static Thread t0;
	private static Thread t1;
	private static Thread t2;
	private static Thread t3;
	private static Thread t4;
	private static Thread t5;
	private static Thread t6;
	private static Thread t7;
	private static Thread t8;
	
	
	public static void main(String[] args) {
		
		if(randomArray == null) {
			randomArray = generateArray();
		}

		sumParallelThread();
		
		while(spt1.isAlive() && spt2.isAlive() && spt3.isAlive() && spt4.isAlive()) {}
		//while(spt1.isAlive() && spt2.isAlive()) {}
		
		sumSingle();
		
	}
	
	
	public static void sumParallelThread() {
		spt1 = new SumParallelThread("sumThread1");
		spt2 = new SumParallelThread("sumThread2");
		spt3 = new SumParallelThread("sumThread3");
		spt4 = new SumParallelThread("sumThread4");
//		spt5 = new SumParallelThread("Five");
//		spt6 = new SumParallelThread("Six");
//		spt7 = new SumParallelThread("Seven");
//		spt8 = new SumParallelThread("Eight");
		
		spt1.start();
		spt2.start();
		spt3.start();
		spt4.start();
//		spt5.start();
//		spt6.start();
//		spt7.start();
//		spt8.start();
		
	}
	
	public static void sumParallel() {
		// Instantiate SumParallel object
		sumParallel = new SumParallel();
		
		// Create new Threads of Runnable SumParallel
		t1 = new Thread(sumParallel);
		t2 = new Thread(sumParallel);
		t3 = new Thread(sumParallel);
		t4 = new Thread(sumParallel);
		t5 = new Thread(sumParallel);
		t6 = new Thread(sumParallel);
		t7 = new Thread(sumParallel);
		t8 = new Thread(sumParallel);
		
		// Spin up Threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}
	
	public static void sumSingle() {
		// Instantiate SumSingle object
		sumSingle = new SumSingle("sumSingle");
		
		// Create a single Thread of Runnable SumSingle
		t0 = new Thread(sumSingle);
		
		// Spin up Thread
		t0.start();
	}
	
	public static double[] generateArray() {
		// Create array to hold randomly generated numbers
		randomArray = new double[arraySize];
		
		for(int i = 0; i < arraySize; i++) {
			// Generate random number (between 1 & 10) 
			double value = Math.random() * 10;
			
			// Make sure it is not less than 1
			while(value < 1) {
				value = Math.random() * 10;
			}
			
			// Add to array at index i
			randomArray[i] = value;
		}
		return randomArray;
	}
	

}
