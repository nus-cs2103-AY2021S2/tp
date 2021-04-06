package seedu.booking.logic.commands.states;

import static seedu.booking.commons.core.Messages.PROMPT_GENERAL_ERROR;
import static seedu.booking.commons.core.Messages.PROMPT_PHONE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;

import java.util.Set;

import seedu.booking.logic.commands.intermediatestate.AddPersonIntermediate;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

public class AddPersonCommandState extends CommandState {
    public static final String STATE_EMAIL = "PERSON_EMAIL";
    public static final String STATE_PHONE = "PHONE";
    public static final String STATE_TAG = "PERSON_TAG";

    private final AddPersonIntermediate addPersonIntermediate;

    /**
     * Initialises a PersonCommandState
     */
    public AddPersonCommandState(Name name) {
        super();
        this.addPersonIntermediate = new AddPersonIntermediate(name);
    }

    @Override
    public void setNextState() {
        String state = this.getState();
        if (state.equals(STATE_EMAIL)) {
            this.setState(STATE_PHONE);
            this.setNextPromptMessage(PROMPT_PHONE_MESSAGE);
        } else if (state.equals(STATE_PHONE)) {
            this.setState(STATE_TAG);
            this.setNextPromptMessage(PROMPT_TAG_MESSAGE);
        } else {
            this.setNextPromptMessage(PROMPT_GENERAL_ERROR);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void processInput(Object value) {
        String state = this.getState();

        if (state.equals(STATE_PHONE)) {
            addPersonIntermediate.setPhone((Phone) value);
        } else if (state.equals(STATE_EMAIL)) {
            addPersonIntermediate.setEmail((Email) value);
        } else if (state.equals(STATE_TAG)) {
            addPersonIntermediate.setTags((Set<Tag>) value);
        }
    }

    @Override
    public Person create() {
        return addPersonIntermediate.createPerson();
    }
}

