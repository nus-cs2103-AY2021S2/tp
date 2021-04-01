package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_CHILD;
import static seedu.address.logic.parser.CliSyntax.OPTION_CONTACT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.predicate.ApptAddressContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptAnyContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptContactsContainKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptDateContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptNameContainsKeywordsPredicate;

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
        Optional<String> argsString = argMultimap.getValue(PREFIX_OPTION);
        if (argsString.isPresent()) { // find with options

            // split args into option and remaining optionArgs
            String unboxedArgsString = argsString.get();
            String[] optionArgsArray = unboxedArgsString.split("\\s+", 2);
            String option = optionArgsArray[0];
            String optionArgs = optionArgsArray[1];

            return parseFindOptions(option, optionArgs);
        } else { // find by all fields
            return parseFindAll(trimmedArgs);
        }
    }

    /**
     * Parses args in find by options context
     * @param option option to determine the option selected
     * @param optionArgs {@code optionArgs} for the rest of the args
     * @return {@code FindCommand}
     */
    public FindAppointmentCommand parseFindOptions(String option, String optionArgs) throws ParseException {
        List<String> keywords = Arrays.asList(optionArgs.split("\\s+"));
        switch (option) {
        case OPTION_NAME:  // find by name
            return new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(keywords));

        case OPTION_ADDRESS:  // find by address
            return new FindAppointmentCommand(new ApptAddressContainsKeywordsPredicate(keywords));
        case OPTION_DATE:  // find by date
            return new FindAppointmentCommand(new ApptDateContainsKeywordsPredicate(keywords));
        case OPTION_CONTACT:  // find by contacts
            return new FindAppointmentCommand(new ApptContactsContainKeywordsPredicate(keywords));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses args in find by all context
     * @param trimmedArgs
     * @return
     */
    public FindAppointmentCommand parseFindAll(String trimmedArgs) throws ParseException {
        String[] keywords = trimmedArgs.split("\\s+");
        assert keywords.length > 0 : "FindCommand keywords are empty";
        return new FindAppointmentCommand(new ApptAnyContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
