package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class GroupBuilder {
    public static final String DEFAULT_NAME = "Close Friends";
    public static final Set<Person> DEFAULT_SET = new HashSet<>();

    private Name name;
    private Set<Person> persons;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = new Name(DEFAULT_NAME);
        persons = DEFAULT_SET;
    }

    /**
     * Creates a {@code GroupBuilder} with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        persons = new HashSet<>(groupToCopy.getPersons());
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code persons} into a {@code Set<Person>} and set it to the {@code Group} that we are building.
     */
    public GroupBuilder withPersons(Person ... persons) {
        this.persons = SampleDataUtil.getPersonSet(persons);
        return this;
    }

    public Group build() {
        return new Group(name, persons);
    }

}
