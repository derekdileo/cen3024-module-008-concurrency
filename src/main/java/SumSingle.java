package main.java;

public class SumSingle implements Runnable {
	
	private String threadName;
	
	public SumSingle(String name) {
		this.threadName = name;
	}

	private static int index = 0;
	private static long sum = 0;
	private static boolean endOfArray = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		endOfArray = sumAtIndexLoop(randomArray);
		
		if(index == (Main.arraySize - 1) && endOfArray) {
			endTime = System.nanoTime();
			runTime = endTime - startTime;
			System.out.println(this.threadName + "'s sum of values is: " + sum + 
					".\nRuntime of " + this.threadName + " is " + runTime + " nanoSeconds.\n");
			
		}
	}

	public synchronized boolean sumAtIndexLoop(double[] array) {
		
		while(index != (Main.arraySize - 1)) {
			sum += array[index];
			index++;
		}
		return true;
		
	}
	
	public synchronized long sumAtIndex(double[] array) {
		if(index == (Main.arraySize - 1)) {
			System.exit(0);
		}
		sum += array[index];
		index++;
		return sum;
	}
	
}
