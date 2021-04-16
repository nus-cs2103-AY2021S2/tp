package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAliasCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;

/**
 * A utility class containing a list of {@code CommandAlias} and {@code Alias} objects to be used in tests.
 */
public class TypicalCommandAliases {

    public static final CommandAlias ADD_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("a")
            .withCommand(AddCommand.COMMAND_WORD).build();
    public static final CommandAlias LIST_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("l")
            .withCommand(ListCommand.COMMAND_WORD).build();
    public static final CommandAlias DELETE_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("d")
            .withCommand(DeleteCommand.COMMAND_WORD).build();
    public static final CommandAlias EDIT_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("ed")
            .withCommand(EditCommand.COMMAND_WORD).build();
    public static final CommandAlias EXIT_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("ex")
            .withCommand(ExitCommand.COMMAND_WORD).build();
    public static final CommandAlias HELP_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("h")
            .withCommand(HelpCommand.COMMAND_WORD).build();
    public static final CommandAlias FIND_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("f")
            .withCommand(FindCommand.COMMAND_WORD).build();
    public static final CommandAlias CLEAR_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("c")
            .withCommand(ClearCommand.COMMAND_WORD).build();

    public static final Alias ADD_ALIAS = new Alias("a");
    public static final Command ADD_COMMAND = new Command(AddCommand.COMMAND_WORD);
    public static final Alias DELETE_ALIAS = new Alias("d");
    public static final Command DELETE_COMMAND = new Command(DeleteCommand.COMMAND_WORD);
    public static final Alias INVALID_ALIAS = new Alias("zzz");

    public static final String ADD_ALIAS_STRING = "a";
    public static final String DELETE_ALIAS_STRING = "d";
    public static final String INVALID_ALIAS_STRING = "z z";
    public static final String INVALID_COMMAND_STRING = "zzz";

    public static final CommandAlias ALIAS_ADD_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("aa")
            .withCommand(AddAliasCommand.COMMAND_WORD + " " + AddAliasCommand.ADD_SUB_COMMAND_WORD).build();
    public static final CommandAlias ALIAS_DELETE_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("ad")
            .withCommand(DeleteAliasCommand.COMMAND_WORD + " " + DeleteAliasCommand.DELETE_SUB_COMMAND_WORD).build();
    public static final CommandAlias ALIAS_LIST_COMMAND_ALIAS = new CommandAliasBuilder().withAlias("al")
            .withCommand(ListAliasCommand.COMMAND_WORD + " " + ListAliasCommand.LIST_SUB_COMMAND_WORD).build();

    private TypicalCommandAliases() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static UniqueAliasMap getTypicalAliasMap() {
        UniqueAliasMap aliasMap = new UniqueAliasMap();
        for (CommandAlias commandAlias : getTypicalCommandAliases()) {
            aliasMap.addAlias(commandAlias);
        }
        return aliasMap;
    }

    public static List<CommandAlias> getTypicalCommandAliases() {
        return new ArrayList<>(Arrays.asList(ADD_COMMAND_ALIAS, LIST_COMMAND_ALIAS, DELETE_COMMAND_ALIAS,
                EDIT_COMMAND_ALIAS, EXIT_COMMAND_ALIAS, HELP_COMMAND_ALIAS, FIND_COMMAND_ALIAS, CLEAR_COMMAND_ALIAS));
    }

    public static CommandAlias getTypicalCommandAlias() {
        return ADD_COMMAND_ALIAS;
    }

    public static Alias getTypicalAlias() {
        return ADD_ALIAS;
    }

}
