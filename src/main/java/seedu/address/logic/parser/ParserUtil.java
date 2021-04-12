package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.food.Food;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final double DOUBLE_DECIMAL_LIMIT = 3;
    public static final int FEB_MONTH_VALUE = 2;
    public static final int FEB_LEAP_YEAR_NUMBER_OF_DAYS = 29;
    public static final String MESSAGE_INVALID_DAY_LEAP_YEAR = "Current leap year only has 29 days in February. "
            + "Please input a correct date.";
    public static final String MESSAGE_INVALID_DAY_NORMAL_YEAR = "Current year only has 28 days in February. "
            + "Please input a correct date.";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String date} into a {@code Date}
     *
     * @param date date string
     * @return a DateTime object
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(trimmedDate, formatter);
        } catch (DateTimeParseException de) {
            throw new ParseException(MESSAGE_INVALID_DATETIME_FORMAT);
        }
        if (localDate.getMonthValue() == FEB_MONTH_VALUE) {
            int inputDay = Integer.parseInt(trimmedDate.substring(0, 2));
            if (localDate.isLeapYear()) {
                if (inputDay > FEB_LEAP_YEAR_NUMBER_OF_DAYS) {
                    throw new ParseException(MESSAGE_INVALID_DAY_LEAP_YEAR);
                }
            } else {
                if (inputDay >= FEB_LEAP_YEAR_NUMBER_OF_DAYS) {
                    throw new ParseException(MESSAGE_INVALID_DAY_NORMAL_YEAR);
                }
            }
        }
        return localDate;
    }

    /**
     * Parses a {@code String value} into a {@code Double}
     *
     * @param doubleValue double string
     * @return a Double value
     * @throws ParseException if the given {@code value} is invalid.
     */
    public static Double parseDouble(String doubleValue) throws ParseException {
        requireNonNull(doubleValue);
        String trimmedValue = doubleValue.trim();
        int dotIndex = trimmedValue.indexOf(".");
        if (!trimmedValue.matches(Food.VALIDATION_POSITIVE_DOUBLE_REGEX)) {
            throw new ParseException(Food.MESSAGE_DIGIT_CONSTRAINTS);
        }
        if (trimmedValue.length() > dotIndex + DOUBLE_DECIMAL_LIMIT && dotIndex != -1) {
            throw new ParseException(Food.MESSAGE_DECIMAL_PLACE_CONSTRAINTS);
        }
        Double result = Double.valueOf(trimmedValue);
        if (result > Food.NUTRIENTS_LIMIT) {
            throw new ParseException(Food.MESSAGE_DIGIT_MAX_LIMIT);
        }
        return Double.valueOf(trimmedValue);
    }

    /**
     * Parses a {@code String name} into a {@code Food}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseFoodName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!trimmedName.matches(Food.VALIDATION_CHAR_REGEX)
                || trimmedName.length() == 0) {
            throw new ParseException(Food.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String name} to conform with a FoodIntake Food name.
     * Allows the '#' character that takes into account for duplicate names.
     * Called only during FoodIntake Update and delete.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseFoodIntakeName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!trimmedName.matches(Food.VALIDATION_CHAR_REGEX_IMPORT)
                || trimmedName.length() == 0) {
            throw new ParseException(Food.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String ageString} into an Integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static int parseAge(String ageString) throws ParseException {
        requireNonNull(ageString);
        String trimmedAge = ageString.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }

        return Integer.parseInt(trimmedAge);
    }

    /**
     * Parses a {@code String weightHeightString} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static double parseWeightAndHeight(String weightHeightString) throws ParseException {
        requireNonNull(weightHeightString);
        String trimmedWeightHeight = weightHeightString.trim();
        int dotIndex = trimmedWeightHeight.indexOf(".");
        if (!Bmi.isValidWeightOrHeight(trimmedWeightHeight)) {
            throw new ParseException(Bmi.MESSAGE_CONSTRAINTS);
        }
        if (trimmedWeightHeight.length() > dotIndex + DOUBLE_DECIMAL_LIMIT && dotIndex != -1) {
            throw new ParseException(Food.MESSAGE_DECIMAL_PLACE_CONSTRAINTS);
        }

        return Double.parseDouble(trimmedWeightHeight);
    }

    /**
     * Parses a {@code String gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }

        return trimmedGender;
    }

    /**
     * Parses a {@code String idealWeightString} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static double parseIdealWeight(String idealWeightString) throws ParseException {
        requireNonNull(idealWeightString);
        String trimmedWeight = idealWeightString.trim();
        int dotIndex = trimmedWeight.indexOf(".");
        if (!IdealWeight.isValidIdealWeight(trimmedWeight)) {
            throw new ParseException(IdealWeight.MESSAGE_CONSTRAINTS);
        }
        if (trimmedWeight.length() > dotIndex + DOUBLE_DECIMAL_LIMIT && dotIndex != -1) {
            throw new ParseException(Food.MESSAGE_DECIMAL_PLACE_CONSTRAINTS);
        }
        return Double.parseDouble(trimmedWeight);
    }

    /**
     * Parses a {@code String indexString} into an Integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static int parsePlan(String indexString) throws ParseException {
        requireNonNull(indexString);
        String trimmedIndex = indexString.trim();
        if (!DietPlanList.isValidIndex(trimmedIndex)) {
            throw new ParseException(DietPlanList.MESSAGE_CONSTRAINTS);
        }

        return Integer.parseInt(trimmedIndex);
    }

}
