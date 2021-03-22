package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_METHOD_GET = "GET";
    public static final String VALID_METHOD_POST = "POST";
    public static final String VALID_ADDRESS_RANDOM =
            "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=2";
    public static final String VALID_ADDRESS_FACT = "https://cat-fact.herokuapp.com/facts";
    public static final String VALID_DATA_PAIR = "{\"key\": \"value\"}";
    public static final String VALID_DATA_PAIR_NEW = "{\"newkey\": \"newvalue\"}";

    public static final String VALID_HEADER_PAIR = "\"key: value\"";
    public static final String VALID_HEADER_PAIR_NEW = "\"newkey: newvalue\"";

    public static final String VALID_TAG_CAT = "cat";
    public static final String VALID_TAG_COOL = "cool";
    public static final String VALID_TAG_1 = "tag1";

    public static final String METHOD_DESC_GET = " " + PREFIX_METHOD + VALID_METHOD_GET;
    public static final String METHOD_DESC_POST = " " + PREFIX_METHOD + VALID_METHOD_POST;
    public static final String ADDRESS_DESC_RANDOM = " " + PREFIX_ADDRESS + VALID_ADDRESS_RANDOM;
    public static final String ADDRESS_DESC_FACT = " " + PREFIX_ADDRESS + VALID_ADDRESS_FACT;
    public static final String TAG_DESC_COOL = " " + PREFIX_TAG + VALID_TAG_COOL;
    public static final String TAG_DESC_CAT = " " + PREFIX_TAG + VALID_TAG_CAT;
    public static final String DATA_DESC_DEFAULT = " " + PREFIX_DATA + VALID_DATA_PAIR;
    public static final String DATA_DESC_NEW = " " + PREFIX_DATA + VALID_DATA_PAIR_NEW;
    public static final String HEADER_DESC_DEFAULT = " " + PREFIX_HEADER + VALID_HEADER_PAIR;
    public static final String HEADER_DESC_NEW = " " + PREFIX_HEADER + VALID_HEADER_PAIR_NEW;



    public static final String INVALID_METHOD_DESC = " " + PREFIX_METHOD + "GOT"; // '&' not allowed in names
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_HEADER_DESC = " " + PREFIX_HEADER + "blahblah"; //header must be "x":"y"
    public static final String INVALID_DATA_DESC = " " + PREFIX_DATA + "blahblah"; //header must be {"x":"y"}


    public static final String PREAMBLE_WHITESPACE = " ";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEndpointDescriptor DESC_GET;
    public static final EditCommand.EditEndpointDescriptor DESC_POST;

    static {
        DESC_GET = new EditEndpointDescriptorBuilder().withName(VALID_METHOD_GET).withAddress(VALID_ADDRESS_RANDOM)
                .withData(VALID_DATA_PAIR).withHeaders(VALID_HEADER_PAIR).withTags(VALID_TAG_COOL).build();
        DESC_POST = new EditEndpointDescriptorBuilder().withName(VALID_METHOD_POST).withAddress(VALID_ADDRESS_FACT)
                .withData(VALID_DATA_PAIR).withHeaders(VALID_HEADER_PAIR)
                .withTags(VALID_TAG_CAT, VALID_TAG_COOL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | RequestException | AbortRequestException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)} that
     * takes a string {@code expectedMessage}.
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
     * - the API endpoint list, filtered endpoint list and selected endpoint in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EndpointList expectedEndpointList = new EndpointList(actualModel.getEndpointList());
        List<Endpoint> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEndpointList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEndpointList, actualModel.getEndpointList());
        assertEquals(expectedFilteredList, actualModel.getFilteredEndpointList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the endpoint at the given
     * {@code targetIndex} in the {@code model}'s API endpoint list.
     */
    public static void showEndpointAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEndpointList().size());

        Endpoint endpoint = model.getFilteredEndpointList().get(targetIndex.getZeroBased());
        final String[] splitTags = endpoint.getTags().toString().split("\\s+");
        model.updateFilteredEndpointList(new EndPointContainsKeywordsPredicate(Arrays.asList(splitTags[0])));

        assertEquals(1, model.getFilteredEndpointList().size());
    }

}
