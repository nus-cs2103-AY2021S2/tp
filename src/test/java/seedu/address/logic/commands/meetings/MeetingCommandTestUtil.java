package seedu.address.logic.commands.meetings;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;


/**
 * Contains helper methods for testing commands.
 */
public class MeetingCommandTestUtil {

    public static final String VALID_NAME_MEETING1 = "CS2103 Conference";
    public static final String VALID_NAME_MEETING2 = "NUS Seminar";
    public static final String VALID_START_MEETING1 = "2021-01-01 19:00";
    public static final String VALID_START_MEETING2 = "2021-03-15 17:00";
    public static final String VALID_TERMINATE_MEETING1 = "2021-01-01 20:00";
    public static final String VALID_TERMINATE_MEETING2 = "2021-03-15 18:00";
    public static final String VALID_PRIORITY_MEETING1 = "2";
    public static final String VALID_PRIORITY_MEETING2 = "5";
    public static final String VALID_DESCRIPTION_MEETING1 = "CS2103 Conference Test";
    public static final String VALID_DESCRIPTION_MEETING2 = "NUS Seminar Test";
    public static final String VALID_TAG_MEETING1 = "SoC";
    public static final String VALID_TAG_MEETING2 = "University";

    public static final String NAME_DESC_MEETING1 = " " + PREFIX_NAME + VALID_NAME_MEETING1;
    public static final String NAME_DESC_MEETING2 = " " + PREFIX_NAME + VALID_NAME_MEETING2;
    public static final String START_DESC_MEETING1 = " " + PREFIX_START_TIME + VALID_START_MEETING1;
    public static final String START_DESC_MEETING2 = " " + PREFIX_START_TIME + VALID_START_MEETING2;
    public static final String END_DESC_MEETING1 = " " + PREFIX_END_TIME + VALID_TERMINATE_MEETING1;
    public static final String END_DESC_MEETING2 = " " + PREFIX_END_TIME + VALID_TERMINATE_MEETING2;
    public static final String PRIORITY_DESC_MEETING1 = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEETING1;
    public static final String PRIORITY_DESC_MEETING2 = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEETING2;
    public static final String DESCRIPTION_DESC_MEETING1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1;
    public static final String DESCRIPTION_DESC_MEETING2 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING2;
    public static final String TAG_DESC_MEETING1 = " " + PREFIX_GROUP + VALID_TAG_MEETING1;
    public static final String TAG_DESC_MEETING2 = " " + PREFIX_GROUP + VALID_TAG_MEETING2;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Lectures&"; // '&' not allowed in names
    public static final String INVALID_DATETIME_DESC = " " + PREFIX_START_TIME + "2020/02/03 09:00";
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "-2";
    public static final String INVALID_TAG_DESC = " " + PREFIX_GROUP + "hubby*"; // '*' not allowed in tags
}

//
//    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
//    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
//
//    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
//    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;
//
//    static {
//        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
//                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
//                .withTags(VALID_TAG_FRIEND).build();
//        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
//                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
//    }

//    /**
//     * Executes the given {@code command}, confirms that <br>
//     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
//     * - the {@code actualModel} matches {@code expectedModel}
//     */
//    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
//                                            Model expectedModel) {
//        try {
//            CommandResult result = command.execute(actualModel);
//            assertEquals(expectedCommandResult, result);
//            assertEquals(expectedModel, actualModel);
//        } catch (CommandException ce) {
//            throw new AssertionError("Execution of command should not fail.", ce);
//        }
//    }
//
//    /**
//     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
//     * that takes a string {@code expectedMessage}.
//     */
//    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
//                                            Model expectedModel) {
//        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
//        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
//    }
//
//    /**
//     * Executes the given {@code command}, confirms that <br>
//     * - a {@code CommandException} is thrown <br>
//     * - the CommandException message matches {@code expectedMessage} <br>
//     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
//     */
//    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
//        // we are unable to defensively copy the model for comparison later, so we can
//        // only do so by copying its components.
//        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
//        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
//
//        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
//        assertEquals(expectedAddressBook, actualModel.getAddressBook());
//        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
//    }
//    /**
//     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
//     * {@code model}'s address book.
//     */
//    public static void showPersonAtIndex(Model model, Index targetIndex) {
//        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());
//
//        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
//        final String[] splitName = person.getName().fullName.split("\\s+");
//        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
//
//        assertEquals(1, model.getFilteredPersonList().size());//}

