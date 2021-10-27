package main.java;

public class SumParallelThread extends Thread {
	
	private String threadName;
	
	public SumParallelThread(String name) {
		this.setThreadName(name);
	}
	
	private static int index = 0;
	//private static long oldSum = 0;
	private static long sum = 0;
	public static boolean endOfArray = false;
	public static boolean firstThreadFinished = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		endOfArray = sumAtIndexLoop(randomArray);
		
		if(index == (Main.arraySize - 1) && endOfArray) {
			endTime = System.nanoTime();
			runTime = endTime - startTime;
			if(!firstThreadFinished) {
				System.out.println(this.threadName + "'s sum of values is: " + sum + 
						".\nRuntime of thread " + this.threadName + " is " + runTime + " nanoSeconds.\n");
				firstThreadFinished = true;
			}
		}
			
	}
	public boolean sumAtIndexLoop(double[] array) {
		
		while(index != (Main.arraySize - 1)) {
			//oldSum = sum;
			sum += array[index];
			//System.out.println("Parallel Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index] + " + " + oldSum + " = "+ sum);
			index++;
		}
		return true;
	}
	
	
//	public synchronized boolean sumAtIndex(double[] array) {
//		if(index == (Main.arraySize - 1)) {
//			endOfArray = true;
//			return endOfArray;
//		}
//		sum += array[index];
//		index++;
//		//incrementIndex();
//		return false;
//	}
	
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
