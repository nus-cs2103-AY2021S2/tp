package seedu.address.model.insurance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name and yearly premium amount of a client's insurance plan.
 */
public class InsurancePlan {
    public static final String MESSAGE_CONSTRAINTS = "Input should be of the form:\n"
            + "plan INDEX i/PLAN_NAME $PREMIUM (add plan) OR plan INDEX c/PLAN_INDEX (remove plan)\n"
            + "e.g. plan 3 i/Protecc $4000 OR plan 2 c/3\n"
            + "The plan name should only contain alphanumeric characters and spaces,"
            + " and it should not be blank.\n"
            + "The yearly premium should be a positive amount with up to 2 decimal places.";
    public static final String PREMIUM_CONSTRAINTS =
            "The yearly premium should be a positive amount with up to 2 decimal places.";

    /*
     * The first character of the plan name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String REGEX_POSITIVE_INTEGER = "[0]*[123456789][\\p{Digit}]*";
    public static final String REGEX_ALL_ZEROES = "0*";

    public final String original;
    public final String name;
    public final String premium;

    /**
     * Constructs a {@code InsurancePlan}.
     *
     * @param plan A valid insurance plan.
     */
    public InsurancePlan(String plan) {
        requireNonNull(plan);
        checkArgument(isValidPlan(plan), MESSAGE_CONSTRAINTS);
        String[] fragments = plan.split(" \\$", 2);
        checkArgument(isValidAmount(fragments[1]), PREMIUM_CONSTRAINTS);
        this.original = plan;
        this.name = fragments[0].trim();

        // Remove leading zeroes from the string
        StringBuilder temp = new StringBuilder(fragments[1].replaceAll("^0+(?!\\$)", ""));
        // Ensure there is at least 1 digit in front of the decimal point
        if (temp.charAt(0) == '.') {
            temp.insert(0, "0");
        }
        // Pad zeroes behind the decimal point up to 2 decimal places
        if (temp.toString().contains(".")) {
            String[] tempArr = temp.toString().split("\\.");
            int numOfDecPlaces;
            if (tempArr.length == 1) {
                numOfDecPlaces = 0;
            } else {
                numOfDecPlaces = tempArr[1].length();
            }
            temp.append("0".repeat(2 - numOfDecPlaces));
        }
        this.premium = temp.toString();
    }

    /**
     * Returns true if a given string is a valid plan.
     */
    public static boolean isValidPlan(String plan) {
        return plan.contains(" $") && plan.split(" \\$")[0].matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given premium amount is a valid amount.
     */
    public static boolean isValidAmount(String premium) {
        String[] fragments = premium.split("\\.");
        if (fragments.length == 1) {
            return fragments[0].matches(REGEX_POSITIVE_INTEGER);
        } else if (fragments.length > 2) {
            return false;
        } else if (fragments[0].matches(REGEX_ALL_ZEROES)) {
            return fragments[1].length() <= 2 && fragments[1].matches(REGEX_POSITIVE_INTEGER);
        } else if (fragments[0].matches(REGEX_POSITIVE_INTEGER)) {
            return fragments[1].length() <= 2
                    && (fragments[1].matches(REGEX_ALL_ZEROES) || fragments[1].matches(REGEX_POSITIVE_INTEGER));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " ($" + premium + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePlan // instanceof handles nulls
                && name.equals(((InsurancePlan) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
