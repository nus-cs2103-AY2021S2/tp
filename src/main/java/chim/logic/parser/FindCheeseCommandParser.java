package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_ASSIGNMENT_STATUS;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;

import java.util.Optional;
import java.util.stream.Stream;

import chim.logic.commands.FindCheeseCommand;
import chim.logic.parser.exceptions.ParseException;
import chim.model.cheese.Cheese;
import chim.model.util.predicate.CompositeFieldPredicateBuilder;

/**
 * Parses input arguments and creates a new FindCheeseCommand
 */
public class FindCheeseCommandParser implements Parser<FindCheeseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of FindCheeseCommand
     * and returns a FindCheeseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCheeseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_ASSIGNMENT_STATUS,
                PREFIX_CHEESE_TYPE);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_CHEESE_ASSIGNMENT_STATUS, PREFIX_CHEESE_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCheeseCommand.MESSAGE_USAGE));
        }

        CompositeFieldPredicateBuilder<Cheese> pBuilder = new CompositeFieldPredicateBuilder<>();

        Optional<String> statusArg = argMultimap.getValue(PREFIX_CHEESE_ASSIGNMENT_STATUS);
        if (statusArg.isPresent()) {
            pBuilder.compose(ParserUtil.parseCheeseAssignmentStatusKeyword(statusArg.get()));
        }

        Optional<String> cheeseTypeArg = argMultimap.getValue(PREFIX_CHEESE_TYPE);
        if (cheeseTypeArg.isPresent()) {
            pBuilder.compose(ParserUtil.parseCheeseCheeseTypeKeywords(cheeseTypeArg.get()));
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
