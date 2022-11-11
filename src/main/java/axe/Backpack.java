package axe;

import java.util.HashMap;
import java.util.Map;

public class Backpack {

    private final Map<String, Object> storage;
    private static final Backpack BACKPACK = new Backpack();

    private Backpack() {
        storage = new HashMap<>();
    }

    public static Backpack getInstance() {
        return BACKPACK;
    }

    public Object get(String key) {
        return storage.get(key);
    }

    public void add(String key, Object value) {
        storage.put(key, value);
    }
}
