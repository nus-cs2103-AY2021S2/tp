---
layout: page
title: User Guide
---

RemindMe User Guide v1.1
---
This user guide provides [start-up](#start-up) instructions as well as detailed descriptions and usage of
all the [features](#features) in the RemindMe app. You can also access the product website via this [link.](https://ay2021s2-cs2103t-w15-1.github.io/tp/)

<div style="page-break-after: always;"></div>

## Table of Contents

* **[Introduction](#introduction)**
* **[Start-up](#start-up)**
* **[Features](#features)**
    * **[1. Viewing Help : `help`](#show-help-page--help)**
    * **[2. Turn On/Off Reminder: `remind`](#turn-onoff-reminder-remind)**
    * **[3. Add a Module: `add m/MODULE`](#add-a-module-add)**
      * [3.1 Add an Assignment](#add-an-assignment)
      * [3.2 Add an Exam](#add-an-exam)
    * **[4. Add an Event: `event`](#add-an-event-event)**  
    * **[5. Add a Person as friend: `add`](#add-a-person-as-friend-add)**  
    * **[6. Delete a Module: `delete`](#delete-a-module-delete)**
      * [6.1 Delete an Assignment/Exam/Event](#delete-an-assignmentexamevent)
    * **[7. List Entries: `list`](#list-entries--list)**
      * [7.1 List Modules: `list module`](#list-modules-list-modules)
      * [7.2 List Assignments: `list assignments`](#list-assignments--list-assignments)
      * [7.3 List Events: `list events`](#list-events--list-events)
      * [7.4 List Exams: `list exams`](#list-exams--list-exams)
    * **[8. Edit an Entry: `edit`](#edit-an-entry--edit)**
    * **[9. Locate Entries: `find`](#locate-entries-find)**
    * **[10. Calendar View: `calendar`](#calendar-view--calendar)**
    * **[11. Clear Entries: `clear`](#clear-entries--clear)**
    * **[12. Save the data](#save-the-data)**
    * **[13. Edit the data file](#edit-an-entry--edit)**
    * **[14. Exit the program: `exit`](#exit-the-program--exit)**
   
* **[Glossary](#glossary)**
* **[Command summary](#command-summary-(RemindMe))**

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Introduction
RemindMe is a desktop app for keeping track of user events and deadlines,
optimized for use via Command Line Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).

Objectives of RemindMe:
1. Allow students to be aware of deadlines of school events and exams.
2. Allow students to have a calendar view of their school curriculums/schedules. 



--------------------------------------------------------------------------------------------------------------------


## Start-up

1. Ensure you have Java 11 or above installed on your computer. You can download Java 11 via this [link.](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html)
   * If you are a Windows user, [here](https://java.tutorials24x7.com/blog/how-to-install-java-11-on-windows) is a tutorial on how to set up Java 11:


2. Download the latest RemindMe.jar from our GitHub release page.
Copy the file to the folder you want to use as the home folder for your RemindMe.
Double click the file to start the app. 
   

3. Alternatively, you can run the command line java -jar RemindMe.jar in your terminal to start the application.
Type the command in the command box and press Enter to execute it.
The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)   

4. Refer to the Features below for details of each command.

   * **`deadline`** `description by DD/MM/YYYY TIME`: Adds a task with a deadline.
     
   * **`ordered list`**: Displays an order list of items.
     
   * **`calendar`**: Dsiplays the calendar with the tasks' deadlines and friends' birthdays.
     
   * **`exit`** : Exits the app.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `exam m/MODULE`, `MODULE` is a parameter which can be used as `add m/CS2103`.


* Items in square brackets are optional.<br>
  e.g `m/MODULE [t/TAG]` can be used as `m/CS21O3 t/final` or as `m/CS2103`.
  

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Viewing Help : `help`

Shows a help page

![help message](images/helpMessage.png)

Format: `help`

### Turn On/Off Reminder: `remind`

Turns on and off the reminder system. If remind is on,
a reminder would pop out when starting the app RemindMe regarding
the upcoming tasks.

![Reminder](images/Reminder.png)

Format: `remind`

Outcome:

* `reminder is turned on!`
* `reminder is turned off!`

### Add a Module: `add`

Adds a module to the calendar.

Format: 

* `add m/MODULE`

Examples:
* `add m/cs2103`

### Add an Assignment

Adds an assignment under a module to a calendar with a date or/and with an optional tag/time.

Format:
* `add m/MODULE a/assignment d/DATE [t/TAG/TIME] `

Examples:
* `add m/cs2103 a/tut2 d/2021-01-12 t/23:59`

### Add an Exam

Add an exam under a module to a calendar with a date or/and with an optional tag/time.

Format:
* `add m/MODULE e/exam d/DATE [t/TAG/TIME]`

Examples:
* `add m/cs2103 e/final d/2021-01-12 t/23:59`

### Add an Event: `event`

Add an event with content and date specified.

Format:
* `event c/CONTENT d/DATE [t/TAG]`

Examples:
* `event c/floor party d/2021-4-30`

### Add a Person as friend: `add`

Add a person as friend with its birthday in RemindMe

Format:

* `add n/Name b/BIRTHDAY`

Example:

* `add n/Marcus b/2000-01-01`

### Delete a Module: `delete`

Deletes a module from RemindMe. Removing the module will remove all the relevant 
exams and assignments.

Format: 
* `delete m/MODULE`


Examples:
* `delete m/cs2103T`

### Delete an Assignment/Exam/Event

Deletes an assignment/exam/event from the calendar.

Format:
* `delete i/index d/date`

Examples:
* `delete i/1 d/2020-11-22`

### List Entries : `list`

Shows a list of all assignments/events/exam deadlines sorted by date.

Format: `list`

### List Modules: `list modules`

Show a list of modules currently registered in RemindMe.

Format: `list modules`


### List Assignments : `list assignments`

Shows a list of all assignments sorted by date.

Format: `list assignments`

### List Events : `list events`
Shows a list of all events sorted by date.

Format: `list events`

### List Exams : `list exams`
Shows a list of all exams sorted by date.

Format: `list exams`

### Edit an Entry : `edit`

Edits an existing exam/event/assignment's date/module/tag in the RemindMe.

Format: `edit INDEX [m/MODULE] [d/DATE] [dd/deadline] [from/TIME to/TIME] [t/TAG]…​`

* Edits the exam/event/assignment's deadline at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the exam/event/assignment will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/exam d/2021-01-22` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 p/assignment t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locate Entries: `find`

Finds entries whose contents contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `math` will match `MATH`
* Only full words will be matched e.g. `math` will not match `maths`


Examples:
* `find math` returns `math exams` and `math assignment`



### Calendar View : `calendar`
Shows an image of the calendar with your reminders (e.g. tasks, assignments datelines, and friends’ birthday) for each specific dates.  
![result for 'calendar'](images/calendarResult.png)  
Format: `calendar`

### Clear Entries : `clear`

Clears all entries from the address book.

Format: `clear``

### Save the data

RemindMe save your data in the hard disk automatically after every command that changes the data. There is no need for you to save manually.

### Edit the data file

RemindME data are saved as a JSON file `[JAR file location]/data/remindme.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RemindMe will discard all data and start with an empty data file at the next run.
</div>

### Exit the program : `exit`

Exits the program.

Format: `exit

## Glossary
* Module: Consists of a module ID and name.
* Assignment: Consists of the name of the assignment and deadline with an optional tag.
* Exam: Consists of the name, date and start-time with an optional tag.
* Event: Consists of the event content and date with an optional tag.


## Command Summary (RemindMe) 

Action | Format, Examples
--------|----------------
Add assignment | `(assignment type) (assignment description) (assignment details)`
Delete assignment | `delete (assignment description)`
View assignments | `view A`
View events | `view E`
Turn on/off reminder | `remind`
See commands available | `help`
View calendar | `calendar`
Save data | `save`
Edit data | `edit`


