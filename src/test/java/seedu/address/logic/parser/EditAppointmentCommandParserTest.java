package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.CHILD_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CHILD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PTM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_PTM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHILD_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PTM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PTM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.Address;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.TypicalContacts;

class EditAppointmentCommandParserTest {
    private static final EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditAppointmentCommand.MESSAGE_USAGE);

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_PTM, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // no fields specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Model modelStub = new ModelManager();
        modelStub.setAddressBook(TypicalContacts.getTypicalAddressBook());
        ParserUtil.setModel(modelStub);

        // name
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PTM;
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withName(VALID_NAME_PTM).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_PTM;
        descriptor = new EditAppointmentDescriptorBuilder().withAddress(VALID_ADDRESS_PTM).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_PTM;
        descriptor = new EditAppointmentDescriptorBuilder().withDateTime(VALID_DATE_PTM).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contacts
        userInput = targetIndex.getOneBased() + CONTACT_DESC_1;
        descriptor = new EditAppointmentDescriptorBuilder().withContacts(TypicalContacts.ALICE).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // child tags
        userInput = targetIndex.getOneBased() + CHILD_DESC_ALEX;
        descriptor = new EditAppointmentDescriptorBuilder().withTags(VALID_CHILD_ALEX).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        Model modelStub = new ModelManager();
        modelStub.setAddressBook(TypicalContacts.getTypicalAddressBook());
        ParserUtil.setModel(modelStub);
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, DateTime.MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + INVALID_CHILD_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_CONTACT_DESC, MESSAGE_INVALID_INDEX);
    }
}
