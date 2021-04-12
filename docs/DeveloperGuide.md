---
layout: page title: Developer Guide
---
--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of
each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete Alex Yeoh`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
   help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete ")` API
call.

![Interactions Inside the Logic Component for the `delete Alex Yeoh` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/conss/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Customer>` that can be 'observed' e.g. the UI can be bound to this list so
  that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Customer` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Customer` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### Find filter Date of birth

Step 1. User will execute find command Step 2. findcommand will be parsed Step 3. Access the specific DOB filter to
filter the results and list them out on the GUI

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

* Car salesperson working in a multi-brand dealership
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* Manage contacts faster than a typical mouse/GUI driven app

* Offers a higher success rate of closing deals as car salesperson can focus on selling cars that fits <br>
  the customer needs and requirements

* Notify potential customers on promotions and new car launches based on their profile preferences

* Follow-up with ex-customers upon expiry of their COE for new sales opportunity

* More complex search operations, to search for multiple fields at once using logical conjunctions like
  'and', 'or' and 'not', as well as brackets.

* Email integration Contact customers directly from the app instead of using a separate app.


### User stories

Here 'User' refers to a Car Salesperson Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (
unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add a new customer             | add new customers to my existing customer base                         |
| `* * *`  | user                                       | delete an existing customer     | remove customer entries that I no longer require                       |
| `* * *`  | user                                       | exit from the app              | safely exit the app, certain that my data is securely saved            |
| `* * *`  | user                                       | list my customers              | have an overview of my customer contacts                               |
| `* * *`  | user                                       | clear all my customers contacts | start afresh with a empty customer database instead of trying to rectify a legacy database riddle with outdated data|
| `* * *`  | user                                       | filter by car type preference  | find customers who prefer a specific car type easily          |
| `* * *`  | user                                       | filter by car brand preference | find customers who prefer a specific car brand easily         |
| `* * *`  | user                                       | filter by address   | find customers who live near a specific region
| `* * *`  | user                                       | filter by name   | find customers whose name match most closely the phone number I remember
| `* * *`  | user                                       | filter by Date Of Birth   | find customers whose Date of Birth match most closely the phone number I remember
| `* * *`  | user                                       | filter by email   | find customers whose email match most closely the phone number I remember
| `* * *`  | user                                       | filter by phone number   | find customers whose phone number match most closely the phone number I remember
| `* * *`  | user                                       | filter by tags   | find customers who have the specified tags
| `* * *`  | user                                       | repeat the last command | use similar commands without having to type out everything everytime
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* *`    | user                                       | use logical operators and brackets to filter | filter using more complex parameters than just 1 field
| `* *`    | user                                       | list customers with expiring COE   |  have a list of potential customers who might purchase a new car   |
| `*`      | user                                       | email customers | to contact them
| `*`      | user                                       | send customer holiday greetings| maintain cordial relationship with my customer                         |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Car@Leads` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a customer**

**MSS**

1. User requests to list customers.
2. System shows a list of customers.
3. User requests to add a specific customer in the list.
4. System adds the specified customer.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case resumes from step 3

* 3a. The given name is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.

**Use case: Delete a customer**

**MSS**

1. User requests to list customers
2. System shows a list of customers
3. User requests to delete a specific customer in the list
4. System deletes the customer

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given name is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.

**Use case: Find a customer**

**MSS**

1. user requests to find a customer using parameters
2. System returns list of customers who satisfy the parameters.

    Use case ends.

**Extensions**

* 2a. the find parameters do not form a proper expression
    - 2a1. System returns error message

**Use case: List customers**

**MSS**

1. User requests to list customers
2. System shows a list of customers

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: Get Last Command**

**MSS** 
1. User asks for the last command he entered, 
2. System shows the last command
Repeat Steps 1-2, each time displaying the next previous command in command history, till
   user stops 
3. User enters a different command (either valid or not)
4. System executes the given command, if valid.

    Use case ends.

**Extensions**
- 2a.  No more commands to go back, already went back to first command in commandhistory
    2a1. System shows error message.

*{More to be added}*

**Use case: Find customers**

**MSS**

1. User requests to list customers
2. System shows a list of customers
3. User requests to find customer using a car preference filter 
4. System shows a list of filtered customers
   Use case ends.

**Extensions**

* 2a. The list is empty.
  
* 3a. The given filter  is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 customers without a noticeable sluggishness (response time within 100ms) in
   performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **COE**: Certificate of Entitlement for Singapore Cars
* **GUI**: Graphical user interface for the Car@leads app

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Adding a customer

1. Adding a customer while all customers are being shown

    1. Prerequisites: List all customers using the `list` command. Multiple customers in the list.

    1. Test case: `add n/Bob Ang p/88765432 e/bobhnd@example.com a/John street, block 123, #01-01 b/1998 07 10  t/friend c/BMW+Coupe|2030 01 01 c/Porsche+SUV|2030 01 01 cp/MercedesBenz+SUV` <br>
       Expected: contact with name 'Bob Ang' is added to the list. Details of the added contact shown in the
       status message. Timestamp in the status bar is updated.

    1. Test case: `add Alex Yeoh`<br> 
       Expected: No customer is added as Alex Yeoh customer already exist in the loaded date file.<br>
       Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `add`
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Deleting a customer

1. Deleting a customer while all customers are being shown

    1. Prerequisites: List all customers using the `list` command. Multiple customers in the list.

    1. Test case: `delete Alex Yeoh`<br>
       Expected: contact with name 'Alex Yeoh' is deleted from the list. Details of the deleted contact shown in the
       status message. Timestamp in the status bar is updated.

    1. Test case: `delete Invalid@Name`<br>
       Expected: No customer is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Finding a customer by attributes

1. Finding a customer while all customers are being shown

    1. Prerequisites: List all customers using the `list` command. Multiple customers in the list.

    1. Test case: `find n/Alex Yeoh`<br>
       Expected: contact with name 'Alex Yeoh' is listed. Details of the filtered contact is shown in the
       status message. Timestamp in the status bar is updated.

    1. Test case: `find cp/Tesla`<br>
       Expected: contacts whose car brand preference is a 'tesla' will be listed. Details of the filtered contacts is shown in the
       status message. Timestamp in the status bar is updated.

    1. Test case: `find n/Alex Yeoh /and cp/Tesla`<br>
       Expected: contact whose name is 'Alex Yeoh' and carbrand preference is a 'tesla' will be listed. 
       <br> Details of the filtered contacts is shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `find n/`<br>
       Expected: No customer is found. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `find`
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Get previous command

1. After entering a few commands, traverse back command history and get previous command.
    1. Prerequisites: Entered multiple commands previously.

    1. Test case: `/up`<br>
       Expected: Previous command is found and reflected in command line input.

   1. Test case: `/up/up/up`<br>
      Expected: Previous command is found and reflected in command line input. 
      <br> /up do not stack, can only can previous command
   
    1. Test case: `/down`<br>
       Expected: Incorrect format. Error details shown in the status message. Status bar remains the same.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. If data file could be corrupted or unable to load when an empty contact list is displayed during initial start-up
       of the app, try deleting addressbook.json file located at data folder and relaunch the app.

1. _{ more test cases …​ }_

## **Appendix: Effort**
Working on AB3, a brown-field project entails a huge amount of effort and time spend on understanding the architecture of AB3 code, 
<br>this involved reading through the developer guide documentation and making incremental changes initially to ensure every milestone release is a working version.

* A disciplined adherence to the Github forking workflow, where constant communication is required when reviewing pull requests by fellow team members.
* rigorous effort to maintain user guide and developer guide that these static information were up to date with to changes made to existing features.
<br>


1.  1. Achievement: Adding several new fields to the Customer entity, the main focus object of the project
    1. Difficulty level: Medium
    1. Challenges Faced: Considerations of having to edit other classes such as command Parser to ensure that the existing add command, edit commands were working
    1. Effort required: Changes to the customer entity yield regression bugs, thus a pro-active update of test cases to new fields were required.

1.  1. Achievement: Implementing a sophisticated Find feature
    1. Difficulty level: High
    1. Challenges Faced: Implement a typo-matching find filter functionality for user inputs that strive to achieve 
       <br>a good balance between returning results that were an exact match and returning similar results.
    1. Effort required: Introduction of a new filter package to incorporate find functionality and to contain the specific attribute filters,
       <br>adding new testcases for the newly introduced filter classes


1. 1. Achievement: Integrated Email feature
    1. Difficulty level: High
    1. Challenges Faced: Understanding a third party JavaFx librabry (sendEmailJFX) to the project was by no means an easy task
       <br> With reference to AB3, which did not have external library added to give the GUI more features
       <br> We had to figure out how to integrate the third party JavaFx library with existing JavaFx and UI classes code
    1. Effort required: A good spirit of google searches to figure how to integrate the third party code to our GUI and JavaFx code.
 

