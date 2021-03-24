package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.ALICE;
import static seedu.dictionote.testutil.TypicalContacts.BENSON;
import static seedu.dictionote.testutil.TypicalContacts.CARL;
import static seedu.dictionote.testutil.TypicalContacts.DANIEL;
import static seedu.dictionote.testutil.TypicalContacts.ELLE;
import static seedu.dictionote.testutil.TypicalContacts.FIONA;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainKeywordsPredicate;
import seedu.dictionote.testutil.TypicalContacts;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindContactCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        TagsContainKeywordsPredicate firstTagsPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("first"));
        TagsContainKeywordsPredicate secondTagsPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("second"));

        FindContactCommand findFirstCommand = new FindContactCommand(firstNamePredicate, firstTagsPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondNamePredicate, secondTagsPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstNamePredicate, firstTagsPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 7);

        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");

        FindContactCommand command = new FindContactCommand(namePredicate, tagsPredicate);
        expectedModel.updateFilteredContactList(namePredicate.and(tagsPredicate));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(TypicalContacts.getTypicalContacts(), model.getFilteredContactList());
    }

    @Test
    public void execute_nameKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);

        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");

        FindContactCommand command = new FindContactCommand(namePredicate, tagsPredicate);
        expectedModel.updateFilteredContactList(namePredicate.and(tagsPredicate));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredContactList());
    }

    @Test
    public void execute_tagKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);

        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate("friends");

        FindContactCommand command = new FindContactCommand(namePredicate, tagsPredicate);
        expectedModel.updateFilteredContactList(namePredicate.and(tagsPredicate));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredContactList());
    }

    @Test
    public void execute_nameAndTagKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);

        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Meier");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate("owesMoney");

        FindContactCommand command = new FindContactCommand(namePredicate, tagsPredicate);
        expectedModel.updateFilteredContactList(namePredicate.and(tagsPredicate));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredContactList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagsContainKeywordsPredicate}.
     */
    private TagsContainKeywordsPredicate prepareTagsPredicate(String userInput) {
        return new TagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
