---
layout: page
title: User Guide
---

RemindMe User Guide v1.1
---
This user guide provide [start-up](#start-up) instructions as well as detailed descriptions and usage of
all the [features](#features) in the RemindMe app. You can also access the product website via this [link.](https://ay2021s2-cs2103t-w15-1.github.io/tp/)

<div style="page-break-after: always;"></div>

## Table of Contents

* **[Introduction](#introduction)**
* **[Start-up](#Start-up)**
* **[Features](#features)**
    * **[1. Show help page : `help`]()**
    * **[2. Add an event/examination: `event/deadline (DESCRIPTION) (DD/MM/YYYY TIME)`]()**
    * **[3. Delete an event/examination: `delete (INDEX)`]()**
    * **[4. Edit a task]()**
    * **[5. Delete a task]()**
    * **[6. Calendar View]()**
    * **[7. Pop-up Reminder]()**
* **[Glossary](#glossary)**
* **[Command summary](#command-summary)**

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Introduction
RemindMe is a desktop app for keeping track of user events and deadlines,
optimized for use via Command Line Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).

Objectives of RemindMe:
1. Allow students to be aware of deadlines of school events and exams.
2. Allow students to have a calendar view of their school curriculums/schedules. 


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
     
   * ** `calendar`**: Dsiplays the calendar with the tasks' deadlines and friends' birthdays.
     
   * **`exit`** : Exits the app.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### *Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### *Turn on/off reminder: `remind`

Turns on and off the reminder system. If remind is on,
a reminder would pop out when starting the app RemindMe regarding
the upcoming tasks.

(insert image of reminder here)

Format: `remind`

Outcome:

* `reminder is turned on!`
* `reminder is turned off!`

### *Adding an Assignment/Event: `event/deadline`

Adds an assignment with a deadline or event to the calendar.

Format: 

* `event (DESCRIPTION) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)`

* `deadline (DESCRIPTION) /by (DD/MM/YYYY TIME)`

Examples:
* `event Christmas Party /from 25/12/2021 1800 /to 25/12/2021 2300`
* `deadline CS2103 Assignment /by 03/03/2021 2359`

### *Deleting an Assignment/Event: `delete`

Deletes an assignment with a deadline or event from the calendar.

Format: 

* `delete /from (DD/MM/YYYY) /index (INDEX)`

Examples:
* `delete /from 25/12/2021 /index 1`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### *Viewing Calendar of the current month
Shows an image of the calendar with your reminders (e.g. tasks, assignments datelines, and friends’ birthday) for each specific dates.  
![result for 'calendar'](images/calendarResult.png)  
Format: `calendar`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear``

### *Saving the data

RemindMe save your data in the hard disk automatically after every command that changes the data. There is no need for you to save manually.

### *Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/remindme.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, RemindMe will discard all data and start with an empty data file at the next run.
</div>

### Exiting the program : `exit`

Exits the program.

Format: `exit

## Glossary
* Examination: Consists of a start time, end time, date which it occurs on and the subject.
* Event: Consists of a start time, end time and the date which it occurs on.

## Command summary (AB3)

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**View Calendar** | `calendar`
**Help** | `help`

## Command summary (RemindMe) 

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


