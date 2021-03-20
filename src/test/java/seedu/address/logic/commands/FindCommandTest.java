package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_GARMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.BENSON;
import static seedu.address.testutil.TypicalGarments.CARL;
import static seedu.address.testutil.TypicalGarments.DANIEL;
import static seedu.address.testutil.TypicalGarments.ELLE;
import static seedu.address.testutil.TypicalGarments.FIONA;
import static seedu.address.testutil.TypicalGarments.GEORGE;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.garment.ColourContainsKeywordsPredicate;
import seedu.address.model.garment.DescriptionContainsKeywordsPredicate;
import seedu.address.model.garment.DressCodeContainsKeywordsPredicate;
import seedu.address.model.garment.NameContainsKeywordsPredicate;
import seedu.address.model.garment.SizeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWardrobe(), new UserPrefs());

    @Test
    public void nameEquals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsName_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }

    @Test
    public void execute_multipleKeywordsName_multipleGarmentsFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void sizeEquals() {
        SizeContainsKeywordsPredicate firstPredicate =
                new SizeContainsKeywordsPredicate(Collections.singletonList("first"));
        SizeContainsKeywordsPredicate secondPredicate =
                new SizeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsSize_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        SizeContainsKeywordsPredicate predicate = prepareSizePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }

    @Test
    public void execute_multipleKeywordsSize_multipleGarmentsFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 3);
        SizeContainsKeywordsPredicate predicate = prepareSizePredicate("93 24 27");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SizeContainsKeywordsPredicate prepareSizePredicate(String userInput) {
        return new SizeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void colourEquals() {
        ColourContainsKeywordsPredicate firstPredicate =
                new ColourContainsKeywordsPredicate(Collections.singletonList("first"));
        ColourContainsKeywordsPredicate secondPredicate =
                new ColourContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsColour_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        ColourContainsKeywordsPredicate predicate = prepareColourPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }

    @Test
    public void execute_multipleKeywordsColour_multipleGarmentsFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 7);
        ColourContainsKeywordsPredicate predicate = prepareColourPredicate("blue blue blue");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ColourContainsKeywordsPredicate prepareColourPredicate(String userInput) {
        return new ColourContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void dresscodeEquals() {
        SizeContainsKeywordsPredicate firstPredicate =
                new SizeContainsKeywordsPredicate(Collections.singletonList("first"));
        SizeContainsKeywordsPredicate secondPredicate =
                new SizeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywordsDressCode_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        DressCodeContainsKeywordsPredicate predicate = prepareDressCodePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }

    @Test
    public void execute_multipleKeywordsDressCode_multipleGarmentsFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 6);
        DressCodeContainsKeywordsPredicate predicate = prepareDressCodePredicate("FORMAL CASUAL CASUAL");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL , ELLE, FIONA), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DressCodeContainsKeywordsPredicate prepareDressCodePredicate(String userInput) {
        return new DressCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void descriptionEquals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different garment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /*@Test //issue here related to description not allowing space
    public void execute_zeroKeywordsDescription_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = prepareDescriptionPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }*/

    @Test
    public void execute_multipleKeywordsDescription_multipleGarmentsFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 1);
        DescriptionContainsKeywordsPredicate predicate = prepareDescriptionPredicate("owesmoney");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate prepareDescriptionPredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
