package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.PlanCommand;
import seedu.address.logic.commands.RemovePlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurance.InsurancePlan;

/**
 * Parses input arguments and creates a new PlanCommand object
 */
public class PlanCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns a PlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_INSURANCE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PlanCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_INSURANCE).isEmpty()) {
            throw new ParseException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }

        InsurancePlan plan = ParserUtil.parsePlan(argMultimap.getValue(PREFIX_INSURANCE).get());
        if (plan != null) {
            return new AddPlanCommand(index, plan);
        }
        Index planIndex = ParserUtil.parseRemovePlanIndex(argMultimap.getValue(PREFIX_INSURANCE).get());
        return new RemovePlanCommand(index, planIndex);
    }

}
