package main.java;

public class SumParallelThread extends Thread {
	
	private String threadName;
	
	public SumParallelThread(String name) {
		this.setThreadName(name);
	}
	
	private int index = 0;
	protected long sum = 0;
	long oldSum = 0;
	public boolean endOfArray = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		while(!endOfArray) {
			if(index == (Main.arraySize - 1)) {
				break;
			}
			sumAtIndex(randomArray);
			Thread.yield();
		}
		endOfArray = true;
		endTime = System.nanoTime();
		runTime = endTime - startTime;
		
		System.out.println("Thread " + this.threadName + "'s sum of values is: " + sum);
		System.out.println("Runtime of parallel sum for thread " + this.threadName + " is: " + runTime + " nanoSeconds");
	}
	
	
	public synchronized boolean sumAtIndex(double[] array) {
		if(index == (Main.arraySize - 1)) {
			endOfArray = true;
			System.exit(0);
			return endOfArray;
		}
		oldSum = sum;
		sum += array[index];
		//System.out.println("Parallel Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index] + " + " + oldSum + " = "+ sum);
		//index++;
		incrementIndex();
		return false;
	}
	
	
	public synchronized void incrementIndex() {
		if(index == (Main.arraySize - 1)) {
			endOfArray = true;
			System.exit(0);
		}
		index++;
	}


	public String getThreadName() {
		return threadName;
	}


	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
