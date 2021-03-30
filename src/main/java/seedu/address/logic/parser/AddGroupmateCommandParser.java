package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGroupmateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class AddGroupmateCommandParser implements Parser<AddGroupmateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddContactToCommand
     * and returns an AddContactToCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGroupmateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupmateCommand.MESSAGE_USAGE));
        }

        Index projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        Name name = ParserUtil.parseGroupmateName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Role> roleList = ParserUtil.parseRoles(argMultimap.getAllValues(PREFIX_ROLE));

        Groupmate contact = new Groupmate(name, roleList);

        return new AddGroupmateCommand(projectIndex, contact);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
