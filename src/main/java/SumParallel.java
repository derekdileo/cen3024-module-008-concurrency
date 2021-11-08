package main.java;

/* Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
 * Make an array of 200 million random numbers between 1 and 10. 
 * Compute the sum in parallel using multiple threads. 
 * Then compute the sum with only one thread, and display the sum and times for both cases.
 * 
 * As a general rule, do NOT use local variables to synchronize!
 * 
 * */

public class SumParallel implements Runnable {

	private static int index = 0;
	private static double tempSum = 0;
	private double sum;
	protected static double finalSharedSum;
	protected static double finalSharedTempSum;
	private boolean endOfArray = false;
	private boolean firstThreadFinished = false;
	private double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	public SumParallel() {
		this.setSum(0);
	}

	private synchronized void setSum(double sum) {
		if((index <= (Main.arraySize)) && !firstThreadFinished) {
			if(sum != Main.sum) {
				this.sum = sum;				
			}
			return;
		}
	}
	
	private double getSum() {
		return this.sum;
	}


	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		while(index != (Main.arraySize - 1)) {

			endOfArray = sumAtIndex(randomArray);
			
			if(endOfArray) {

				// Kill remaining threads if one has finished
				if(firstThreadFinished) {
					endOfArray = false;
					setSum(tempSum);
					System.out.println(ThreadColor.ANSI_RED + "\n" + Thread.currentThread().getName() + ", Thread ID:  " + Thread.currentThread().getId() + " is late to the party." + 
					"\nTemp sum here is: " + tempSum);
					endOfArray = true;
					finalSharedSum = getSum();
					finalSharedTempSum = tempSum;
					return;
				}
				
				if(!firstThreadFinished) {
					firstThreadFinished = true;
					endTime = System.nanoTime();
					runTime = endTime - startTime;
					setSum(tempSum);
					
					System.out.println(ThreadColor.ANSI_GREEN + "\nSum of values up to index: " + index + " for Thread ID: " + Thread.currentThread().getId() + " is: " + getSum() +
					"\nRuntime of parallel sum for Thread ID: " + Thread.currentThread().getId() + " is: " + runTime + " nanoSeconds and has finished executing.");
					return;
				}
			}
		}
	}
	
	/* Synchronized method used to add value at index to static sum.*/
	private synchronized boolean sumAtIndex(double[] array) {

		if(!endOfArray && index < (Main.arraySize - 1)) {
			tempSum = getSum() + array[index];
			setSum(tempSum);
			index++;
		}

		if(index == (Main.arraySize - 1)) {
			tempSum = getSum() + array[index];
			setSum(tempSum);
			index++;
			return true;
		}
		
		if(index >= Main.arraySize) {
			return true;
		}
		
		return false;
	}

}
