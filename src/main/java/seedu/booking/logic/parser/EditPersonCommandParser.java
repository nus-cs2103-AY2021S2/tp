package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_ORIGINAL_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.EditPersonCommand;
import seedu.booking.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Email;

/**
 * Parses input arguments and creates a new EditPersonCommand object.
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        System.out.println(args);
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORIGINAL_EMAIL, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        Email email;
        if (!arePrefixesPresent(argMultimap, PREFIX_ORIGINAL_EMAIL)
                || argMultimap.getValue(PREFIX_ORIGINAL_EMAIL).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE));
        }

        try {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_ORIGINAL_EMAIL).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPersonCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(email, editPersonDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
