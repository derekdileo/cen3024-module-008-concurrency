package main.java;

public class SumParallelThreads {
	
	private String threadName;
	private long threadSum;
	private long threadTime;
	
	public SumParallelThreads(String threadName, long threadSum, long threadTime) {
		super();
		this.threadName = threadName;
		this.threadSum = threadSum;
		this.threadTime = threadTime;
	}
	
	public SumParallelThreads() {
		super();
		this.threadName = "";
		this.threadSum = 0;
		this.threadTime = 0;
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
	
}
