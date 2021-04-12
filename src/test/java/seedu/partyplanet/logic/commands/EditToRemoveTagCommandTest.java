package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.partyplanet.testutil.TypicalPersons.AMY;
import static seedu.partyplanet.testutil.TypicalPersons.BOB;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;
import seedu.partyplanet.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditToRemoveTagCommandTest {

    private AddressBook addressBook;
    {
        addressBook = new AddressBook();
        addressBook.setPersons(List.of(AMY, BOB));
    }

    private Model model = new ModelManager(addressBook, getTypicalEventBook(), new UserPrefs());

    private Person notFriendAmy;
    private Person notFriendBob;
    private Person notFriendNotHusbandBob;
    {
        notFriendAmy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withBirthday(VALID_BIRTHDAY_AMY)
                .withAddress(VALID_ADDRESS_AMY).withRemark(VALID_REMARK_AMY)
                .build();
        notFriendBob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withBirthday(VALID_BIRTHDAY_BOB)
                .withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        notFriendNotHusbandBob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withBirthday(VALID_BIRTHDAY_BOB)
                .withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB)
                .build();
    }

    @Test
    public void execute_multipleValidTagsUnfilteredList_success() {
        Set<Tag> targetTags = Set.of(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND));
        EditCommand editCommand = new EditToRemoveTagCommand(targetTags);
        List<Person> editedPersons = List.of(AMY, BOB);

        String expectedMessage = String.format(
                EditToRemoveTagCommand.MESSAGE_REMOVE_TAGS_SUCCESS, displayPersons(editedPersons));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setPerson(AMY, notFriendAmy);
        expectedModel.setPerson(BOB, notFriendNotHusbandBob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allInvalidTagsSpecified_success() {
        Set<Tag> targetTags = Set.of(new Tag("nosuchtag"), new Tag("fakeTag"));
        EditCommand editCommand = new EditToRemoveTagCommand(targetTags);

        String expectedMessage = String.format(
                EditToRemoveTagCommand.MESSAGE_TAGS_NOT_REMOVED);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validThenInvalidTagsUnfilteredList_success() {
        Set<Tag> targetTags = Set.of(new Tag(VALID_TAG_FRIEND), new Tag("nosuchtag"));
        EditCommand editCommand = new EditToRemoveTagCommand(targetTags);
        List<Person> editedPersons = List.of(AMY, BOB);

        String expectedMessage = String.format(
                EditToRemoveTagCommand.MESSAGE_REMOVE_TAGS_SUCCESS, displayPersons(editedPersons));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setPerson(AMY, notFriendAmy);
        expectedModel.setPerson(BOB, notFriendBob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidThenValidTagsUnfilteredList_success() {
        Set<Tag> targetTags = Set.of(new Tag("nosuchtag"), new Tag(VALID_TAG_FRIEND));
        EditCommand editCommand = new EditToRemoveTagCommand(targetTags);
        List<Person> editedPersons = List.of(AMY, BOB);

        String expectedMessage = String.format(
                EditToRemoveTagCommand.MESSAGE_REMOVE_TAGS_SUCCESS, displayPersons(editedPersons));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setPerson(AMY, notFriendAmy);
        expectedModel.setPerson(BOB, notFriendBob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTagFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Set<Tag> targetTags = Set.of(new Tag(VALID_TAG_FRIEND));
        EditCommand editCommand = new EditToRemoveTagCommand(targetTags);
        List<Person> editedPersons = List.of(BOB);

        String expectedMessage = String.format(
                EditToRemoveTagCommand.MESSAGE_REMOVE_TAGS_SUCCESS, displayPersons(editedPersons));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), notFriendBob);
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
                new EditToRemoveTagCommand(Set.of(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND)));

        // same values -> returns true
        EditCommand commandWithSameValues =
                new EditToRemoveTagCommand(Set.of(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND)));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new EditToRemoveTagCommand(Set.of(new Tag("anothertag")))));
    }

    /**
     * Returns names of edited persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> editedPersons) {
        assert editedPersons.size() > 0;
        return editedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .get();
    }

}
