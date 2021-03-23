package seedu.address.model.person;

import java.util.Observable;

public class SelectedPerson extends Observable {
    private Person currentPerson;

    public SelectedPerson(Person person) {
        currentPerson = person;
    }

    public void setPerson(Person person) {
        this.currentPerson = person;
        setChanged();
        notifyObservers(person);
    }
}
