package telran.treadsRace;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ThreadsRace extends Thread {

	private static final int MAX_TIMEOUT = 5;
	private static final int MIN_TIMEOUT = 2;
	int threadId;
	int distance;
	int timeOut;
	private static int num;
	private Instant timeStart;
	private static ArrayList<int[]> result = new ArrayList<int[]>(); 
	public ThreadsRace(int threadId, int distance, Instant timeStart) {
		super();
		this.threadId = threadId;
		this.distance = distance;
		this.timeStart = timeStart;
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
		
		writeResult(threadId, timeStart);
	}

	synchronized static private void writeResult(int numberThread, Instant timeS) {
		int[] e = { ++num, numberThread, (int) ChronoUnit.MILLIS.between(timeS, Instant.now()) };

		result.add(e);		

	}

	public static ArrayList<int[]> getResult() {
		return result;
	}

}
