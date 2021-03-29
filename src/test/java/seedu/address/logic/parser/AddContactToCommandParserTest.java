package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_STANDALONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ROXY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SYLPH;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_LEADER;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_MAGICIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_LEADER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_MAGICIAN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalGroupmates.SYLPH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactToCommand;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;
import seedu.address.testutil.GroupmateBuilder;

public class AddContactToCommandParserTest {
    private AddContactToCommandParser parser = new AddContactToCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Groupmate expectedContact = new GroupmateBuilder(SYLPH).withRoles(VALID_ROLE_LEADER).build();

        // all field appear once
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_SYLPH + ROLE_DESC_LEADER,
                new AddContactToCommand(expectedProjectIndex, expectedContact)
        );

        // multiple names - last name accepted
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_ROXY + NAME_DESC_SYLPH + ROLE_DESC_LEADER,
                new AddContactToCommand(expectedProjectIndex, expectedContact));

        // multiple roles - all accepted
        Groupmate expectedGroupmateMultipleTags = new GroupmateBuilder(SYLPH)
                .withRoles(VALID_ROLE_MAGICIAN, VALID_ROLE_LEADER)
                .build();
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + NAME_DESC_SYLPH + ROLE_DESC_LEADER
                        + ROLE_DESC_MAGICIAN,
                new AddContactToCommand(expectedProjectIndex, expectedGroupmateMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactToCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, NAME_DESC_SYLPH + ROLE_DESC_LEADER,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + ROLE_DESC_LEADER + ROLE_DESC_MAGICIAN,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, INVALID_INDEX_STANDALONE + NAME_DESC_SYLPH + ROLE_DESC_LEADER,
                MESSAGE_INVALID_INDEX);

        // invalid name
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_NAME_DESC + ROLE_DESC_LEADER,
                Name.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, INDEX_STANDALONE_ONE + NAME_DESC_SYLPH + INVALID_ROLE_DESC
                + ROLE_DESC_MAGICIAN, Role.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_NAME_DESC + INVALID_ROLE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }
}
