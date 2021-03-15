package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;

import static seedu.address.logic.parser.CliSyntax.*;

public class DeleteSessionCommandParser implements Parser<DeleteSessionCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSessionCommand
     * and returns a DeleteSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new DeleteSessionCommand(name, index);
    }
}
