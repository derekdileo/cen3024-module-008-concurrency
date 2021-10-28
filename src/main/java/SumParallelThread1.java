package main.java;

public class SumParallelThread1 extends Thread {
	
	private String threadName;
	private long threadSum;
	private long threadTime;
	private int indexStart;
	private int indexFinish;
	
	public SumParallelThread1(String name, int indexStart, int indexFinish) {
		this.setThreadName(name);
		this.threadSum = 0;
		this.threadTime = 0;
		this.indexStart = indexStart;
		this.indexFinish = indexFinish;
	}
	
	private static int index = 0;
	//private static long oldSum = 0;
	//private static long sum = 0;
	public static boolean endOfArray = false;
	public static boolean firstThreadFinished = false;
	protected double[] randomArray = Main.randomArray;
	private long startTime, endTime, runTime;
	
	@Override
	public void run() {
		
		startTime = System.nanoTime();
		
		endOfArray = sumAtIndexLoop(randomArray);
		
		if(endOfArray) {
			endTime = System.nanoTime();
			runTime = endTime - startTime;
			if(!firstThreadFinished) {
//				System.out.println(this.threadName + "'s sum of values is: " + threadSum + 
//						".\nRuntime of thread " + this.threadName + " is " + runTime + " nanoSeconds.\n");
				firstThreadFinished = true;
				setThreadTime(runTime);
				setThreadSum(threadSum);
				// Create object to "return" to Main after thread dies
				Main.spts1.setThreadName(threadName);
				Main.spts1.setThreadSum(threadSum);
				Main.spts1.setThreadTime(threadTime);
			}
		}
			
	}
	public synchronized boolean sumAtIndexLoop(double[] array) {
		
		while(index >= indexStart && index < indexFinish) {
			//oldSum = sum;
			threadSum += array[index];
			//System.out.println("Parallel Thread ID: " + Thread.currentThread().getId() + " \tSum at " + index + " is:  " + array[index] + " + " + oldSum + " = "+ sum);
			index++;
		}
		setThreadSum(threadSum);
		return true;
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
	
	public long getThreadSum() {
		return threadSum;
	}
	
	public void setThreadSum(long threadSum) {
		this.threadSum = threadSum;
	}
	
	public long getThreadTime() {
		return threadTime;
	}
	
	public void setThreadTime(long threadTime) {
		this.threadTime = threadTime;
	}
	
	public int getIndexStart() {
		return indexStart;
	}
	
	public void setIndexStart(int indexStart) {
		this.indexStart = indexStart;
	}
	
	public int getIndexFinish() {
		return indexFinish;
	}
	
	public void setIndexFinish(int indexFinish) {
		this.indexFinish = indexFinish;
	}
	
//	public synchronized boolean sumAtIndex(double[] array) {
//	if(index == (Main.arraySize - 1)) {
//		endOfArray = true;
//		return endOfArray;
//	}
//	sum += array[index];
//	index++;
//	//incrementIndex();
//	return false;
//}

}
