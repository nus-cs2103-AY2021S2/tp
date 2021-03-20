package seedu.us.among.model.endpoint;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.us.among.model.endpoint.exceptions.DuplicateApiEndpointException;
import seedu.us.among.model.endpoint.exceptions.EndpointNotFoundException;

/**
 * A list of endpoints that enforces uniqueness between its elements and does not allow nulls.
 * A endpoint is considered unique by comparing using {@code Endpoint#isSameEndpoint(Endpoint)}. As such, adding and
 * updating of endpoints uses Endpoint#isSameEndpoint(Endpoint) for equality so as to ensure that the endpoint being
 * added or updated is unique in terms of identity in the UniqueEndpointList. However, the removal of a endpoint
 * uses Endpoint#equals(Object) so as to ensure that the endpoint with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Endpoint#isSameEndpoint(Endpoint)
 */
public class UniqueEndpointList implements Iterable<Endpoint> {

    private final ObservableList<Endpoint> internalList = FXCollections.observableArrayList();
    private final ObservableList<Endpoint> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent endpoint as the given argument.
     */
    public boolean contains(Endpoint toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEndpoint);
    }

    /**
     * Adds a endpoint to the list.
     * The endpoint must not already exist in the list.
     */
    public void add(Endpoint toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApiEndpointException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the endpoint {@code target} in the list with {@code editedEndpoint}.
     * {@code target} must exist in the list.
     * The endpoint identity of {@code editedEndpoint} must not be the same as another existing endpoint in the list.
     */
    public void setEndpoint(Endpoint target, Endpoint editedEndpoint) {
        requireAllNonNull(target, editedEndpoint);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EndpointNotFoundException();
        }

        if (!target.isSameEndpoint(editedEndpoint) && contains(editedEndpoint)) {
            throw new DuplicateApiEndpointException();
        }

        internalList.set(index, editedEndpoint);
    }

    /**
     * Removes the equivalent endpoint from the list.
     * The endpoint must exist in the list.
     */
    public void remove(Endpoint toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EndpointNotFoundException();
        }
    }

    public void setEndpoints(UniqueEndpointList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code endpoints}.
     * {@code endpoints} must not contain duplicate endpoints.
     */
    public void setEndpoints(List<Endpoint> endpoints) {
        requireAllNonNull(endpoints);
        if (!endpointsAreUnique(endpoints)) {
            throw new DuplicateApiEndpointException();
        }

        internalList.setAll(endpoints);
    }

    /**
     * Returns true if there are no endpoints in the list.
     * Useful for the list command to deal with the case of no endpoints saved.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Endpoint> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEndpointList // instanceof handles nulls
                        && internalList.equals(((UniqueEndpointList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code endpoints} contains only unique endpoints.
     */
    private boolean endpointsAreUnique(List<Endpoint> endpoints) {
        for (int i = 0; i < endpoints.size() - 1; i++) {
            for (int j = i + 1; j < endpoints.size(); j++) {
                if (endpoints.get(i).isSameEndpoint(endpoints.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
