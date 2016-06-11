package org.mac.sim.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.mac.sim.domain.WorkerTask;

import junit.framework.Assert;

public class QueueManagerTest {

	/**
	 * Test that the proper number of tasks have been added to the queue.
	 */
	@Test
	public void runTest() {

		BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<WorkerTask>(25);
		QueueManager qm = new QueueManager(queue, 5, 5);
		qm.run();
		Assert.assertEquals(25, queue.size());

		queue = new ArrayBlockingQueue<WorkerTask>(50);
		qm = new QueueManager(queue, 5, 10);
		qm.run();
		Assert.assertEquals(50, queue.size());

		queue = new ArrayBlockingQueue<WorkerTask>(500);
		qm = new QueueManager(queue, 5, 100);
		qm.run();
		Assert.assertEquals(500, queue.size());
	}

	@Test
	public void workersTest() {

		BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<WorkerTask>(500);
		QueueManager qm = new QueueManager(queue, 5, 100);
		Worker w1 = new Worker(queue, 3, 3);
		Worker w2 = new Worker(queue, 3, 3);

		qm.start();
		w1.start();
		w2.start();

		// few extra nanos saved by declaring variable early (I think)
		long endTime;
		long startTime = System.nanoTime();
		while (!queue.isEmpty()) {
			System.out.println(queue.size());
		}
		endTime = System.nanoTime();

		w1.doStop();
		w2.doStop();

		System.out.println("Execution time ??? " + Math.floor((endTime - startTime)) / 1000000);
		System.out.println("Periods in loop: " + qm.getPeriodsSpentInLoop());
		System.out.println("~~~" + qm.getAddActions());
		System.out.println("~~~" + w1.getTasksCompleted());
		System.out.println("~~~" + w2.getTasksCompleted());

	}

}
