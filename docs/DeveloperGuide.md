---
layout: page
title: Developer Guide
---



## **Overview**

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

### Going forward
These 3 design ideals should be adhered to as much as possible when implementing new features for your version of the application.
This Developer Guide aims to provide insights for other developers on how the initial functionalities and system architecture were designed and implemented.

### Table of Contents
* Table of Contents
{:toc}

<br><br>

------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

<br><br>

------------------------------------------------------------------------

## **Definitions**

1. **API** (Application Programming Interface) A set of function declarations as well as explanations on their usage.


2. **Architecture diagram** A type of UML diagram that shows the overall organization of the system and how components are connected with each other.


3. **CLI** (Command line interface) A text-box-like interface which allows a user to enter commands.


4. **GUI** (Graphical user interface) A form of user interface with graphical features such as icons that allows a user to interact with our program.


5. **JavaFx** A software platform for creating and delivering desktop applications, as well as rich Internet applications that can run across a wide variety of devices. We use it to construct our graphical user interface.


6. **Mainstream OS** Windows, Linux, macOS.


6. **MSS** (Main success scenario) The expected flow of events when a use case goes as expected. 


8. **Private contact detail** A contact detail that is not meant to be shared with others.


7. **Sequence diagram** A type of UML diagram that describes a particular instance of components interacting with each other.


8. **UML** (Unified Modeling Language) A standard for creating models and diagrams to visualize the design of a system.


9. **UI** (User interface) An interface for a user to interact with the program.

<br><br>

------------------------------------------------------------------------

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

For example, the `UI` component (see the class diagram given below) defines its API in the `Ui.java` interface and exposes its functionality using the `UiManager.java` class which implements the `Ui` interface. 
<p align="center"><img src="images/UiClassDiagram.png"></p>

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

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are 
in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

<br>

### Logic component

<p align="center"><img src="images/LogicClassDiagram.png"></p>
<p align="center">Overall Logic Class Diagram (due to limitations of PlantUML, the 1 in the Parser box is meant to be the multiplicity from LogicManager to Parser)</p>
<br><br>
<p align="center"><img src="images/ParserClassDiagram.png"></p>
<p align="center">Parser Class Diagram in Logic Component</p>

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `ClientBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a client contact).
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
* stores the shortcut library data.  
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<br>

### Storage component

<p align="center"><img src="images/StorageClassDiagram.png"></p>

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W15-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in JSON format and read it back.
* can save the address book data in JSON format and read it back.
* can save the shortcut library data in JSON format and read it back.

<br><br>

------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Feature to display the insurance policies associated with a selected client

#### Motivation

It would not be a good user experience if there is no easy way for the user to quickly retrieve the insurance policy URLs 
from ClientBook. Since the application's contact card user interface does not support direct copy-paste functionality, a new approach 
to display and facilitate retrieval of this essential information had to be implemented. Below is a succinct but complete 
explanation of how the chosen approach, which is to implement a `PolicyCommand`, works. Other alternatives 
we considered and the design considerations are further elaborated below.

#### Implementation

A new command `PolicyCommand` was created. It extends the abstract class `Command`, overriding and implementing its `execute` 
method. `PolicyCommand#execute()` is called by `LogicManager`, all the insurance policies and their associated policy URLs are fetched from the 
selected `Person` through `Person#getPersonNameAndAllPoliciesInString()`.

Below is an example usage scenario and how the information and data are passed around at each step.

**Step 1.** The user types `policy 1` into the input box and presses enter.

**Step 2.** `MainWindow` receives the `commandText` (`policy 1`), which is then executed by `LogicManager`.

**Step 3.** `ClientBookParser` then parses the full `commandText`, returning a `Command`. In this case, it would return a 
`PolicyCommand`, which would contain the index of the selected client in the displayed list (which in this case is 1).

**Step 4.** `PolicyCommand#execute()` is called by `LogicManager`, returning a `CommandResult`. This `CommandResult` contains the concatenated string 
of all the insurance policies and their associated URLs as the feedback. The `CommandResult` also contains a `boolean` value 
indicating whether a popup window is to be displayed. This `boolean` value can be retrieved using the method `CommandResult#isShowPolicies()`.

