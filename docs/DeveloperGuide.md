---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/) folder.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
App-Ointment is intended for Receptionists of Medical Clinics who help schedule appointments, and maintain patient records and accounts.

* has a need to manage a significant number of scheduled appointments
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps


**Value proposition**

- Allows users to track and reschedule appointments for a clinic, reducing no-shows.
- Allow users to verify the patient on arriving at the clinic for the appointment.
- No cross clinic support for clinics within a health group.
- No support for users who want to view their own appointments.


### User stories
Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                      | I want to …​                              | So that I can…​                                                     |
| -------- | ------------------------------------------- | ---------------------------------------- | ------------------------------------------------------------------- |
| `* * *`  | new user                                    | see usage instructions                   | refer to instructions when I forget how to use the App              |
| `* * *`  | user                                        | add a new appointment                    |                                                                     |
| `* * *`  | user                                        | delete an appointment                    | remove appointments that have expired or on behalf of the patient   |
| `* * *`  | user                                        | find an appointment by specific fields   | locate details of relevant appointments without having to go through the entire list |
| `* * *`  | user                                        | edit a patient by specific fields        | update the patient information without having to delete and add a new patient |
| `* * *`  | user                                        | edit an appointment by specific fields   | update the appointment information without having to delete and add a new appointment |
| `* *`    | user                                        | lookup previous records of a patient     | fill in missing information where ommitted by the patient           |
| `* *`    | user with many appointments in the schedule | be reminded of overdue appointments      | take the appropriate action to resolve the issues                   |
| `* *`    | user with many appointments in the schedule | tag appointments with urgency categories | more urgent appointments can take priority                          |
| `*`      | user with many appointments in the schedule | sort appointments by specific fields     | locate a category of appointments easily                            |
| `*`      | user with many appointments in the schedule | automatically recommended available timings and doctors for new appointments | create appointments without manually checking availability in the schedule |

*{More to be added}*

### Use cases

[Coming Soon]

(For all use cases below, the **System** is the `App-Ointment` and the **Actor** is the `User`, unless specified otherwise)

### UC01: Enters a command:
**MSS**
1. User enters a command.
2. App-Ointment performs the corresponding action.

**Extensions**
* **1a.** App-Ointment detects an invalid command from the user.
    * **1a1.** App-Ointment prompts user that the command is not recognised.<br>
    * **1a2.** App-Ointment executes the `help` command.<br>
    Steps 1a1 to 1a2 are repeated until command entered is recognised.
    Use case ends.

### UC02: Add a patient
**MSS**
1. User <u>enters the `add-patient` command and corresponding subcommands (UC01)</u>
2. App-Ointment adds a new patient to the patient records.

**Extensions** 
* **1a.** App-Ointment detects an invalid subcommand format.
    * **1a1.** App-Ointment prompts user that syntax is incorrect and displays the expected format.<br>
    Steps 1a1 is repeated until the subcommand entered is correct/free from errors.
    Use case resumes from step 2.

* **2a.** App-Ointment detects that a patient with the same name exists in the patient records.
    * **2a1.** App-Ointment warns user about the duplicate patient.<br>
    * **2a2.** App-Ointment suggest user to update patient information through an `edit-patient` command instead.<br>
    Use case ends.<br>

### UC03: Add an appointment
**MSS**
1. User <u>enters the `add-appt` command and corresponding subcommands (UC01)</u>
2. App-Ointment adds a new appointment to the appointment schedule.

**Extensions** 
* **1a.** App-Ointment detects an invalid subcommand format.
    * **1a1.** App-Ointment prompts user that syntax is incorrect and displays the expected format.<br>
    Steps 1a1 is repeated until the subcommand entered is correct/free from errors.
    Use case resumes from step 2.
      
* **2a.** The patient index out of the bounds of the displayed list of patients.
    * **2a1.** App-Ointment warns user that the index is out of bounds and displays the bounds of the displayed list of patients.<br>
    Steps 2a1 is repeated until the index entered is correct/free from errors.
    Use case resumes from step 2.

* **2b.** App-Ointment detects an existing appointment with the same patient or doctor at an overlapping appointment time.
    * **2b1.** App-Ointment warns user about the conflicting appointment.<br>
    * **2b2.** App-Ointment suggest user to either change existing appointment details through an `edit-appt` command, before adding the new appointment again, or change the new appointment details.<br>
    Use case ends.<br>

### UC04: List all appointments
**MSS**
1. User <u>enters the `list` command (UC01)</u>
2. App-Ointment displays all appointments.

