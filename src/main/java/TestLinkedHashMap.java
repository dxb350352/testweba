import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true);
        for (int i = 0; i < 3; i++) {
            map.put(i, i);
        }
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        map.get(1);
        iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
    }
}
