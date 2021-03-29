package seedu.address.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonStreak;
import seedu.address.model.person.Streak;

public class StreakCard extends UiPart<Region> {

    private static final String FXML = "StreakCard.fxml";

    public final PersonStreak personStreak;

    @FXML
    private Label name;

    @FXML
    private Label streak;

    /**
     * Creates a {@code StreakCard} with the given {@code PersonStreak} to display.
     */
    public StreakCard(PersonStreak personStreak) {
        super(FXML);
        this.personStreak = personStreak;

        Person p = personStreak.getPerson();
        Streak s = personStreak.getStreak();

        name.setText(p.getName().fullName);
        streak.setText(s.toUi());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreakCard that = (StreakCard) o;
        return Objects.equals(personStreak, that.personStreak)
                && Objects.equals(name, that.name)
                && Objects.equals(streak, that.streak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personStreak, name, streak);
    }
}
