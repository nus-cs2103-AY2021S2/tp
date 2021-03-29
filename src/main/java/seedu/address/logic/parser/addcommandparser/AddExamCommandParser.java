package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.time.LocalDateTime;

import seedu.address.logic.commands.addcommand.AddExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.tag.Tag;

public class AddExamCommandParser extends AddCommandParser implements Parser<AddExamCommand> {

    @Override
    public AddExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXAM, PREFIX_MODULE);

        boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        boolean isModulePrefixAbsent = !arePrefixesPresent(argMultimap, PREFIX_EXAM);
        boolean isExamPrefixAbsent = !arePrefixesPresent(argMultimap, PREFIX_EXAM);

        if (isModulePrefixAbsent || isExamPrefixAbsent || isPreamblePresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddExamCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE).get());
        assert title != null;

        Module module = new Module(title);

        LocalDateTime examDate = ParserUtil.parseExamDate(argMultimap.getValue(PREFIX_EXAM).get());
        assert examDate != null;

        Tag tag = new Tag(title.modTitle.replaceAll(" ", ""));
        Exam exam = new Exam(examDate, tag);

        return new AddExamCommand(module, exam);
    }
}
