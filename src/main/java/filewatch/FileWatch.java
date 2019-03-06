package filewatch;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileWatch {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        String filePath = "D:\\";
        Paths.get(filePath).register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            WatchKey watchKey = watchService.take();
            List<WatchEvent<?>> list = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : list) {
                if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("创建：[" + filePath + "/" + watchEvent.context() + "]" + watchEvent.count());
                } else if (StandardWatchEventKinds.ENTRY_DELETE == watchEvent.kind()) {
                    System.out.println("删除：[" + filePath + "/" + watchEvent.context() + "]" + watchEvent.count());
                } else if (StandardWatchEventKinds.ENTRY_MODIFY == watchEvent.kind()) {
                    System.out.println("修改：[" + filePath + "/" + watchEvent.context() + "]" + watchEvent.count());
                }
            }
            watchKey.reset();
        }
    }
}

