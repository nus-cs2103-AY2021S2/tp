package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonStreak;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a list of PersonStreaks that will always be sorted in the descending order.
 * Uses a mutable list to achieve better efficiency.
 */
public class PersonStreakList {

    private final ObservableList<PersonStreak> lst;
    private final ObservableList<PersonStreak> unmodifiableLst;
    private final FilteredList<PersonStreak> filteredLst;

    /**
     * Initialises the PersonStreakList
     */
    public PersonStreakList() {
        lst = FXCollections.observableArrayList();
        filteredLst = new FilteredList<>(lst, (PersonStreak::isActiveGoal));
        unmodifiableLst = FXCollections.unmodifiableObservableList(filteredLst);
    }

    /**
     * Replaces the contents of this list with {@code persons}
     * @param persons must not contain duplicate persons
     */
    public void setPersons(List<Person> persons) {
        requireNonNull(persons);
        List<PersonStreak> personStreaks = persons
                .stream()
                .distinct()
                .map(PersonStreak::fromPerson)
                .sorted()
                .collect(Collectors.toList());

        lst.clear();
        lst.addAll(personStreaks);
    }

    /**
     * Adds a person to the list
     * @param p must not exist in the list
     */
    public void add(Person p) {
        requireNonNull(p);
        PersonStreak toAdd = PersonStreak.fromPerson(p);

        if (lst.contains(toAdd)) {
            throw new DuplicatePersonException();
        }

        int i;
        for (i = 0; i < lst.size(); i++) {
            PersonStreak ps = lst.get(i);

            if (toAdd.compareTo(ps) <= -1) {
                break;
            }
        }

        lst.add(i, toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}
     * @param target must exists in the list.
     * @param editedPerson must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        remove(target);
        add(editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * @param p must exist in the list
     */
    public void remove(Person p) {
        requireNonNull(p);
        PersonStreak toRemove = PersonStreak.fromPerson(p);
        if (!lst.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonStreak> asUnmodifiableList() {
        return unmodifiableLst;
    }
}
