package seedu.address.logic.parser;

import static seedu.address.logic.commands.FilterCommand.MESSAGE_FAILURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.validatePrefixEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.DisplayFilterPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    public static final String FORCE_PREAMBLE = " ";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand and returns
     * an FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        assert args != null;

        if (args.trim().isEmpty()) {
            return new FilterCommand(new DisplayFilterPredicate());
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(FORCE_PREAMBLE + args.trim(), PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_COMPANY, PREFIX_JOB_TITLE, PREFIX_TAG,
                        PREFIX_REMARK);

        if (!argMultimap.getPreamble().isEmpty()) {
            final String invalidPrefix = args.substring(args.indexOf(argMultimap.getPreamble()));
            throw new ParseException(String.format(MESSAGE_FAILURE, invalidPrefix) + "\n"
                    + FilterCommand.MESSAGE_USAGE);
        }

        try {
            List<String> validFlags = validateFlags(argMultimap);
            return new FilterCommand(new DisplayFilterPredicate(argMultimap), validFlags);
        } catch (ParseException pe) {
            final String invalidPrefix = args.substring(args.indexOf(pe.getMessage()));
            throw new ParseException(String.format(MESSAGE_FAILURE, invalidPrefix) + "\n"
                    + FilterCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Validates prefixes provided by a user.
     *
     * @param argMultimap Arguments of filter command.
     * @return List of valid parsed prefixes.
     * @throws ParseException if a given prefix is invalid.
     */
    private List<String> validateFlags(ArgumentMultimap argMultimap) throws ParseException {
        List<String> validFlags = new ArrayList<>();
        for (Prefix prefix : Arrays
                .asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_COMPANY,
                        PREFIX_JOB_TITLE, PREFIX_TAG, PREFIX_REMARK)) {
            if (validatePrefixEmpty(prefix, argMultimap)) {
                validFlags.add(prefix.getPrefix());
            }
        }
        return validFlags;
    }

    @Override
    public boolean isValidCommandToAlias(String userInput) {
        try {
            parse(userInput);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
