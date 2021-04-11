package seedu.address.commons;

/**
 * This is part of the Observer's pattern, where the Observable is observed by Observers and notifies its Observers
 * for any change to its variable of type T. This is done by calling the implemented update method of the Observer,
 * which carries out the relevant actions.
 *
 * @param <T> Type of variable that is observable.
 */
public interface Observable<T> {
    /**
     * Sets the variable that is observable. Notifies its Observers of this change.
     *
     * @param t The value to set the observable variable to.
     */
    void set(T t);

    /**
     * Adds the given Observer to a collection of this observable. Observers added will be notified of changes.
     *
     * @param observer Object that is observing this Observable.
     */
    void addObserver(Observer observer);
}
