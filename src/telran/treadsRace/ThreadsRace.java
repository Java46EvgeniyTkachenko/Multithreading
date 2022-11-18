package telran.treadsRace;

public class ThreadsRace extends Thread {

	private static final int MAX_TIMEOUT = 5;
	private static final int MIN_TIMEOUT = 2;
	int threadId;
	int distance;
	int timeOut;

	public ThreadsRace(int threadId, int distance) {
		super();
		this.threadId = threadId;
		this.distance = distance;
	}

	@Override
	public void run() {

		for (int i = 0; i < distance; i++) {
			timeOut = (int) ((Math.random() * (MAX_TIMEOUT - MIN_TIMEOUT)) + MIN_TIMEOUT);
			try {
				sleep(timeOut);
				System.out.println(i+"  "+threadId);

			} catch (InterruptedException e) {

			}
		}
		
	}
	

}
