---
layout: page
title: Developer Guide
---
ClientBook is an application for managing client contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
Aimed at insurance agents who are always on the move, ClientBook aims to provide the following utilities:
* Minimise usage of the trackpad
* Minimise resource consumption of ClientBook
* Provide minimal yet essential functionalities to ClientBook

### Minimise usage of trackpad
ClientBook aims to minimise the use of the trackpad and process commands essential to contact management purely through keyboard inputs. 
We believe that minimising the use of the trackpad can improve the efficiency of the user.

### Minimise resource consumption of ClientBook
As an application that is meant to be used on the go, our end-users might not necessarily have easy access to power plugs. 
Hence, the application should be kept lightweight and not consume significant resources (e.g. battery, RAM).

### Provide minimal yet essential functionalities to ClientBook
ClientBook would not be complete without a set of features that help our end-users be more productive while not at their office desks. 
We have also seen the problems that come with overly complicated user interfaces - users tend to get confused easily and might hence not take full advantage of what the program can do for them.
ClientBook thus aims to keep things simple, by only providing what is essential, so as not to confused our end-users.

### Going forward,
These 3 design ideals should be adhered to as much as possible when implementing new features for your version of the application.
This Developer Guide aims to provide insights for other developers on how the initial functionalities and system architecture were designed and implemented.


* Table of Contents
{:toc}

<br>
<br>

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

<br>
<br>

## **Definitions**

1. **API** (Application Programming Interface) A set of function declarations as well as explanations on their usage.


2. **Architecture diagram** A type of UML diagram that shows the overall organization of the system and how components are connected with each other.


3. **CLI** (Command Line Interface) A text box like interface which allows a user to enter commands.


4. **GUI** (Graphical user interface) A form of user interface with graphical features such as icons that allows a user to interact with our program.


5. **JavaFx** A software platform for creating and delivering desktop applications, as well as rich Internet applications that can run across a wide variety of devices. We use it to construct our graphical user interface.


6. **Mainstream OS** Windows, Linux, Unix, OS-X


6. **MSS** (Main Success Scenario) The expected flow of events when a use case goes as expected. 


8. **Private contact detail**: A contact detail that is not meant to be shared with others


7. **Sequence diagram** A type of UML diagram that describes a particular instance of components interacting with each other.


8. **UML** (Unified Modeling Language) A standard for creating models and diagrams to visualize the design of a system.


9. **UI** (User Interface) An interface for a user to interact with the program.


<br>
<br>

## **Design**

### Architecture

ClientBook consists of 4 primary components which are the UI, Logic, Model, and Storage. The components interact with one another to form a cohesive system.


<p align="center"><img src="images/ArchitectureDiagram.png" width="450" /></p>
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

The architecture diagram given above explains the high-level design of the App. Given below is a quick overview of each component.

<br>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

<br>

**`Commons`** represents a collection of classes used by multiple other components.

<br>
The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

<br>
Each of the four components

* defines its *API* in an interface with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class which implements the corresponding API interface mentioned in the previous point.

<br>

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.
<p align="center"><img src="images/LogicClassDiagram.png"></p>

<br>

**How the architecture components interact with each other**

The sequence diagram below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<p align="center"><img src="images/ArchitectureSequenceDiagram.png" width="574" /></p>

The sections below give more details of each component.

<br>

### UI component
<p align="center"><img src="images/UiClassDiagram.png"></p>

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

<br>

### Logic component

<p align="center"><img src="images/LogicClassDiagram.png"></p>

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

<p align="center"><img src="images/DeleteSequenceDiagram.png"></p>
<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>
<br>

### Model component
<p align="center"><img src="images/ModelClassDiagram.png"></p>

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<br>

### Storage component

<p align="center"><img src="images/StorageClassDiagram.png"></p>

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in JSON format and read it back.
* can save the address book data in JSON format and read it back.

<br>
<br>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

<br>

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

**Step 1**. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<p align="center"><img src="images/UndoRedoState0.png"></p>

<br>

**Step 2**. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<p align="center"><img src="images/UndoRedoState1.png"></p>

<br>

**Step 3**. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<p align="center"><img src="images/UndoRedoState2.png"></p>
<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.
</div>
<br>

**Step 4**. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<p align="center"><img src="images/UndoRedoState3.png"></p>

<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.
</div>
<br>

The following sequence diagram shows how the undo operation works:

<p align="center"><img src="images/UndoSequenceDiagram.png" width="90%"></p>
<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.
</div>


**Step 5**. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<p align="center"><img src="images/UndoRedoState4.png"></p>

<br>

**Step 6**. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<p align="center"><img src="images/UndoRedoState5.png"></p>

