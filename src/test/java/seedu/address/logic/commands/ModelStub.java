package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasResident(Resident resident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasResident(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteResident(Resident target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addResident(Resident resident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getIndexOfResidentWithSameName(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    public Resident getResidentWithSameName(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredResidentList(Predicate<Resident> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRoom(Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRoom(RoomNumber roomNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRoom(Room target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRoom(Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Room getRoomWithSameRoomNumber(RoomNumber roomNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getIndexOfRoomWithSameRoomNumber(RoomNumber roomNumber) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEitherResidentRoom(ResidentRoom residentRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasBothResidentRoom(ResidentRoom residentRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addResidentRoom(ResidentRoom residentRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteResidentRoom(ResidentRoom target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResidentRoom(ResidentRoom target, ResidentRoom editedResidentRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRoom(Room target, Room editedRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyCommandHistory getCommandHistory() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteIssue(Issue target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void appendCommandHistoryEntry(String commandText) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addIssue(Issue issue) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setIssue(Issue target, Issue editedIssue) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Issue> getFilteredIssueList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredIssueList(Predicate<Issue> predicate) {
        throw new AssertionError("This method should not be called.");
    }


    public void updateFilteredResidentRoomList(Predicate<ResidentRoom> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ResidentRoom> getFilteredResidentRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAlias(Alias alias) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public AliasMapping getAliasMapping() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAliasMapping(AliasMapping aliasMapping) {
        throw new AssertionError("This method should not be called.");
    }
}
