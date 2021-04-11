package seedu.plan.logic.parser;

import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.MasterPlanCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CurrentSemesterCommand
 */
public class MasterPlanCommandParser implements Parser<MasterPlanCommand> {
    private final Logger logger = LogsCenter.getLogger(MasterPlanCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CurrentSemesterCommand
     * and returns an CurrentSemesterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MasterPlanCommand parse(String args) throws ParseException {
        logger.info("----------------[PARSE][START]");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLAN_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            logger.info("----------------[PARSE][EXCEPTION]");
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    MasterPlanCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PLAN_NUMBER).get());
        logger.info("----------------[PARSE][END][" + index.getOneBased() + "]");
        return new MasterPlanCommand(index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
