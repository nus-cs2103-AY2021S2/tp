package seedu.address.commons.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pair stores key value pair
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
    private final K key;
    private final V value;

    /**
     * Constructor
     * @param key
     * @param value
     */
    @JsonCreator
    public Pair(@JsonProperty("key") K key, @JsonProperty("value") V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets key method
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     * Get value method
     * @return
     */
    public V getValue() {
        return value;
    }

    /**
     * equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (key != null ? !key.equals(pair.key) : pair.key != null) {
                return false;
            }
            if (value != null ? !value.equals(pair.value) : pair.value != null) {
                return false;
            }
            return true;
        }
        return false;
    }
}
