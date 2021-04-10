package seedu.partyplanet.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;

public class EEditAutocompleteUtilTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void parseCommmand() throws Exception {
        String arguments = " 1 -n -d -r";
        EEditAutocompleteUtil eEditAutocompleteUtil = new EEditAutocompleteUtil(arguments);

        String returnCommand = eEditAutocompleteUtil.parse(model);

        Event event = model.getEventBook().getEventList().get(0);

        String name = event.getName().fullName;
        String date = event.getEventDate().value;
        String remark = event.getRemark().value;

        String expectedReturnCommand = "eedit 1 -n " + name + " -d " + date + " -r " + remark;

        assertEquals(returnCommand, expectedReturnCommand);
    }

}
