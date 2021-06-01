package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_UPDATED;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SYLPH;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_LEADER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_LEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGroupmates.SYLPH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateGroupmateCommand;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.testutil.GroupmateBuilder;

class UpdateGroupmateCommandParserTest {
    private UpdateGroupmateCommandParser parser = new UpdateGroupmateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = INDEX_FIRST;
        Index expectedGroupmateIndex = INDEX_FIRST;
        Groupmate expectedGroupmate = new GroupmateBuilder(SYLPH).withRoles(VALID_ROLE_LEADER).build();

        // all field appear
        UpdateGroupmateCommand.UpdateGroupmateDescriptor expectedUpdateGroupmateDescriptor1 =
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor();
        expectedUpdateGroupmateDescriptor1.setName(expectedGroupmate.getName());
        expectedUpdateGroupmateDescriptor1.setRoles(expectedGroupmate.getRoles());
        assertParseSuccess(parser, expectedProjectIndex.getOneBased() + " " + PREFIX_INDEX + " "
                        + expectedGroupmateIndex.getOneBased() + NAME_DESC_SYLPH + ROLE_DESC_LEADER,
                new UpdateGroupmateCommand(expectedProjectIndex, expectedGroupmateIndex,
                        expectedUpdateGroupmateDescriptor1)
        );

        // only update name
        UpdateGroupmateCommand.UpdateGroupmateDescriptor expectedUpdateGroupmateDescriptor2 =
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor();
        expectedUpdateGroupmateDescriptor2.setName(expectedGroupmate.getName());
        assertParseSuccess(parser, expectedProjectIndex.getOneBased() + " " + PREFIX_INDEX + " "
                        + expectedGroupmateIndex.getOneBased() + NAME_DESC_SYLPH,
                new UpdateGroupmateCommand(expectedProjectIndex, expectedGroupmateIndex,
                        expectedUpdateGroupmateDescriptor2)
        );

        // only update roles
        UpdateGroupmateCommand.UpdateGroupmateDescriptor expectedUpdateGroupmateDescriptor3 =
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor();
        expectedUpdateGroupmateDescriptor3.setRoles(expectedGroupmate.getRoles());
        assertParseSuccess(parser, expectedProjectIndex.getOneBased() + " " + PREFIX_INDEX + " "
                        + expectedGroupmateIndex.getOneBased() + ROLE_DESC_LEADER,
                new UpdateGroupmateCommand(expectedProjectIndex, expectedGroupmateIndex,
                        expectedUpdateGroupmateDescriptor3)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateGroupmateCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, PREFIX_INDEX + " " + INDEX_FIRST.getOneBased()
                        + NAME_DESC_SYLPH + ROLE_DESC_LEADER, expectedMessage);

        // missing index prefix
        assertParseFailure(parser, "" + INDEX_FIRST.getOneBased() + " " + INDEX_FIRST.getOneBased()
                + NAME_DESC_SYLPH + ROLE_DESC_LEADER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, "0 " + PREFIX_INDEX + INDEX_FIRST.getOneBased()
                + NAME_DESC_SYLPH + ROLE_DESC_LEADER, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid groupmate index
        assertParseFailure(parser, "" + INDEX_FIRST.getOneBased() + " " + PREFIX_INDEX + " 0"
                        + NAME_DESC_SYLPH + ROLE_DESC_LEADER, MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
    }

    @Test
    public void parse_notEdited_failure() {
        assertParseFailure(parser, "" + INDEX_FIRST.getOneBased() + " "
                + PREFIX_INDEX + INDEX_FIRST.getOneBased(), MESSAGE_NOT_UPDATED);
    }
}
