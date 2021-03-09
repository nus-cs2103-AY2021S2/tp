package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;

import java.time.LocalDateTime;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Exam;

public class AddExamCommandParser extends AddCommandParser implements Parser<AddExamCommand> {

    @Override
    public AddExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXAM);

        boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        boolean isExamPrefixAbsent = !arePrefixesPresent(argMultimap, PREFIX_EXAM);

        if (isExamPrefixAbsent || isPreamblePresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddExamCommand.MESSAGE_USAGE));
        }

        LocalDateTime examDate = ParserUtil.parseExamDate(argMultimap.getValue(PREFIX_EXAM).get());

        Exam exam = new Exam(examDate);

        return new AddExamCommand(exam);
    }
}
