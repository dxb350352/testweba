package zookeeper.curator_recipes.leader;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/5/7 12:50
 */
public class LeaderLatchDemo {
    protected static final String connectionInfo = "192.168.130.230:2181";
    protected static String PATH = "/francis/leader";
    private static final int CLIENT_QTY = 10;


    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client
                        = CuratorFrameworkFactory.newClient(connectionInfo, new ExponentialBackoffRetry(10000, 3));
                clients.add(client);
                LeaderLatch latch = new LeaderLatch(client, PATH, "Client #" + i);
                latch.addListener(new LeaderLatchListener() {

                    @Override
                    public void isLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am Leader");
                    }

                    @Override
                    public void notLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am not Leader");
                    }
                });
                examples.add(latch);
                client.start();
                latch.start();
            }
            System.out.println("sleep");
            Thread.sleep(3000);
            LeaderLatch currentLeader = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            examples.remove(currentLeader);
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            currentLeader.close();

            Thread.sleep(3000);

            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            examples.remove(currentLeader);
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
        } finally {
            for (LeaderLatch latch : examples) {
                if (null != latch.getState())
                    CloseableUtils.closeQuietly(latch);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }

}
