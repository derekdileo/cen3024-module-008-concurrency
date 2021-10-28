package main.java;

/* Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
 * Make an array of 200 million random numbers between 1 and 10. 
 * Compute the sum in parallel using multiple threads. 
 * Then compute the sum with only one thread, and display the sum and times for both cases.
 * */

public class SumSingle implements Runnable {

	private static int index = 0;
	private static double sum = 0;
	private static boolean endOfArray = false;
	private boolean firstThreadFinished = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		while(index != (Main.arraySize)) {
			
			endOfArray = sumAtIndexLoop(randomArray);

			if(endOfArray) {
				
				if(!firstThreadFinished) {
					endTime = System.nanoTime();
					runTime = endTime - startTime;
					
					System.out.println("sumSingle's sum of values is: " + sum + 
							".\nRuntime of sumSingle is: " + runTime + " nanoSeconds.\n");
					firstThreadFinished = true;
					break;
				}
			}
		}
	}

	
	private boolean sumAtIndexLoop(double[] array) {
		sum += array[index];
		
		if(index == (Main.arraySize - 1)) {
			return true;
		}

		index++;
		return false;
	}
	
}
