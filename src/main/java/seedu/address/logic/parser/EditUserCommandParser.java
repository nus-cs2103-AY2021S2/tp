package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IDEAL_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;
import seedu.address.model.user.User;

public class EditUserCommandParser implements Parser<EditUserCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditUserCommand
     * and returns an EditUserCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditUserCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GENDER, PREFIX_AGE, PREFIX_HEIGHT, PREFIX_WEIGHT,
                        PREFIX_IDEAL_WEIGHT);

        if (!arePrefixesPresent(argMultimap, PREFIX_GENDER, PREFIX_AGE, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_IDEAL_WEIGHT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE));
        }

        String gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        int age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        double height = ParserUtil.parseWeightAndHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        double weight = ParserUtil.parseWeightAndHeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        double idealWeight = ParserUtil.parseIdealWeight(argMultimap.getValue(PREFIX_IDEAL_WEIGHT).get());

        Bmi newBmi = new Bmi(weight, height);
        Gender newGender = new Gender(gender);
        Age newAge = new Age(age);
        IdealWeight newIdealWeight = new IdealWeight(idealWeight);

        List<Food> foodList = new ArrayList<Food>();
        FoodIntakeList foodIntakeList = new FoodIntakeList();
        User newUser = new User(newBmi, foodList, foodIntakeList, newAge, newGender, newIdealWeight);
        return new EditUserCommand(newUser);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
