package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatriculationNumberContainsKeywordsPredicate;

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

        String trimmedArgs = args.trim();

        if(trimmedArgs.length() == 9 && String.valueOf(trimmedArgs.charAt(0)).equalsIgnoreCase("A")){
            check_valid_matriculation_number(trimmedArgs);
        }
        else{
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new MatriculationNumberContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Check if the parsed input is a valid matriculation number with 9 characters, starting from letter "A"
     * and ending with another alphabet.
     * @param matriculationNumber
     * @throws ParseException
     */
    public void check_valid_matriculation_number(String matriculationNumber ) throws ParseException {

        if(!isLetter(matriculationNumber.substring(8))){
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try{
            Integer.parseInt(matriculationNumber.substring(2,8));
        } catch(NumberFormatException | IndexOutOfBoundsException outOfBounds) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Check to see if the last character of the matriculation number is a letter.
     * @param input last character of the matriculation number
     * @throws ParseException
     */

    public boolean isLetter(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
