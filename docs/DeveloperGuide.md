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
## **Introduction**
**Tutor Tracker** is a desktop tuition management application meant for secondary students to track their tuition information, such as upcoming tuition appointments and tutor's contact information.
It focuses on the Command Line Interface (CLI) while providing users with a simple and clean Graphical User Interface (GUI).
Thus, the main interaction with **Tutor Tracker** will be done through commands.

Tutor Tracker is an all-in-one tuition management solution for tech-savvy secondary school students.
The features of Tutor Tracker includes:

- Viewing of tutors' profile
- Adding new tuition appointments
- Filtering tutors by personal preference (i.e. availability, experiences, name, location, price, etc.)
- Viewing upcoming tuition appointments

The purpose of this Developer Guide is to help you understand the design and implementation of Tutor Tracker to get started on your contributions to Tutor Tracker.

## **Design**
This section will help you learn more about the design and structure of Tutor Tracker. Each section will describe and explains how each component of the application works.
It's noteworthy to mention that the Tutor Tracker follows an Object-Oriented Programming paradigm.


### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

_Figure 1. Architecture Diagram of Tutor Tracker_


The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

The application consists of 6 main components:

| Component                           | Description
| ----------------------------------- | -------------------------------------------------------------------- |
| `Main`                              | **`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). <br/>It is responsible for: <li>At app launch: Initializes the components in the correct sequence, and connects them up with each other.</li> <li>At shut down: Shuts down the components and invokes cleanup methods where necessary.</li>|
| [**`UI`**](#ui-component)           | The UI of the App.                                                   |
| [**`Logic`**](#logic-component)     | The command executor.                                                |
| [**`Model`**](#model-component)     | Holds the data of the App in memory.                                 |
| [**`Storage`**](#storage-component) | Reads data from, and writes data to, the hard disk.                  |
| [**`Commons`**](#common-classes)    | Represents a collection of classes used by multiple other components.|

For each of `UI`, `Logic`, `Model` and `Storage` component, it
* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_appointment 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103-T14-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `TutorTrackerParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an appointment).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete_appointment 1")` API call.

