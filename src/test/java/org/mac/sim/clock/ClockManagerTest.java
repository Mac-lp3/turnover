package org.mac.sim.clock;

import org.junit.Assert;
import org.junit.Test;
import org.mac.sim.clock.Clock;
import org.mac.sim.clock.ClockBuilder;
import org.mac.sim.clock.ClockManager;

public class ClockManagerTest {

	/**
	 * Ensure clock maintains current and max periods when the thread is stopped
	 * and resumes.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void pauseTest() throws InterruptedException {

		Clock clock = ClockBuilder.build(5000);
		ClockManager clockManager = new ClockManager(clock);

		clockManager.start();
		Thread.sleep(1000);
		clockManager.doStop();

		int tempPeriodCount = clock.getCurrentPeriod();
		Assert.assertTrue(tempPeriodCount > 0);

		// Cannot start a single thread more than once
		clockManager = new ClockManager(clock);

		clockManager.start();
		Thread.sleep(100);
		clockManager.doStop();

		// Even though it ran for 1/10 the time, should be larger.
		Assert.assertTrue(clock.getCurrentPeriod() > tempPeriodCount);
	}

	/**
	 * Ensure that the proper number of periods have passed.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void accuracyTest() throws InterruptedException {

		Clock clock = ClockBuilder.build(100);
		ClockManager clockManager = new ClockManager(clock);

		clockManager.run();

		Assert.assertEquals(clock.getCurrentPeriod(), 100);
		Assert.assertEquals(clock.getNextPeriod(), 101);
	}

}
