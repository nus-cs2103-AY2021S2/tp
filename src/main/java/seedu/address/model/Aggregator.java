package seedu.address.model;

//@@ author kangtinglee
public interface Aggregator<T> {
    /** Checks if a particular object is contained within the current one */
    boolean contains(T t);
}
