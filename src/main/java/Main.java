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
	
	private static Thread t1;
	private static Thread t2;
	
	// Variables for single-thread execution
	private static long startTime, endTime, runTime;
	protected static double sum;
	
	
	public static void main(String[] args) {
		
		// Create array with 200M random numbers between 1 & 10
		if(randomArray == null) {
			randomArray = generateArray();
		}
		
		// Use main Thread to perform array sum
		System.out.println(ThreadColor.ANSI_RESET + "Calculating single thread sum...final array value is: " + randomArray[arraySize-1]);
		
		// Grab current time
		startTime = System.nanoTime();
		
		// Iterate through randomArray and sum all values
		int index = 0;
		for(int i = 0; i <= arraySize - 1; i++) {
			index++;
			sum += randomArray[i];
		}

		// Calculate total execution time & print results
		endTime = System.nanoTime();
		runTime = endTime - startTime;
		System.out.println(ThreadColor.ANSI_CYAN + "\nSingle thread at index " + index + " sum is: " + sum + 
				".\nSingle thread runtime is: " + runTime + " nanoSeconds.");

		// Console separation
		System.out.println(ThreadColor.ANSI_PURPLE + "...");
		System.out.println("...");
		System.out.println("Calculating multi-thread sum...");
		System.out.println("...");
		System.out.println("...");
		
		// Create two Threads to execute the array sum concurrently
		sumParallel();
		
		
		// Wait until t2 & t2 die...
		while (t1.isAlive() && t2.isAlive()) {}
		
		// Calculate difference between two
		double difference = sum - SumParallel.finalSharedSum;
		double tempDifference = sum - SumParallel.finalSharedTempSum;
		
		System.out.println(ThreadColor.ANSI_RED + "\nDifference between single thread and double thread calculations is: " + difference);
		System.out.println(ThreadColor.ANSI_GREEN + "\n\nDifference between single thread sum and double thread tempSum is: " + tempDifference);
		
	}
	
	/* Create and start two Threads with same object reference */
	public static void sumParallel() {
		// Instantiate SumParallel object
		sumParallel = new SumParallel();
		
    	// Create new Threads of Runnable SumParallel
		t1 = new Thread(sumParallel);
		t1.setName("Thread-1");
		t2 = new Thread(sumParallel);
		t2.setName("Thread-2");
		
		// Spin up Threads
		t1.start();
		t2.start();
	
	}
	
	
	/* Method to generate an array of random numbers */
	public static double[] generateArray() {
		// Create array to hold randomly generated numbers
		randomArray = new double[arraySize];
		
		for(int i = 0; i < arraySize; i++) {
			// Generate random number (between 1 & 10) 
			double value = Math.random() * 10;
			
			// Make sure value is not less than 1
			while(value < 1) {
				value = Math.random() * 10;
			}
			
			// Add to array at index i
			randomArray[i] = value;
		}
		return randomArray;
	}

}
