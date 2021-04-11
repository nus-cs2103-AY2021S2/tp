package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.commons.core.Messages.MESSAGE_READERS_LISTED_OVERVIEW;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.BENSON;
import static seedu.smartlib.testutil.TypicalModels.CARL;
import static seedu.smartlib.testutil.TypicalModels.DANIEL;
import static seedu.smartlib.testutil.TypicalModels.ELLE;
import static seedu.smartlib.testutil.TypicalModels.FIONA;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;
import seedu.smartlib.model.reader.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindReaderCommand}.
 */
public class FindReaderCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindReaderCommand findFirstCommand = new FindReaderCommand(firstPredicate);
        FindReaderCommand findSecondCommand = new FindReaderCommand(secondPredicate);

        // EP: same object
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // EP: same values
        FindReaderCommand findFirstCommandCopy = new FindReaderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // EP: not a string
        assertFalse(findFirstCommand.equals(1));

        // EP: null
        assertFalse(findFirstCommand.equals(null));

        // EP: different readers
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    // tests for reader names

    @Test
    public void execute_zeroKeywords_noReaderFound() {
        // EP: empty strings
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: multiple spaces
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate2 = preparePredicate("           ");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_invalidKeyword_noReaderFound() {
        // EP: single invalid keyword, alphabetical
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Hello");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: single invalid keyword, numerical
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate2 = preparePredicate("123");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: single invalid keyword, special characters
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate3 = preparePredicate("+-*/");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleInvalidKeywords_noReaderFound() {
        // EP: multiple invalid keywords
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Hello 123 +-*/");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_partialKeyword_noReaderFound() {
        // EP: part of a valid keyword
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kur");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_validKeyword_singleReaderFound() {
        // EP: single valid keyword
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CARL), model.getFilteredReaderList());

        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate2 = preparePredicate("Elle");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(ELLE), model.getFilteredReaderList());

        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate3 = preparePredicate("Kunz");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredReaderList());
    }

    @Test
    public void execute_validKeywordIgnoreCase_singleReaderFound() {
        // EP: single valid keyword, all lower case
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("kunz");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredReaderList());

        // EP: single valid keyword, all upper case
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate2 = preparePredicate("KUNZ");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredReaderList());

        // EP: single valid keyword, mix of upper and lower case
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate3 = preparePredicate("kUnZ");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleValidKeywords_multipleReadersFound() {
        // EP: multiple valid keywords
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("KuRz ElLe KUnz");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredReaderList());
    }

    @Test
    public void execute_validAndInvalidKeywords_singleReaderFound() {
        // EP: one valid keyword and one invalid alphabetical keyword
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("KuRz Hello");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CARL), model.getFilteredReaderList());

        // EP: one valid keyword and one invalid numerical keyword
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate2 = preparePredicate("123 ELLe");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(ELLE), model.getFilteredReaderList());

        // EP: one valid keyword and one invalid keyword made up of special characters
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate3 = preparePredicate("+-*/ KuNz");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredReaderList());
    }

    // tests for reader tags

    @Test
    public void execute_zeroTags_noReaderFound() {
        // EP: empty strings
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: multiple spaces
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate2 = prepareTagPredicate("t/           ");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_invalidTag_noReaderFound() {
        // EP: single invalid tag, alphabetical
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/Hello");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: single invalid tag, numerical
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate2 = prepareTagPredicate("t/123");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());

        // EP: single invalid tag, special characters
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate3 = prepareTagPredicate("t/+-*/");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleInvalidTags_noReaderFound() {
        // EP: multiple invalid tags
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/Hello 123 +-*/");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_partialTag_noReaderFound() {
        // EP: part of a valid tag
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/VI");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_validTag_multipleReadersFound() {
        // EP: single valid tag
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/VIP");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredReaderList());

        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate2 = prepareTagPredicate("t/TopBorrower");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredReaderList());

        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate3 = prepareTagPredicate("t/VVIP");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(DANIEL), model.getFilteredReaderList());
    }

    @Test
    public void execute_validTagIgnoreCase_singleReaderFound() {
        // EP: single valid keyword, all lower case
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/topborrower");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredReaderList());

        // EP: single valid keyword, all upper case
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate2 = prepareTagPredicate("t/TOPBORROWER");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredReaderList());

        // EP: single valid keyword, mix of upper and lower case
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate3 = prepareTagPredicate("t/tOPborRoWeR");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleValidTags_multipleReadersFound() {
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/VIP VVIP");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredReaderList());
    }

    @Test
    public void execute_validAndInvalidTags_singleReaderFound() {
        // EP: one valid tag and one invalid alphabetical tag
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("t/vip Hello");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredReaderList());

        // EP: one valid tag and one invalid numerical tag
        String expectedMessage2 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate2 = prepareTagPredicate("t/123 topBoRROWER");
        FindReaderCommand command2 = new FindReaderCommand(predicate2);
        expectedModel.updateFilteredReaderList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredReaderList());

        // EP: one valid tag and one invalid tag made up of special characters
        String expectedMessage3 = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate3 = prepareTagPredicate("t/+-*/ VVIP");
        FindReaderCommand command3 = new FindReaderCommand(predicate3);
        expectedModel.updateFilteredReaderList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Collections.singletonList(DANIEL), model.getFilteredReaderList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        String[] nameKeywords = userInput.split("\\s+");
        int endOfTag = 2;
        nameKeywords[0] = nameKeywords[0].substring(endOfTag);
        return new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords));
    }

}
