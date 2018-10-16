package redis;

import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * Created by liuyang on 2017/4/20.
 */
public class DistributedLockBetter {
    private final JedisPool jedisPool;

    public DistributedLockBetter(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static final long TIME = 1000;
    public static final String LOCK_MSG = "1";
    public static final String UNLOCK_MSG = "1";

    public boolean tryLock(String key, String request) {
        String result = this.jedisPool.getResource().set(key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
        if (LOCK_MSG.equals(result)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean unlock(String key, String request) {
        //lua script
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisPool.getResource().eval(script, Collections.singletonList(key), Collections.singletonList(request));
        if (UNLOCK_MSG.equals(result)) {
            return true;
        } else {
            return false;
        }
    }
}
