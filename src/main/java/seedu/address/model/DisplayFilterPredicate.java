package seedu.address.model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * Tests if a {@code Person}'s field in {@code PersonCard} should be hidden. By default, all the
 * fields are shown if no {@code ArgumentMultimap} is provided.
 */
public class DisplayFilterPredicate implements Predicate<Prefix> {

    private final HashMap<Prefix, Boolean> map;

    /**
     * Initializes known prefixes to true. See {@code CliSyntax} for known prefixes.
     */
    public DisplayFilterPredicate() {
        map = new HashMap<>();
        map.put(PREFIX_NAME, true);
        map.put(PREFIX_PHONE, true);
        map.put(PREFIX_EMAIL, true);
        map.put(PREFIX_ADDRESS, true);
        map.put(PREFIX_TAG, true);
        map.put(PREFIX_REMARK, true);
    }

    /**
     * Initializes prefixes in argumentMultimap to true if present. See {@code CliSyntax} for known
     * prefixes.
     */
    public DisplayFilterPredicate(ArgumentMultimap argumentMultimap) {
        this();
        if (argumentMultimap.getArgumentSize() != 0) {
            map.put(PREFIX_PHONE, argumentMultimap.getValue(PREFIX_PHONE).isPresent());
            map.put(PREFIX_EMAIL, argumentMultimap.getValue(PREFIX_EMAIL).isPresent());
            map.put(PREFIX_ADDRESS, argumentMultimap.getValue(PREFIX_ADDRESS).isPresent());
            map.put(PREFIX_TAG, argumentMultimap.getValue(PREFIX_TAG).isPresent());
            map.put(PREFIX_REMARK, argumentMultimap.getValue(PREFIX_REMARK).isPresent());
        }
    }

    @Override
    public boolean test(Prefix prefix) {
        return map.getOrDefault(prefix, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisplayFilterPredicate) // instanceof handles nulls
                && map.keySet().stream()
                .allMatch(e -> map.get(e).equals(((DisplayFilterPredicate) other).map.get(e)));
    }
}
