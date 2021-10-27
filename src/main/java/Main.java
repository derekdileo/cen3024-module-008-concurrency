package main.java;

public class Main {

	/* Implement a parallel array sum, and compare performance with single thread sum. 
	 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
	 * Make an array of 200 million random numbers between 1 and 10. 
	 * Compute the sum in parallel using multiple threads. 
	 * Then compute the sum with only one thread, and display the sum and times for both cases.
	 * */
	protected static int arraySize = 200000;
	protected static double[] randomArray = null;
	
	
	
	public static void main(String[] args) {
		
		if(randomArray == null) {
			randomArray = generateArray();
		}

		SumParallel sum = new SumParallel();
		
		Thread t1 = new Thread(sum);
		Thread t2 = new Thread(sum);
		Thread t3 = new Thread(sum);
		Thread t4 = new Thread(sum);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
		
//		try { 
//			// Java Thread join method can be used to pause the current 
//			// thread execution until & unless the specified thread is dead
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		// You can just create a thread directly inside the main method without creating another Class
//		Thread t = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				
//				
//			}
//			
//			
//		});
//		
//		t.start();
	}
	
	public static double[] generateArray() {
		// Create array to hold randomly generated numbers
		randomArray = new double[arraySize];
		
		for(int i = 0; i < arraySize; i++) {
			// Generate random number (between 1 & 10) 
			double value = Math.random() * 10;
			
			// Make sure it is not less than 1
			while(value < 1) {
				value = Math.random() * 10;
			}
			
			// Add to array at index i
			randomArray[i] = value;
		}
		return randomArray;
	}
	

}
