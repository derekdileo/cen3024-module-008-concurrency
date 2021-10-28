package main.java;

public class Main {

	/* Implement a parallel array sum, and compare performance with single thread sum. 
	 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
	 * Make an array of 200 million random numbers between 1 and 10. 
	 * Compute the sum in parallel using multiple threads. 
	 * Then compute the sum with only one thread, and display the sum and times for both cases.
	 * */
	protected static int arraySize = 200000000;
	protected static int arraySizeQuarter = arraySize / 4;
	protected static int arraySizeHalf = arraySize / 2;
	protected static int arraySizeThreeQuarter = arraySizeQuarter + arraySizeHalf;
	protected static double[] randomArray = null;
	private static long startTime, endTime, runTime;
	private static long singleSum = 0;

	private static SumParallelThread1 spt1;
	private static SumParallelThread2 spt2;
	private static SumParallelThread3 spt3;
	private static SumParallelThread4 spt4;
	
	
	protected static SumParallelThreads spts1;
	protected static SumParallelThreads spts2;
	protected static SumParallelThreads spts3;
	protected static SumParallelThreads spts4;

	private static SumSingle sumSingle;
	private static Thread t0;
//	private static Thread t1;
//	private static Thread t2;
//	private static Thread t3;
//	private static Thread t4;
//	private static Thread t5;
//	private static Thread t6;
//	private static Thread t7;
//	private static Thread t8;
	
	
	// Single (Thread) with SumParallelThreads
	public static void main(String[] args) {
		
		if(randomArray == null) {
			randomArray = generateArray();
		}

		
		// Get current time
		startTime = System.nanoTime();
		
		// Count using single thread
		for (int i = 0; i < arraySize; i++) {
			singleSum += randomArray[i];
		}
		
		// Calculate total runTime of single thread sum & print results
		endTime = System.nanoTime();
		runTime = endTime - startTime;
		
		System.out.println("Result of singleSum = " + singleSum + 
				"\nRuntime for singleSum: " + runTime + " nanoSeconds.");
		
		
		
		
		spts1 = new SumParallelThreads();
		spts2 = new SumParallelThreads();
		spts3 = new SumParallelThreads();
		spts4 = new SumParallelThreads();
		
		// Console separation
		System.out.println("...");
		System.out.println("...");
		
		//while(spt1.isAlive() && spt2.isAlive()) {}
		sumParallelThreads();
		
		while(spt1.isAlive() && spt2.isAlive() && spt3.isAlive() && spt4.isAlive()) {}
		
		// Console separation
		System.out.println("...");
		System.out.println("...");
		
		sumAllParallelThreads();
		
	}
	
	private static void sumAllParallelThreads() {
		
		long t1 = spts1.getThreadTime();
		long t2 = spts2.getThreadTime();
		long t3 = spts3.getThreadTime();
		long t4 = spts4.getThreadTime();
		
		System.out.println("Thread times: " + t1 + ", " + t2 + ", " + t3 + ", " + t4);
		
		long sum1 = spts1.getThreadSum();
		long sum2 = spts1.getThreadSum();
		long sum3 = spts1.getThreadSum();
		long sum4 = spts1.getThreadSum();

		System.out.println("Thread sums: " + sum1 + ", " + sum2 + ", " + sum3 + ", " + sum4);
		
		long totalTime = t1 + t2 + t3 + t4;
		
		long totalSum = sum1 + sum2 + sum3 + sum4;
		
		System.out.println("Total sum of parallel threads is: " + totalSum + 
				".\nTotal runtime of parallel threads is: " + totalTime + " nanoSeconds.");
		
	}
	
	public static void sumParallelThreads() {
		spt1 = new SumParallelThread1("sumThread1", 0, arraySizeQuarter);
		spt2 = new SumParallelThread2("sumThread2", arraySizeQuarter, arraySizeHalf);
		spt3 = new SumParallelThread3("sumThread3", arraySizeHalf, arraySizeThreeQuarter);
		spt4 = new SumParallelThread4("sumThread4", arraySizeThreeQuarter, arraySize);
		//spt3 = new SumParallelThread("sumThread3");
		//spt4 = new SumParallelThread("sumThread4");
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
	
//	public static void sumParallel() {
//		// Instantiate SumParallel object
//		sumParallel = new SumParallel();
//		
//		// Create new Threads of Runnable SumParallel
//		t1 = new Thread(sumParallel);
//		t2 = new Thread(sumParallel);
//		t3 = new Thread(sumParallel);
//		t4 = new Thread(sumParallel);
//		t5 = new Thread(sumParallel);
//		t6 = new Thread(sumParallel);
//		t7 = new Thread(sumParallel);
//		t8 = new Thread(sumParallel);
//		
//		// Spin up Threads
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();
//	}
	
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
