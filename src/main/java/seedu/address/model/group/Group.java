package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Group in FriendDex.
 */
public class Group {

    private Name groupName;
    private Set<Person> persons;

    /**
     * Constructs a {@code Group}.
     *  @param name A valid name.
     * @param persons A set of person.
     */
    public Group(Name name, Set<Person> persons) {
        requireAllNonNull(name, persons);
        this.groupName = name;
        this.persons = persons;
    }

    /**
     * Constructs a {@code Group} with an empty set of Persons.
     *
     * @param name A valid name.
     */
    public Group(Name name) {
        requireNonNull(name);
        this.groupName = name;
        this.persons = new HashSet<>();
    }

    public Name getName() {
        return groupName;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void setPersons(Set<Person> editedPersonSet) {
        persons = editedPersonSet;
    }

    @Override
    public String toString() {
        return groupName.toString();
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getName().equals(getName());
    }

    /**
     * Returns true if both groups have field values.
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getName().equals(getName())
                && otherGroup.getPersons().equals(getPersons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, persons);
    }
}
