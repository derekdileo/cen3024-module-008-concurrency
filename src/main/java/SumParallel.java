package main.java;


/* Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
 * Make an array of 200 million random numbers between 1 and 10. 
 * Compute the sum in parallel using multiple threads. 
 * Then compute the sum with only one thread, and display the sum and times for both cases.
 * */


public class SumParallel implements Runnable {

	int index = 0;
	long sum = 0;
	long oldSum = 0;
	public boolean endOfArray = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		while(index != (Main.arraySize - 1)) {
//			if(index == (Main.arraySize - 1)) {
//				break;
//			}
			sumAtIndex(randomArray);
//			Thread.yield();
		}
		endOfArray = true;
		endTime = System.nanoTime();
		runTime = endTime - startTime;
		
		System.out.println("Sum of values is: " + sum);
		System.out.println("Runtime of parallel sum is: " + runTime + " nanoSeconds");
	}
	
	
	public synchronized void sumAtIndex(double[] array) {
		if(index == (Main.arraySize - 1)) {
			System.exit(0);
		}
		oldSum = sum;
		sum += array[index];
		//System.out.println("Parallel Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index] + " + " + oldSum + " = "+ sum);
		index++;
		//incrementIndex();
	}
	
	
	public synchronized void incrementIndex() {
		if(index == (Main.arraySize - 1)) {
			System.exit(0);
		}
		index++;
	}

}
