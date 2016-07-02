package org.mac.sim.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.mac.sim.clock.Clock;
import org.mac.sim.clock.ClockBuilder;
import org.mac.sim.clock.ClockManager;

import junit.framework.Assert;

public class QueueManagerTest {

	/**
	 * Test that the proper number of tasks have been added to the queue.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void runTest() throws InterruptedException {

		BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<WorkerTask>(25);

		Clock fivePeriodClock = ClockBuilder.build(5);
		ClockManager fiveClockManager = new ClockManager(fivePeriodClock);
		WorkQueueManager qm = new WorkQueueManager(queue, 5, fivePeriodClock);
		qm.start();
		fiveClockManager.start();

		fiveClockManager.join();

		Assert.assertEquals(25, queue.size());

		queue = new ArrayBlockingQueue<WorkerTask>(50);

		Clock tenPeriodClock = ClockBuilder.build(10);
		ClockManager tenClockManager = new ClockManager(tenPeriodClock);
		WorkQueueManager qm2 = new WorkQueueManager(queue, 5, tenPeriodClock);
		qm2.start();
		tenClockManager.start();

		tenClockManager.join();

		Assert.assertEquals(50, queue.size());

		queue = new ArrayBlockingQueue<WorkerTask>(500);
		Clock hundredPeriodClock = ClockBuilder.build(100);
		ClockManager hundredClockManager = new ClockManager(hundredPeriodClock);
		qm = new WorkQueueManager(queue, 5, hundredPeriodClock);
		qm.start();
		hundredClockManager.start();

		hundredClockManager.join();

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
		Clock hundredClock = ClockBuilder.build(100);
		ClockManager cm = new ClockManager(hundredClock);
		WorkQueueManager qm = new WorkQueueManager(queue, 5, hundredClock);
		ThreadWorker w1 = new ThreadWorker(queue, 3, 3, hundredClock);
		ThreadWorker w2 = new ThreadWorker(queue, 3, 3, hundredClock);

		qm.start();
		w1.start();
		w2.start();
		cm.start();

		cm.join();

		cm.doStop();
		w1.doStop();
		w2.doStop();

		Assert.assertEquals(500, qm.getAddActions());
		Assert.assertEquals(500, w1.getTasksCompleted() + w2.getTasksCompleted());
		Assert.assertEquals(100, qm.getPeriodsSpentInLoop());

	}

	/**
	 * This test should prove that the application is able to adjust the sleep
	 * intervals appropriately given an excessive amount of workers, task
	 * length, buffer time, or a combination of all three.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void overLoadedWorkersTest() throws InterruptedException {

		BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<WorkerTask>(1000);
		Clock hundredClock = ClockBuilder.build(100);
		ClockManager cm = new ClockManager(hundredClock);
		WorkQueueManager qm = new WorkQueueManager(queue, 10, hundredClock);
		ThreadWorker w1 = new ThreadWorker(queue, 10, 5, hundredClock);
		ThreadWorker w2 = new ThreadWorker(queue, 5, 5, hundredClock);
		ThreadWorker w3 = new ThreadWorker(queue, 10, 5, hundredClock);
		ThreadWorker w4 = new ThreadWorker(queue, 5, 5, hundredClock);
		ThreadWorker w5 = new ThreadWorker(queue, 10, 5, hundredClock);
		ThreadWorker w6 = new ThreadWorker(queue, 5, 5, hundredClock);

		qm.start();
		w1.start();
		w2.start();
		w3.start();
		w4.start();
		w5.start();
		w6.start();
		cm.start();

		cm.join();

		cm.doStop();
		w1.doStop();
		w2.doStop();
		w3.doStop();
		w4.doStop();
		w5.doStop();
		w6.doStop();

		Assert.assertEquals(1000, qm.getAddActions());
		int totalTasksCompleted = w1.getTasksCompleted() + w2.getTasksCompleted() + w3.getTasksCompleted()
				+ w4.getTasksCompleted() + w5.getTasksCompleted() + w6.getTasksCompleted();
		Assert.assertEquals(1000, totalTasksCompleted);
		Assert.assertEquals(100, qm.getPeriodsSpentInLoop());

		// Assert the slower workers actually performed worse
		Assert.assertTrue(w1.getTasksCompleted() < w2.getTasksCompleted());
		Assert.assertTrue(w1.getTasksCompleted() < w4.getTasksCompleted());
		Assert.assertTrue(w1.getTasksCompleted() < w6.getTasksCompleted());
		Assert.assertTrue(w3.getTasksCompleted() < w2.getTasksCompleted());
		Assert.assertTrue(w3.getTasksCompleted() < w4.getTasksCompleted());
		Assert.assertTrue(w3.getTasksCompleted() < w6.getTasksCompleted());
		Assert.assertTrue(w5.getTasksCompleted() < w2.getTasksCompleted());
		Assert.assertTrue(w5.getTasksCompleted() < w2.getTasksCompleted());
		Assert.assertTrue(w5.getTasksCompleted() < w2.getTasksCompleted());

	}

}