**Step 5.** This `CommandResult` is passed back to `MainWindow`, which then checks if the method `CommandResult#isShowPolicies()` 
returns true, and launches the insurance policy window if so.

Below is a sequence diagram illustrating the flow of this entire process.

<p align="center"><img src="images/PolicyCommandDiagram.png"></p>

#### Design Considerations

Since `PolicyCommand` is just one of the many `Commands`, conscious effort had to be made to ensure that the behaviour of 
its methods strictly resembled those of its fellow `Command` classes. 

#### Alternatives considered

* **Alternative 1 (current choice):** Display the insurance policies and their URLs in a popup window, retrieve URL through a 'Copy URL button'
  * Have a popup window to display the insurance policies and their associated URLs.
  * The window should also have a 'Copy URL' button similar to that in the 'help' window that appears when `help` is called.
  * Pros: Easy to implement a button.
  * Cons: Not the best way to display a URL.
    

* **Alternative 2:** Display the insurance policies and their URLs in a popup window, where the URLs launches the browser to the selected webpage upon click
  * Pros: More intuitive in terms of user experience.
  * Cons: Harder to implement.

<br>

### Lock and unlock ClientBook feature

#### Motivation

As an insurance agent, our target user is likely to be always on the go which increases the risk of the user's clients' information being exposed to 
unauthorised accessors. Having a lock function for ClientBook will give the user a peace of mind that all of ClientBook's information is secured by a password.

#### Implementation

A new class `Authentication` was created as part of the `Storage` component. It is responsible for the locking and unlocking of ClientBook. 
Two new commands, `LockCommand` and `UnlockCommand` were created to interface the user with `Authentication`.

Below is an example usage scenario involving the locking of ClientBook and how the information and data are passed around at each step.

**During program launch:** An `Authentication` object is created and attached as an attribute in `ModelManager`.

**Step 1.**  The user locks ClientBook for the first time and enters `lock 1234` into the command box and presses enter.

**Step 2.** `MainWindow` receives the `commandText` (`lock 1234`), which is then executed by `LogicManager`.

**Step 3.** `AddressBookParser` then parses the full `commandText`, returning a `Command`. In this case, it would return a
`LockCommand`, which would contain the password that the user wants to use to lock ClientBook (in this case, 1234).

**Step 4.** `LogicManager` then executes `LockCommand` which makes use of `Authentication` to lock ClientBook. A `CommandResult` is returned.
The `CommandResult` describes whether the locking process was successful.

**Step 5.** This `CommandResult` is passed back to `MainWindow` to reflect the result of the lock command to the user.

Below is a sequence diagram illustrating the flow of this entire process.

<p align="center"><img src="images/LockSequenceDiagram.png"></p>

#### Design Considerations

The lock and unlock feature was designed such that the existing system is totally unaware of any locking and unlocking 
of the existing data file `clientbook.json`. Hence, there is minimal dependency between existing components, the newly added commands and `Authentication`.

<br>

### Feature to allow more options for user to edit insurance policy information of each client in ClientBook

#### Motivation

In the previous implementation of the `EditCommand`, each time a user edits a client's policy information, the user's only option is to
replace the client's entire existing policy list with the specified policies. This enhancement of the `EditCommand` gives the user
the option to append, replace, remove or modify specific policies within a client's policy list.

#### Implementation

A new enumeration `EditPolicyMode` was created within the `EditCommand` class. It provides the developer with an enumeration of
modes to notify the program of the different ways of editing a client's policy list, namely `MODIFY`, `APPEND`, `REPLACE` and `REMOVE`.
This editing mode is parsed from the user input, and then passed as an argument to the constructor of `EditCommand` to specify how 
the client's policy list should be edited.

Within the `EditCommand#execute` method, a new `Person` object is created through the `EditCommand#createEditedPerson` method.
This method creates the updated policy list of the new `Person` object based on the specified `EditPolicyMode`.
The created `Person` is then used to update the model, which in turn updates the view and shows the change to the user.

Below is an example usage scenario and how the information and data are passed around at each step.

