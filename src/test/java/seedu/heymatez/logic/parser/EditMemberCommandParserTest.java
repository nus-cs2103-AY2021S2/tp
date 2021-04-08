package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_PERSON_EMAIL;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_PERSON_PHONE;
import static seedu.heymatez.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_NEW_NAME_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.NEW_NAME_DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.EditMemberCommand;
import seedu.heymatez.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.person.Email;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Phone;
import seedu.heymatez.testutil.EditMemberDescriptorBuilder;

/**
 * Contains unit tests for {@code EditMemberCommandParser}.
 */
public class EditMemberCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMemberCommand.MESSAGE_USAGE);

    private EditMemberCommandParser parser = new EditMemberCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no name specified
        assertParseFailure(parser, "p/93451122", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, VALID_NAME_AMY, EditMemberCommand.MESSAGE_NOT_EDITED);

        // no name and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "N@ME", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "h/", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NEW_NAME_DESC,
                MESSAGE_INVALID_PERSON_DISPLAYED_NAME + Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC,
                MESSAGE_INVALID_PERSON_PHONE + Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC,
                MESSAGE_INVALID_PERSON_EMAIL + Email.MESSAGE_CONSTRAINTS); // invalid email

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                MESSAGE_INVALID_PERSON_PHONE + Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                MESSAGE_INVALID_PERSON_PHONE + Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NEW_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                MESSAGE_INVALID_PERSON_DISPLAYED_NAME + Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Name targetName = ParserUtil.parseName(VALID_NAME_BOB);
        String userInput = VALID_NAME_BOB + NEW_NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditMemberCommand expectedCommand = new EditMemberCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() throws ParseException {
        Name targetName = ParserUtil.parseName(VALID_NAME_AMY);
        String userInput = VALID_NAME_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditMemberCommand expectedCommand = new EditMemberCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException {
        // name
        Name targetName = ParserUtil.parseName(VALID_NAME_BOB);
        String userInput = VALID_NAME_BOB + NEW_NAME_DESC_AMY;
        EditMemberCommand.EditMemberDescriptor descriptor =
                new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditMemberCommand expectedCommand = new EditMemberCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_NAME_BOB + PHONE_DESC_AMY;
        descriptor = new EditMemberDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditMemberCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = VALID_NAME_BOB + EMAIL_DESC_AMY;
        descriptor = new EditMemberDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditMemberCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() throws ParseException {
        Name targetName = ParserUtil.parseName(VALID_NAME_AMY);
        String userInput = VALID_NAME_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        EditMemberCommand.EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        EditMemberCommand expectedCommand = new EditMemberCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException {
        // no other valid values specified
        Name targetName = ParserUtil.parseName(VALID_NAME_AMY);
        String userInput = VALID_NAME_AMY + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditMemberCommand.EditMemberDescriptor descriptor =
                new EditMemberDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditMemberCommand expectedCommand = new EditMemberCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = VALID_NAME_AMY + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditMemberDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        expectedCommand = new EditMemberCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
