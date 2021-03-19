package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import java.util.Arrays;

import seedu.address.logic.commands.findcommand.FindEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.DescriptionContainsKeywordsPredicate;

public class FindEventCommandParser extends FindCommandParser {

    public FindEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GENERAL_EVENT);

        if (earlyExitCondition(argumentMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argumentMultimap.getValue(PREFIX_GENERAL_EVENT).get().trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindEventCommand.MESSAGE_USAGE));
        }

        String[] titleKeywords = trimmedArgs.split("\\s+");
        return new FindEventCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(titleKeywords)));
    }

    private boolean earlyExitCondition(ArgumentMultimap argumentMultimap) {
        return !arePrefixesPresent(argumentMultimap, PREFIX_GENERAL_EVENT)
                || !argumentMultimap.getPreamble().isEmpty()
                || argumentMultimap.getValue(PREFIX_GENERAL_EVENT).isEmpty();
    }
}
