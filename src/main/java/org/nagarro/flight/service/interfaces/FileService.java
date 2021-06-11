package org.nagarro.flight.service.interfaces;

import java.nio.file.Path;

public interface FileService extends Runnable {

	void fileReader(Path file);

	void setFile(Path file);

}