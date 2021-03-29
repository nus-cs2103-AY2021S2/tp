package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.ApptNameContainsKeywordsPredicate;

public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FindAppointmentCommand}
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        ArgumentMultimap argMultimaptest = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Optional<String> option = argMultimap.getValue(PREFIX_OPTION);
        if (option.isPresent()) {
            String unboxedOption = option.get();
            return parseFindOptions(new Option(unboxedOption), trimmedArgs);
        } else { // find by all fields
            return parseFindAll(trimmedArgs);
        }
    }

    /**
     * Parses other fields in find by options context
     * @param option {@code Option} to determine the option selected
     * @param trimmedArgs {@code trimmedArgs} for the rest of the args
     * @return {@code FindCommand}
     */
    public FindAppointmentCommand parseFindOptions(Option option, String trimmedArgs) throws ParseException {
        if (option.equals(OPTION_NAME)) { // find by name
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME);
            List<String> names = argMultimap.getAllValues(PREFIX_NAME);
            return new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(names));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses others in find by all context
     * @param trimmedArgs
     * @return
     */
    public FindAppointmentCommand parseFindAll(String trimmedArgs) throws ParseException {
        String[] nameKeywords = trimmedArgs.split("\\s+");
        assert nameKeywords.length > 0 : "FindCommand keywords are empty";
        return new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
