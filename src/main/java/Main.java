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

	private static SumSingle sumSingle;
	private static SumParallel sumParallel;
	
	private static Thread t0;
	private static Thread t1;
	private static Thread t2;
	private static Thread t3;
	private static Thread t4;
	
	// Variables for single-thread execution
	private static long startTime, endTime, runTime, sum;
	
	
	// Single (Thread) with SumParallelThreads
	public static void main(String[] args) {
		
		if(randomArray == null) {
			randomArray = generateArray();
		}

		// Use single Thread to perform array sum
		System.out.println("Calculating single thread sum...");
		startTime = System.nanoTime();
		
		for(int i = 0; i < arraySize; i++) {
			sum += randomArray[i];
		}

		endTime = System.nanoTime();
		runTime = endTime - startTime;
		
		System.out.println("Single thread sum is: " + sum + 
				".\nSingle thread runtime is: " + runTime + " nanoSeconds.\n");

		// Console separation
		System.out.println("...");
		System.out.println("...");
		
		System.out.println("Calculating multi-thread sum...");
		
		
		sumParallel();
		
		
//		// Get current time
//		startTime = System.nanoTime();
//		
//		// Count using single thread
//		for (int i = 0; i < arraySize; i++) {
//			singleSum += randomArray[i];
//		}
//		
//		// Calculate total runTime of single thread sum & print results
//		endTime = System.nanoTime();
//		runTime = endTime - startTime;
//		
//		System.out.println("Result of singleSum = " + singleSum + 
//				"\nRuntime for singleSum: " + runTime + " nanoSeconds.");
	}
	
	
	public static void sumParallel() {
		// Instantiate SumParallel object
		sumParallel = new SumParallel();
		
    	// Create new Threads of Runnable SumParallel
		t1 = new Thread(sumParallel);
		t2 = new Thread(sumParallel);
		t3 = new Thread(sumParallel);
		t4 = new Thread(sumParallel);
		
		// Spin up Threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	public static void sumSingle() {
		// Instantiate SumSingle object
		sumSingle = new SumSingle();
		
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
