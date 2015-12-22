package org.chinacloud.mq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Store {
	private BlockingQueue<TaskProduct> store = new LinkedBlockingDeque<>();
	public void put(TaskProduct taskProduct){
		store.add(taskProduct);
	}
	
	public TaskProduct get() throws InterruptedException{
		return store.take();
	}
}
