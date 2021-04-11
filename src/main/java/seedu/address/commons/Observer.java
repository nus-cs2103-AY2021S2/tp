package seedu.address.commons;

/**
 * This is part of the Observer's pattern, where the Observer subscribes to an Observable and will be notified when the
 * Observable value changes. This is done by the Observable calling the implemented update method of the Observer,
 * which carries out the relevant actions.
 */
public interface Observer {
    /**
     * Carries out the relevant actions of the Observer when the Observable notifies it.
     */
    void update();
}
