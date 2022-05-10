package controller;

import java.util.HashMap;

/**
 * Class for passing request attributes via POST
 */
public class TemporaryAttributes {

    private final HashMap<String, Object> attributes = new HashMap<>();

    public TemporaryAttributes() {
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(String key, Object value) {
        attributes.put(key, value);
    }
}
