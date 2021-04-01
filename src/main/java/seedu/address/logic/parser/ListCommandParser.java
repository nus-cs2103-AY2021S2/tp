package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            return new ListCommand();
        } else {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);

            Optional<String> option = argMultimap.getValue(PREFIX_OPTION);

            if (option.isPresent()) {
                String unboxedOption = option.get();
                if (unboxedOption.equals(ListCommand.OPTION_FAV)) {
                    return new ListCommand(unboxedOption);
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_OPTION, unboxedOption));
                }
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
        }
    }
}
