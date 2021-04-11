package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.AMY;
import static seedu.partyplanet.testutil.TypicalPersons.BOB;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteContactWithTagCommand}.
 */
public class DeleteContactWithTagCommandTest {

    private Model model;
    private Set<Tag> validTags;
    private Set<Tag> invalidTags;

    {
        AddressBook ab = new AddressBook();
        // AMY has tag friends
        // BOB has tag friends and husband
        ab.setPersons(List.of(AMY, BOB));
        model = new ModelManager(ab, getTypicalEventBook(), new UserPrefs());

        validTags = Set.of(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND));
        invalidTags = Set.of(new Tag("thistagshouldnotmatch"), new Tag("thistagshouldnotmatchtoo"));
    }


    @Test
    public void execute_validAnyTagUnfilteredList_success() {

        List<Person> personsToDelete = List.of(AMY, BOB);

        DeleteCommand deleteCommand = new DeleteContactWithTagCommand(validTags, true);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                displayPersons(personsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllTagUnfilteredList_success() {

        List<Person> personsToDelete = List.of(BOB);

        DeleteCommand deleteCommand = new DeleteContactWithTagCommand(validTags, false);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                displayPersons(personsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAnyTagNoneMatch_success() {

        DeleteCommand deleteCommand = new DeleteContactWithTagCommand(invalidTags, true);

        String expectedMessage = String.format(DeleteContactWithTagCommand.MESSAGE_PERSON_NOT_REMOVED_ANY);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllTagNoneMatch_success() {

        DeleteCommand deleteCommand = new DeleteContactWithTagCommand(invalidTags, false);

        String expectedMessage = String.format(DeleteContactWithTagCommand.MESSAGE_PERSON_NOT_REMOVED_ALL);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        DeleteCommand deleteAnyValidCommand = new DeleteContactWithTagCommand(validTags, true);
        DeleteCommand deleteAllValidCommand = new DeleteContactWithTagCommand(validTags, false);
        DeleteCommand deleteAnyInvalidCommand = new DeleteContactWithTagCommand(invalidTags, true);

        // same object -> returns true
        assertTrue(deleteAnyValidCommand.equals(deleteAnyValidCommand));

        // same values -> returns true
        DeleteCommand deleteAnyValidCommandCopy = new DeleteContactWithTagCommand(validTags, true);
        assertTrue(deleteAnyValidCommand.equals(deleteAnyValidCommandCopy));

        // different types -> returns false
        assertFalse(deleteAnyValidCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAnyValidCommand.equals(null));

        // any vs all -> return false
        assertFalse(deleteAnyValidCommand.equals(deleteAllValidCommand));

        // different tags -> returns false
        assertFalse(deleteAnyValidCommand.equals(deleteAnyInvalidCommand));
    }

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> deletedPersons) {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
