package seedu.hippocampus.logic.parser;

import static seedu.hippocampus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hippocampus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hippocampus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.hippocampus.logic.commands.FindCommand;
import seedu.hippocampus.logic.parser.exceptions.ParseException;
import seedu.hippocampus.model.person.Person;
import seedu.hippocampus.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.hippocampus.model.person.predicates.TagsContainsTagPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);

        boolean noName = !argMap.getValue(PREFIX_NAME).isPresent();
        boolean noTag = !argMap.getValue(PREFIX_TAG).isPresent();
        boolean noNameAndNoTag = noName && noTag;

        boolean moreThanOneName = argMap.getAllValues(PREFIX_NAME).size() > 1;
        boolean moreThanOneTag = argMap.getAllValues(PREFIX_TAG).size() > 1;
        boolean invalidNumberOfNamesOrTag = moreThanOneName || moreThanOneTag;

        if (noNameAndNoTag || invalidNumberOfNamesOrTag) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Person>> predicates = new ArrayList<>();

        argMap.getValue(PREFIX_NAME)
            .map(words -> new NameContainsKeywordsPredicate(words))
            .ifPresent(predicates::add);

        argMap.getValue(PREFIX_TAG)
            .map(tag -> new TagsContainsTagPredicate(tag))
            .ifPresent(predicates::add);

        return new FindCommand(predicates);
    }

}
