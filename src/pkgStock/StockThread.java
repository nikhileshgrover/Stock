package pkgStock;

import java.util.logging.Level;

public class StockThread implements Runnable {
//	https://www.tutorialspoint.com/java/java_multithreading.htm
	private String stockThread;
	private Thread thread;
	private Utility logger;
	
	StockThread(String stockThreadName) {
		this.stockThread = stockThreadName;
		logger = new Utility();
	}
	
	public void run() {
		
	}
	
	public void startThread() {
		logger.log(Level.INFO, "Starting thread : " + this.stockThread);
		if(this.stockThread == null) {
			thread = new Thread(this, stockThread);
			thread.start();
		}
	}
}
