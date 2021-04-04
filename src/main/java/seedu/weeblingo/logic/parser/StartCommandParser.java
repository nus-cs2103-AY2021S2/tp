package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_START_NUMBER;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.weeblingo.logic.commands.StartCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.tag.Tag;

public class StartCommandParser implements Parser<StartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of StartCommand and returns the StartCommand
     * object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public StartCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_NUMBER, PREFIX_TAG);
        Optional<String> stringNumberOfQuestions = argMultimap.getValue(PREFIX_START_NUMBER);
        int numberOfQuestions;

        if (stringNumberOfQuestions.isEmpty()) {
            numberOfQuestions = 0;
        } else {
            try {
                numberOfQuestions = Integer.parseInt(stringNumberOfQuestions.get());
            } catch (NumberFormatException e) {
                throw new ParseException(StartCommand.MESSAGE_NUMBER_TOO_LARGE);
            }
            if (numberOfQuestions <= 0) {
                throw new ParseException(StartCommand.MESSAGE_INVALID_NUMBER_OF_QUESTIONS);
            }
        }

        List<String> tagsList = argMultimap.getAllValues(PREFIX_TAG);
        Set<Tag> tagsSet = new HashSet<>();

        try {
            for (String tag : tagsList) {
                tagsSet.add(new Tag(tag));
            }
        } catch (IllegalArgumentException exception) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new StartCommand(numberOfQuestions, tagsSet);
    }
}
