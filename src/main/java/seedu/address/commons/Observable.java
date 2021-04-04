package seedu.address.commons;

public interface Observable<T> {
    void set(T t);

    void addObserver(Observer observer);
}
