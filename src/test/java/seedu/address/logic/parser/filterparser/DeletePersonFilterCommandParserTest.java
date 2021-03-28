package seedu.address.logic.parser.filterparser;

// import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.DeletePersonFilterCommand;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.PersonFilter;
import seedu.address.model.person.Name;

public class DeletePersonFilterCommandParserTest {
    private DeletePersonFilterCommandParser parser = new DeletePersonFilterCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>();
        nameFilters.add(new NameFilter("Alice"));
        PersonFilter personFilter = new PersonFilter(nameFilters);

        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(personFilter);

        assertParseSuccess(parser, " n/Alice", deletePersonFilterCommand);
    }

    // TODO: ParseException Failures
    // @Test
    // public void parse_invalidValue_failure() {
    //     assertParseFailure(parser, " n/-", NameFilter.MESSAGE_CONSTRAINTS);
    // }
}
