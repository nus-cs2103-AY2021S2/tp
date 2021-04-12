package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;

import seedu.address.logic.commands.AddDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.Description;
import seedu.address.model.date.Details;
import seedu.address.model.date.ImportantDate;

public class AddDateCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddDateCommand
     * and returns an AddDateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDateCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DETAILS);

        boolean isDescriptionPresent = argMultimap.getValue(PREFIX_DESCRIPTION).isPresent();
        boolean isTimePresent = argMultimap.getValue(PREFIX_DETAILS).isPresent();

        if (!isDescriptionPresent || !isTimePresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDateCommand.MESSAGE_USAGE));
        }

        Description descriptionKeyword = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Details detailsKeyword = ParserUtil.parseDetails(argMultimap.getValue(PREFIX_DETAILS).get());

        ImportantDate importantDate = new ImportantDate(descriptionKeyword, detailsKeyword);

        return new AddDateCommand(importantDate);
    }


}
