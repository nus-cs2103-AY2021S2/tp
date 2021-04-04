package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class GroupTest {
    private Set<Name> personNameSet = new HashSet<>();

    private final Name name = new Name("test");
    private final Name diffName = new Name("Test");

    @Test
    public void constructor_nullGroupName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null, personNameSet));
    }

    @Test
    public void constructor_nullPersonSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(name, null));
    }

    @Test
    public void isSameGroup() {
        Set<Name> diffPersonNameSet = new HashSet<>();
        diffPersonNameSet.add(ALICE.getName());

        Group group = new Group(name, personNameSet);
        Group diffGroup = new Group(diffName, personNameSet);
        assertTrue(group.isSameGroup(group));
        assertFalse(group.isSameGroup(null));
        assertFalse(group.isSameGroup(diffGroup)); //different group name

        diffGroup = new Group(name, diffPersonNameSet);
        assertTrue(group.isSameGroup(diffGroup)); //different group person sets.
    }

    @Test
    public void equals() {
        Set<Name> diffPersonNameSet = new HashSet<>();
        diffPersonNameSet.add(ALICE.getName());

        Group group = new Group(name, personNameSet);
        Group diffGroup = new Group(diffName, personNameSet);
        assertTrue(group.equals(group));
        assertFalse(group.equals(null));
        assertFalse(group.equals(diffGroup)); //different group name

        diffGroup = new Group(name, diffPersonNameSet);
        assertFalse(group.equals(diffGroup)); //different group person sets.
    }

}
