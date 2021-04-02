package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupHashMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEvent;
import seedu.address.model.person.PersonStreak;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final GroupHashMap groups;
    private final PersonStreakList personStreaks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new GroupHashMap();
        personStreaks = new PersonStreakList();
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
     * Replaces the contents of the person list with {@code persons}.
     * Groups will be emptied.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
        this.groups.setGroups(new HashMap<>());
        personStreaks.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGroups(HashMap<Name, Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setGroups(new HashMap<>(newData.getGroupMap()));
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        personStreaks.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        if (!target.isSamePerson(editedPerson)) {
            groups.replacePerson(target.getName(), editedPerson.getName());
        }
        personStreaks.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        personStreaks.remove(key);
    }

    //// group-level operations

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Replaces the group for {@code groupName} in the map with {@code editedGroup}.
     * {@code groupName} must exist in the group map.
     */
    public void setGroup(Name groupName, Group editedGroup) {
        requireAllNonNull(groupName, editedGroup);

        groups.setGroup(groupName, editedGroup);
    }
    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableMap<Name, Group> getGroupMap() {
        return groups.asUnmodifiableObservableMap();
    }

    @Override
    public ObservableList<PersonEvent> getUpcomingDates() {
        List<PersonEvent> personEvents = new ArrayList<>();
        persons.forEach(person -> {
            // Add birthday
            personEvents.add(new PersonEvent(person.getBirthday().getDate(), person,
                    PersonEvent.getBirthdayDescription(person)));
            // Add special dates
            person.getDates().forEach(event -> personEvents.add(new PersonEvent(event.getDate(), person,
                    PersonEvent.getEventDescription(person, event))));
        });

        LocalDate now = LocalDate.now();
        personEvents.sort((x, y) -> {
            // Sorts dates by proximity to current date
            int xMonth = (x.getMonth() < now.getMonthValue()) ? (x.getMonth() + 12) : x.getMonth();
            int yMonth = (y.getMonth() < now.getMonthValue()) ? (y.getMonth() + 12) : y.getMonth();
            if (xMonth == yMonth) {
                return x.getDate() - y.getDate();
            } else {
                return xMonth - yMonth;
            }
        });

        return FXCollections.observableArrayList(personEvents);
    }

    @Override
    public ObservableList<PersonStreak> getPersonStreaks() {
        return personStreaks.asUnmodifiableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && groups.equals(((AddressBook) other).groups));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
