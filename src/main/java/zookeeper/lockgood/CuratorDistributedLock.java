package zookeeper.lockgood;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CuratorDistributedLock {
    CuratorFramework zk;
    // 根节点
    private String ROOT_LOCK = "/curator";
    private String lockName;
    private String CURRENT;
    private String WAIT_LOCK;
    private CountDownLatch countDownLatch;

    public CuratorDistributedLock(String config, String lockName) {
        this.lockName = lockName;
        zk = CuratorFrameworkFactory.newClient(config, new RetryNTimes(10, 5000));
        zk.start();
        try {
            Stat stat = zk.checkExists().forPath(ROOT_LOCK);
            if (stat == null) {
                zk.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(ROOT_LOCK, new byte[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock() throws Exception {
        if (!tryLock()) {
            waitForLock();
        }
        System.out.println(Thread.currentThread().getName() + "获得了锁" + CURRENT);
    }

    private void waitForLock() throws Exception {
        var stat = zk.checkExists().usingWatcher((CuratorWatcher) event -> {
            if (countDownLatch != null) {
                System.out.println(2 + CURRENT);
                countDownLatch.countDown();
            }
        }).forPath(ROOT_LOCK + "/" + WAIT_LOCK);
        if (stat != null) {
            countDownLatch = new CountDownLatch(1);
            System.out.println(1 + CURRENT);
            countDownLatch.await(5, TimeUnit.SECONDS);
        }
    }

    private boolean tryLock() throws Exception {
        CURRENT = zk.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(ROOT_LOCK + "/" + lockName);
        var children = zk.getChildren().forPath(ROOT_LOCK);
        Collections.sort(children);
        var current = CURRENT.substring(CURRENT.lastIndexOf("/") + 1);
        if (current.equals(children.get(0))) {
            return true;
        }
        WAIT_LOCK = children.get(children.indexOf(current) - 1);
        return false;
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName() + "释放锁 " + CURRENT);
        try {
            zk.delete().forPath(CURRENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        zk.close();
    }

    private static int store = 500;

    private static void getAndMinus() {
        System.out.println(--store);
    }

    public static void main(String args[]) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                var curator = new CuratorDistributedLock("192.168.130.230:2181", "xxx");
                try {
                    curator.lock();
                    getAndMinus();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (curator != null) {
                            curator.unlock();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
