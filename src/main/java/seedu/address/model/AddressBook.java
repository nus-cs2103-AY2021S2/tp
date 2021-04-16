package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        meetings = new UniqueMeetingList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of Link.me with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.meetings.setPersons(persons);
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in Link.me.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns the clashed meeting if a person's meeting time clashes with another meeting in Link.me.
     */
    public Optional<Person> clash(Person person) {
        requireNonNull(person);
        return meetings.clash(person);
    }

    /**
     * Adds a person to Link.me.
     * The person must not already exist in Link.me.
     */
    public void addPerson(Person p) {
        meetings.add(p);
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in Link.me.
     * The person identity of {@code editedPerson} must not be the same as another existing person in Link.me.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        meetings.setPerson(target, editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Link.me.
     */
    public void removePerson(Person key) {
        meetings.remove(key);
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    public String getNotifications() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the things you might want to take note of:\n\n");
        int length = sb.length() + 1;
        String meetingNotif = meetings.getNotifications();
        String personNotif = persons.getNotifications();
        if (meetingNotif.length() > 0) {
            sb.append(meetingNotif);
            sb.append("\n");
        }
        if (personNotif.length() > 0) {
            sb.append(personNotif);
            sb.append("\n");
        }
        if (sb.length() > length) {
            return sb.toString();
        }
        return "Your schedule is clear today!\n";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
