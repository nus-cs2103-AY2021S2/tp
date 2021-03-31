package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.update.UpdateCancelCommand;
import seedu.address.logic.commands.update.UpdateCommand;
import seedu.address.logic.commands.update.UpdateNewCommand;
import seedu.address.logic.commands.update.UpdateProceedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.status.Offer;

public class UpdateCommandParser implements Parser<UpdateCommand> {

    private static final String UPDATE_STRING_REGEX = "(\\s+([1-9]\\d*){1}\\s+u/((new\\s+(?<amount>"
            + "[^\\s]+))|(proceed)|(cancel))\\s*){1}";
    private static final Pattern UPDATE_STRING_FORMAT = Pattern.compile(UPDATE_STRING_REGEX,
            Pattern.CASE_INSENSITIVE);

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns an UpdateCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_UPDATE);

        Matcher matcher = UPDATE_STRING_FORMAT.matcher(args);

        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_UPDATE).isPresent()) {
            String command = argMultimap.getValue(PREFIX_UPDATE).get();
            if (command.contains("new")) {
                Offer offer;
                try {
                    offer = new Offer(matcher.group("amount"));
                } catch (IllegalArgumentException e) {
                    throw new ParseException(Offer.MESSAGE_CONSTRAINTS);
                }
                return new UpdateNewCommand(index, offer);
            } else if (command.contains("proceed")) {
                return new UpdateProceedCommand(index);
            } else if (command.contains("cancel")) {
                return new UpdateCancelCommand(index);
            }
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
    }
}
