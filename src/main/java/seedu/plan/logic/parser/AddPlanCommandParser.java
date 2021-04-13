package seedu.plan.logic.parser;

import java.util.Set;
import java.util.stream.Stream;

import seedu.plan.commons.core.Messages;
import seedu.plan.logic.commands.AddPlanCommand;
import seedu.plan.logic.parser.exceptions.ParseException;
import seedu.plan.model.plan.Description;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPlanCommand object
 */
public class AddPlanCommandParser implements Parser<AddPlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPlanCommand
     * and returns an AddPlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPlanCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPlanCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parsePlan(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        Plan plan = new Plan(description, tagList);
        return new AddPlanCommand(plan);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
