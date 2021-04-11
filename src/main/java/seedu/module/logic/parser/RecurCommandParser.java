package seedu.module.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_RECURRENCE;

import java.util.Optional;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.commons.exceptions.IllegalIntegerException;
import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.logic.commands.Command;
import seedu.module.logic.commands.RecurCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.task.Recurrence;


public class RecurCommandParser implements Parser<Command> {

    public static final String RECUR_EXCEPTION_MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RecurCommand.MESSAGE_USAGE);

    @Override
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RECURRENCE);

        Index index;
        RecurCommand recurCommand;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
            recurCommand = new RecurCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(RECUR_EXCEPTION_MESSAGE, ive);
        } catch (IllegalIntegerException iie) {
            throw new ParseException(iie.getMessage());
        }

        String recurValue;
        recurValue = argumentMultimap.getValue(PREFIX_RECURRENCE)
                .orElseThrow(() -> new ParseException(RECUR_EXCEPTION_MESSAGE));

        parseRecurrence(recurValue).ifPresent(recurCommand::setRecurrence);

        return recurCommand;
    }

    private Optional<OptionalField<Recurrence>> parseRecurrence(String recurStr) throws ParseException {
        assert recurStr != null;

        if (recurStr.equals("")) {
            return Optional.of(new OptionalField<>(null));
        } else {
            return Optional.of(new OptionalField<>(ParserUtil.parseRecurrence(recurStr)));
        }
    }
}
