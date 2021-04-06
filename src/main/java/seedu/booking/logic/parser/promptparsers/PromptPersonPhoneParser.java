package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptPersonPhoneCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Phone;

public class PromptPersonPhoneParser implements Parser<PromptPersonPhoneCommand> {
    /**
     * Parses the given {@code String} of arguments for phone in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonPhoneCommand parse(String args) throws ParseException {
        Phone phone;
        phone = ParserUtil.parsePhone(args);

        return new PromptPersonPhoneCommand(phone);
    }
}
