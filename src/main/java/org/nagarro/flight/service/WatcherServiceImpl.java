package org.nagarro.flight.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.nagarro.flight.config.MvcConfiguration;
import org.nagarro.flight.service.interfaces.FileService;
import org.nagarro.flight.service.interfaces.FlightsDaoService;
import org.nagarro.flight.service.interfaces.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("watcherService")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class WatcherServiceImpl implements WatcherService {

	private final String FILE_PATH = "E:\\Training\\Advance Java\\flights";

	@Autowired
	private ExecutorService ececutorService;

	@Autowired
	ApplicationContext appContext;
	
	@Autowired
	FlightsDaoService flightsDaoService;

	@Override
	public void run() {
		readDirectory();
		watcherHandler();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nagarro.flight.service.WatcherService#watcherHandler()
	 */
	@Override
	public void watcherHandler() {
		Path dir = Paths.get(FILE_PATH);

		WatchService watcher = null;
		WatchKey key = null;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);

		} catch (IOException e) {
			e.printStackTrace();
		}
		for (;;) {
			try {
				key = watcher.take();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				WatchEvent<Path> path = (WatchEvent<Path>) event;
				Path file = path.context();
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				} else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
					flightsDaoService.deleteFlights(file.getFileName().toString());
				} else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
					FileService fileService = appContext.getBean("fileService", FileService.class);
					fileService.setFile(Paths.get(FILE_PATH, file.getFileName().toString()));
					ececutorService.submit(fileService);
				}

			}
			key.reset();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nagarro.flight.service.WatcherService#readDirectory()
	 */
	@Override
	public void readDirectory() {
		Path dir = Paths.get(FILE_PATH);

		try (Stream<Path> file = Files.list(dir);) {
			file.forEach(f -> {
				FileService fileService = appContext.getBean("fileService", FileService.class);
				fileService.setFile(f);
				ececutorService.submit(fileService);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