**Step 1.** The user types `edit 1 n/Tom Doe i/P12345 i/P54321 -insert` into the input box.

**Step 2.** `MainWindow` receives the `commandText` (`edit 1 n/Tom Doe i/P12345 i/P54321 -insert`), 
which is then executed by `LogicManager`.

**Step 3.** `ClientBookParser` then parses the full `commandText`, returning a `Command` object. In this case, it would return an
`EditCommand`, which would contain the index of the selected client in the displayed list (in this case 1), followed by
the values that the user intends to edit, followed by the edit policy mode (in this case insert).

**Step 4.** `EditCommand#execute()` is called by `LogicManager`, returning a `CommandResult`. This `CommandResult` contains the feedback string message
which indicates to the user which client was edited.

**Step 5.** This `CommandResult` is passed back to `MainWindow`, which then displays the list after the edit to the user.

Below is a sequence diagram illustrating the flow of this entire process.

<p align="center"><img src="images/EditSequenceDiagram.png"></p>

#### Design Considerations

`EditPolicyMode` is implemented as an inner class within `EditCommand`, as it is not used anywhere else in the application.
If future extensions require the use of `EditPolicyMode` in other areas, it is recommended to make `EditPolicyMode` into a
an outer class instead.

#### Implementation/Testing Considerations

Compared to other commands, the `edit` command takes many arguments of varying types, so extra care should be taken during the
parsing of its arguments and extensive testing should be done on the varying argument types.

<br>

### Sort list of clients in ClientBook feature

#### Motivation

As an insurance agent, our target user may have many clients and might need a way to organise the list of clients in 
ClientBook. Having a sort function will allow the user to sort the list of clients to make it more organised.

#### Implementation

A new command `SortCommand` was created. It extends the abstract class `Command`, overriding and implementing its `execute`
method. When `SortCommand#execute()` is called by `LogicManager`, a comparator will be created based on the attribute and direction specified
by the user and `ModelManager#updateSortedPersonList(comparator)` is called to sort the list of clients.

Below is an example usage scenario and how the information and data are passed around at each step.

**Step 1.** The user types `sort -n -asc` into the input box and presses enter.

**Step 2.** `MainWindow` receives the `commandText` (`sort -n -asc`), which is then executed by `LogicManager`.

**Step 3.** `ClientBookParser` then parses the full `commandText`, returning a `Command`. In this case, it would return 
a `SortCommand`, which would contain the attribute to be sorted (in this case name), followed by the direction that the 
user intends to sort in (in this case ascending order).

**Step 4.** `LogicManager` then calls `SortCommand#execute()`, sorting the list of clients by calling `ModelManager#updateSortedPersonList(comparator)` 
with the comparator created and returning a `CommandResult`. This `CommandResult` 
contains the feedback string message which indicates to the user how the list of clients is sorted.

**Step 5.** This `CommandResult` is passed back to `MainWindow`, which then displays the sorted list of clients.

Below is a sequence diagram illustrating the flow of this entire process.

<p align="center"><img src="images/SortSequenceDiagram.png"></p>

#### Design Considerations

The sort feature was designed such that the original list of clients is modified and the list will remain modified after 
other commands are executed. The list of clients in the existing data file `clientbook.json` is also modified for the 
list in the storage organised too.

<br>

### Create a shortcut in ClientBook feature

#### Motivation

As an insurance agent, our target user is likely to always be meeting up with clients to discuss about their portfolios 
and may want to have a faster way to use ClientBook to avoid wasting the clients' time. Having a `Shortcut` feature for 
ClientBook will give the user a way to be more efficient during meetings with clients.

#### Implementation

A new command `AddShortcutCommand` was created. It extends the abstract class `Command`, overriding and implementing its
`execute` method. When `AddShortcutCommand#execute()` is called by `LogicManager`, a `Shortcut` is added to the `ShortcutLibrary`. When a 
`Shortcut` is added, there will be a check for any existing `Shortcut`s with the same name.


Below is an example usage scenario and how the information and data are passed around at each step.

**Step 1.** The user types `addshortcut sn/aia sc/find i/aia` into the input box and presses enter.

