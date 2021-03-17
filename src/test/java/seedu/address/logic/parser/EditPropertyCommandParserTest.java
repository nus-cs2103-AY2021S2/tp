package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_POSTAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.POSTAL_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MAYFAIR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE);

    private EditPropertyCommandParser parser = new EditPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MAYFAIR, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPropertyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_MAYFAIR, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_MAYFAIR, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_PROPERTY_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid postalcode
        assertParseFailure(parser, "1" + INVALID_PROPERTY_POSTAL_DESC, PostalCode.MESSAGE_CONSTRAINTS);
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_PROPERTY_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);
        // invalid address
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        // invalid type
        assertParseFailure(parser, "1" + INVALID_PROPERTY_TYPE_DESC, Type.MESSAGE_CONSTRAINTS);

        // invalid postalcode followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_PROPERTY_POSTAL_DESC + DEADLINE_DESC_MAYFAIR,
                PostalCode.MESSAGE_CONSTRAINTS);

        // valid postalcode followed by invalid postalcode. The test case for invalid postalcode
        // followed by valid postalcode
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + POSTAL_DESC_MAYFAIR + INVALID_PROPERTY_POSTAL_DESC,
                PostalCode.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PROPERTY_NAME_DESC
                        + INVALID_PROPERTY_DEADLINE_DESC + VALID_ADDRESS_MAYFAIR + VALID_POSTAL_MAYFAIR,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_MAYFAIR + TYPE_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + NAME_DESC_MAYFAIR;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_MAYFAIR)
                .withPostalCode(VALID_POSTAL_MAYFAIR).withDeadline(VALID_DEADLINE_MAYFAIR)
                .withAddress(VALID_ADDRESS_MAYFAIR)
                .withType(VALID_TYPE_MAYFAIR).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withPostalCode(VALID_POSTAL_MAYFAIR)
                .withDeadline(VALID_DEADLINE_MAYFAIR).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_MAYFAIR;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_MAYFAIR).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // postalcode
        userInput = targetIndex.getOneBased() + POSTAL_DESC_MAYFAIR;
        descriptor = new EditPropertyDescriptorBuilder().withPostalCode(VALID_POSTAL_MAYFAIR).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_MAYFAIR;
        descriptor = new EditPropertyDescriptorBuilder().withDeadline(VALID_DEADLINE_MAYFAIR).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_MAYFAIR;
        descriptor = new EditPropertyDescriptorBuilder().withAddress(VALID_ADDRESS_MAYFAIR).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // type
        userInput = targetIndex.getOneBased() + TYPE_DESC_MAYFAIR;
        descriptor = new EditPropertyDescriptorBuilder().withType(VALID_TYPE_MAYFAIR).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR
                + TYPE_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR + TYPE_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + ADDRESS_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + TYPE_DESC_BURGHLEY_DRIVE;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withPostalCode(VALID_POSTAL_MAYFAIR)
                .withDeadline(VALID_DEADLINE_BURGHLEY_DRIVE).withAddress(VALID_ADDRESS_BURGHLEY_DRIVE)
                .withType(VALID_TYPE_BURGHLEY_DRIVE)
                .build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PROPERTY_POSTAL_DESC + POSTAL_DESC_MAYFAIR;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPostalCode(VALID_POSTAL_MAYFAIR).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_BURGHLEY_DRIVE
                + INVALID_PROPERTY_POSTAL_DESC + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_MAYFAIR;
        descriptor = new EditPropertyDescriptorBuilder().withPostalCode(VALID_POSTAL_MAYFAIR)
                .withDeadline(VALID_DEADLINE_BURGHLEY_DRIVE)
                .withAddress(VALID_ADDRESS_BURGHLEY_DRIVE).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
