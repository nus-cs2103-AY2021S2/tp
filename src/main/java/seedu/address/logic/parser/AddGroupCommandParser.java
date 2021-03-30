package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class AddGroupCommandParser implements Parser<AddGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddGroupCommand
     * and returns an AddGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PERSONS);

        List<Index> indexes;
        Name name;

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PERSONS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
        }


        name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        indexes = ParserUtil.parseIndexes(argMultimap.getValue(PREFIX_PERSONS).get());

        return new AddGroupCommand(indexes, name);
    }
}
