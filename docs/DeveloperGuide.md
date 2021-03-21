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
## **Target User Profile**

The target user profile are dog school managers that own and manage the daily operations of the dog schools. They handle a wide range of operations such as keeping track of the dogs under their care, arranging classes and taking care of the dogs on a daily basis. They need a systematic way of maintaining their handle on the operations of their school at all times.

## **Value Proposition**

In Singapore, dog schools are popular among dog owners. Besides day care, they also provide training, grooming and workshops. With many moving parts daily, managing operations  can get overwhelming. PawBook is an all-in-one management system to help dog school managers keep track of attendance, scheduling and services and maintain organisation.

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-T10-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/MainApp.java). It is responsible for,
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
[`Ui.java`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `EntityListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/logic/Logic.java)

1. `Logic` uses the `PawbookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a owner).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete owner 1")` API call.

![Interactions Inside the Logic Component for the `delete owner 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Entity>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Entity` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Entity` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-T10-1/tp/blob/master/src/main/java/dog/pawbook/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `pawbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Adding/deleting feature
Pawbook manages more than one type of entity, each with their own unique attributes. An OOP approach is used here whereby both the `AddCommand` and `DeleteCommand` are generic classes that extends the `Command` class. This way any number of other classes extending `Entity` can be added/deleted as well.

The actual execution of these commands are largely the same and can be easily reimplemented to include more verification to the data if necessary, e.g. verifying that the owner ID refers to an actual owner instead of taking in an arbitrary number.

In order to generate the respective commands, the raw input needs to be parsed first. It is required that the user provide a second keyword right after the `add`/`delete` command keyword to indicate the correct entity type to be added. Using this information, the arguments can be forwarded to the correct parser from within `PawbookParser` to be further processed. 

Below is an example activity diagram for a valid add command from the user, it is similar for the delete command as well.

![AddDeleteActivityDiagram](images/AddDeleteActivityDiagram.png)

#### Alternate implementations


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

Step 2. The user executes `delete 5` command to delete the 5th owner in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new owner. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the owner was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the owner being deleted).
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

### Product Scope

**Target user profile**:

* has a need to manage a significant number of dogs and owners
* prefer desktop apps over other types
* is a fast typist
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* manage contacts faster than a typical mouse/GUI driven app
* saves significant time for the business owner, who beforehand had to manage the details of dogs and owners
* consolidates information on dogs, owners and programs into one place


### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | Dog school manager   | Create a list of dogs and their respective owners in the dog school        | Keep track of the dogs we are responsible for     |
| `* * *`  | Busy owner          | Delete dog profiles and owner profiles            | Remove dogs or owners that are no longer in the school                                                           |
| `* * *`  | Dog school manager   | Add dog profiles and owner profiles             |  Add new dogs or owners that join the school         |
| `* * *`  | Dog school manager   | Get instructions         | Get help when I do not know how to use the application |
| `* * *`  | Dog school manager   | Exit the application   |           |
| `* *`    | Dog school manager   | Edit a dog profile when the information is wrong/outdated |                                             |
| `* *`    | Dog school manager   | Create a dog program for the dog students            |                                                  |
| `* *`    | Dog school manager   | Enrol dogs into a specific dog program | Dogs who recently joined a program are added to the class list |
| `* *`    | Dog school manager   | Drop dogs out of a specific dog program | Dogs that have left the class are no longer in the class list  |
| `* *`    | Advanced user        | Edit in bulk quickly without having to be familiar with the app | Minimize chance of someone else seeing them by accident |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Pawbook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a dog/owner profile or program**

**MSS**

1.  User request to add a dog/owner profile or program to the list.
2.  Pawbook adds the dog/owner.

    Use case ends.

**Extensions**

* 1a. Missing mandatory dog/owner/program details.

    * 1a1. Pawbook shows an error message.
    * 1a2. User supplies missing details.

      Use case resumes at step 2.

*Note:* The mandatory details here refer to name, breed, owner ID for dogs; name, phone number, email and address for owners; name and time for programs.

**Use case: UC02 - Delete a dog/owner profile or program**

**MSS**

1.  User requests to delete a specific dog/owner/program in the list.
2.  Pawbook deletes the dog/owner/program.

    Use case ends.

**Extensions**

* 1a. The given dog/owner/program ID is invalid or not specified.

    * 1a1. Pawbook shows an error message.
    * 1a2. User supplies the corrected dog/owner/program ID.

      Use case resumes at step 2.

**Use case: UC03 - List**

**MSS**

1.  User requests to list dogs with a given tag.
2.  Pawbook lists the related dogs.

    Use case ends.

**Use case: UC04 - Enrol dog to a program**

**MSS**

1.  User requests to enrol a dog to a program.
2.  Pawbook enrol the dog to the correct program.

    Use case ends.

**Extensions**

* 1a. The program ID is invalid/not specified.

    * 1a1. Pawbook shows an error message.
    * 1a2. User supplies correct program ID.

      Use case resumes at step 2.

**Use case: UC05 - Drop a dog from a program**

**MSS**

1.  User requests to drop a dog from a program.
2.  Pawbook drop a dog from a program.

    Use case ends.

**Extensions**

* 1a. The dog/program ID is invalid/not specified.

    * 1a1. Pawbook shows an error message.
    * 1a2. User supplies correct dog/program ID.

      Use case resumes at step 2.

**Use case: UC06 - View schedule**

**MSS**

1.  User requests to view schedule.
2.  Pawbook shows the schedule.

    Use case ends.

**Use case: UC07 - View instructions**

**MSS**

1.  User requests to view instructions.
2.  Pawbook shows the list of instructions available.

    Use case ends.

**Use case: UC08 - Exit**

**MSS**

1.  User requests to exit the program.
2.  Pawbook shows goodbye message.
3.  Pawbook terminates.

    Use case ends.


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has `Java 11` or above installed.
2.  Should be able to hold up to 1000 dogs, owners and dog programs without a noticeable sluggishness in performance for typical usage.
3.  Should be usable by a tech novice who is not familiar with CLI.
4.  Should respond within 2 seconds.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6.  A simple interface that is easy to navigate.
7.  Not required to handle finance-related bookkeeping.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

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

### Deleting a owner

1. Deleting a owner while all owners are being shown

   1. Prerequisites: List all owners using the `list` command. Multiple owners in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No owner is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
