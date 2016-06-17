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

	/**
	 * Assert that the time intervals are large enough to hit the specified
	 * number of periods. This may have to change depending on CPU value.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void workersTest() throws InterruptedException {

		BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<WorkerTask>(500);
		QueueManager qm = new QueueManager(queue, 5, 100);
		Worker w1 = new Worker(queue, 3, 3);
		Worker w2 = new Worker(queue, 3, 3);

		qm.start();
		w1.start();
		w2.start();

		Thread.sleep(10000);

		w1.doStop();
		w2.doStop();

		Assert.assertEquals(500, qm.getAddActions());
		Assert.assertEquals(500, w1.getTasksCompleted() + w2.getTasksCompleted());
		Assert.assertEquals(100, qm.getPeriodsSpentInLoop());

	}

}