![Interactions Inside the Logic Component for the `delete appointment_1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : `Model.java`

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the TutorTracker data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TutorTracker`, which `Person` references. This allows `TutorTracker` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : `Storage.java`

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the Tutor Hunter details in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.TutorTracker.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### [Proposed] Favourite Feature
####Proposed Implementation
The proposed favourite feature is to facilitate the user to keep track of his/her favourites 
out of the entire list of tutors. It implements the following operations:
* `Favourite tutor` - Add a tutor to the list of favourite tutors.
* `Unfavourite tutor` - Delete the tutor from the list of favourite.
* `List favourites` - Show the list of the favourite tutor.

These operations are exposed in the `Logic` interface by parsing respective `FavouriteCommand`,
`UnfavouriteCommand` and `ListFavouriteCommand`.

Given below are example usage scenarios and how the favourite feature behaves at each step.

### [Proposed] Note Feature
####Proposed Implementation
The proposed note feature is to facilitate the user to keep track of his/her own notes of different 
tutors and appointments. Additionally, it implements the following operations:
* `Add note` - Add a note
* `Delete note` - Delete a note
* `Edit note` - Edit a note

Given below are example usage scenarios and how the note feature behaves at each step.

### [Proposed] Gradebook Feature
####Proposed Implementation
The proposed gradebook feature is to facilitate the user to keep track of his/her
own grades of different subjects for reference, which are internally stored as `gradeList`. Additionally,
it implements the following operations:  
* `Add a subject grade` - Add a subject grade to user's gradebook
* `Delete a subject grade` - Delete a subject grade by subject name
* `Edit a subject grade` - Edit a subject grade by subject name
   
These operations are exposed in the `Logic` interface by parsing respective `AddGradeCommand`, 
`DeleteGradeCommand` and `EditGradeCommand`.

Given below is example usage scenarios and how the gradebook features behave.

### [Proposed] Schedule Feature
####Proposed Implementation
The proposed schedule feature is to facilitate the user to keep track of his/her 
own schedule, which are events that are closely related to tuition, i.e., tuition's homework deadline. 
The schedules are internally stored in `scheduleList`. 
Additionally,  it implements the following operations:
* `Add a schedule` - Add a schedule to the user's schedules
* `Delete a schedule` - Delete a schedule by schedule name
* `Edit a schedule` - Edit a schedule by schedule name

These operations are exposed in the `Logic` interface by parsing respective `AddScheduleCommand`,
`DeleteScheduleCommand` and `EditScheduleCommand`.

Given below is example usage scenarios and how the schedule features behave.

### [Proposed] Reminder Feature
####Proposed Implementation
The proposed reminder feature is to facilitate the user to keep track of his/her
own events (`appointment` or `schedule`) in the form of reminders when he/she runs the application.
The reminders are internally stored in `reminderList`.
Additionally,  it implements the following operations:
* `Add a reminder` - Add a reminder to the user's reminders
* `Delete a reminder` - Delete a reminder by index
* `Edit a reminder` - Edit a reminder by index

These operations are exposed in the `Logic` interface by parsing respective `AddReminderCommand`,
`DeleteReminderCommand` and `EditReminderCommand`.

Given below is example usage scenarios and how the reminder features behave.

_{More to be added}_



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

Tech-savvy secondary school students in Singapore who to need to search for tutors and manage their tuition appointments, and prefer CLI over GUI.

**Value proposition**:

The demand for tuition in Singapore is escalating, especially among secondary school students. A large amount of time and money has been invested in finding tutors and managing ever growing lists of tuition appointments. Currently, there are limited number of apps and websites that cater to this need, particularly in a streamlined typing oriented CLI. Therefore, this app aims to assist secondary school students in streamlining the process of searching for tutors and managing their tuition appointments. These students can search for an ideal tutor based on their personal preferences (such as subjects, expertise, years of experience, cost, availability etc.), and cut down on the time taken tracking their favoured tutors and tuition appointments.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | User | Add new tutor details                              | Keep track of a new tutor that I have heard about               |
| `* * *` | User | List all tutors                                    | See all known tutors                                            |
| `* * *` | User | View details of a tutor (subject, background, age) | Determine whether I should choose this tutor                    |
| `* * *` | User | Get the email address of a tutor                   | Contact tutors directly for queries                             |
| `* * *` | User | Add tuition appointment                            | Keep track of appointments I have made                          |
| `* * *` | User | View my tuition appointments                       | Keep track of appointments                                      |
| `* * *` | User | Filter my tuition appointments by tutor's name     | Keep track of all the tuition appointments under a particular tutor|
| `* * *` | User | Filter my tuition appointments by date             | Keep track of all the tuition appointments that falls on the same day|
| `* * *` | User | Delete a tuition appointment                       | Remove canceled appointments                                    |
| `* * *` | User | Check my own tuition appointments list             | Know the timing of ALL my appointments in order                 |
| `* * *` | User | Filter tutors by their subject discipline          | Find a tutor that caters to my needs (academic)                 |
| `* * *` | User | Filter tutor by cost                               | Find a tutor that fits into budget as well                      |
| `* * *` | User | Filter a tutor by his/her name                     | View tutor's profile                                            |
| `* * *` | User | Filter tutors by their years of experience         | Find a tutor with experience within the range of my expectation |
| `* * *` | User | Filter tutors by their available timeslots         | Find a tutor with matched tuition time                          |
| `* * *` | User | Filter tutors by their available location          | Find tutors in a specific area                                  |


*{More to be added}*

### Use cases

_For all use cases below, the **System** is the `TutorTracker` and the **Actor** is the `user`, unless specified otherwise._

<hr>

**Use Case UC0001: Add new tutor details**

**MSS**

1. User inputs tutor details.
2. TutorTracker confirms that tutor details have been added to list.

**Extensions**
* 2a. Tutor details already exists in list.
    * 2a1. TutorTracker shows an error message

  Use case resumes at step 1.

<hr/>

**Use Case UC0002: List all tutors**

**MSS**
1. User requests to list tutors.
2. TutorTracker shows a list of tutors.

    Use case ends.

<hr/>

**Use Case UC0003: View a tutor**

**MSS**

1.  User requests to list the list of all tutors.
2.  TutorTracker shows a list of tutors.
3.  User requests to view a tutor by index.
4.  TutorTracker displays the tutor.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no tutors.

  Use case ends.

* 3a. The index is invalid.
    * 3a1. TutorTracker shows an error message.

  User case ends.

<hr/>

**Use Case UC0004: Add new tuition appointment**

**MSS**

1.  User requests to add an appointment.
2.  TutorTracker adds the appointment and displays the new appointment.

    Use case ends.

**Extensions**
* 1a. The tutor name, date of appointment or start and end time is empty.
    * 1a1. TutorTracker shows an error message.

      Use case ends.

* 1b. The given date or start and end time is invalid.
    * 1b1. TutorTracker shows an error message.

        Use case ends.

<hr/>

**Use Case UC0005: List all tuition appointments**

**MSS**

1. User requests to view the list of tuition appointments.
2. TutorTracker displays the list of tuition appointments to the user.

    Use case ends.

<hr/>

**Use Case UC0006: View tuition appointment**

**MSS**

1.  User requests to view the list of tuition appointments.
2.  TutorTracker displays the list of tuition appointments to the user.
3.  User requests to view an appointment by date.
2.  TutorTracker displays the appointment.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no appointments.

    Use case ends.

* 3a. The date is invalid.
    * 3a1. TutorTracker shows an error message.

    User case ends.

<hr/>

**Use Case UC0007: Find tuition appointment**

**MSS**

1.  User requests to view the list of tuition appointments.
2.  TutorTracker displays the list of tuition appointments to the user.
3.  User requests to find appointments by tutor's name.
2.  TutorTracker displays the appointment that match the search value.

    Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. TutorTracker shows a message that there are no appointments.

  Use case ends.

* 3a. No appointment matches the search value.
    * 3a1. TutorTracker displays an empty list.

  User case ends.

<hr/>

**Use Case UC0008: Delete a tuition appointment**

**MSS**

1.  User requests to list appointments.
2.  TutorTracker shows a list of appointments.
3.  User requests to delete a specific appointment in the list.
4.  TutorTracker deletes that specific appointment.

<hr/>


*{More to be added}*

### Non-Functional Requirements
**Technical Requirements**:
* Application should be able to launch in any operating
  systems (Linux, Max, Windows) with JDK 11 installed on computer.
* Should be able to
  run on both 32-bit and 64-bit systems.

**Performance Requirements**
* Response to user command (add, delete, update, retrieve)
  should be visible within 2 seconds.
* Should be able to hold at least 10000 persons
  and appointments without any noticeable decrease in loading time.

**Quality Requirements**
* Interface can be used by a user with no programming
  experience, i.e., user should not be expected to key in complicated commands or
  logical statements to get a desired output.

**Process Requirements**
* Project to be updated with one new feature/improvement from
  each member each week.
* Updates will be pushed to each teammates' individual branches,
  where PRS are made to the master branch.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Appointment**: An event in user's local schedule with related details, including tutor's name, date of appointment, start and end time and location(optional).
* **Education Level**: The level of education offered by a tutor for a specific subject, e.g, "O level".
* **Years of Experience**: Years of experience of tutoring a specific subject.
* **Qualifications**: Official certificates of successful completion of an education programme, e.g, Bachelor of Science.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample tutors. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a tutor

1. Deleting a tutor while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple tutors in the list.

    1. Test case: `delete 1`<br>
       Expected: First tutor is deleted from the list. Details of the deleted tutor shown in the status message. Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No tutor is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
