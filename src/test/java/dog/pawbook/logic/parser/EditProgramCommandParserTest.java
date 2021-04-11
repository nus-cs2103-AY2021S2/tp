package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_ZERO_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_DOGS;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_PUPPIES;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
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

    private final EditProgramCommandParser parser = new EditProgramCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no id specified
        assertParseFailure(parser, VALID_NAME_OBEDIENCE_TRAINING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, String.valueOf(ID_ONE), EditProgramCommand.MESSAGE_NOT_EDITED);

        // no id and no field specified
        assertParseFailure(parser, EMPTY_STRING, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative id
        assertParseFailure(parser, INVALID_NEGATIVE_ID_STRING + NAME_DESC_OBEDIENCE_TRAINING, MESSAGE_INVALID_FORMAT);

        // zero id
        assertParseFailure(parser, INVALID_ZERO_ID_STRING + NAME_DESC_OBEDIENCE_TRAINING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, ID_ONE + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, ID_ONE + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid session
        assertParseFailure(parser, ID_ONE + INVALID_SESSION_DESC, Session.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ID_ONE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid session
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC + SESSION_DESC_OBEDIENCE_TRAINING,
                Name.MESSAGE_CONSTRAINTS);

        // valid session followed by invalid session. The test case for invalid session followed by valid session
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, ID_ONE + SESSION_DESC_OBEDIENCE_TRAINING + INVALID_SESSION_DESC,
                Session.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Program} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, ID_ONE + TAG_DESC_ALL + TAG_DESC_PUPPIES + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_DESC_ALL + TAG_EMPTY + TAG_DESC_PUPPIES, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_ONE + TAG_EMPTY + TAG_DESC_ALL + TAG_DESC_PUPPIES, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, ID_ONE + INVALID_NAME_DESC + INVALID_SESSION_DESC + VALID_TAG_ALL,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        int targetId = ID_TWO;
        String userInput = targetId + TAG_DESC_ALL + SESSION_DESC_OBEDIENCE_TRAINING
                + NAME_DESC_OBEDIENCE_TRAINING + TAG_DESC_PUPPIES;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_OBEDIENCE_TRAINING)
                .withSessions(VALID_SESSION_OBEDIENCE_TRAINING).withTags(VALID_TAG_ALL, VALID_TAG_PUPPIES).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        int targetId = ID_ONE;
        String userInput = targetId + SESSION_DESC_OBEDIENCE_TRAINING + NAME_DESC_POTTY_TRAINING;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_POTTY_TRAINING)
                .withSessions(VALID_SESSION_OBEDIENCE_TRAINING).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        int targetId = ID_THREE;
        String userInput = targetId + NAME_DESC_OBEDIENCE_TRAINING;
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withName(VALID_NAME_OBEDIENCE_TRAINING).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sessions
        userInput = targetId + SESSION_DESC_OBEDIENCE_TRAINING;
        descriptor = new EditProgramDescriptorBuilder().withSessions(VALID_SESSION_OBEDIENCE_TRAINING).build();
        expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetId + TAG_DESC_ALL;
        descriptor = new EditProgramDescriptorBuilder().withTags(VALID_TAG_ALL).build();
        expectedCommand = new EditProgramCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedNames_acceptsLast() {
        int targetId = ID_ONE;
        String userInput = targetId + NAME_DESC_OBEDIENCE_TRAINING + NAME_DESC_POTTY_TRAINING;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withName(VALID_NAME_POTTY_TRAINING).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedTags_acceptsUnique() {
        int targetId = ID_ONE;
        String userInput = targetId + TAG_DESC_DOGS + TAG_DESC_PUPPIES + TAG_DESC_ALL + TAG_DESC_ALL
                + TAG_DESC_PUPPIES + TAG_DESC_ALL + TAG_DESC_DOGS;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withTags(VALID_TAG_ALL, VALID_TAG_PUPPIES, VALID_TAG_DOGS).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedSessions_acceptsUnique() {
        int targetId = ID_ONE;
        String userInput = targetId + SESSION_DESC_OBEDIENCE_TRAINING + SESSION_DESC_POTTY_TRAINING
                + SESSION_DESC_OBEDIENCE_TRAINING + SESSION_DESC_POTTY_TRAINING;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withSessions(VALID_SESSION_POTTY_TRAINING, VALID_SESSION_OBEDIENCE_TRAINING).build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetSessions_success() {
        int targetId = ID_THREE;
        String userInput = targetId + SESSION_EMPTY;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withSessions().build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        int targetId = ID_THREE;
        String userInput = targetId + TAG_EMPTY;

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withTags().build();
        EditProgramCommand expectedCommand = new EditProgramCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
