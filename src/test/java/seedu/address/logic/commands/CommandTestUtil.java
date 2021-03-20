package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Wardrobe;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditGarmentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_SIZE_AMY = "11";
    public static final String VALID_SIZE_BOB = "22";
    public static final String VALID_COLOUR_AMY = "blue";
    public static final String VALID_COLOUR_BOB = "red";
    public static final String VALID_DRESSCODE_AMY = "FORMAL";
    public static final String VALID_DRESSCODE_BOB = "ACTIVE";
    public static final String VALID_DESCRIPTION_HUSBAND = "husband";
    public static final String VALID_DESCRIPTION_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String SIZE_DESC_AMY = " " + PREFIX_SIZE + VALID_SIZE_AMY;
    public static final String SIZE_DESC_BOB = " " + PREFIX_SIZE + VALID_SIZE_BOB;
    public static final String COLOUR_DESC_AMY = " " + PREFIX_COLOUR + VALID_COLOUR_AMY;
    public static final String COLOUR_DESC_BOB = " " + PREFIX_COLOUR + VALID_COLOUR_BOB;
    public static final String DRESSCODE_DESC_AMY = " " + PREFIX_DRESSCODE + VALID_DRESSCODE_AMY;
    public static final String DRESSCODE_DESC_BOB = " " + PREFIX_DRESSCODE + VALID_DRESSCODE_BOB;
    public static final String DESCRIPTION_DESC_FRIEND = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_FRIEND;
    public static final String DESCRIPTION_DESC_HUSBAND = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_SIZE_DESC = " " + PREFIX_SIZE + "911a"; // 'a' not allowed in sizes
    public static final String INVALID_COLOUR_DESC = " " + PREFIX_COLOUR + " "; // missing colour
    public static final String INVALID_DRESSCODE_DESC = " "
            + PREFIX_DRESSCODE; // empty string not allowed for addresses
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "hubby*"; // '*' not allowed in descriptions

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditGarmentDescriptor DESC_AMY;
    public static final EditCommand.EditGarmentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditGarmentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withSize(VALID_SIZE_AMY).withColour(VALID_COLOUR_AMY).withDressCode(VALID_DRESSCODE_AMY)
                .withDescriptions(VALID_DESCRIPTION_FRIEND).build();
        DESC_BOB = new EditGarmentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withSize(VALID_SIZE_BOB).withColour(VALID_COLOUR_BOB).withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND, VALID_DESCRIPTION_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the wardrobe, filtered garment list and selected garment in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Wardrobe expectedWardrobe = new Wardrobe(actualModel.getWardrobe());
        List<Garment> expectedFilteredList = new ArrayList<>(actualModel.getFilteredGarmentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWardrobe, actualModel.getWardrobe());
        assertEquals(expectedFilteredList, actualModel.getFilteredGarmentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the garment at the given {@code targetIndex} in the
     * {@code model}'s wardrobe.
     */
    public static void showGarmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGarmentList().size());

        Garment garment = model.getFilteredGarmentList().get(targetIndex.getZeroBased());
        final String[] splitName = garment.getName().fullName.split("\\s+");
        model.updateFilteredGarmentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredGarmentList().size());
    }

}
