package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_SCHOOL_AMY = "Abc Secondary School";
    public static final String VALID_SCHOOL_BOB = "Cba Secondary School";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GUARDIAN_NAME_AMY = "Alice Bee";
    public static final String VALID_GUARDIAN_NAME_BOB = "Ben Choo";
    public static final String VALID_GUARDIAN_PHONE_AMY = "33333333";
    public static final String VALID_GUARDIAN_PHONE_BOB = "44444444";
    public static final String VALID_SUBJECT_CHEM = "chem";
    public static final String VALID_SUBJECT_MATH = "math";
    public static final String VALID_LESSON_AMY = "monday 1500";
    public static final String VALID_LESSON_BOB = "wednesday 1100";

    public static final String VALID_IMPORTANT_DATE_DESCRIPTION_CEDAR_EXAM = "Cedar Secondary 4 Exam";
    public static final String VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM = "Raffles Institution Year 4 Exam";
    public static final String VALID_IMPORTANT_DATE_DETAILS_CEDAR_EXAM = "2021-10-12 1200";
    public static final String VALID_IMPORTANT_DATE_DETAILS_RAFFLES_EXAM = "2021-05-10 0900";



    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String SCHOOL_DESC_AMY = " " + PREFIX_SCHOOL + VALID_SCHOOL_AMY;
    public static final String SCHOOL_DESC_BOB = " " + PREFIX_SCHOOL + VALID_SCHOOL_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GUARDIAN_NAME_DESC_AMY = " " + PREFIX_GUARDIAN_NAME + VALID_GUARDIAN_NAME_AMY;
    public static final String GUARDIAN_NAME_DESC_BOB = " " + PREFIX_GUARDIAN_NAME + VALID_GUARDIAN_NAME_BOB;
    public static final String GUARDIAN_PHONE_DESC_AMY = " " + PREFIX_GUARDIAN_PHONE + VALID_GUARDIAN_PHONE_AMY;
    public static final String GUARDIAN_PHONE_DESC_BOB = " " + PREFIX_GUARDIAN_PHONE + VALID_GUARDIAN_PHONE_BOB;
    public static final String SUBJECT_DESC_MATH = " " + PREFIX_SUBJECT + VALID_SUBJECT_MATH;
    public static final String SUBJECT_DESC_CHEM = " " + PREFIX_SUBJECT + VALID_SUBJECT_CHEM;
    public static final String LESSON_DESC_AMY = " " + PREFIX_LESSON + VALID_LESSON_AMY;
    public static final String LESSON_DESC_BOB = " " + PREFIX_LESSON + VALID_LESSON_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT + "hubby"; // 'hubby' is not a subject
    public static final String INVALID_LESSON_DESC = " " + PREFIX_LESSON + "monday"; // time must be included after day

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withSchool(VALID_SCHOOL_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withGuardianName(VALID_GUARDIAN_NAME_AMY).withGuardianPhone(VALID_GUARDIAN_PHONE_AMY)
                .withSubjects(VALID_SUBJECT_MATH).withLessons(VALID_LESSON_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withSchool(VALID_SCHOOL_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withGuardianName(VALID_GUARDIAN_NAME_BOB).withGuardianPhone(VALID_GUARDIAN_PHONE_BOB)
                .withSubjects(VALID_SUBJECT_CHEM, VALID_SUBJECT_MATH).withLessons(VALID_LESSON_BOB).build();
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
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the person in {@code actualModel} matches {@code expectedPerson}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Person expectedPerson) {
        try {
            CommandResult result = command.execute(actualModel);
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            assertEquals(expectedCommandResult, result);
            Person actualPerson = actualModel.getSelectedPerson();
            assertEquals(actualPerson, expectedPerson);
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
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
