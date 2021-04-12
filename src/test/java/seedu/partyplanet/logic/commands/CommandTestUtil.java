package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.predicates.EventNameContainsKeywordsPredicate;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.partyplanet.testutil.EditEventDescriptorBuilder;
import seedu.partyplanet.testutil.EditPersonDescriptorBuilder;
import seedu.partyplanet.testutil.EventBuilder;
import seedu.partyplanet.testutil.PersonBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_BIRTHDAY_AMY = "1990-10-27";
    public static final String VALID_BIRTHDAY_BOB = "1990-11-15";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_AMY = "loves purple";
    public static final String VALID_REMARK_BOB = "loves white";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_NAME_CNY = "CNY";
    public static final String VALID_NAME_EASTER = "Easter";
    public static final String VALID_DATE_CNY = "2022-02-01";
    public static final String VALID_DATE_EASTER = "2022-04-04";
    public static final String VALID_REMARK_CNY = "buy oranges";
    public static final String VALID_REMARK_EASTER = "buy easter eggs";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + " " + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + " " + VALID_BIRTHDAY_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + " " + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + " " + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + " " + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + " " + VALID_TAG_HUSBAND;

    public static final String NAME_DESC_CNY = " " + PREFIX_NAME + " " + VALID_NAME_CNY;
    public static final String NAME_DESC_EASTER = " " + PREFIX_NAME + " " + VALID_NAME_EASTER;
    public static final String DATE_DESC_CNY = " " + PREFIX_DATE + " " + VALID_DATE_CNY;
    public static final String DATE_DESC_EASTER = " " + PREFIX_DATE + " " + VALID_DATE_EASTER;
    public static final String REMARK_DESC_CNY = " " + PREFIX_REMARK + " " + VALID_REMARK_CNY;
    public static final String REMARK_DESC_EASTER = " " + PREFIX_REMARK + " " + VALID_REMARK_EASTER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + " " + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + " " + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_BIRTHDAY_DESC =
            " " + PREFIX_BIRTHDAY + " " + "12345678"; // not in yyyy-mm-dd format
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + " " + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_EVENT_NAME_DESC =
            " " + PREFIX_NAME + " " + "Christmas!!"; // '!' not allowed in names
    public static final String INVALID_EVENT_DATE_DESC =
            " " + PREFIX_DATE + " " + "31 Jan 0000"; // 0000 not allowed as year
    public static final String INVALID_EVENT_REMARK_DESC = " " + PREFIX_REMARK + " " + ""; // empty remark

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String ADD_DEFAULT_PERSON_COMMAND = "add DEFAULT_PERSON";
    public static final String DELETE_FIRST_PERSON_COMMAND = "delete 1";
    public static final String ADD_DEFAULT_EVENT_COMMAND = "eadd DEFAULT_EVENT";
    public static final String DELETE_FIRST_EVENT_COMMAND = "edelete 1";

    public static final EditFieldCommand.EditPersonDescriptor DESC_AMY;
    public static final EditFieldCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withBirthday(VALID_BIRTHDAY_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withBirthday(VALID_BIRTHDAY_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EEditCommand.EditEventDescriptor DESC_CNY;
    public static final EEditCommand.EditEventDescriptor DESC_EASTER;

    static {
        DESC_CNY = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_CNY)
                .withDate(VALID_DATE_CNY)
                .withRemark(VALID_REMARK_CNY)
                .build();
        DESC_EASTER = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_EASTER)
                .withDate(VALID_DATE_EASTER)
                .withRemark(VALID_REMARK_EASTER)
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
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(splitName[0]));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     * Multiple targetIndexes can be provided.
     * Every targetIndex has to be valid.
     */
    public static void showPersonAtMultipleIndex(Model model, Index... targetIndexes) {
        assertTrue(targetIndexes.length >= 1);

        List<String> personNames = new ArrayList<>();
        for (Index i : targetIndexes) {
            assertTrue(i.getZeroBased() < model.getFilteredPersonList().size());
            personNames.add(model.getFilteredPersonList().get(i.getZeroBased()).getName().fullName);
        }

        Predicate<Person> allPersons = new NameContainsKeywordsPredicate(personNames.get(0));

        for (int c = 1; c < personNames.size(); c++) {
            allPersons = allPersons.or(new NameContainsKeywordsPredicate(personNames.get(c)));
        }

        model.updateFilteredPersonList(allPersons);

        assertEquals(targetIndexes.length, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s event book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new EventNameContainsKeywordsPredicate(splitName[0]));

        assertEquals(1, model.getFilteredEventList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s event book.
     * Multiple targetIndexes can be provided.
     * Every targetIndex has to be valid.
     */
    public static void showEventAtMultipleIndex(Model model, Index... targetIndexes) {
        assertTrue(targetIndexes.length >= 1);

        List<String> eventNames = new ArrayList<>();
        for (Index i : targetIndexes) {
            assertTrue(i.getZeroBased() < model.getFilteredEventList().size());
            eventNames.add(model.getFilteredEventList().get(i.getZeroBased()).getName().fullName);
        }

        Predicate<Event> allEvents = new EventNameContainsKeywordsPredicate(eventNames.get(0));

        for (int c = 1; c < eventNames.size(); c++) {
            allEvents = allEvents.or(new EventNameContainsKeywordsPredicate(eventNames.get(c)));
        }

        model.updateFilteredEventList(allEvents);

        assertEquals(targetIndexes.length, model.getFilteredEventList().size());
    }

    /**
     * Adds a default person from PersonBuilder into {@code model}'s address book
     */
    public static void addDefaultPerson(Model model) {
        Person defaultPerson = new PersonBuilder().build();
        model.addPerson(defaultPerson);
        model.addState(ADD_DEFAULT_PERSON_COMMAND);
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.addState(DELETE_FIRST_PERSON_COMMAND);
    }
    /**
     * Adds a default event from EventBuilder into {@code model}'s event book
     */
    public static void addDefaultEvent(Model model) {
        Event defaultEvent = new EventBuilder().build();
        model.addEvent(defaultEvent);
        model.addState(ADD_DEFAULT_EVENT_COMMAND);
    }

    /**
     * Deletes the first event in {@code model}'s filtered list from {@code model}'s event book
     */
    public static void deleteFirstEvent(Model model) {
        Event firstEvent = model.getFilteredEventList().get(0);
        model.deleteEvent(firstEvent);
        model.addState(DELETE_FIRST_EVENT_COMMAND);
    }
}
