package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCheeseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.predicates.CheeseAssignmentStatusPredicate;
import seedu.address.model.cheese.predicates.CheeseCheeseTypePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;

/**
 * Parses input arguments and creates a new FindCheeseCommand
 */
public class FindCheeseCommandParser implements Parser<FindCheeseCommand> {

    public static final String EMPTY_CHEESE_TYPE_MESSAGE = "Cheese type keywords must not be empty.";
    public static final String INVALID_STATUS_MESSAGE = "Assignment status must be either 'assigned' or 'unassigned'.";

    /**
     * Parses the given {@code String} of arguments in the context of FindCheeseCommand
     * and returns a FindCheeseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCheeseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT_STATUS, PREFIX_CHEESE_TYPE);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_ASSIGNMENT_STATUS, PREFIX_CHEESE_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCheeseCommand.MESSAGE_USAGE));
        }

        CompositeFieldPredicateBuilder<Cheese> pBuilder = new CompositeFieldPredicateBuilder<>();

        Optional<String> statusArg = argMultimap.getValue(PREFIX_ASSIGNMENT_STATUS);
        if (statusArg.isPresent()) {
            boolean status = ParserUtil.parseCheeseStatusKeyword(statusArg.get());
            pBuilder.compose(new CheeseAssignmentStatusPredicate(status));
        }

        Optional<String> cheeseArg = argMultimap.getValue(PREFIX_CHEESE_TYPE);
        if (cheeseArg.isPresent()) {
            List<String> cheeseTypeKeywords = ParserUtil.parseCheeseTypeKeywords(cheeseArg.get());
            pBuilder.compose(new CheeseCheeseTypePredicate(cheeseTypeKeywords));
        }

        return new FindCheeseCommand(pBuilder.build());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
