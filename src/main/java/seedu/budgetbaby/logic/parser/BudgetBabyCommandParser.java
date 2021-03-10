package seedu.budgetbaby.logic.parser;

import seedu.budgetbaby.logic.commands.BudgetBabyCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code BudgetBabyCommand} of type {@code T}.
 */
public interface BudgetBabyCommandParser<T extends BudgetBabyCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
