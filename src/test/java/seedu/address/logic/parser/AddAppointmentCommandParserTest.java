package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BALLET_RECITAL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BALLET_RECITAL;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BALLET_RECITAL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_PTM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PTM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PTM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointments.BALLET_RECITAL;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Address;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());
        ParserUtil.setModel(model);
    }

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Appointment expectedAppointment = new AppointmentBuilder(BALLET_RECITAL).build();
        // Contact expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BALLET_RECITAL + ADDRESS_DESC_BALLET_RECITAL
                + DATE_DESC_BALLET_RECITAL, new AddAppointmentCommand(expectedAppointment));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_PTM + NAME_DESC_BALLET_RECITAL + ADDRESS_DESC_BALLET_RECITAL
                + DATE_DESC_BALLET_RECITAL, new AddAppointmentCommand(expectedAppointment));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BALLET_RECITAL + ADDRESS_DESC_BALLET_RECITAL
                + DATE_DESC_BALLET_RECITAL, new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Appointment expectedAppointment = new AppointmentBuilder(BALLET_RECITAL).build();
        assertParseSuccess(parser, NAME_DESC_BALLET_RECITAL + ADDRESS_DESC_BALLET_RECITAL + DATE_DESC_BALLET_RECITAL,
                new AddAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_PTM + ADDRESS_DESC_PTM + DATE_DESC_PTM + CONTACT_DESC_2,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_PTM + VALID_ADDRESS_PTM + VALID_DATE_PTM + CONTACT_DESC_2,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_PTM + DATE_DESC_PTM + CONTACT_DESC_2,
                Name.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_PTM + INVALID_ADDRESS_DESC + DATE_DESC_PTM + CONTACT_DESC_2,
                Address.MESSAGE_CONSTRAINTS);

        // invalid date format
        assertParseFailure(parser, NAME_DESC_PTM + ADDRESS_DESC_PTM + INVALID_DATE_DESC + CONTACT_DESC_2,
                DateTime.MESSAGE_INVALID_FORMAT);

        // invalid contact
        // assertParseFailure(parser, NAME_DESC_PTM + ADDRESS_DESC_PTM + DATE_DESC_PTM + INVALID_CONTACT_DESC,
        //        MESSAGE_INVALID_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_PTM + ADDRESS_DESC_PTM + DATE_DESC_PTM
                + CONTACT_DESC_2, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
