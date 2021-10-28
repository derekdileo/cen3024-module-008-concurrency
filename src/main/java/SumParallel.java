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
	private double tempSum = 0;
	private double sum;
	private boolean endOfArray = false;
	private boolean firstThreadFinished = false;
	private double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	public SumParallel() {
		this.setSum(0);
	}

	private void setSum(double sum) {
		if(index != (Main.arraySize - 1)) {
			this.sum = sum;
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
					System.out.println(Thread.currentThread().getName() + ", Thread ID:  " + Thread.currentThread().getId() + "is late to the party." + 
					"\nAdding " + tempSum + " to final sum, which is currently " + getSum());
					setSum(tempSum);
					endOfArray = true;
					return;
				}
				
				if(!firstThreadFinished) {
					endTime = System.nanoTime();
					runTime = endTime - startTime;
					
					System.out.println(ThreadColor.ANSI_GREEN + "Sum of values for Thread ID: " + Thread.currentThread().getId() + " is: " + getSum());
					System.out.println(ThreadColor.ANSI_GREEN + "Runtime of parallel sum for Thread ID: " + Thread.currentThread().getId() + " is: " + runTime + " nanoSeconds");
					System.out.println("The thread " + Thread.currentThread().getName() + " has finished executing. " + Thread.currentThread().getId());
					firstThreadFinished = true;
					return;
				}
			}
		}
	}
	
	private boolean sumAtIndex(double[] array) {

		tempSum = getSum() + array[index];
		setSum(tempSum);

		if(!endOfArray && index != (Main.arraySize - 1)) {
			index++;
		}
		
		if(index == (Main.arraySize - 1)) {
			tempSum = getSum() + array[index];
			setSum(tempSum);
			return true;
		}
		
		return false;
	}
	
//	private synchronized boolean incrementIndex() {
//		index++;
//		if(index == (Main.arraySize - 1)) {
//			return true;
//		}
//		return false;
//	}

}
