package seedu.us.among.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.UniqueEndpointList;

/**
 * Wraps all data at the API endpoint list level
 * Duplicates are not allowed (by .isSameEndpoint comparison)
 */
public class EndpointList implements ReadOnlyEndpointList {

    private final UniqueEndpointList endpoints;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        endpoints = new UniqueEndpointList();
    }

    public EndpointList() {}

    /**
     * Creates an EndpointList using the API endpoints in the {@code toBeCopied}
     */
    public EndpointList(ReadOnlyEndpointList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the endpoint list with {@code endpoints}.
     * {@code endpoints} must not contain duplicate endpoints.
     */
    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints.setEndpoints(endpoints);
    }

    /**
     * Resets the existing data of this {@code EndpointList} with {@code newData}.
     */
    public void resetData(ReadOnlyEndpointList newData) {
        requireNonNull(newData);

        setEndpoints(newData.getEndpointList());
    }

    //// endpoint-level operations

    /**
     * Returns true if a endpoint with the same identity as {@code endpoint} exists in the API endpoint list.
     */
    public boolean hasEndpoint(Endpoint endpoint) {
        requireNonNull(endpoint);
        return endpoints.contains(endpoint);
    }

    /**
     * Adds a endpoint to the API endpoint list.
     * The endpoint must not already exist in the API endpoint list.
     */
    public void addEndpoint(Endpoint p) {
        endpoints.add(p);
    }

    /**
     * Replaces the given endpoint {@code target} in the list with {@code editedEndpoint}.
     * {@code target} must exist in the API endpoint list.
     * The endpoint identity of {@code editedEndpoint} must not be the same as another existing endpoint in the
     * API endpoint list.
     */
    public void setEndpoint(Endpoint target, Endpoint editedEndpoint) {
        requireNonNull(editedEndpoint);

        endpoints.setEndpoint(target, editedEndpoint);
    }

    /**
     * Removes {@code key} from this {@code EndpointList}.
     * {@code key} must exist in the API endpoint list.
     */
    public void removeEndpoint(Endpoint key) {
        endpoints.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return endpoints.asUnmodifiableObservableList().size() + " endpoints";
        // TODO: refine later
    }

    @Override
    public ObservableList<Endpoint> getEndpointList() {
        return endpoints.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndpointList // instanceof handles nulls
                && endpoints.equals(((EndpointList) other).endpoints));
    }

    @Override
    public int hashCode() {
        return endpoints.hashCode();
    }

    public boolean isEmpty() {
        return endpoints.isEmpty();
    }
}
