package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class GroupBuilder {
    public static final String DEFAULT_NAME = "Close Friends";
    public static final Set<Name> DEFAULT_SET = new HashSet<>();

    private Name name;
    private Set<Name> personNames;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = new Name(DEFAULT_NAME);
        personNames = DEFAULT_SET;
    }

    /**
     * Creates a {@code GroupBuilder} with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        personNames = new HashSet<>(groupToCopy.getPersonNames());
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
        List<Name> personNames = Arrays.stream(persons).map(Person::getName).collect(Collectors.toList());
        Name[] arrayPersonNames = personNames.toArray(new Name[0]);
        this.personNames = SampleDataUtil.getPersonSet(arrayPersonNames);
        return this;
    }

    public Group build() {
        return new Group(name, personNames);
    }

}
