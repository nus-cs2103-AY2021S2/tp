package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommand.DeleteExamCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Title;

public class DeleteExamCommandParser extends DeleteCommandParser implements Parser<DeleteExamCommand> {
    @Override
    public DeleteExamCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_ASSIGNMENT);

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE)
                .filter(Title::isValidTitle)
                .orElseThrow(() -> new ParseException(
                        String.format(Title.MESSAGE_CONSTRAINTS, "Modules'")
                )));
        Index index = ParserUtil.parseExamIndex(argMultimap.getValue(PREFIX_EXAM).get());
        return new DeleteExamCommand(title, index);
    }
}
