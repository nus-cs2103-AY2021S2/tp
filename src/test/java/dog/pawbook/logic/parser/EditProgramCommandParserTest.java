package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_PROGRAM1;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_PROGRAM2;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_PROGRAM1;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_PROGRAM2;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_DOGS;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_PUPPIES;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_DOGS;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_PUPPIES;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand;
import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;

public class EditProgramCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProgramCommand.MESSAGE_USAGE);

    private EditProgramCommandParser parser = new EditProgramCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_PROGRAM_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditProgramCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_PROGRAM1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_PROGRAM1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_SESSION_DESC, Session.MESSAGE_CONSTRAINTS); // invalid session
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid name followed by valid session
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + SESSION_DESC_PROGRAM1, Name.MESSAGE_CONSTRAINTS);

        // valid session followed by invalid session. The test case for invalid session followed by valid session
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + SESSION_DESC_PROGRAM1 + INVALID_SESSION_DESC, Session.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Owner} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_DOGS + TAG_DESC_PUPPIES + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_DOGS + TAG_EMPTY + TAG_DESC_PUPPIES, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_DOGS + TAG_DESC_PUPPIES, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SESSION_DESC + VALID_TAG_DOGS,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Integer targetId = ID_TWO;
        String userInput = targetId + TAG_DESC_DOGS + SESSION_DESC_PROGRAM1 + NAME_DESC_PROGRAM1 + TAG_DESC_PUPPIES;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM_A)
                .withSessions(VALID_SESSION_PROGRAM_A).withTags(VALID_TAG_DOGS, VALID_TAG_PUPPIES).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Integer targetId = ID_ONE;
        String userInput = targetId + SESSION_DESC_PROGRAM1 + NAME_DESC_PROGRAM2;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM_B)
                .withSessions(VALID_SESSION_PROGRAM_A).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Integer targetId = ID_THREE;
        String userInput = targetId + NAME_DESC_PROGRAM1;
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM_A).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sessions
        userInput = targetId + SESSION_DESC_PROGRAM1;
        descriptor = new EditProgramDescriptorBuilder().withSessions(VALID_SESSION_PROGRAM_A).build();
        expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetId + TAG_DESC_DOGS;
        descriptor = new EditProgramDescriptorBuilder().withTags(VALID_TAG_DOGS).build();
        expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Integer targetId = ID_ONE;
        String userInput = targetId + TAG_DESC_PUPPIES + TAG_DESC_DOGS + TAG_DESC_DOGS + TAG_DESC_PUPPIES
                + SESSION_DESC_PROGRAM2 + SESSION_DESC_PROGRAM2 + TAG_DESC_PUPPIES;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withSessions(VALID_SESSION_PROGRAM_B)
                .withTags(VALID_TAG_DOGS, VALID_TAG_PUPPIES).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Integer targetId = ID_THREE;
        String userInput = targetId + TAG_EMPTY;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withTags().build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
