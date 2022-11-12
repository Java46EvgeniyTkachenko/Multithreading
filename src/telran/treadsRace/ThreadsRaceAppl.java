package telran.treadsRace;

import telran.view.*;

public class ThreadsRaceAppl {
	private static final int DISTANCE_MIN = 100;
	private static final int DISTANCE_MAX = 3500;
	private static final int NUMBERS_MIN = 3;
	private static final int NUMBERS_MAX = 10;
	private static int numbersOfThreads;
	private static int distance;

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Menu menu = new Menu("Threads Race", getItems());
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item items[] = {

				Item.of("Numbers of threads", ThreadsRaceAppl::numbersOfThreads),
				Item.of("Distance", ThreadsRaceAppl::distance), Item.of("Start the Race", ThreadsRaceAppl::startRace),
				Item.exit() };
		return items;
	}

	static void numbersOfThreads(InputOutput io) {
		numbersOfThreads = io.readInt("enter numbers of threads  ", "no numbers");
		if (numbersOfThreads > NUMBERS_MAX || numbersOfThreads < NUMBERS_MIN) {
			io.writeLine("Please enter a threads numbers in the range [" + NUMBERS_MIN + "," + NUMBERS_MAX + "]");
			numbersOfThreads = 0;
		}
	}

	static void distance(InputOutput io) {
		distance = io.readInt("enter distance of range ", "no numbers");
		if (distance > DISTANCE_MAX || distance < DISTANCE_MIN) {
			io.writeLine("Please enter a distance in the range [" + DISTANCE_MIN + "," + DISTANCE_MAX + "]");
			distance = 0;
		}
	}

	static void startRace(InputOutput io) {
		if (numbersOfThreads == 0) {
			io.writeLine("Please enter a threads numbers in the range [" + NUMBERS_MIN + "," + NUMBERS_MAX + "]");
			return;
		}
		if (distance == 0) {
			io.writeLine("Please enter a distance in the range [" + DISTANCE_MIN + "," + DISTANCE_MAX + "]");
			return;
		}

		ThreadsRace myThreads[] = new ThreadsRace[numbersOfThreads];
		for (int j = 0; j < numbersOfThreads - 1; j++) {
			myThreads[j] = new ThreadsRace(j + 1, distance);
			myThreads[j].start();
		}

		boolean finishMoument = true;

		while (finishMoument) {

			for (int i = 0; i < myThreads.length - 1; i++) {
				if (!myThreads[i].isAlive()) {
					io.writeLine("Congratulations to thread #" + i);
					finishMoument = false;
					break;
				}

			}
		}

	}
}
