package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTeachingAssistant.BEN;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.ContactTagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterContactCommand}.
 */
public class FilterContactCommandTest {
    private Model model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());

    @Test
    public void equals() {
        ContactTagsContainKeywordsPredicate predicate0 =
                new ContactTagsContainKeywordsPredicate(Collections.singletonList("a"));
        ContactTagsContainKeywordsPredicate predicate1 =
                new ContactTagsContainKeywordsPredicate(Collections.singletonList("a"));
        ContactTagsContainKeywordsPredicate predicate2 =
                new ContactTagsContainKeywordsPredicate(Collections.singletonList("b"));

        FilterContactCommand command0 = new FilterContactCommand(predicate0);
        FilterContactCommand command1 = new FilterContactCommand(predicate1);
        FilterContactCommand command2 = new FilterContactCommand(predicate2);

        assertEquals(command0, command0);
        assertEquals(command0, command1);
        assertNotEquals(command0, command2);
    }

    @Test
    public void execute_oneKeyword_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactTagsContainKeywordsPredicate predicate = preparePredicate("a");
        FilterContactCommand command = new FilterContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_oneKeyword_oneEntryFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        ContactTagsContainKeywordsPredicate predicate = preparePredicate("owesMoney"); // case insensitive
        FilterContactCommand command = new FilterContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BEN), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywordsAndSearch_oneEntryFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        ContactTagsContainKeywordsPredicate predicate = preparePredicate("friends owesMoney"); // case insensitive
        FilterContactCommand command = new FilterContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BEN), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywordsOrSearch_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactTagsContainKeywordsPredicate predicate = preparePredicate("friends colleagues"); // case insensitive
        FilterContactCommand command = new FilterContactCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    /**
     * Parses {@code userInput} into a {@code ContactTagsContainKeywordsPredicate}.
     */
    private ContactTagsContainKeywordsPredicate preparePredicate(String userInput) {
        return new ContactTagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
