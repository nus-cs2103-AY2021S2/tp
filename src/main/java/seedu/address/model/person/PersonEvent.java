package seedu.address.model.person;

import java.time.LocalDate;
import java.util.Objects;

public class PersonEvent {
    private final int date;
    private final int month;
    private final Person person;
    private final String description;

    public PersonEvent(LocalDate localDate, Person person, String description) {
        this.date = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.person = person;
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public Person getPerson() {
        return person;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonEvent)) {
            return false;
        }

        PersonEvent otherPersonEvent = (PersonEvent) other;
        return otherPersonEvent.getDate() == getDate()
                && otherPersonEvent.getMonth() == getMonth()
                && otherPersonEvent.getPerson().equals(getPerson())
                && otherPersonEvent.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, month, person, description);
    }

    @Override
    public String toString() {
        return date + "/" + month + " " + person.getName() + " " + description;
    }
}
