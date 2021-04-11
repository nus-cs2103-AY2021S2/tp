package seedu.flashback.logic.parser;

import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.flashback.logic.commands.AddCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.flashcard.Answer;
import seedu.flashback.model.flashcard.Category;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.Priority;
import seedu.flashback.model.flashcard.Question;
import seedu.flashback.model.flashcard.Remark;
import seedu.flashback.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION,
                PREFIX_ANSWER, PREFIX_CATEGORY, PREFIX_PRIORITY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_PRIORITY, PREFIX_ANSWER, PREFIX_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        Remark remark = new Remark("");
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Flashcard flashcard = new Flashcard(question, answer, category, priority, remark, tagList);

        return new AddCommand(flashcard);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
