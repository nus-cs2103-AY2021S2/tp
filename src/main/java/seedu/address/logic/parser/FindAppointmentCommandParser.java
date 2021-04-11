package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_ADDRESS_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_CHILD_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_CONTACT_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_DATE_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_FIND_APPOINTMENT_OPTION;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_NAME_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_USAGE;
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
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.predicate.ApptAddressContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptAnyContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptContactsContainKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptDateContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptNameContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptTagsContainKeywordsPredicate;

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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        if (trimmedArgs.contains(PREFIX_OPTION.getPrefix())) { // find with options
            // get everything after PREFIX_OPTION
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
            Optional<String> argsString = argMultimap.getValue(PREFIX_OPTION);
            String preamble = argMultimap.getPreamble();
            if (!argsString.isPresent() || !preamble.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }
            String unboxedArgsString = argsString.get();
            if (unboxedArgsString.trim().isEmpty()) { // option prefix present but option not present
                throw new ParseException(
                        String.format(MESSAGE_MISSING_OPTION, MESSAGE_MISSING_FIND_APPOINTMENT_OPTION)
                );
            }
            // split args into option and remaining optionArgs
            String[] optionArgsArray = unboxedArgsString.split("\\s+", 2);
            String option = optionArgsArray[0];
            if (optionArgsArray.length == 1) { //if no option args provided
                handleMissingFindAppointmentOptionsArgsExceptions(option);
            }
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
     * @return {@code FindAppointmentCommand}
     */
    private FindAppointmentCommand parseFindOptions(String option, String optionArgs) throws ParseException {
        List<String> keywords = Arrays.asList(optionArgs.split("\\s+"));
        if (keywords.size() == 0) {
            handleMissingFindAppointmentOptionsArgsExceptions(option);
        }
        switch (option) {
        case OPTION_NAME: // find by name
            return new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(keywords));
        case OPTION_CHILD: //find by child
            return new FindAppointmentCommand(new ApptTagsContainKeywordsPredicate(keywords));
        case OPTION_ADDRESS: // find by address
            return new FindAppointmentCommand(new ApptAddressContainsKeywordsPredicate(keywords));
        case OPTION_DATE: // find by date
            return new FindAppointmentCommand(new ApptDateContainsKeywordsPredicate(keywords));
        case OPTION_CONTACT: // find by contact
            return new FindAppointmentCommand(new ApptContactsContainKeywordsPredicate(keywords));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));
        }
    }

    /**
     * Handles exceptions
     * @param option
     * @throws ParseException
     */
    private void handleMissingFindAppointmentOptionsArgsExceptions(String option) throws ParseException {

        switch (option) {
        case OPTION_NAME: // find by name
            throw new ParseException(
                    String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_NAME_ARGS)
            );
        case OPTION_CHILD:
            throw new ParseException(
                    String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_CHILD_ARGS)
            );
        case OPTION_ADDRESS: // find by address
            throw new ParseException(
                    String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_ADDRESS_ARGS)
            );
        case OPTION_DATE: // find by date
            throw new ParseException(
                    String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_DATE_ARGS)
            );
        case OPTION_CONTACT: // find by contacts
            throw new ParseException(
                    String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_CONTACT_ARGS)
            );
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));
        }
    }

    /**
     * Parses args in find by all context
     * @param trimmedArgs
     * @return
     */
    private FindAppointmentCommand parseFindAll(String trimmedArgs) throws ParseException {
        String[] keywords = trimmedArgs.split("\\s+");
        assert keywords.length > 0 : "FindAppointmentCommand keywords are empty";
        return new FindAppointmentCommand(new ApptAnyContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
