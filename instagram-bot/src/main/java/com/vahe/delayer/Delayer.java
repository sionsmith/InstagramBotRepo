package com.vahe.delayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Delayer {
	private static final int ONE_HOUR = 3_600_000;

	private final InsagramAction insagramAction;
	private final int maxActionPerHour;
	private final int delay;

	public Delayer(InsagramAction insagramAction, int maxActionPerHour,int delay) {
		this.insagramAction = insagramAction;
		this.maxActionPerHour = maxActionPerHour;
		this.delay = delay;
	}


	private Calendar startTime;
	private long currentCount = 0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Delayer.class);
	
	public void start(){
		startTime = Calendar.getInstance();
		while (true) {
			try {
                checkForTime();
				insagramAction.action();
				currentCount++;
				TimeUnit.SECONDS.sleep(delay);
			} catch (Exception e) {
				LOGGER.error("General Exception Handler {}", e);
			}
		}
	}
	
	
	private void checkForTime() {
		Calendar currentTime = Calendar.getInstance();
		long delta = currentTime.getTimeInMillis() - startTime.getTimeInMillis();
		if (delta < ONE_HOUR && currentCount >= maxActionPerHour) {
			try {
				long w = (ONE_HOUR - delta + 10_000) / (1000 * 60);
				LOGGER.info("Wainting  " + w + " min .............");
				TimeUnit.MILLISECONDS.sleep(ONE_HOUR - delta + 10_000);
			} catch (InterruptedException e) {
				LOGGER.error("Exception in checkForTime() {}", e);
			}
		}
		if (delta > ONE_HOUR) {
			startTime = Calendar.getInstance();
			currentCount = 0;
		}

	}
	
}
