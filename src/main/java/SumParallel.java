package main.java;


/* Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
 * Make an array of 200 million random numbers between 1 and 10. 
 * Compute the sum in parallel using multiple threads. 
 * Then compute the sum with only one thread, and display the sum and times for both cases.
 * */


public class SumParallel implements Runnable {

	int index = 0;
//	int arraySize = 200000000;
	
	long sum = 0;
	long oldSum = 0;
	boolean limit = false;
	protected double[] randomArray = Main.randomArray;
	
	@Override
	public void run() {
		
		while(index != (Main.arraySize - 1)) {
			if(index == (Main.arraySize - 1)) {
				break;
			}
			sumAtIndex(randomArray);
			Thread.yield();
		}
		
		System.out.println("Sum of values is: " + sum);
	}
	
	
	public synchronized void sumAtIndex(double[] array) {
		oldSum = sum;
		sum += array[index];
		incrementIndex();
		System.out.println("Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index - 1] + " + " + oldSum + " = "+ sum);
	}
	
	
	public synchronized void incrementIndex() {
		index++;
	}

}
