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
            + "The yearly premium amount should be a positive integer.";
    public static final String PREMIUM_CONSTRAINTS =
            "The yearly premium amount should be a positive integer.";

    /*
     * The first character of the plan name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String PREMIUM_VALIDATION_REGEX = "[\\p{Digit}]+";
    public static final String INPUT_VALIDATION_REGEX = NAME_VALIDATION_REGEX + " \\$" + PREMIUM_VALIDATION_REGEX;

    public final String original;
    public final String name;
    public final int premium;

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
        this.name = fragments[0];
        this.premium = Integer.parseInt(fragments[1]);

    }

    /**
     * Returns true if a given string is a valid plan.
     */
    public static boolean isValidPlan(String plan) {
        return plan.matches(INPUT_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given premium amount is a valid amount.
     */
    public static boolean isValidAmount(String premium) {
        try {
            int i = Integer.parseInt(premium);
            return i > 0;
        } catch (NumberFormatException e) {
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
