package org.chinacloud.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
	private static Logger logger = LoggerFactory.getLogger(Consumer.class);
	Store store;
	private boolean active;
	private Thread thread;
	private ExecutorService executorService;
	private int threshold = 10;

	public void start() {
		active = true;
		thread.setDaemon(true);
		thread.start();
		executorService = Executors.newFixedThreadPool(10);
	}

	public void stop() {
		active = false;
		thread = null;
		executorService.shutdown();
	}

	public void run() {
		while (active) {
			for (int i = 0; i < threshold; i++) {
				doConsume();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				logger.info(ex.getMessage(), ex);
			}
		}
	}

	public void doConsume() {
		try {
			TaskProduct taskProduct = store.get();
			executorService.submit(taskProduct);
		} catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
	}
}
