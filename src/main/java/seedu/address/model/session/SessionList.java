package seedu.address.model.session;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.SessionNotFoundException;


/**
 * A list of sessions that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class SessionList {

    private final ObservableList<Session> internalList = FXCollections.observableArrayList();
    private final ObservableList<Session> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a session to the list.
     */
    public void add(Session toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
<<<<<<< HEAD
     * Removes the equivalent session from the list.
     * The session must exist in the list.
     */
    public void remove(Session toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SessionNotFoundException();
        }
    }

    /**
=======
>>>>>>> cd001c730f07dc44f63ed430127d1a1e1c32e3e7
     * Returns true if the list contains an equivalent session as the given argument.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Session> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<Session> getInternalList() {
        return internalList;
    }

    public Iterator<Session> iterator() {
        return internalList.iterator();
    }
}
