package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_EMAIL_FORMAT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.commands.PromptPersonEmailCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Email;

public class PromptPersonEmailParser implements Parser<PromptPersonEmailCommand> {

    /**
     * Parses the given {@code String} of arguments for email in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonEmailCommand parse(String args) throws ParseException {
        Email email;

        if (!Email.isValidEmail(args.trim())) {
            throw new ParseException(MESSAGE_INVALID_EMAIL_FORMAT + PROMPT_MESSAGE_TRY_AGAIN);
        }

        email = ParserUtil.parseEmail(args);
        return new PromptPersonEmailCommand(email);
    }
}
