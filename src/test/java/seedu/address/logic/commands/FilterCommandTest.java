package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class FilterCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());

    @Test
    public void execute_modelUpdated_success() throws Exception {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");

        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        FilterCommand filterCommand = new FilterCommand(displayFilterPredicate);

        // before executing
        assertNotEquals(model.getDisplayFilter(), displayFilterPredicate);

        filterCommand.execute(model);

        // after executing
        assertEquals(model.getDisplayFilter(), displayFilterPredicate);
    }

    @Test
    public void equals() {
        final ArgumentMultimap addressMultimap = new ArgumentMultimap();
        addressMultimap.put(PREFIX_ADDRESS, "");
        final ArgumentMultimap remarkMultimap = new ArgumentMultimap();
        addressMultimap.put(PREFIX_REMARK, "");
        final DisplayFilterPredicate addressOnlyPredicate = new DisplayFilterPredicate(
                addressMultimap);
        final DisplayFilterPredicate remarkOnlyPredicate = new DisplayFilterPredicate(
                remarkMultimap);
        final FilterCommand standardCommand = new FilterCommand(new DisplayFilterPredicate());
        final FilterCommand addressPredicateCommand = new FilterCommand(addressOnlyPredicate);
        final FilterCommand remarkPredicateCommand = new FilterCommand(remarkOnlyPredicate);
        final FilterCommand dupeAddressPredicateCommand = new FilterCommand(addressOnlyPredicate);
        final FilterCommand commandWithSameValues = new FilterCommand(new DisplayFilterPredicate());

        // same predicate, default -> returns true
        assertEquals(commandWithSameValues, standardCommand);

        // same predicate, address -> returns true
        assertEquals(addressPredicateCommand, dupeAddressPredicateCommand);

        final List<String> prefixes = Arrays
                .asList(PREFIX_ADDRESS.getPrefix(), PREFIX_REMARK.getPrefix(),
                        PREFIX_EMAIL.getPrefix());
        final List<String> prefixesReversed = new ArrayList<>(prefixes);
        Collections.reverse(prefixesReversed);
        // same prefixes -> returns true
        assertEquals(new FilterCommand(new DisplayFilterPredicate(), prefixes),
                new FilterCommand(new DisplayFilterPredicate(), prefixesReversed));

        // different prefixes -> returns false
        assertNotEquals(new FilterCommand(new DisplayFilterPredicate(), prefixes),
                new FilterCommand(new DisplayFilterPredicate(), new ArrayList<>()));

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        assertEquals(addressPredicateCommand, addressPredicateCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);
        assertNotEquals(addressPredicateCommand, null);

        // different command types -> returns false
        assertNotEquals(new ClearCommand(), standardCommand);

        // different predicate -> returns false
        assertNotEquals(addressPredicateCommand, standardCommand);
        assertNotEquals(addressPredicateCommand, remarkPredicateCommand);
    }
}
