import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapSet {
    int id;
    String name;

    public HashMapSet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashMapSet that = (HashMapSet) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static void main(String[] args) {
        //如果hashCode和equals相同，则替换value，不是key
        Map<Integer, HashMapSet> map = new HashMap<>();
        map.put(1, new HashMapSet(1, "1"));
        map.put(1, new HashMapSet(1, "2"));
        System.out.println(map.get(1).name);
        Set<HashMapSet> set = new HashSet<>();
        set.add(new HashMapSet(1, "1"));
        set.add(new HashMapSet(1, "2"));
        System.out.println(set.iterator().next().name);
    }
}
