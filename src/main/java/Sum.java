package main.java;

public class Sum implements Runnable {

	
	
	
	@Override
	public void run() {

		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getId() + " Value : " + i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	
	public synchronized void sumSynchro(int[] array) {
		
		
		
		
	}
	
	
	public void sumNotSynchro(int[] array) {
		
		
		
		
	}
	
	
	
}
