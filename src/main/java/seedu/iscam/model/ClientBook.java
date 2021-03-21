package seedu.iscam.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.UniqueClientList;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.UniqueMeetingList;

/**
 * Wraps all data at the iscam-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class ClientBook implements ReadOnlyClientBook {

    private final UniqueClientList clients;
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        meetings = new UniqueMeetingList();
    }

    public ClientBook() {
    }

    /**
     * Creates an ClientBook using the Clients in the {@code toBeCopied}
     */
    public ClientBook(ReadOnlyClientBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Resets the existing data of this {@code ClientBook} with {@code newData}.
     */
    public void resetData(ReadOnlyClientBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the iscam book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the iscam book.
     * The client must not already exist in the iscam book.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the iscam book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the iscam book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code ClientBook}.
     * {@code key} must exist in the iscam book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the iscam book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the iscam book.
     * The meeting must not already exist in the iscam book.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the iscam book.
     * The identity of {@code editedMeeting} must not be the same as another existing meeting in the iscam book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code ClientBook}.
     * {@code key} must exist in the iscam book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    @Override
    public ObservableList<Meeting> getMeetingList() { return meetings.asUnmodifiableObservableList(); }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientBook // instanceof handles nulls
                && clients.equals(((ClientBook) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
