package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DoneCommandParser implements Parser<DoneCommand> {
    @Override
    public DoneCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        try {
            Identifier identifier = ParserUtil.parseIdentifier(userInput);
            return new DoneCommand(identifier);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n"
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
