package org.nagarro.flight.service.interfaces;

public interface WatcherService extends Runnable{

	void watcherHandler();

	void readDirectory();

}