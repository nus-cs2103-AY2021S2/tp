---
layout: page
title: Developer Guide
---


* Table of Contents
{:toc}


--------------------------------------------------------------------------------------------------------------------
## **1. Introduction to BookCoin**

Welcome! This is BookCoin, a compact application for booking management for administrative personnel. This developer guide is created to give a quick introduction of BookCoin to interested developers on its structure and implementation. All are welcome to contribute!

This guide covers several aspects of BookCoin, starting from its high-level design implementation and following with an overview of the implementation behind key features and rationale behind certain design decisions. Links are also provided to various guides on the tools used in Documentation, Testing, and DevOps. Finally, the appendices specify the product scope, requirements, a glossary, and instructions for manual testing.

--------------------------------------------------------------------------------------------------------------------

## **2. Setting Up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **3. Design**

This section provides an overview of the high-level design of BookCoin.

### 3.1 Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components. One example class is (#LogsCenter), which is used by many classes to write log messages to BookCoin’s log file.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of BookCoin in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_booking 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### 3.2 UI Component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### 3.3 Logic Component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/logic/Logic.java)

1. `Logic` uses the `BookingSystemParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete_booking 1")` API call.

![Interactions Inside the Logic Component for the `delete_booking 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteBookingCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### 3.4 Model Component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the booking system data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `BookingSystem`, which `Person` references. This allows `BookingSystem` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)
<br>

</div>


### 3.5 Storage Component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103-W17-3/tp/tree/master/src/main/java/seedu/booking/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the booking system data in json format and read it back.

### 3.6 Common Classes

Classes used by multiple components are in the `seedu.booking.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **4. Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 4.1 Find Feature
BookCoin allows users to narrow down the search of persons, venues and bookings through filters. 

#### 4.1.1 Implementation Details
The find functionality is implemented through an FindCommand and FindCommandParser for Person, Venue and Booking. Corner cases such as searching for non-existent entries are also handled properly with suitable notifications displayed to the user. For example, if a search returns with no results, the corresponding notice that there are no persons/ venues/ bookings is displayed. The find feature is implemented with `FindPersonCommand`/ `FindVenueCommand`/ `FindBookingCommand` and their respective parsers `FindPersonCommandParser`/ `FindVenueCommandParser`/ `FindBookingCommandParser`. Below is a class diagram of the find command:
<br>
![Class Diagram of Find Command](images/FindCommandClassDiagram.png)

Since the functionality for all three classes are similar, we can focus our discussion here on the Person class without loss of generality. The `find_person` command has the following format:
`find_person [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]`, where at least one field must be provided.

Given below is a sequence diagram how the `find_person` command behaves in BookCoin after user input is parsed if a user wishes to find all persons by the name of "Anna" by inputting `find_person n/Anna`.
![Sequence Diagram of Find Command](images/FindCommandSequenceDiagram.png)

#### 4.1.2 Design Considerations
##### Aspect: Deciding between full and partial matching

* **Alternative 1 (current choice):** Full matching which requires users to input at least one full word for a field.
    * Pros: Easier to implement and use, prevents the return of too many unrelated results.
    * Cons: User has to be able to remember and input at least one complete field for find command to work.

* **Alternative 2:** Partial matching which would return all valid partial results when users input a field.
    * Pros: More convenient for situations when a user cannot remember a full field.
    * Cons: Results returned may contain many unrelated and undesirable entries (e.g. if searching for a person "Ann", the system would return partial matches "Annabelle", "Annabella", "Anna" etc.)

### 4.2 Delete Feature
BookCoin supports the deletion of person, venue and booking objects. However, since there are dependencies between the different classes (e.g. there is a possibility that a venue is deleted while it still has bookings associated with it), we also had to ensure that a deletion of persons/ venues would also result in a deletion of the corresponding affected bookings.

#### 4.2.1 Implementation Details
The user input is first retrieved by the MainWindow class. Following which, the input is passed to LogicManager via the execute function, which will then call the parseCommand function belonging to bookingSystemParser. A `deletePersonCommandParser`/`deleteVenueCommandParser`/`deleteBookingCommandParser` object is created for temporary use to parse the input and return the appropriate respective `DeleteCommand` depending on the class. The command is finally executed in LogicManager to return a CommandResult object to be returned to the user as feedback.

Below is the activity diagram derived from running the command `delete_booking 1` to delete the booking stored in the system at index 1. 
![Activity Diagram of Delete Booking](images/DeleteBookingActivityDiagram.png)
<br>

#### 4.2.2 Design Considerations
##### Aspect: Deciding between allowing deletion of persons and venues with existing bookings

* **Alternative 1 (current choice):** Allowing deletion of persons and venues with existing bookings.
    * Pros: More flexibility for users as there are indeed situations that arise in real life where persons or venues are no longer valid in the system (e.g. person's membership has expired and will no longer be able to book facilities/ venue is no longer to be used due to unforseen circumstances).
    * Cons: Admin may accidentally delete persons/ venues without knowing that they have existing bookings. A good workaround for future iterations would be to have an alert message if they wish to delete said persons/ venues.

* **Alternative 2:** Disallowing deletion of persons and venues with existing bookings.
    * Pros: Easier to implement, and less likely for users to make mistakes in deletions of persons and venues if this is enforced.
    * Cons: Rigid implementation that does not account for possible real life scenarios that might arise, as aforementioned.


### 4.3 \[Proposed\] Undo/redo Feature

#### 4.3.1 Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedBookingSystem`. It extends `BookingSystem` with an undo/redo history, stored internally as an `bookingSystemStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedBookingSystem#commit()` — Saves the current booking system state in its history.
* `VersionedBookingSystem#undo()` — Restores the previous booking system state from its history.
* `VersionedBookingSystem#redo()` — Restores a previously undone booking system state from its history.

These operations are exposed in the `Model` interface as `Model#commitBookingSystem()`, `Model#undoBookingSystem()` and `Model#redoBookingSystem()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedBookingSystem` will be initialized with the initial booking system state, and the `currentStatePointer` pointing to that single booking system state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in BookCoin. The `delete` command calls `Model#commitBookingSystem()`, causing the modified state of the booking system after the `delete 5` command executes to be saved in the `bookingSystemStateList`, and the `currentStatePointer` is shifted to the newly inserted booking system state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitBookingSystem()`, causing another modified booking system state to be saved into the `bookingSystemStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitBookingSystem()`, so the booking system state will not be saved into the `bookingSystemStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoBookingSystem()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous booking system state, and restores the booking system to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial BookingSystem state, then there are no previous BookingSystem states to restore. The `undo` command uses `Model#canUndoBookingSystem()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoBookingSystem()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the booking system to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `bookingSystemStateList.size() - 1`, pointing to the latest booking system state, then there are no undone BookingSystem states to restore. The `redo` command uses `Model#canRedoBookingSystem()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the booking system, such as `list`, will usually not call `Model#commitBookingSystem()`, `Model#undoBookingSystem()` or `Model#redoBookingSystem()`. Thus, the `bookingSystemStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitBookingSystem()`. Since the `currentStatePointer` is not pointing at the end of the `bookingSystemStateList`, all booking system states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### 4.4.2 Design Considerations

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire booking system.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person/ venue/ booking being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.
    

--------------------------------------------------------------------------------------------------------------------

## **5. Documentation, Logging, Testing, Configuration, Dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix A: Requirements**

### A.1 Product Scope

**Target User Profile**:

* needs to manage a large number of bookings
* has to ensure bookings abide by restrictions
* needs to check that the bookings are valid
* is the administrative personnel of schools or similar organisations
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value Proposition**:

* manage bookings faster than a typical mouse/GUI driven app
* ease of managing bookings since it is a centralised system
* automatically keeps track of room usage, conflicts and rule violations
* clear and structured way to avoid ambiguity

### A.2 Non-functional Requirements
1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 venues and bookings without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to perform queries quickly even with significant amounts of data present.
5.  Should be easy to download and use without the need of other tools or installers.


## **Appendix B: User Stories**

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | admin in charge of facilities              | add venues                     | make bookings in that venue.                                                                       |
| `* * `  | admin in charge of facilities              | edit venues                    | reflect the most up to date details of the venue.                       |
| `* * *`  | admin in charge of facilities              | delete venues                  | remove venues not available for booking.                                                                       |
| `* * *`  | admin in charge of facilities              | view venues                    | check the venues that have been added.                                                                       |
| `* *`  | admin in charge of facilities              | find a venue by searching the venue's attribute(s)| quickly get details of that venue.                                                                      |
| `* * *`  | admin in charge of facilities              | add bookings                   | keep track of bookings.                                                                       |
| `* * `  | admin in charge of facilities              | edit bookings                  | change booking details when the person decides to amend the booking.    |
| `* * *`  | admin in charge of facilities              | delete bookings                | remove bookings that have been cancelled by the booker.                                                                       |
| `* * *`  | admin in charge of facilities              | view bookings                  | check the bookings that have been made.
| `* *`  | admin in charge of facilities              | find a booking by searching the booking's attribute(s)| quickly get details of that booking.                                                                        ||
| `* * *`  | admin in charge of facilities              | add persons                    | make bookings for that person.                                                                       |
| `* * `  | admin in charge of facilities              | edit persons                   | reflect the most up to date details of the person.                       |
| `* * *`  | admin in charge of facilities              | delete persons                 | remove persons who are not able to make a booking.                                                                       |
| `* * *`  | admin in charge of facilities              | view persons                   | check the persons that have been added.
| `* *`  | admin in charge of facilities              | find a person by searching the person's attribute(s)| quickly get details of that person.                                                                        ||
| `*`      | admin in charge of facilities              | be able to access past data           | easily check the history of certain venues                      |

## **Appendix D: Use Cases**

(For all use cases below, the **System** is `BookCoin` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a venue**

**MSS**

1.  User requests to add a new venue into the system, and provides venue details.
2.  BookCoin adds the venue into the system.

    Use case ends.

**Extensions**

* 1a. The venue to be added is already in the system.
    * 1a1. BookCoin shows an error message.

  Use case resumes at step 1.

* 1b. Venue details are invalid or missing compulsory fields.
    * 1b1. BookCoin shows an error message and prompts the user to reenter their command.
* 1b. Venue details are missing, or are provided but invalid.
    * 1b1. BookCoin shows an error message.

  Use case resumes at step 1.


**Use case: UC02 - Add a booking**

This use case is similar to UC01 - Add a venue, except that venues are replaced with bookings.

**Use case: UC03 - Add a person**

This use case is similar to UC01 - Add a venue, except that venues are replaced with persons.
 

**Use case: UC04 - Delete a venue**

**MSS**

1.  User requests to delete a specific venue.
2.  BookCoin deletes the venue.

    Use case ends.

**Extensions**

* 1a. The specified venue does not exist in the system.

    * 1a1. BookCoin shows an error message.

      Use case resumes at step 1.


**Use case: UC05 - Delete a booking**


This use case is similar to UC04 - Delete a venue, except that venues are replaced with bookings.


**Use case: UC06 - Delete a person**

This use case is similar to UC04 - Delete a venue, except that venues are replaced with persons.


**Use case: UC07 - List all bookings**

**MSS**

1.  User requests to list all bookings.
2.  BookCoin shows a list of bookings.

    Use case ends.

**Use case: UC08 - List all venues**

This use case is similar to UC07 - List all bookings, except that bookings are replaced with venues.

**Use case: UC09 - List all person**

This use case is similar to UC07 - List all bookings, except that bookings are replaced with persons.


**Use case: UC10 - Find a venue**

**MSS**

1.  User requests to find all venues that match the specified fields.
2.  BookCoin shows the matching venue(s).

    Use case ends.

**Extensions**

* 1a. No venues with the specified field(s) exists in the system.

    * 1a1. BookCoin shows an error message.

      Use case ends.

* 1b. The field(s) specified is/are invalid.

    * 1a1. BookCoin shows an error message.

      Use case resumes at step 1.

**Use case: UC11 - Find a booking**

**MSS**

This use case is similar to UC10 - Find a venue, except that venues are replaced with bookings.

**Use case: UC12 - Find a person**

This use case is similar to UC10 - Find a venue, except that venues are replaced with persons.

**Use case: UC13 - Edit a venue**

**MSS**

1.  User requests to edit certain fields belonging to a specified venue.
2.  BookCoin updates the venue information and saves it to the booking system.
1.  User requests to edit certain fields belonging to a specific venue.
2.  BookCoin updates the venue information and saves it to the booking system.

    Use case ends.

**Extensions**

* 1a. The specified venue to be edited does not exist in the system.

    * 1a1. BookCoin shows an error message.

      Use case resumes at step 1.

* 1b. The specified field(s) and/or the specified venue to be edited is/are invalid.

    * 1b1. BookCoin shows an error message.

      Use case resumes at step 1.


**Use case: UC14 - Edit a booking**

**MSS**

This use case is similar to UC13 - Edit a venue, except that venues are replaced with bookings.


**Use case: UC15 - Edit a person**

This use case is similar to UC13 - Edit a venue, except that venues are replaced with persons.


**Use case: UC16 - Exit the program**

**MSS**

1.  User requests to exit the program.
2.  BookCoin closes the window and terminates.

    Use case ends.


## **Appendix E: Glossary**

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Booking**: A person's request to occupy a venue for a specified ****duration

--------------------------------------------------------------------------------------------------------------------

## **Appendix F: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### F.1 Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Open the jar file by running `java -jar bookingapp.jar`. Expected: Shows the GUI with a set of sample contacts. Adjust the window size accordingly to your preference.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### F.2 Adding a person
1. A multi-step command to add a person to the booking system
    1. Prerequisites: list all persons using the `list_person` command. A person with the same email address and/or phone number cannot already exist. If it is present as a record in the system, delete it.

    1. Test case: `add_person n/John Doe` <br>
       Expected: The command should be successfully parsed and a prompt should appear asking for email address input.
       
    1. Test case: `add_person n/112`<br>
       Expected: There should be an error stating that the command is invalid as names must only contain alphabetic characters and spaces.

    1. Test case: `add_person John Doe` <br>
       Expected: There should be an error stating that the command format is invalid. This is because the name prefix `n/` must precede the name.
       
    1. If prompt is for email address, test case: `johndoe@gmail.com`<br>
       Expected: The command should be succesfully parsed and a prompt should appear asking for email address input.
    
    1. If initial prompt has been successfully parsed, test case: `exit_prompt`<br>
       Expected: The multi-step command should terminate and exit, and users can now input other commands.


### F.3 Adding a venue
1. A multi-step command to add a venue to the booking system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. List all venues using the `list_venue` command, and verify that a venue by the same name cannot already exist. If it is present as a record in the system, delete it.

    2. Test case: `add_venue v/Victoria Hall`, and all other inputs are left empty by pressing <kbd>enter</kbd> whenever prompted to input a field<br>
    Expected: Victoria Hall should appear in the list of venues. The default capacity should be set to 10 as it was unspecified in the command, and there should be no description or tag.

    3. Test case: `add_venue v/Victoria Hall` followed by `50`, and all other inputs left empty.<br>
    Expected: Victoria Hall should appear in the list of venues with a capacity indicated to be 50. No tag should be present, and the description should be "No description provided".

    4. Test case: `add_venue v/Victoria Hall` followed by `50` followed by `Popular concert hall`, and all other inputs left empty. <br>
    Expected: Victoria Hall should appear in the list of venues with a capacity indicated to be 50, and a description "Popular concert hall". There should be no tag.

    5. Test case: `add_venue v/Victoria Hall` followed by no input (when prompted for capacity), followed by `Popular concert hall` (when prompted for a venue description), followed by `indoors` (when prompted for tags) <br>
    Expected: Victoria Hall should appear in the list of venues with a default capacity of 10, a description "Popular concert hall", and a tag "indoors".
  
  
### F.4 Adding a booking
1. A multi-step command to add a booking to the booking system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. A person with the email `johndoe@gmail.com` exists, and a person with the venue name `Victoria Hall` exists. List all bookings using the `list_booking` command, and verify that the date and duration you intend to book the venue for does not already exist.

    2. Test case: `add_booking e/johndoe@gmail.com`<br>, followed by `Victoria Hall` (when prompted for venue), followed by `Important meeting` (when prompted for description), followed by `business, finance` (when prompted for tags), followed by `2021-01-01 01:00` and `2021-01-01 02:00` respectively when prompted for start and end date.<br>
    Expected: A booking should appear in the list of bookings with the booker by the email "johndoe@gmail.com", venue "Victoria Hall", with a description of "Important meeting", two tags "business" and "finance". The duration booked should also be listed in the same way as the input, which is in a YYYY-MM-DD HH:MM" format.
       
    3. Test case: `add_booking` followed by `example@gmail.com` followed by `Victoria Hall` followed by `For FYP Meeting` followed by `2012-02-01 22:59` followed by `2012-01-31 23:59` followed by `meeting`<br>
    Expected: There should be an error stating that the starting time of a booking should not be later than its ending time.
    
### F.5 Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. List all persons using the `list_person` command. 
      Multiple persons in the list. Person with email `johndoe@gmail.com` is in the list.
      Person with email `nonexistent@gmail.com` is not in the list.

   1. Test case: `delete_person e/johndoe@gmail.com`<br>
      Expected: Person with email `johndoe@gmail.com` is deleted from the list.<br>
      Details of the deleted contact shown in the status message.

   1. Test case: `delete_person e/nonexistent@gmail.com`
      Expected: No person is deleted.
      Error details shown in the status message. 

   1. Other incorrect delete commands to try: `delete_person`, `delete_person x` <br>
      Expected: Similar to previous.


### F.6 Deleting a venue

1. Deleting a venue while all venues are being shown

    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. List all venues using the `list_venue` command.
       Multiple venues in the list. Venue with venue name `Victoria Hall` is in the list.
       Venue with venue name `NonExistent Venue` is not in the list.

    1. Test case: `delete_venue v/Victoria Hall`<br>
       Expected: Venue with venue name `Victoria Hall` is deleted from the list.
       Details of the deleted venue shown in the status message.

    1. Test case: `delete_venue v/NonExistent Venue`<br>
       Expected: No venue is deleted. Error details shown in the status message.

    1. Other incorrect delete venue commands to try: `delete_venue`, `delete_venue x` <br>
       Expected: Similar to previous.


### F.7 Deleting a booking

1. Deleting a booking while all bookings are being shown

    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. At least one booking record in the system (use list_booking to verify). Booking with booking index 1 is in the list. Booking with booking index 22 is not in the list.

    1. Test case: `delete_booking 1`<br>
       Expected: Booking with booking id 1 is deleted from the list.
       Details of successful booking deletion shown in the status message.

    1. Test case: `delete_booking 22`<br>
       Expected: No booking is deleted. Error details shown in the status message.

    1. Other incorrect delete booking commands to try: `delete_booking`, `delete_booking x`, `delete_booking 1032309` (where the input digit exceeds the current index of the booking system, and the current accepted range) <br>
       Expected: Similar to previous.

### F.8 Editing a person

1. Editing a person's information

    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. At least one person's record is in the booking system (use list_person to verify). Add a person with email `johndoe@gmail.com` to the system using `add_person` if the entry does not already exist. Person with email `nonexistent@gmail.com` is not in the list, or delete the entry if it exists using the command `delete_person e/nonexistent@gmail.com`.

    1. Test case: `edit_person eo/johndoe@gmail.com p/90398472`<br>
       Expected: Person with email `johndoe@gmail.com` has his/her phone number edited to 90398472.
       Details of the edited person in the status message.

    1. Test case: `edit_person eo/nonexistent@gmail.com p/90398471`<br>
       Expected: No person is edited.
       Error details shown in the status message that the email address does not exist in the system.

    1. Other incorrect edit commands to try: `edit_person johndoe@gmail.com` (missing prefix), `edit person eo/johndoe@gmail.com` (no fields to be edited) <br>
       Expected: Error details to be shown in the status box corresponding to the respective errors.
       
       
### F.9 Editing a venue
1. Editing a venue's information
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. Venue "Hall" exists in the system. If not, create a venue with the specified name of "Hall".

    1. Test case: `edit_venue vo/Hall v/Court`<br>
       Expected: the name of the venue should change from "Hall" to "Court".
    2. Test case: `edit_venue vo/Hall`<br>
       Expected: No venue is edited. Error details shown in the status box that command format is invalid.
    3. Other incorrect edit commands to try can be followed similarly to the edit command for Persons under F.8 (i.e. missing `vo/` prefix or no edit fields).
    
       
### F.10 Editing a booking
1. Editing a booking's information
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. The booking at index 1 is linked to the venue "Victoria Hall", which exists in the system. A venue by the name of "Hall" exists. A venue by the name of "John" does not.
  
    1. Test case: `edit_booking 1 v/Hall`<br>
       Expected: the name of the booking venue should change from "Victoria Hall" to "Hall".
    2. Test case: `edit_booking 1 v/John`<br>
       Expected: No venue is edited. Error details shown in the status box that the venue does not exist in the system.
    3. Other incorrect edit commands to try: the above test cases can similarly be replicated by editing the booker's email with an email that exists in the system and belongs to a booker, and an email that does not. A similar error should appear notifying users that the booker does not exist if a non-existing (but syntactically correct) email is inputted.

### F.11 Listing all persons
1. Listing all persons in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing persons in the system.
  
    1. Test case: `list_person`<br>
       Expected: The status box should indicate success and all persons are listed.
    2. Test case: `list_person eorifjeoifj` (redundant trailing output) <br>
       Expected: The status box should indicate success and all persons are listed.
    3. Other possible commands to try: delete all persons before running the command `list_person`. 
       Expected: The list of persons should return empty. Bookings made associated with the person, if any, should all be deleted (the system might take a short while to process).
       
### F.12 Listing all venues
1. Listing all venues in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing venues in the system.
  
    1. Test case: `list_venue`<br>
       Expected: The status box should indicate success, and all venues are listed. Bookings made associated with the venue, if any, should all be deleted (the system might take a short while to process).
       
### F.13 Listing all bookings
1. Listing all bookings in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing bookings in the system.
  
    1. Test case: `list_venue`<br>
       Expected: The status box should indicate success, and all bookings are listed.

### F.14 Finding certain persons
1. Filtering and finding persons with certain fields in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing persons in the system. There are two people with the tag "basketballer", and other people without the tag. 
  
    1. Test case: `find_person t/basketballer`<br>
       Expected: All persons with the tag "basketballer" are displayed in a list, and nobody without the tag is displayed.
    2. Test case: `find_person`
       Expected: Error message indicating invalid command format, because at least one attribute to be filtered by must be given.
       
### F.15 Finding certain venues
1. Filtering and finding venues with certain fields in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing venues in the system. There are two venues with the same capacity of 20, and other venues with other capacities that are not 20. 
  
    1. Test case: `find_venue max/20`<br>
       Expected: All venues with a capacity of 20 are displayed in a list, and none with capacities that are not 20 are displayed.
    2. Other commands to try: filtering by multiple fields e.g. both tag and capacity

### F.16 Finding certain bookings
1. Filtering and finding bookings with certain fields in the system
    1. Prerequisites: BookCoin is not in the middle of executing a multi-step command. There are some existing bookings in the system. There are is only one booking on the date "2021-01-01" booked by "johndoe@gmail.com", a valid email belonging to an existing booker in the system. 
  
    1. Test case: `find_booking e/johndoe@gmail.com date/2021-01-01`<br>
       Expected: Only the single booking booked by "johndoe@gmail.com" for the date 1st January 2021 should be displayed. No other bookings made by the same person, or same date, or neither, should be displayed.

### F.17 Accessing help

1. Accessing help feature

    1. Test case: `help`<br>
    Expected: An additional pop up help window should appear with a url to the user guide for Bookcoin.


### F.18 Saving data

1. Dealing with missing/corrupted data files

    1. Test case: corrupt the file `bookingsystem.json` under ./data/. There are many ways to do so, such as inputting invalid values for fields (e.g. adding digits in name fields that can only take in alphabetic characters and spaces). 
    1. Open the jar file by running `java -jar bookingapp.jar` on your terminal.
    Expected: GUI starts up with no data populated.
<br>
    1. Test case: delete the current save file under ./data/.
    1. Open the jar file by running `java -jar bookingapp.jar` on your terminal.
    Expected: GUI starts up with no data populated.

----------------------
## **Appendix G: Effort**

### G.1 Model
The model was much more difficult to implement for BookCoin than for addressbook, because addressbook only revolved around Persons, while we had to manage Persons, Venues and Bookings. The booking class was especially difficult because it depended heavily on the first two classes: what happens to a booking object if we delete its associated person of venue? That was something that we struggled with initally. The modification of the storage format was also non-trivial for us as we did not know how to update and store changes to booking objects from changes in its associated person/ venue accordingly. 

### G.2 Logic
We implemented multi-step commands as we felt that it was too difficult for users to input such a long string of fields for the various `AddCommands`. It was difficult to maintain a stateful command as we had to handle how to exit the command, and other corner cases such as if the user were to input commands as usual during a multi-step command. 

### G.3 UI
It was difficult to create a new UI that could accommodate the listing of three different types of object, so we came up with a new design that would make it easier for users to navigate between the three. 
