package com.emppayrolljavaio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class WatchServiceDirectory {

	/**
	 * private variables can only be accessed within the same class
	 */
	private static final Kind<?> ENTRY_CREATE = null;
	private static final Kind<?> ENTRY_MODIFY = null;
	private static final Kind<?> ENTRY_DELETE = null;
	private final WatchService watcher;
	private final Map<WatchKey, Path> dirWatcher;

	/**
	 * Method to watch particular directory with all files and sub directories
	 * @param dir - parameter as path directory
	 * @throws IOException - throws exception
	 */
	public WatchServiceDirectory(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.dirWatcher = new HashMap<WatchKey, Path>();
		scanAndRegisterDirectories(dir);
	}

	/**
	 * Method for Register directory with watch service
	 * 
	 * @param dir -passing parameter as path directory
	 * @throws IOException -throws exception
	 */
	private void registerDirWatchers(Path dir) throws IOException {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
		dirWatcher.put(key, dir);
	}

	/**
	 * Method to scan and register directory and sub directories
	 * 
	 * @param start
	 * @throws IOException
	 */
	private void scanAndRegisterDirectories(final Path start) throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				registerDirWatchers(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	
	/**
	 *Method for processing the events
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void processEvents() {
		while (true) {
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}
			Path dir = dirWatcher.get(key);
			if (dir == null)
				continue;
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				Path name = ((WatchEvent<Path>) event).context();
				Path child = dir.resolve(name);
				System.out.format("%s : %s\n", event.kind().name(), child);

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(child))
							scanAndRegisterDirectories(child);
					} catch (IOException x) {
					}
				} else if (kind.equals(ENTRY_DELETE)) {
					if (Files.isDirectory(child))
						dirWatcher.remove(key);
				}

			}
			boolean valid = key.reset();
			if (!valid) {
				dirWatcher.remove(key);
				if (dirWatcher.isEmpty())
					break;
			}
		}
	}
}
