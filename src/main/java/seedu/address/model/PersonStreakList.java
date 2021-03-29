package seedu.address.model;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonStreak;

/**
 * Represents a list of PersonStreaks that will always be sorted in the descending order. Uses a mutable list to achieve
 * better efficiency.
 */
public class PersonStreakList {

    private final ObservableList<PersonStreak> lst;
    private final ObservableList<PersonStreak> unmodifiableLst;

    public PersonStreakList() {
        lst = FXCollections.observableArrayList();
        unmodifiableLst = FXCollections.unmodifiableObservableList(lst);
    }

    public void setPersons(List<Person> persons) {
        List<PersonStreak> personStreaks = persons
                .stream()
                .map(PersonStreak::fromPerson)
                .sorted()
                .collect(Collectors.toList());

        lst.clear();
        lst.addAll(personStreaks);
    }

    public void add(Person p) {
        PersonStreak toAdd = PersonStreak.fromPerson(p);

        int i;
        for (i = 0; i < lst.size(); i++) {
            PersonStreak ps = lst.get(i);

            if (toAdd.compareTo(ps) <= -1) {
                break;
            }
        }

        lst.add(i, toAdd);
    }

    public void setPerson(Person target, Person editedPerson) {
        remove(target);
        add(editedPerson);
    }

    public void remove(Person key) {
        PersonStreak toRemove = PersonStreak.fromPerson(key);
        lst.remove(toRemove);
    }

    public ObservableList<PersonStreak> asUnmodifiableList() {
        return unmodifiableLst;
    }
}
