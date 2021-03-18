package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Lists all persons in PartyPlanet to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists people in PartyPlanet "
            + "according to specified prefix combinations, with optional sort order.\n"
            + "Parameters: [--partial] [--any] [-n NAME]... [-t TAG]... [-s SORT_ORDER]\n"
            + "Sort Orders: asc (Name Ascending - Default), desc (Name Descending)\n"
            + "Example: list --partial -n alice -t friend -s desc\n";

    public static final Comparator<Person> ASC = (x, y) ->
        x.getName().fullName.compareTo(y.getName().fullName);

    public static final Comparator<Person> DESC = (x, y) ->
        y.getName().fullName.compareTo(x.getName().fullName);

    private final Comparator<Person> comparator;

    private final List<Predicate<Person>> predicates;

    private final boolean isAnySearch;

    /**
     * Default empty ListCommand.
     * Shows the whole list.
     */
    public ListCommand() {
        this(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * More general ListCommand accepting a single filtering predicate.
     * Default in ascending order, and the ANY flag is not applicable.
     */
    public ListCommand(Predicate<Person> predicate) {
        this(List.of(predicate), false, ASC);
    }

    /**
     * Most general ListCommand.
     *
     * @param predicates List of predicates to filter people by
     * @param isAnySearch Whether filtering of predicates uses AND or OR boolean query
     * @param comparator Sorting comparator
     */
    public ListCommand(List<Predicate<Person>> predicates,
                       boolean isAnySearch,
                       Comparator<Person> comparator) {
        this.predicates = predicates;
        this.isAnySearch = isAnySearch;
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Filter by predicate
        Predicate<Person> overallPredicate;
        if (isAnySearch && predicates.isEmpty()) {
            overallPredicate = x -> true;
        } else if (isAnySearch) {
            overallPredicate = x -> false;
            for (Predicate<Person> predicate: predicates) {
                overallPredicate = overallPredicate.or(predicate);
            }
        } else {
            overallPredicate = x -> true;
            for (Predicate<Person> predicate: predicates) {
                overallPredicate = overallPredicate.and(predicate);
            }
        }
        model.sortPersonList(comparator);
        model.updateFilteredPersonList(overallPredicate);
        if (model.getPersonListCopy().size() == model.getFilteredPersonList().size()) {
            return new CommandResult(ListCommand.MESSAGE_SUCCESS); // No person filtered out
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ListCommand)) {
            return false;
        }
        ListCommand command = (ListCommand) other;
        return isAnySearch == command.isAnySearch
                && comparator.equals(command.comparator)
                && predicates.equals(((ListCommand) other).predicates); // state check
    }

}
