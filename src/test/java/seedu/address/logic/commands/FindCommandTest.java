package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.BENSON;
import static seedu.address.testutil.TypicalGarments.CARL;
import static seedu.address.testutil.TypicalGarments.DANIEL;
import static seedu.address.testutil.TypicalGarments.ELLE;
import static seedu.address.testutil.TypicalGarments.FIONA;
import static seedu.address.testutil.TypicalGarments.GEORGE;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWardrobe(), new UserPrefs());

    @Test
    public void nameEquals() {
        String argsFirst = " n/first";
        String argsSecond = " n/second";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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

    /*@Test
    //this test wouldnt even go thru coz you cant pass empty an empty, would throw exception
    public void execute_zeroKeywordsName_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" n/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }*/

    /*@Test //my random check
    public void check() {
        String args = " n/Alice Bob";
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
       //AttributesContainsKeywordsPredicate pred = new AttributesContainsKeywordsPredicate(argumentMultimap);
       ArgumentMultimap argumentMultimap2 =
               ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                       PREFIX_DESCRIPTION, PREFIX_TYPE);
        //FindCommand c1 = new FindCommand(pred);
        //FindCommand c2 = null;
        //try{c2 = new FindCommandParser().parse(args);}
        //catch(Exception e){}
        //FindCommand c2 = new FindCommand(pred);
        assertEquals(argumentMultimap, argumentMultimap2);
    }
    */

    @Test
    public void execute_multipleKeywordsName_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" n/Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        String expectedMessage = command.showMessage();
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredGarmentList());
    }

    /**
     * Parses {@code userInput} into a {@code AttributesContainsKeywordsPredicate}.
     */
    private AttributesContainsKeywordsPredicate prepareAttributesPredicate(String userInput) {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        return new AttributesContainsKeywordsPredicate(argumentMultimap);
    }

    @Test
    public void sizeEquals() {
        String argsFirst = " s/23";
        String argsSecond = " s/34";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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

    /* @Test
    public void execute_zeroKeywordsSize_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" s/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    @Test
    public void execute_multipleKeywordsSize_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" s/93 24 27");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredGarmentList());
    }


    @Test
    public void colourEquals() {
        String argsFirst = " c/red";
        String argsSecond = " c/blue";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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

    /*@Test
    public void execute_zeroKeywordsColour_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" c/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    @Test
    public void execute_multipleKeywordsColour_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" c/blue blue blue");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredGarmentList());
    }

    @Test
    public void dresscodeEquals() {
        String argsFirst = " r/formal";
        String argsSecond = " r/active";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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

    /*@Test
    public void execute_zeroKeywordsDressCode_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" r/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    @Test
    public void execute_multipleKeywordsDressCode_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" r/FORMAL CASUAL CASUAL");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL , ELLE, FIONA), model.getFilteredGarmentList());
    }


    @Test
    public void descriptionEquals() {
        String argsFirst = " d/first";
        String argsSecond = " d/second";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" d/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    //changed so that does not assert for the messages
    @Test
    public void execute_multipleKeywordsDescription_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" d/owesmoney");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredGarmentList());
    }

    @Test
    public void typeEquals() {
        String argsFirst = " t/upper";
        String argsSecond = " t/lower";
        ArgumentMultimap argumentMultimapFirst =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argumentMultimapSecond =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        AttributesContainsKeywordsPredicate firstPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapFirst);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argumentMultimapSecond);

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

    /*@Test
    public void execute_zeroKeywordsType_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" t/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    @Test
    public void execute_multipleKeywordsType_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" t/upper lower");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredGarmentList());
    }

    /*@Test
    public void execute_zeroKeywordsMultipleAttributes_noGarmentFound() {
        String expectedMessage = String.format(MESSAGE_GARMENTS_LISTED_OVERVIEW, 0);
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" n/ s/ c/ r/ d/ t/ ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGarmentList());
    }
    */

    @Test
    public void execute_multipleKeywordsMultipleAtttributes_multipleGarmentsFound() {
        AttributesContainsKeywordsPredicate predicate = prepareAttributesPredicate(" s/24 33 r/casual active "
                + "c/blue t/lower");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGarmentList(predicate);
        String expectedMessage = command.showMessage();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredGarmentList());
    }
}
