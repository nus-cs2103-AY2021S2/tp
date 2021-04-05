package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_EMAIL_FORMAT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.commands.PromptBookingEmailCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Email;

public class PromptEmailParser implements Parser<PromptBookingEmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the email
     * and returns an Email object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptBookingEmailCommand parse(String args) throws ParseException {
        if (!Email.isValidEmail(args)) {
            throw new ParseException(MESSAGE_INVALID_EMAIL_FORMAT + PROMPT_MESSAGE_TRY_AGAIN);
        }

        return new PromptBookingEmailCommand(new Email(args));
    }
}
