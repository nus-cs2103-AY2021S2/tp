package seedu.address.testutil;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.filter.ExclusiveFilterSet;
import seedu.address.model.filter.FilterSet;
import seedu.address.model.filter.InclusiveFilterSet;

public class TypicalFilters {
    public static class FilterStub implements Predicate<String> {
        private String filter;

        public FilterStub(String filter) {
            this.filter = filter;
        }

        @Override
        public boolean test(String value) {
            return value.contains(filter);
        }
    }

    public static final String FILTERABLE_A = "amz";
    public static final String FILTERABLE_B = "059";
    public static final String FILTERABLE_C = "!%)";

    public static final Predicate<String> FILTER_A = new FilterStub("amz");
    public static final Predicate<String> FILTER_B = new FilterStub("059");
    public static final Predicate<String> FILTER_C = new FilterStub("!%)");

    public static final Set<Predicate<String>> SET_A = Stream.of(FILTER_A).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_B = Stream.of(FILTER_B).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_C = Stream.of(FILTER_C).collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_AB = Stream.of(FILTER_A, FILTER_B)
            .collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_AC = Stream.of(FILTER_A, FILTER_C)
            .collect(Collectors.toSet());
    public static final Set<Predicate<String>> SET_ABC = Stream.of(FILTER_A, FILTER_B, FILTER_C)
            .collect(Collectors.toSet());

    public static final FilterSet<String> INCLUSIVE_FILTERSET_EMPTY = new InclusiveFilterSet<>();
    public static final FilterSet<String> INCLUSIVE_FILTERSET_A = new InclusiveFilterSet<>(SET_A);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_B = new InclusiveFilterSet<>(SET_B);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_C = new InclusiveFilterSet<>(SET_C);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_AB = new InclusiveFilterSet<>(SET_AB);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_AC = new InclusiveFilterSet<>(SET_AC);
    public static final FilterSet<String> INCLUSIVE_FILTERSET_ABC = new InclusiveFilterSet<>(SET_ABC);

    public static final FilterSet<String> EXCLUSIVE_FILTERSET_EMPTY = new ExclusiveFilterSet<>();
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_A = new ExclusiveFilterSet<>(SET_A);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_B = new ExclusiveFilterSet<>(SET_B);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_C = new ExclusiveFilterSet<>(SET_C);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_AB = new ExclusiveFilterSet<>(SET_AB);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_AC = new ExclusiveFilterSet<>(SET_AC);
    public static final FilterSet<String> EXCLUSIVE_FILTERSET_ABC = new ExclusiveFilterSet<>(SET_ABC);
}