<br>

The following activity diagram summarizes what happens when a user executes a new command:

<p align="center"><img src="images/CommitActivityDiagram.png" height="90%"></p>

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

<br>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

<br>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* tech-savvy insurance agents
* have to manage large number of client related information
* mainly use laptops on the go

**Value proposition**:
* manage contacts faster than a typical mouse/GUI driven app
* a central avenue to store information about their clients
* typing-based easier to use than the trackpads
* do not allow the user to insert attachments or group policies in our application.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                | So that I can…​                                                     |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new client               |                                                                        |
| `* * *`  | user                                       | delete a client                | remove the contact of a client that I no longer serve                                   |
| `* * *`  | user                                       | find a client by name          | locate details of clients without having to go through the entire list |
| `* * *`  | forgetful user                             | store many clients details     | so that I do not have to remember them                                                   |
| `* * *`  | first time user                            | find out how to use ClientBook | familiarise myself with the application                                        |
| `* * *`  | insurance agent                            | find clients by insurance policy    | find my clients who share the same insurance policy                    |
| `* * *`  | insurance agent                            | link contact to portfolio      | access my clients' portfolio  easily                                                     |
| `* * *`  | insurance agent                            | edit individual client details |                               |
| `* *`    | disorganised user                          | display only certain attributes queried| avoid cluttering the screen with unnecessary information               |
| `* *`    | insurance agent                            | sort my clients                | see my clients in a more organized way                                 |
| `* *`    | insurance agent on the go                  | lock ClientBook with a password| prevent the leakage of my clients' information                         |

### Use cases

(For all use cases below, the **System** is the `ClientBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case 1: Delete a client**

**MSS**

1.  User requests to list clients.
    
1.  ClientBook shows a list of clients.
    
1.  User requests to delete a specific client in the list.
    
1.  ClientBook deletes the client.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ClientBook shows an error message.

      Use case resumes at step 3.
    
<br>

**Use case 2: Add a client**

**MSS**

1.  User requests to add a client.
    
1.  ClientBook adds the client.

    Use case ends.

**Extensions**

* 1a. The user input does not follow the format required.

  * 1a1. ClientBook shows an error message.

    Use case resumes at step 1.

<br>

**Use case 3: List all clients**

**MSS**

1.  User requests to list clients.
    
2.  ClientBook shows a list of clients.

    Use case ends.

**Extensions**

2a. The list is empty.

  Use case ends.

<br>

**Use case 4: Edit a client**

**MSS**

1.  User requests to list clients.
    
2.  ClientBook shows a list of clients.
    
3.  User requests to edit a specific client in the list.
    
4.  ClientBook edits the client.

    Use case ends.

**Extensions**

* 2a. The list of clients is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. ClientBook shows an error message.

      Use case resumes at step 3.

<br>

**Use case 5: Find a client**

**MSS**

1.  User requests to find clients with keywords.
    
2.  ClientBook shows a list of clients that matches keywords.

    Use case ends.

**Extensions**

* 2a. The list of matched clients is empty.

  Use case ends.

<br>

**Use case 6: Filter list of clients**

**MSS**

1.  User requests to filter clients with details.
    
2.  ClientBook shows a list of clients that matches details.

    Use case ends.

**Extensions**

* 2a. The list of matched clients is empty.

  Use case ends.

<br>

**Use case 7: Sort list of clients**

**MSS**

1.  User requests to sort clients with the specified attribute and direction.
    
2.  ClientBook shows the sorted list of clients.

    Use case ends.

**Extensions**

* 2a. The list of clients is empty.

  Use case ends.

<br>

**Use case 8: Lock ClientBook**

**MSS**

1. User requests to lock ClientBook.
   
2. ClientBook is locked.

    Use case ends.

**Extensions**

* 1a. ClientBook is already locked but user did not enter the current password.
  
    * 1a1. ClientBook shows an error message. Use case resumes at step 1. <br>
    
* 1b. ClientBook is already locked and user entered the incorrect current password.
  
    * 1b1. ClientBook shows an error message. Use case resumes at step 1.

<br>

**Use case 9: Unlock ClientBook**

**MSS**

1. User requests to unlock ClientBook.
   
2. ClientBook is unlocked.

    Use case ends.

**Extensions**

* 1a. User enters the incorrect current password that is used to lock ClientBook.
  
    * 1a1. ClientBook shows an error message. Use case resumes at step 1.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
    
2.  Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
    
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
    
4.  Should be able to have the client information stored in a file that can easily transfer/share between computers.
    
5.  Should be able to use ClientBook even if there is no internet around the vicinity.
    
6.  Should be able to have ClientBook stay on for a long period of time.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.


### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_
