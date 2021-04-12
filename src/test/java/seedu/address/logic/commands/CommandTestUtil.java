package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.identifier.Identifier;
//import seedu.address.commons.core.identifier.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventContainsKeywordsPredicate;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
//import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_CS2030 = "CS2030";
    public static final String VALID_NAME_CS2107 = "CS2107";
    public static final String VALID_NAME_CS2100 = "CS2100";
    public static final String VALID_DESCRIPTION_CS2030 = "Object oriented Programming module";
    public static final String VALID_DESCRIPTION_CS2107 = "Introduction to Information Security";
    public static final String VALID_DESCRIPTION_CS2100 = "Computer Organisation";
    public static final EventStatus VALID_STATUS_CS2030 = EventStatus.DONE;
    public static final EventStatus VALID_STATUS_CS2107 = EventStatus.IN_PROGRESS;
    public static final EventStatus VALID_STATUS_CS2100 = EventStatus.IN_PROGRESS;
    public static final EventPriority VALID_PRIORITY_CS2030 = EventPriority.HIGH;
    public static final EventPriority VALID_PRIORITY_CS2107 = EventPriority.MEDIUM;
    public static final EventPriority VALID_PRIORITY_CS2100 = EventPriority.LOW;

    //Event
    public static final String EVENTNAME_DESC_CS2030 = " " + PREFIX_NAME + VALID_NAME_CS2030;
    public static final String EVENTNAME_DESC_CS2107 = " " + PREFIX_NAME + VALID_NAME_CS2107;
    public static final String DESCRIPTION_DESC_CS2030 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS2030;
    public static final String DESCRIPTION_DESC_CS2107 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS2107;
    public static final String EVENTSTATUS_DESC_CS2030 = " " + PREFIX_STATUS + VALID_STATUS_CS2030;
    public static final String EVENTSTATUS_DESC_CS2107 = " " + PREFIX_STATUS + VALID_STATUS_CS2107;
    public static final String EVENTPRIORITY_DESC_CS2030 = " " + PREFIX_PRIORITY + VALID_PRIORITY_CS2030;
    public static final String EVENTPRIORITY_DESC_CS2107 = " " + PREFIX_PRIORITY + VALID_PRIORITY_CS2107;


    //Event
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "CS2030&"; // '&' not allowed in names
    public static final String INVALID_EVENTSTATUS_DESC = " " + PREFIX_STATUS + "tdo"; // eventStatus must be valid

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEventDescriptor DESC_CS2030;
    public static final EditCommand.EditEventDescriptor DESC_CS2107;

    static {
        DESC_CS2030 = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2030)
                .withDescription(VALID_DESCRIPTION_CS2030).withEventStatus(VALID_STATUS_CS2030)
                .build();
        DESC_CS2107 = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2107)
                .withDescription(VALID_DESCRIPTION_CS2107).withEventStatus(VALID_STATUS_CS2107)
                .build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EventBook expectedEventBook = new EventBook(actualModel.getEventBook());
        List<Event> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEventList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEventBook, actualModel.getEventBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredEventList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIdentifier} in the
     * {@code model}'s event book.
     */
    public static void showEventAtIdentifier(Model model, Identifier targetIdentifier) {
        assertTrue(targetIdentifier.getValue() < Event.getLatestIdentifier().getValue());

        Optional<Event> optEvent = model.getEventByIdentifier(targetIdentifier.getValue());
        assertTrue(optEvent.isPresent());
        Event event = optEvent.get();
        final String[] splitName = event.getName().eventName.split("\\s+");
        model.updateFilteredEventList(new EventContainsKeywordsPredicate(Arrays.asList(splitName)));

        assertTrue(model.getFilteredEventList().size() > 0);
    }

}
