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
		Worker w = new Worker(queue);
		
		qm.start();
		w.start();
		
		while (!queue.isEmpty()) {
			System.out.println(queue.size());
		}
		
		w.doStop();
		
	}

}