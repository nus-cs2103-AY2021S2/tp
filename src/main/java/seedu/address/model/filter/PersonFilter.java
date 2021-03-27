package seedu.address.model.filter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class PersonFilter implements Predicate<Person> {
    private final List<Predicate<Name>> nameFilters;

    private final Predicate<Name> composedNameFilter;

    /**
     * Constructs an empty {@code PersonFilter} that shows all people by default.
     */
    public PersonFilter() {
        this.nameFilters = new ArrayList<>();

        this.composedNameFilter = x -> true;
    }

    /**
     * Constructs a {@code PersonFilter} from lists of filters.
     * Every field must be present and not null.
     */
    public PersonFilter(List<Predicate<Name>> nameFilters) {
        requireAllNonNull(nameFilters);
        this.nameFilters = nameFilters;

        this.composedNameFilter = nameFilters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);
    }

    /**
     * Add all filters from another person filter and merge them.
     */
    public PersonFilter add(PersonFilter personFilter) {
        List<Predicate<Name>> newNameFilters = new ArrayList<>(nameFilters);
        newNameFilters.addAll(personFilter.nameFilters);

        return new PersonFilter(newNameFilters);
    }

    /**
     * Remove filters from this person filter according to another person filter.
     */
    public PersonFilter remove(PersonFilter personFilter) {
        List<Predicate<Name>> newNameFilters = new ArrayList<>(nameFilters);
        newNameFilters.removeAll(personFilter.nameFilters);

        return new PersonFilter(newNameFilters);
    }

    @Override
    public boolean test(Person person) {
        boolean isFiltered = false;
        isFiltered = isFiltered || composedNameFilter.test(person.getName());
        return isFiltered;
    }
}
