package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTimePredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPostalCodePredicate;
import seedu.address.model.util.DateTimeFormat;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;
import seedu.address.testutil.SortPropertyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // For testing clients
    public static final String VALID_CLIENT_NAME_ALICE = "Alice";
    public static final String VALID_CLIENT_NAME_BOB = "Bob";
    public static final String VALID_CLIENT_CONTACT_ALICE = "91234567";
    public static final String VALID_CLIENT_CONTACT_BOB = "98664535";
    public static final String VALID_CLIENT_EMAIL_ALICE = "alice@gmail.com";
    public static final String VALID_CLIENT_EMAIL_BOB = "bob@gmail.com";
    public static final String VALID_CLIENT_ASKING_PRICE_ALICE = "$1,000,000";
    public static final String VALID_CLIENT_ASKING_PRICE_BOB = "$800,000";
    public static final Long DEFAULT_CLIENT_ASKING_PRICE_LONG_ALICE = Long.parseLong("1000000");
    public static final Long DEFAULT_CLIENT_ASKING_PRICE_LONG_BOB = Long.parseLong("800000");

    public static final String CLIENT_NAME_DESC_ALICE = " " + PREFIX_CLIENT_NAME + VALID_CLIENT_NAME_ALICE;
    public static final String CLIENT_NAME_DESC_BOB = " " + PREFIX_CLIENT_NAME + VALID_CLIENT_NAME_BOB;
    public static final String CLIENT_CONTACT_DESC_ALICE = " " + PREFIX_CLIENT_CONTACT + VALID_CLIENT_CONTACT_ALICE;
    public static final String CLIENT_CONTACT_DESC_BOB = " " + PREFIX_CLIENT_CONTACT + VALID_CLIENT_CONTACT_BOB;
    public static final String CLIENT_EMAIL_DESC_ALICE = " " + PREFIX_CLIENT_EMAIL + VALID_CLIENT_EMAIL_ALICE;
    public static final String CLIENT_EMAIL_DESC_BOB = " " + PREFIX_CLIENT_EMAIL + VALID_CLIENT_EMAIL_BOB;
    public static final String CLIENT_ASKING_PRICE_DESC_ALICE =
            " " + PREFIX_CLIENT_ASKING_PRICE + VALID_CLIENT_ASKING_PRICE_ALICE;
    public static final String CLIENT_ASKING_PRICE_DESC_BOB =
            " " + PREFIX_CLIENT_ASKING_PRICE + VALID_CLIENT_ASKING_PRICE_BOB;

    public static final String INVALID_CLIENT_NAME_DESC =
            " " + PREFIX_CLIENT_NAME + "Alice&"; // '&' not allowed in names
    public static final String INVALID_CLIENT_CONTACT_DESC =
            " " + PREFIX_CLIENT_CONTACT + "91234567a"; // letters not allowed in contact number
    public static final String INVALID_CLIENT_EMAIL_DESC =
            " " + PREFIX_CLIENT_EMAIL + "alice@gmail"; // missing top level domain in email
    public static final String INVALID_CLIENT_ASKING_PRICE_DESC =
            " " + PREFIX_CLIENT_ASKING_PRICE + "001000000"; // leading zeroes not allowed in asking price

    // For testing properties
    public static final String VALID_NAME_MAYFAIR = "Mayfair";
    public static final String VALID_NAME_BURGHLEY_DRIVE = "Burghley Drive";
    public static final String VALID_TYPE_MAYFAIR = "Condo";
    public static final String VALID_TYPE_BURGHLEY_DRIVE = "Landed";
    public static final String VALID_ADDRESS_MAYFAIR = "1 Jurong East Street 32, #08-111";
    public static final String VALID_ADDRESS_BURGHLEY_DRIVE = "12 Burghley Drive";
    public static final String VALID_POSTAL_MAYFAIR = "609477";
    public static final String VALID_POSTAL_BURGHLEY_DRIVE = "558977";
    public static final String VALID_DEADLINE_MAYFAIR = "31-12-2021";
    public static final LocalDate VALID_DEADLINE_LOCALDATE_MAYFAIR =
            LocalDate.parse(VALID_DEADLINE_MAYFAIR, DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_DEADLINE_BURGHLEY_DRIVE = "31-07-2021";
    public static final LocalDate VALID_DEADLINE_LOCALDATE_BURGHLEY_DRIVE =
            LocalDate.parse(VALID_DEADLINE_BURGHLEY_DRIVE, DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_PROPERTY_TAG_BALCONY = "Balcony";
    public static final String VALID_PROPERTY_TAG_4_BEDROOMS = "4 bedrooms";
    public static final String VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD = "99 year leasehold";
    public static final String VALID_REMARK_MAYFAIR = "Urgent to sell";
    public static final String VALID_REMARK_BURGHLEY_DRIVE = "Lowest selling price is $5,040,0000";

    public static final String NAME_DESC_MAYFAIR = " " + PREFIX_NAME + VALID_NAME_MAYFAIR;
    public static final String NAME_DESC_BURGHLEY_DRIVE = " " + PREFIX_NAME + VALID_NAME_BURGHLEY_DRIVE;
    public static final String TYPE_DESC_MAYFAIR = " " + PREFIX_TYPE + VALID_TYPE_MAYFAIR;
    public static final String TYPE_DESC_BURGHLEY_DRIVE = " " + PREFIX_TYPE + VALID_TYPE_BURGHLEY_DRIVE;
    public static final String ADDRESS_DESC_MAYFAIR = " " + PREFIX_ADDRESS + VALID_ADDRESS_MAYFAIR;
    public static final String ADDRESS_DESC_BURGHLEY_DRIVE = " " + PREFIX_ADDRESS + VALID_ADDRESS_BURGHLEY_DRIVE;
    public static final String POSTAL_DESC_MAYFAIR = " " + PREFIX_POSTAL + VALID_POSTAL_MAYFAIR;
    public static final String POSTAL_DESC_BURGHLEY_DRIVE = " " + PREFIX_POSTAL + VALID_POSTAL_BURGHLEY_DRIVE;
    public static final String DEADLINE_DESC_MAYFAIR = " " + PREFIX_DEADLINE + VALID_DEADLINE_MAYFAIR;
    public static final String DEADLINE_DESC_BURGHLEY_DRIVE = " " + PREFIX_DEADLINE + VALID_DEADLINE_BURGHLEY_DRIVE;
    public static final String REMARK_DESC_MAYFAIR = " " + PREFIX_REMARK + VALID_REMARK_MAYFAIR;
    public static final String REMARK_DESC_BURGHLEY_DRIVE = " " + PREFIX_REMARK + VALID_REMARK_BURGHLEY_DRIVE;
    public static final String TAG_DESC_BALCONY = " " + PREFIX_TAGS + VALID_PROPERTY_TAG_BALCONY;
    public static final String TAG_DESC_4_BEDROOMS = " " + PREFIX_TAGS + VALID_PROPERTY_TAG_4_BEDROOMS;
    public static final String TAG_DESC_99_YEAR_LEASEHOLD = " " + PREFIX_TAGS + VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD;
    public static final String TAG_DESC_BALCONY_AND_99_YEAR_LEASEHOLD =
            " " + PREFIX_TAGS + VALID_PROPERTY_TAG_BALCONY + ", " + VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD;

    public static final String INVALID_PROPERTY_NAME_DESC =
            " " + PREFIX_NAME + "Mayfair&"; // '&' not allowed in names
    public static final String INVALID_PROPERTY_TYPE_DESC =
            " " + PREFIX_TYPE + "apartment"; // 'apartment' is not a valid type
    public static final String INVALID_PROPERTY_ADDRESS_DESC =
            " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PROPERTY_POSTAL_DESC =
            " " + PREFIX_POSTAL + "12345a"; // 'a' not allowed in postal codes
    public static final String INVALID_PROPERTY_DEADLINE_IN_INVALID_FORMAT_DESC =
            " " + PREFIX_DEADLINE + "30-4-2021"; // 1 digit in month part is not valid
    public static final String INVALID_PROPERTY_DEADLINE_IN_VALID_FORMAT_DESC =
            " " + PREFIX_DEADLINE + "31-04-2021"; // 31st April not valid
    public static final String INVALID_PROPERTY_REMARK_DESC =
            " " + PREFIX_REMARK; // empty string not allowed for remark
    public static final String INVALID_PROPERTY_TAG_DESC =
            " " + PREFIX_TAGS + "4 bedrooms*"; // '*' not allowed in tags

    // For testing appointments
    public static final String VALID_NAME_MEET_ALEX = "Meet Alex";
    public static final String VALID_NAME_MEET_BOB = "Meet Bob";
    public static final String VALID_REMARK_MEET_ALEX = "Bring him around Bishan to look at the properties";
    public static final String VALID_REMARK_MEET_BOB = "To meet with interested client for viewing of his house";
    public static final String VALID_DATE_MEET_ALEX = "25-12-2021";
    public static final LocalDate VALID_DATE_LOCALDATE_MEET_ALEX = LocalDate.parse(VALID_DATE_MEET_ALEX,
        DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_DATE_MEET_BOB = "30-04-2021";
    public static final LocalDate VALID_DATE_LOCALDATE_MEET_BOB = LocalDate.parse(VALID_DATE_MEET_BOB,
        DateTimeFormat.INPUT_DATE_FORMAT);
    public static final String VALID_TIME_MEET_ALEX = "1500";
    public static final LocalTime VALID_TIME_LOCALTIME_MEET_ALEX = LocalTime.parse(VALID_TIME_MEET_ALEX,
        DateTimeFormat.INPUT_TIME_FORMAT);
    public static final String VALID_TIME_MEET_BOB = "1030";
    public static final LocalTime VALID_TIME_LOCALTIME_MEET_BOB = LocalTime.parse(VALID_TIME_MEET_BOB,
        DateTimeFormat.INPUT_TIME_FORMAT);

    public static final String NAME_DESC_MEET_ALEX = " " + PREFIX_NAME + VALID_NAME_MEET_ALEX;
    public static final String NAME_DESC_MEET_BOB = " " + PREFIX_NAME + VALID_NAME_MEET_BOB;
    public static final String REMARK_DESC_MEET_ALEX = " " + PREFIX_REMARK + VALID_REMARK_MEET_ALEX;
    public static final String REMARK_DESC_MEET_BOB = " " + PREFIX_REMARK + VALID_REMARK_MEET_BOB;
    public static final String DATE_DESC_MEET_ALEX = " " + PREFIX_DATE + VALID_DATE_MEET_ALEX;
    public static final String DATE_DESC_MEET_BOB = " " + PREFIX_DATE + VALID_DATE_MEET_BOB;
    public static final String TIME_DESC_MEET_ALEX = " " + PREFIX_TIME + VALID_TIME_MEET_ALEX;
    public static final String TIME_DESC_MEET_BOB = " " + PREFIX_TIME + VALID_TIME_MEET_BOB;

    public static final String INVALID_APPOINTMENT_NAME_DESC =
            " " + PREFIX_NAME + "Meet Alex&"; // '&' not allowed in names
    public static final String INVALID_APPOINTMENT_REMARK_DESC =
            " " + PREFIX_REMARK; // empty string not allowed for remark
    public static final String INVALID_APPOINTMENT_DATE_IN_INVALID_FORMAT_DESC =
            " " + PREFIX_DATE + "30-4-2021"; // 1 digit in month part is not valid
    public static final String INVALID_APPOINTMENT_DATE_IN_VALID_FORMAT_DESC =
            " " + PREFIX_DATE + "31-04-2021"; // 31st April not valid
    public static final String INVALID_APPOINTMENT_TIME_IN_INVALID_FORMAT_DESC =
            " " + PREFIX_TIME + "950"; // there must be 4 digits in a valid time
    public static final String INVALID_APPOINTMENT_TIME_IN_VALID_FORMAT_DESC =
        " " + PREFIX_TIME + "1260"; // 60 is not valid for the minute component

    // For testing of SortAppointmentDescriptor and SortPropertyDescriptor
    public static final String VALID_SORTING_ORDER_ASC = "asc";
    public static final String VALID_SORTING_ORDER_DESC = "desc";
    public static final String VALID_SORTING_KEY_APPOINTMENT_DATETIME = "datetime";
    public static final String VALID_SORTING_KEY_APPOINTMENT_NAME = "name";
    public static final String VALID_SORTING_KEY_PROPERTY_DEADLINE = "deadline";
    public static final String VALID_SORTING_KEY_PROPERTY_NAME = "name";
    public static final String VALID_SORTING_KEY_PROPERTY_POSTAL_CODE = "postalcode";
    public static final String VALID_SORTING_KEY_PROPERTY_PRICE = "price";

    public static final String DESC_SORTING_ORDER = " " + PREFIX_SORTING_ORDER + VALID_SORTING_ORDER_DESC;
    public static final String DATETIME_APPOINTMENT_SORTING_KEY = " " + PREFIX_SORTING_KEY
            + VALID_SORTING_KEY_APPOINTMENT_DATETIME;
    public static final String DEADLINE_PROPERTY_SORTING_KEY = " " + PREFIX_SORTING_KEY
            + VALID_SORTING_KEY_PROPERTY_DEADLINE;

    public static final String INVALID_SORTING_ORDER = " " + PREFIX_SORTING_ORDER + "des"; // can only be desc and asc
    public static final String INVALID_APPOINTMENT_SORTING_KEY = " " + PREFIX_SORTING_KEY
            + "deadline"; // can only be datetime and name
    public static final String INVALID_PROPERTY_SORTING_KEY = " " + PREFIX_SORTING_KEY
            + "datetime"; // can only be name, price, postalcode, address, and deadline

    public static final SortAppointmentCommand.SortAppointmentDescriptor ASC_DATETIME;
    public static final SortAppointmentCommand.SortAppointmentDescriptor DESC_APPOINTMENT_NAME;

    public static final SortPropertyCommand.SortPropertyDescriptor ASC_DEADLINE;
    public static final SortPropertyCommand.SortPropertyDescriptor DESC_PROPERTY_NAME;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPropertyCommand.EditPropertyDescriptor DESC_MAYFAIR;
    public static final EditPropertyCommand.EditPropertyDescriptor DESC_BURGHLEY_DRIVE;

    static {
        DESC_MAYFAIR = new EditPropertyDescriptorBuilder().withName(VALID_NAME_MAYFAIR)
                .withPostalCode(VALID_POSTAL_MAYFAIR).withDeadline(VALID_DEADLINE_MAYFAIR)
                .withAddress(VALID_ADDRESS_MAYFAIR)
                .withType(VALID_TYPE_MAYFAIR).build();
        DESC_BURGHLEY_DRIVE = new EditPropertyDescriptorBuilder().withName(VALID_NAME_BURGHLEY_DRIVE)
                .withPostalCode(VALID_POSTAL_BURGHLEY_DRIVE).withDeadline(VALID_DEADLINE_BURGHLEY_DRIVE)
                .withAddress(VALID_ADDRESS_BURGHLEY_DRIVE)
                .withType(VALID_TYPE_BURGHLEY_DRIVE).build();
        ASC_DATETIME = new SortAppointmentDescriptorBuilder().withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        DESC_APPOINTMENT_NAME = new SortAppointmentDescriptorBuilder().withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_NAME).build();
        ASC_DEADLINE = new SortPropertyDescriptorBuilder().withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE).build();
        DESC_PROPERTY_NAME = new SortPropertyDescriptorBuilder().withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_NAME).build();
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
     * Executes the given {@code command}, and confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the property book, filtered property list and selected property in {@code actualModel} remain unchanged
     */
    public static void assertPropertyCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PropertyBook expectedPropertyBook = new PropertyBook(actualModel.getPropertyBook());
        List<Property> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPropertyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPropertyBook, actualModel.getPropertyBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPropertyList());
    }

    /**
     * Executes the given {@code command}, and confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the appointment book, filtered appointment list and selected appointment in {@code actualModel} remain
     * unchanged
     */
    public static void assertAppointmentCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AppointmentBook expectedAppointmentBook = new AppointmentBook(actualModel.getAppointmentBook());
        List<Appointment> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAppointmentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAppointmentBook, actualModel.getAppointmentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredAppointmentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s appointment book.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());

        final LocalDate date = appointment.getDate().date;
        final LocalTime time = appointment.getTime().time;
        model.updateFilteredAppointmentList(new AppointmentDateTimePredicate(date, time));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the property at the given {@code targetIndex} in the
     * {@code model}'s property book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property property = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String address = property.getAddress().propertyAddress;
        final String postal = property.getPostalCode().postal;
        model.updateFilteredPropertyList(new PropertyAddressPostalCodePredicate(address, postal));

        assertEquals(1, model.getFilteredPropertyList().size());
    }
}
