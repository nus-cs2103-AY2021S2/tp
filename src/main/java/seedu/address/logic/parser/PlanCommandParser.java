package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.PlanCommand;
import seedu.address.logic.commands.RemovePlanCommand;
import seedu.address.logic.parser.exceptions.InvalidIntegerException;
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
                PREFIX_INSURANCE, PREFIX_CLEAR);

        Prefix singlePrefix;
        try {
            singlePrefix = getSinglePrefix(argMultimap, PREFIX_INSURANCE, PREFIX_CLEAR);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE), pe);
        }
        assert singlePrefix != null : "Unexpected null value";

        if (argMultimap.getValue(PREFIX_INSURANCE).isEmpty() && argMultimap.getValue(PREFIX_CLEAR).isEmpty()) {
            throw new ParseException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (InvalidIntegerException pe) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE));
        }

        if (singlePrefix.equals(PREFIX_INSURANCE)) {
            InsurancePlan plan = ParserUtil.parsePlan(argMultimap.getValue(PREFIX_INSURANCE).get());
            return new AddPlanCommand(index, plan);
        } else {
            Index planIndex;
            try {
                planIndex = ParserUtil.parseRemovePlanIndex(argMultimap.getValue(PREFIX_CLEAR).get());
            } catch (ParseException pe) {
                throw new ParseException("The plan index provided is invalid.");
            }
            return new RemovePlanCommand(index, planIndex);
        }
    }

    /**
     * Returns the singular prefix contained in the given {@code ArgumentMultimap}.
     * @throws ParseException if more or less than 1 prefix is provided by user.
     */
    private static Prefix getSinglePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) throws ParseException {
        Prefix singlePrefix = null;
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                if (singlePrefix != null) {
                    throw new ParseException("More than 1 prefix provided.");
                }
                singlePrefix = prefix;
            }
        }
        if (singlePrefix == null) {
            throw new ParseException("No prefix provided. ");
        }
        return singlePrefix;
    }
}
