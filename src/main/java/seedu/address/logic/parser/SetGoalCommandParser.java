package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetGoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetGoalCommandParser implements Parser<SetGoalCommand> {
    @Override
    public SetGoalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FREQUENCY);

        Index index;
        Optional<String> frequency;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            frequency = argMultimap.getValue(PREFIX_FREQUENCY);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGoalCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_FREQUENCY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGoalCommand.MESSAGE_USAGE));
        }

        return new SetGoalCommand(index, ParserUtil.parseFrequency(frequency.orElse("n")));
    }
}
