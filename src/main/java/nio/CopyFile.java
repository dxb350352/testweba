package nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CopyFile {
    static String src = "F:\\goland-2017.3.exe";
    static String desc = "F:\\gaxz.log.2017.09.18.exe";
    static int threadNum = 10;
    static long batchSize = 20 * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        multiThread();
        singleThread();
    }


    private static void multiThread() throws Exception {
        long start = System.currentTimeMillis();
        long lenth = new File(src).length();
        long partNum = lenth / threadNum;
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(threadNum + 2, threadNum + 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < threadNum; i++) {
            fixedThreadPool.execute(new MultiThead(i * partNum, partNum));
        }
        if (lenth % threadNum != 0) {
            fixedThreadPool.execute(new MultiThead(threadNum * partNum, lenth - threadNum * partNum));
        }
        fixedThreadPool.execute(() -> {
            File file = new File(desc);
            long len = file.length();
            while (((ThreadPoolExecutor) fixedThreadPool).getActiveCount() > 1) {
                System.out.println(file.length() + "---" + (file.length() - len) + "/s");
                len = file.length();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fixedThreadPool.shutdown();
        fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("multi耗时" + (System.currentTimeMillis() - start));
    }

    private static void singleThread() throws Exception {
        long start = System.currentTimeMillis();
        doPart(0, new File(src).length());
//        FileChannel fci = new FileInputStream(src).getChannel();
//        FileChannel fco = new RandomAccessFile(desc, "rw").getChannel();
//        System.out.println(2147483647 == Integer.MAX_VALUE);
//        long size = fci.size();
//        System.out.println(size);
//        long position = 0;
//        while (position < size) {
//            System.out.println(position);
//            position += fci.transferTo(position, batchSize, fco);
//            System.out.println(position);
//        }
//        fci.close();
//        fco.close();
        System.out.println("single耗时" + (System.currentTimeMillis() - start));
    }

    static class MultiThead implements Runnable {
        long start;
        long count;

        public MultiThead(long start, long count) {
            this.start = start;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                doPart(start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void doPart(long start, long count) throws Exception {
        RandomAccessFile rafin = new RandomAccessFile(src, "r");
        rafin.seek(start);
        RandomAccessFile rafout = new RandomAccessFile(desc, "rw");
        rafout.seek(start);

        FileChannel fcin = rafin.getChannel();
        FileChannel fcout = rafout.getChannel();

        FileLock fileLock = null;
        try {
            fileLock = fcout.lock(start, count, false);
            long lenth = start + count;
            while (start < lenth) {
                if (start + batchSize > lenth) {
                    start += fcin.transferTo(start, lenth - start, fcout);
                } else {
                    start += fcin.transferTo(start, batchSize, fcout);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileLock.release();
            fcin.close();
            fcout.close();
        }
    }

}
