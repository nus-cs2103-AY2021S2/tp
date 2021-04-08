package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

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
            if (pe.getMessage().equals(ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS)
                    || pe.getMessage().equals(ParserUtil.MESSAGE_EMPTY_IDENTIFIER)) {
                throw new ParseException(pe.getMessage()
                        + DoneCommand.MESSAGE_USAGE);
            }
            throw new ParseException(pe.getMessage());
        }
    }
}
