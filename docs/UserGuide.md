---
layout: page
title: User Guide
---



## Introduction
HEY MATEz is a desktop application to get rid of CCA leaders' woes by allowing them to track 
members and tasks within the CCA efficiently. It is
a Command Line Interface (CLI) application while still retaining a Graphical User Interface (GUI)
as this application is catered to students who are used to typing on the keyboard frequently.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. You can install Java `11` [here](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html).

1. Download the latest `heymatez.jar` from [here](https://github.com/AY2021S2-CS2103T-W14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Hey MATEz.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`viewMembers`** : Lists all members.

   * **`addTask`**`Proposal -d Write out detailed proposal plan for CCA timeline -b 2021-04-22` : Adds a task named `Proposal` to Hey MATEz.

   * **`deleteTask`**`3` : Deletes the 3rd task shown in the current list.

   * **`clear`** : Deletes all tasks and members.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

-------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:bookmark_tabs: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addTask -n NAME`, `NAME` is a parameter which can be used as `addTask -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `-d DESCRIPTION [-p PRIORITY]` can be used as `-d Meeting -p high` or as `-d Meeting`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE_NUMBER`, `-p PHONE_NUMBER -n NAME` is also acceptable.
  
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------

## General Features

<a name="clear"></a>
### 1. Clear all data in application: `clear`
Clears all entries from the application.

Format: `clear`

Example: `clear`

<a name="exit"></a>
### 2. Exiting the programme: `exit`
Exits the program.

Format: `exit`

Example: `exit`

<a name="help"></a>
### 3. View Help: `help`
Shows a message explaining how to access the help page.

Format: `help`

Example: `help`

-------------------------------------------------------------------------------------------------------------

## Member Related Features

<a name="addMember"></a>
### 1. Add Member: `addMember`

Adds a member along with his/ her contract number to contact list.

Format: `addMember NAME -p PHONE_NUMBER -e EMAIL [-r ROLE]`

Example: `addMember Dylan -p 64529356 -e dylan@gmail.com -r Member`

* The field ROLE is optional
* If role field is not specified, person will be assigned a default role of member.

<a name="deleteMember"></a>
### 2. Delete Member: `deleteMember`

Delete a member along with all of his/her contact details from the members list.

Format: `deleteMember NAME`

Example: `deleteMember Rachel`

<a name="viewMembers"></a>
### 3. View Member: `viewMembers`

View the list of members the user has added.

Format: `viewMembers `

Example: `viewMembers `

<a name="editMember"></a>
### 4. Edit Member: `editMember`

Format: `editMember NAME_IN_LIST [-n NEW_NAME] [-p NEW_PHONE_NUMBER] [-e NEW_EMAIL] [-r ROLE]`

Example: `editMember Alice -e newAlice@gmail.com -r Events head`

* Edits the member at the specified NAME_IN_LIST.
* The fields NEW_NAME, NEW_PHONE_NUMBER, NEW_EMAIL, NEW_ROLE are all optional
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

<a name="findmember"></a>
### 5. Find Members by Keywords: `findMembers`

Find all members whose details contain any of the specified keywords.

Format: `findMembers KEYWORD [MORE_KEYWORDS]`

Example: `findMembers Rachel 98562154 john@gmail.com`

* Keywords are case-insensitive.

-------------------------------------------------------------------------------------------------------------

## Task Related Features

<a name="addTask"></a>
### 1. Add Task: `addTask`

Adds a task, with its description and deadline to the list.

Format: `addTask TITLE -d DESCRIPTION -b DEADLINE [-s STATUS] [-p PRIORITY] [-a ASSIGNEE]`

Example: `addTask CCA Timeline -d Plan semester's timeline -b 2021-04-04 -s completed -p high -a Rachel`

* The status, priority and assignee fields are optional.
* If status field is not provided, the Task will be assigned a default status value of uncompleted.
* Status field can only take on the values completed or uncompleted.
* If priority field is not provided, the Task will be assigned a default priority of unassigned.
* Priority field can only take on the values high, medium or low if a value is specified by the user.

<div markdown="block" class="alert alert-info">
:bulb: **Tip:**
* A task can have any number of assignees (including 0).   
* To add multiple assignees, simply use multiple assignees prefix. 
* Example: addTask TITLE ... [-a ASSIGNEE] [-a MORE_ASSIGNEE]
</div>

<a name="deleteTask"></a>
### 2. Delete Task: `deleteTask`
Deletes a task from the list by specifying the task's INDEX.

Format: `deleteTask INDEX`

Example: `deleteTask 1`

<a name="viewTasks"></a>
### 3. View Tasks: `viewTasks`
Views the list of tasks that the user has added into HEY MATEz.

Format: `viewTasks`

Example: `viewTasks`

<a name="editTask"></a>
### 4. Edit Task: `editTask`
Edit task details. 

Format: `editTask INDEX [-n NEW_TITLE] [-d NEW_DESCRIPTION] [-b NEW_DEADLINE] [-s NEW_STATUS] [-p NEW_PRIORITY] [-a NEW_ASSIGNEE]`

Example: `editTask 1 -n Plan meeting -d Plan board meeting -b 2021-04-04`

* The fields NEW_TITLE, NEW_DESCRIPTION, NEW_DEADLINE, NEW_STATUS, NEW_PRIORITY, NEW_ASSIGNEE are all optional
* Edits the Task at the specified index.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* If the assignee field is being edited, the previous assignees will be overwritten. 

<div markdown="block" class="alert alert-info">
:bulb: **Tip:**
* Any number of assignees can be specified here (including 0).   
* To specify multiple assignees, simply use multiple assignees prefix. 
* Example: editTask INDEX [-a ASSIGNEE] [-a MORE_ASSIGNEE]
</div>

<a name="doneTask"></a>
### 5. Mark Task as Completed: `done`

Change the status of a task from uncompleted to completed.

Format: `done INDEX`

Example: `done 1`

<a name="undoTask"></a>
### 6. Mark Task as Uncompleted: `undo`

Change the status of a task from completed to uncompleted.

Format: `undo INDEX`

Example: `undo 1`

<a name="findTask"></a>
### 7. Find Tasks by Keywords: `findTasks`

Find all tasks containing any of the specified keywords in its title or description.

Format: `findTasks KEYWORD [MORE_KEYWORDS]...`

Example: `findTasks Meeting Proposal Draft`

* Keywords are case-insensitive. 

<a name="findBefore"></a>
### 8. Find Tasks due before certain date: `findBefore`

Find all tasks with deadline before the specified date.

Format: `findBefore DATE`

Example: `findBefore 2021-05-04`

<a name="findPriority"></a>
### 9. Find Tasks by Priority: `findPriority`

Find all tasks containing the specified priority.

Note: The valid input values for `findPriority`: high, medium, low and unassigned (Case sensitive)

Format: `findPriority PRIORITY`

Example: `findPriority high`

<a name="removeAssignees"></a>
### 10. Remove all Members Assigned to a Task: `removeAssignees`

Remove all members assigned to a task by specifying the task's INDEX.

Format: `removeAssignees INDEX`

Example: `removeAssignees 2`

<a name="findUnassigned"></a>
### 11. View List of Unassigned Tasks: `viewUnassignedTasks`

View the list of unassigned tasks.

Format: `viewUnassignedTasks`

Example: `viewUnassignedTasks`

<a name="findUncompleted"></a>
### 12. View List of Uncompleted Tasks: `viewUncompletedTasks`

View the list of uncompleted tasks.

Format: `viewUncompletedTasks`

Example: `viewUncompletedTasks`

--------------------------------------------------------------------------------------------------------------------
## Data Storage 

### Saving the data

Hey MATEz data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HEY MATEz data is saved as a JSON file `[JAR file location]/data/heymatez.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HEY Matz home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Clear Data** | `clear`
**Exit Program** | `exit`
||
**Add Member** | `addMember NAME -p PHONE_NUMBER -e EMAIL [-r ROLE]` <br> e.g., `addMember Dylan -p 64529356 -e dylan@gmail.com -r Member`
**Delete Member** | `deleteMember NAME` <br> e.g., `deleteMember Rachel`
**View Members** | `viewMembers`<br> e.g., `viewMembers`
**Edit Member** | `editMember NAME_IN_LIST [-n NEW_NAME] [-p NEW_PHONE_NUMBER] [-e NEW_EMAIL] [-r ROLE]`<br> e.g.,`editMember Alice -e newAlice@gmail.com -r Events head`
**Find Members by Keywords** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `findMembers Rachel 98562154 john@gmail.com`
||
**Add Task** | `addTask TITLE -d DESCRIPTION -b DEADLINE [-s STATUS] [-p PRIORITY] [-a ASSIGNEE]` <br> e.g., Example: `addTask CCA Timeline -d Plan semester's timeline -b 2021-04-04 -s completed -p high -a Rachel`
**Delete Task** | `deleteTask INDEX` <br> e.g., `deleteTask 1`
**View Tasks** | `viewTasks`<br> e.g., `viewTasks`
**Edit Task** | `editTask INDEX [-n NEW_TITLE] [-d NEW_DESCRIPTION] [-b NEW_DEADLINE] [-s NEW_STATUS] [-p NEW_PRIORITY] [-a NEW_ASSIGNEE]`<br> e.g.,`editTask 1 -n Plan meeting -d Plan board meeting -b 2021-04-04`
**Mark Task as Completed** | `done INDEX`<br> e.g., `done 1`
**Mark Task as Uncompleted** | `undo INDEX`<br> e.g., `undo 1`
**Find Tasks by Keywords** | `findTasks KEYWORD [MORE_KEYWORDS]`<br> e.g., `findTasks Meeting Proposal Draft`
**Find Tasks by Priority** | `findPriority PRIORITY`<br> e.g., `findPriority HIGH`
**Find Tasks by Deadline** | `findBefore DATE`<br> e.g., `findBefore 2021-04-05`
**Remove all Members Assigned to a Task** | `removeAssignees INDEX`<br> e.g., `removeAssignees 2`
**View Unassigned Tasks** | `viewUnassignedTasks`<br> e.g., `viewUnassignedTasks`
**View Uncompleted Tasks** | `viewUncompletedTasks`<br> e.g., `viewUncompletedTasks`
