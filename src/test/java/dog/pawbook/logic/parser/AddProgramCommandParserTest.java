//@@author branzuelajohn
package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_SESSION_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.NAME_DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static dog.pawbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.SESSION_DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_PUPPIES;
import static dog.pawbook.logic.commands.CommandTestUtil.TAG_DESC_QUIET;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_PUPPIES;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalEntities.POTTY_TRAINING;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.AddProgramCommand;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.ProgramBuilder;

public class AddProgramCommandParserTest {
    private AddProgramCommandParser parser = new AddProgramCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Program expectedProgram = new ProgramBuilder(POTTY_TRAINING).withSessions(VALID_SESSION_POTTY_TRAINING)
                .withTags(VALID_TAG_PUPPIES).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_POTTY_TRAINING + SESSION_DESC_POTTY_TRAINING
                + TAG_DESC_PUPPIES, new AddProgramCommand(expectedProgram));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_OBEDIENCE_TRAINING + NAME_DESC_POTTY_TRAINING
                + SESSION_DESC_POTTY_TRAINING + TAG_DESC_PUPPIES, new AddProgramCommand(expectedProgram));

        // multiple sessions - all accepted
        Program expectedProgramMultipleSessions = new ProgramBuilder(POTTY_TRAINING)
                .withSessions(VALID_SESSION_POTTY_TRAINING, VALID_SESSION_OBEDIENCE_TRAINING)
                .build();
        assertParseSuccess(parser, NAME_DESC_OBEDIENCE_TRAINING + NAME_DESC_POTTY_TRAINING
                + SESSION_DESC_POTTY_TRAINING + SESSION_DESC_OBEDIENCE_TRAINING
                + TAG_DESC_PUPPIES, new AddProgramCommand(expectedProgramMultipleSessions));
        // multiple tags - all accepted
        Program expectedProgramMultipleTags = new ProgramBuilder(POTTY_TRAINING)
                .withTags(VALID_TAG_QUIET, VALID_TAG_PUPPIES)
                .build();
        assertParseSuccess(parser, NAME_DESC_POTTY_TRAINING + SESSION_DESC_POTTY_TRAINING
                + TAG_DESC_QUIET + TAG_DESC_PUPPIES, new AddProgramCommand(expectedProgramMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Program expectedProgram = new ProgramBuilder(POTTY_TRAINING).withTags().build();
        assertParseSuccess(parser, NAME_DESC_POTTY_TRAINING + SESSION_DESC_POTTY_TRAINING,
                new AddProgramCommand(expectedProgram));
        // zero sessions
        expectedProgram = new ProgramBuilder(POTTY_TRAINING).withSessions().build();
        assertParseSuccess(parser, NAME_DESC_POTTY_TRAINING + TAG_DESC_PUPPIES,
                new AddProgramCommand(expectedProgram));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProgramCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_POTTY_TRAINING + SESSION_DESC_POTTY_TRAINING,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SESSION_DESC_POTTY_TRAINING
                + TAG_DESC_PUPPIES, Name.MESSAGE_CONSTRAINTS);

        // invalid sessions
        assertParseFailure(parser, NAME_DESC_POTTY_TRAINING + INVALID_SESSION_DESC
                + TAG_DESC_PUPPIES, Session.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, NAME_DESC_POTTY_TRAINING + SESSION_DESC_POTTY_TRAINING
                + INVALID_TAG_DESC + VALID_TAG_PUPPIES, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SESSION_DESC_POTTY_TRAINING + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_POTTY_TRAINING
                + SESSION_DESC_POTTY_TRAINING + TAG_DESC_PUPPIES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProgramCommand.MESSAGE_USAGE));
    }
}