**Step 2.** `MainWindow` receives the `commandText` (`addshortcut sn/aia sc/find i/aia`), which is then executed by 
`LogicManager`.

**Step 3.** `ClientBookParser` then parses the full `commandText`, returning a `Command`. In this case, it would return 
a `AddShortcutCommand`, which would contain the name of the `Shortcut` (in this case `aia`), followed by the `Command` 
mapped to the `Shortcut` (in this case `find i/aia`).

**Step 4.** `LogicManager` then calls `AddShortcutCommand#execute()`, storing the `Shortcut` in the `ShortcutLibrary` and returning a 
`CommandResult`. This `CommandResult` contains the feedback string message which indicates to the user whether the 
specified `Shortcut` has been added successfully.

**Step 5.** This `CommandResult` is passed back to `MainWindow` to be displayed to the user through the `ResultDisplay`.

Below is a sequence diagram illustrating the flow of this entire process.

<p align="center"><img src="images/ShortcutSequenceDiagram.png"></p>

#### Design Considerations

The `Shortcut` feature was designed such that the `ShortcutLibrary` is stored separately from the `AddressBook` in 
`shortcutlibrary.json`. Hence, there is minimal dependency between existing components and components of the `Shortcut` 
feature. It was also implemented in a way that there are checks performed to detect duplicate `Shortcut`s and the 
validity of the `Command`s mapped to the `Shortcut`s.

<br>

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

**Step 2**. The user executes `delete 5` command to delete the 5th client contacts in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<p align="center"><img src="images/UndoRedoState1.png"></p>

<br>

**Step 3**. The user executes `add n/David …​` to add a new client contacts. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<p align="center"><img src="images/UndoRedoState2.png"></p>
<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.
</div>
<br>

**Step 4**. The user now decides that adding the client contacts was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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

**Step 6**. The user executes `batch delete` on all client contacts, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<p align="center"><img src="images/UndoRedoState5.png"></p>

<br>

The following activity diagram summarizes what happens when a user executes a new command:

<p align="center"><img src="images/CommitActivityDiagram.png" height="90%"></p>

#### Design consideration

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the client contact being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

<br><br>

------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

<br><br>

------------------------------------------------------------------------

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
| `* * *`  | forgetful user                             | store many clients details     | remember them easily                                       |
| `* * *`  | first time user                            | find out how to use ClientBook | familiarise myself with the application                                        |
| `* * *`  | insurance agent                            | find clients by insurance policy    | find my clients who share the same insurance policy                    |
| `* * *`  | insurance agent                            | link contact to portfolio      | access my clients' portfolio  easily                                                     |
| `* * *`  | insurance agent                            | edit individual client details |                               |
| `* * *`  | insurance agent                            | view all the insurance policies that a particular client signed with me | prepare the policy documents before meeting up with them   |
| `* * *`  | busy insurance agent                       | change the policy ID of a policy co-owned by multiple clients | save time by not having to edit policy ID for each client individually   |
| `* *`    | disorganised user                          | display only certain attributes queried| avoid cluttering the screen with unnecessary information               |
| `* *`    | insurance agent                            | sort my clients                | see my clients in a more organized way                                 |
| `* *`    | insurance agent on the go                  | lock ClientBook with a password| prevent the leakage of my clients' information                         |
| `* *`    | insurance agent                            | schedule meetings with clients | check what meetings I have with my clients                             |
| `* *`    | busy insurance agent                       | edit details common to multiple clients at once | save time by not having to edit details for each client individually |
| `* *`    | busy insurance agent                       | delete multiple client contacts at once | save time by not having to delete each client individually |
| `*`      | busy insurance agents                      | have access to keyboard commands e.g. CTRL + J | minimize time spent typing                         |
| `*`      | tech-savvy user                            | create my own custom commands  | bookmark some commands which I frequently use into a simpler format.   |

### Use cases

