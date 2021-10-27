package main.java;

public class SumSingle implements Runnable {

	int index = 0;
	long sum = 0;
	long oldSum = 0;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		while(index != (Main.arraySize - 1)) {
			if(index == (Main.arraySize - 1)) {
				break;
			}
			sumAtIndex(randomArray);
			Thread.yield();
		}
		
		endTime = System.nanoTime();
		runTime = endTime - startTime;
		System.out.println("Sum of values is: " + sum);
		System.out.println("Runtime of single sum is: " + runTime + " nanoSeconds");
	}

	public synchronized long sumAtIndex(double[] array) {
		if(index == (Main.arraySize - 1)) {
			System.exit(0);
		}
		oldSum = sum;
		sum += array[index];
		//System.out.println("Single Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index] + " + " + oldSum + " = "+ sum);

		index++;
		//		incrementIndex();
		return sum;
	}
	
	public synchronized void incrementIndex() {
		if(index == (Main.arraySize - 1)) {
			System.exit(0);
		}
		index++;
	}
	
	
}
