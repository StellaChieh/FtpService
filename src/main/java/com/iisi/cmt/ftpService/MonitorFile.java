package com.iisi.cmt.ftpService;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MonitorFile {

    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private Path dir;
    private String filename;
    private Notified notify;

    private static Logger logger= LoggerFactory.getLogger(MonitorFile.class);
    
    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register() throws IOException {
        WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE
        		,StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);    
        Path prev = keys.get(key);
        if (prev == null) {
            logger.debug("register: %s\n", dir);
        } else {
            if (!dir.equals(prev)) {
            	logger.debug("update: %s -> %s\n", prev, dir);
            }
        }
        keys.put(key, dir);
    }

    
    /**
     * Creates a WatchService and registers the given directory
     */
    MonitorFile(Path dir, String filename, Notified notify) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.notify = notify;
        this.dir = dir;
        this.filename = filename;
        register();
    }

    /**
     * Process all events for keys queued to the watcher
     */
    public void monitor() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
            	logger.debug("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                Kind<?> kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                if(name.getFileName().toString().equals(filename)) {
                	logger.info("%s: %s\n", event.kind().name(), name.getFileName().toString());
                	notify.restart();
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }


}