**Extensions**
* **2a.** There are no appointments to display.
    * **2a1.** App-Ointment informs user that there are no appointments to display.<br>
    Use case ends.

### UC05: Edit a patient
**MSS**
1. User <u>enters the `edit-patient` command and corresponding subcommands (UC01)</u>
2. App-Ointment changes the details of the patient.

**Extensions**
* **1a.** App-Ointment detects an invalid subcommand format.
    * **1a1.** App-Ointment prompts user that syntax is incorrect and displays the expected format.<br>
      Steps 1a1 is repeated until the subcommand entered is correct/free from errors.
      Use case resumes from step 2.

* **1b.** The currently displayed list of patients is empty.
    * **1b1.** App-Ointment prompts user that there are no patients in the current display.<br>
      Use case ends.

* **2a.** The index out of the bounds of the displayed list of patients.
    * **2a1.** App-Ointment warns user that the index is out of bounds and displays the bounds of the displayed list of patients.<br>
      Steps 2a1 is repeated until the index entered is correct/free from errors.
      Use case resumes from step 2.


### UC06: Edit an appointment
**MSS**
1. User <u>enters the `edit-appt` command and corresponding subcommands (UC01)</u>
2. App-Ointment changes the details of the appointment.

**Extensions**
* **1a.** App-Ointment detects an invalid subcommand format.
    * **1a1.** App-Ointment prompts user that syntax is incorrect and displays the expected format.<br>
    Steps 1a1 is repeated until the subcommand entered is correct/free from errors.
    Use case resumes from step 2.

* **1b.** The currently displayed list of appointments is empty.
    * **1b1.** App-Ointment prompts user that there are no appointments in the current display.<br>
    Use case ends.

* **2a.** The index out of the bounds of the displayed list of appointments.
    * **2a1.** App-Ointment warns user that the index is out of bounds and displays the bounds of the displayed list of appointments.<br>
    Steps 2a1 is repeated until the index entered is correct/free from errors.
    Use case resumes from step 2.

* **2b.** App-Ointment detects an existing appointment having conflict with the new appointment.
    * **2b1.** App-Ointment warns user about the conflicting appointment.<br>
    * **2b2.** App-Ointment suggest user to either change the other existing appointment details through a separate `edit-appt` command, before editing the current appointment again, or change the edit details of the current appointment.<br>
    Use case ends.

### UC07: Find appointments by search fields
**MSS**
1. User <u>enters the `find` command and corresponding subcommands (UC01)</u>
2. App-Ointment changes the displayed list of appointments to fit.

**Extensions**
* **1a.** System detects an invalid subcommand format.
    * **1a1.** App-Ointment prompts user that syntax is incorrect and displays the expected format.<br>
    Steps 1a1 is repeated until the subcommand entered is correct/free from errors.
    Use case resumes from step 2.

* **2a.** There are no appointments to display.
    * **2a1.** App-Ointment informs user that there are no appointments to display.<br>
    Use case ends.

### UC08: Delete an appointment
**MSS**
1. User <u>enters the `delete` command and corresponding subcommands (UC01)</u>
2. App-Ointment removes the appointment from the appointment schedule

**Extensions**
* **1a.** The currently displayed list of appointments is empty.
    * **1a1.** App-Ointment prompts user that there are no appointments in the current display.<br>
    Use case ends.

* **2a.** The index out of the bounds of the displayed list of appointments.
    * **2a1.** App-Ointment warns user that the index is out of bounds and displays the bounds of the displayed list of appointments.<br>
    * **2a2.** User enters a valid index<br>
    Use case resumes from step 2.

*{More to be added}*

### Non-Functional Requirements
_Non-functional requirements specify the constraints under which App-Ointment is developed and operated._

#### Constraints:
* The system should be backward compatible with data produced by earlier versions of the system.

#### Technical requirements:
* Should work on any _mainstream OS_ as long as it has Java `11` or above installed.

#### Performance requirements:
* Should be able to hold up to 1000 appointments without a noticeable sluggishness in performance for typical usage.

#### Quality requirements:
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

#### Notes about project scope:
* The App-Ointment data file is private and local to the user.
*{More to be added}*

### Glossary

* **System**: The App-Ointment App
* **User**: The Receptionist, not the patient or doctor
* **Appointment Schedule**: The list of appointments maintained in the App-Ointment, arranged by appointment datetime.
* **Mainstream OS**: Windows, Linux, Unix, OS-X

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