(For all use cases below, the **System** is the `ClientBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case 1: List all clients**

**MSS**

1.  User requests to list clients.

2.  ClientBook shows a list of clients.

    Use case ends.

<br>

**Use case 2: Delete a client**

**MSS**

1.  <ins>User lists clients (Use case 1).</ins>
    
1.  User requests to delete a specific client in the list.
    
1.  ClientBook deletes the client.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. One or more of the given arguments are invalid.

    * 2a1. ClientBook shows an error message.

      Use case resumes at step 2.
    
<br>

**Use case 3: Add a client**

**MSS**

1.  User requests to add a client.
    
1.  ClientBook adds the client.

    Use case ends.

**Extensions**

* 1a. One or more of the given arguments are invalid.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.

<br>

**Use case 4: Edit a client**

**MSS**

1.  <ins>User lists clients (Use case 1).</ins>
    
2.  User requests to edit a specific client in the list.
    
3.  ClientBook edits the client.

    Use case ends.

**Extensions**

* 1a. The list of clients is empty.

  Use case ends.

* 2a. The user input does not follow the format required.

    * 2a1. ClientBook shows an error message.

      Use case resumes at step 2.

* 2b. One or more of the given arguments are invalid.

    * 2b1. ClientBook shows an error message.

      Use case resumes at step 2.

<br>

**Use case 5: Find clients**

**MSS**

1.  User requests to find clients with keywords.
    
2.  ClientBook shows a list of clients that matches keywords.

    Use case ends.

**Extensions**

* 1a. One or more of the given arguments are invalid.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.

<br>

**Use case 6: Display selected attributes of clients**

**MSS**

1.  User requests to display selected attributes of clients.
    
2.  ClientBook shows a list of clients and the specified attributes.

    Use case ends.

**Extensions**

* 1a. One or more of the given arguments are invalid.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.

<br>

**Use case 7: Sort list of clients**

**MSS**

1.  User requests to sort clients with the specified attribute and direction.
    
2.  ClientBook shows the sorted list of clients.

    Use case ends.

**Extensions**

* 1a. The user input does not follow the format required.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.

* 1b. One or more of the given arguments are invalid.

    * 1b1. ClientBook shows an error message.

      Use case resumes at step 1.

<br>

**Use case 8: Schedule a meeting with a client**

**MSS**

1.  <ins>User lists clients (Use case 1).</ins>
    
2.  User requests to schedule a meeting with a specific client in the list.
    
3.  ClientBook schedules a meeting with the client.

    Use case ends.

**Extensions**

* 2a. One or more of the given arguments are invalid.

    * 2a1. ClientBook shows an error message.

      Use case resumes at step 2.

<br>

**Use case 9: Lock ClientBook**

**MSS**

1. User requests to lock ClientBook.
   
2. ClientBook is locked.

    Use case ends.

**Extensions**

* 1a. ClientBook is already locked but user has not entered the current password.
  
    * 1a1. ClientBook shows an error message. 
      
        Use case resumes at step 1.
    
* 1b. ClientBook is already locked and user enters the incorrect current password.
  
    * 1b1. ClientBook shows an error message. 
      
        Use case resumes at step 1.

<br>

**Use case 10: Unlock ClientBook**

**Preconditions:** ClientBook is already locked.

**MSS**

1. User requests to unlock ClientBook.
   
2. ClientBook is unlocked.

    Use case ends.

**Extensions**

* 1a. User enters the incorrect password.
  
    * 1a1. ClientBook shows an error message. Use case resumes at step 1.
    
<br>

**Use case 11: Delete a shortcut**

**MSS**

1.  User requests to delete a specific shortcut in the shortcut library.

2.  ClientBook deletes the shortcut.

    Use case ends.

**Extensions**

* 1a. The given shortcut name is invalid.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.

<br>

**Use case 12: Add a shortcut**

**MSS**

1.  User requests to add a shortcut.

2.  ClientBook adds the shortcut.

    Use case ends.

**Extensions**

* 1a. One or more of the given arguments are invalid.

    * 1a1. ClientBook shows an error message.
    
      Use case resumes at step 1.

<br>

**Use case 13: List all shortcuts**

**MSS**

1.  User requests to list shortcuts.

2.  ClientBook shows the shortcut library.

    Use case ends.

<br>

**Use case 14: Edit a shortcut**

**MSS**

1.  One or more of the given arguments are invalid.

2.  ClientBook edits the shortcut.

    Use case ends.

**Extensions**

* 1a. At least one of the given arguments is invalid.

    * 1a1. ClientBook shows an error message.

      Use case resumes at step 1.
    
<br>

**Use case 15: Clear the shortcut library**

**MSS**

1.  User requests to clear the shortcut library.

2.  ClientBook clears the shortcut library.

    Use case ends.

<br>

**Use case 16: View insurance policies of selected client**

**MSS**

1. <ins>User lists clients (Use case 1).</ins>

2. User requests to display policies associated with a selected client.

3.  ClientBook shows all policies associated with this client.

    Use case ends.

**Extensions**

* 2a. The selected client has no policies.

    * 2a1. ClientBook shows message indicating that no policies are associated with the selected client.

      Use case ends.

* 2b. One or more of the given arguments are invalid.

    * 2b1. ClientBook shows an error message.

      Use case resumes at step 2.

<br>

**Use case 17: Retrieve URL for policy**

**MSS**

1. <ins>User lists clients (Use case 1).</ins>

2. <ins>User views insurance policies of selected client (Use case 16)</ins>.

3. User retrieves URL from ClientBook.

   Use case ends.

<br>

**Use case 18: Batch edit client details**

**MSS**

1. <ins>User lists clients (Use case 1).</ins>

2. User requests to change the details of several clients.

3. ClientBook updates the details.

    Use case ends.

**Extensions**

* 2a. One or more of the given arguments are invalid.

    * 2a1. ClientBook shows an error message.

      Use case resumes at step 2.

<br>

**Use case 19: Batch delete client contacts**

**MSS**

1. <ins>User lists clients (Use case 1).</ins>

2. User requests to delete several clients at once.

3. ClientBook deletes the client contacts.

   Use case ends.

**Extensions**

* 2a. One or more of the given arguments are invalid.

    * 2a1. ClientBook shows an error message.

      Use case resumes at step 2.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
    
2.  Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
    
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
    
4.  Should be able to have the client information stored in a file that can easily transfer/share between computers.
    
5.  Should be able to use ClientBook even if there is no internet around the vicinity.
    
6.  Should be able to have ClientBook stay on for a long period of time.

<br><br>

------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-secondary">
:information_source: **Note:** These instructions only provide a starting point for testers to work on;
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
      
### Viewing a client's policies

1. Displaying all policies associated with a selected client who has no insurance policies

    *  Prerequisites: 
       * List all client contacts using the list command. 
       * Multiple client contacts in the list.
    
    * Test case: `policy 2`
       * Expected: A small window pops up, with a message that says the selected client has no policies currently.

1. Displaying all policies associated with a selected client who has insurance policies

    * Prerequisites: 
       * List all client contacts using the list command. 
       * Multiple client contacts in the list. 
       * Client to be selected should have at least 1 insurance policy.

    * Test case: `policy 3`
       * Expected: A small window pops up, displaying the insurance policies associated with the selected client. If the insurance policies have URLs, a "Copy URL" button will be displayed beside the URL.

    * Test case: `policy 0`
       * Expected: No display window appears. Error details shown in the status message.
 
    * Other incorrect policy commands to try: `policy`, `policy x`, `...` (where x is larger than the list size)
       * Expected: Similar to previous.

### Deleting a client contact

1. Deleting a client contact while all client contacts are being shown

   * Prerequisites: 
      * List all client contacts using the `list` command. 
      * Multiple client contacts in the list.

   * Test case: `delete 1`
      * Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   * Test case: `delete 0`
      * Expected: No client contact is deleted. Error details shown in the status message. Status bar remains the sameass

   * Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)
      * Expected: Similar to previous.

### Saving data

1. Dealing with missing data files

   * Prerequisites: 
      * Remove the clientbook.zip file from the folder with name 'data' in the same directory as your clientbook.jar file. 
      * Ensure that the data folder is empty.
    
   * Test case: Launch ClientBook
      * Expected: ClientBook launches and loads the data of the sample contacts.

