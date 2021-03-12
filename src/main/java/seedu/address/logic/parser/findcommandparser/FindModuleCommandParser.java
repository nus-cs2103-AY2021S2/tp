package seedu.address.logic.parser.findcommandparser;

import seedu.address.logic.commands.findcommand.FindModuleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.TitleContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

public class FindModuleCommandParser extends FindCommandParser implements Parser<FindModuleCommand> {

    public FindModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        if (earlyExitCondition(argumentMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argumentMultimap.getValue(PREFIX_MODULE).get().trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindModuleCommand.MESSAGE_USAGE));
        }

        String[] titleKeywords = trimmedArgs.split("\\s+");
        return new FindModuleCommand(new TitleContainsKeywordsPredicate(Arrays.asList(titleKeywords)));
    }

    private boolean earlyExitCondition(ArgumentMultimap argumentMultimap) {
        return !arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                || !argumentMultimap.getPreamble().isEmpty()
                || argumentMultimap.getValue(PREFIX_MODULE).isEmpty();
    }
}
