package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.CLEAN_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOOKED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_RESERVED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLEAN_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_RESERVED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditResidenceDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, ADDRESS_DESC_RESIDENCE1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_RESIDENCE1, ParserUtil.MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_RESIDENCE1, ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ResidenceName.MESSAGE_CONSTRAINTS);
        // invalid address
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, ResidenceAddress.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Residence} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_RESERVED + TAG_DESC_BOOKED + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_RESERVED + TAG_EMPTY + TAG_DESC_BOOKED, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC + VALID_ADDRESS_RESIDENCE1,
                ResidenceName.MESSAGE_CONSTRAINTS);
    }

    @Test
     public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_RESIDENCE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED;
        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE1).withCleanStatusTag(VALID_CLEAN_TAG)
                .withTags(VALID_TAG_RESERVED).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RESIDENCE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1;
        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder()
                .withName(VALID_NAME_RESIDENCE1).withAddress(VALID_ADDRESS_RESIDENCE1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_FIRST_RESIDENCE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_RESIDENCE1;
        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder()
                .withName(VALID_NAME_RESIDENCE1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_RESIDENCE1;
        descriptor = new EditResidenceDescriptorBuilder().withAddress(VALID_ADDRESS_RESIDENCE1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Clean Status Tag
        userInput = targetIndex.getOneBased() + CLEAN_STATUS_DESC;
        descriptor = new EditResidenceDescriptorBuilder().withCleanStatusTag(VALID_CLEAN_TAG).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_RESERVED;
        descriptor = new EditResidenceDescriptorBuilder().withTags(VALID_TAG_RESERVED).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RESIDENCE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED + NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED + NAME_DESC_RESIDENCE1 + ADDRESS_DESC_RESIDENCE1
                + CLEAN_STATUS_DESC + TAG_DESC_RESERVED + TAG_DESC_REPAIR;

        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withAddress(VALID_ADDRESS_RESIDENCE1).withCleanStatusTag(VALID_CLEAN_TAG)
                .withTags(VALID_TAG_RESERVED, VALID_TAG_REPAIR)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RESIDENCE;
        String userInput = targetIndex.getOneBased() + INVALID_ADDRESS_DESC + ADDRESS_DESC_RESIDENCE1;
        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder()
                .withAddress(VALID_ADDRESS_RESIDENCE1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_RESIDENCE1 + CLEAN_STATUS_DESC + ADDRESS_DESC_RESIDENCE1
                + TAG_DESC_RESERVED;
        descriptor = new EditResidenceDescriptorBuilder().withAddress(VALID_ADDRESS_RESIDENCE1)
                .withCleanStatusTag(VALID_CLEAN_TAG)
                .withTags(VALID_TAG_RESERVED).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_FIRST_RESIDENCE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
