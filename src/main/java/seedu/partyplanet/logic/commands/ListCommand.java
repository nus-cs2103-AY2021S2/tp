package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Lists all persons in PartyPlanet to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons in the PartyPlanet. "
            + "Can take in a sort order to show the list by.\n"
            + "Parameters: [-s SORT_ORDER]\n"
            + "Sort Orders: asc (Name Ascending - Default), desc (Name Descending)\n"
            + "Example: list -s desc\n";

    public static final Comparator<Person> ASC = (x, y) ->
        x.getName().fullName.compareTo(y.getName().fullName);

    public static final Comparator<Person> DESC = (x, y) ->
        y.getName().fullName.compareTo(x.getName().fullName);

    private final Comparator<Person> comparator;

    public ListCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(comparator);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
