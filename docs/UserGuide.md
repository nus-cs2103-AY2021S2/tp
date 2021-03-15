---
layout: page
title: User Guide
---

EzManage is a **desktop app for managing students, tutors and classes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is named as EzManage as it allows tuition centres managers to easily manage students, tutors and classes all in one single web application.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ezmanage.jar`. [coming soon!]
# from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EzManage.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add_person`**`tp/student n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a student named `John Doe` to the Contact List.

   * **`delete_person`**`t/3` : Deletes the tutor with the ID `t/3` from the tutor list.
     
   * **`assign`** : Assigns student or tutor to a specific class (Coming Soon!).

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add_person n/NAME`, `NAME` is a parameter which can be used as `add_person n/John Doe`.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a tutor: `add`

Adds a tutor to the address book.

Format: `add_person pt/tutor n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add_person pt/tutor n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add_person pt/tutor n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding a student: `add`

Adds a student to the address book.

Format: `add_person pt/student n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add_person pt/student n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add_person pt/student n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding a session: `add`

Adds a session to the address book.

Format: `add_session d/DAY t/TIMESLOT s/SUBJECT [t/TAG] …
`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A session can have any number of tags (including 0)
</div>

Examples:
* `add_session d/Saturday t/1300 to 1500 s/A Math`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list persons`

### Listing all sessions : `list`

Shows a list of all sessions in the address book.

Format: `list sessions`

### Viewing a tutor : `view`

Views an existing tutor's details.

Format: `view t/ID`

* Views the tutor with the specified tutor ID.
* Tutor’s name, contact number, existing classes, email and address will be given.

Example:
* `view t/1` views the details of the tutor with tutor ID 1.

### Viewing a student : `view`

Views an existing student's details.

Format: `view s/ID`

* Views the student with the specified student ID.
* Student’s name, contact number, email and address will be given.

Example:
* `view s/1` views the details of the student with student ID 1.

### Viewing a class : `view`

Views an existing class's details.

Format: `view c/ID`

* Views the class with the specified class ID.
* The class's assigned tutor, assigned students, time slot, subject and class size will be given.

Example:
* `view c/1` views the details of the class with class ID 1.


### Editing a person : `edit` (coming soon)

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

### Locating persons by name: `find` (coming soon)

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

### Deleting a student : `delete`

Deletes the specified student from the address book.

Format: `delete_person s/ID`

* Deletes the student with the specified `s/ID`.
* The s/ID refers to the student ID shown in the displayed person list.

Examples:
* `list persons` followed by `delete s/2` deletes the student with student ID s/2 in the address book.

### Deleting a tutor : `delete`

Deletes the specified tutor from the address book.

Format: `delete_person t/ID`

* Deletes the tutor with the specified `t/ID`.
* The t/ID refers to the tutor ID shown in the displayed person list.

Examples:
* `list persons` followed by `delete t/1` deletes the tutor with tutor ID t/1 in the address book.

### Deleting a session : `delete`

<<<<<<< HEAD
Deletes the specified person from the list of tutors/students or deletes the specified class from the list of classes.

Format:<br>
`delete_person t/ID` for tutors<br>
`delete_person s/ID` for students<br>
`delete_session c/ID` for classes

* Deletes the tutor/student at the specified ID from the list of tutors/students. Or deletes the class at the specified ID from the list of classes.
* The index refers to the ID shown in the displayed tutor/student/class list.
* The index must be a in the format of:<br>
`t/ID` for tutors<br>
`s/ID` for students<br>
`c/ID` for classes

Examples:
* `delete_person t/1` deletes the tutor with the ID `t/1` from the tutor list.
* `delete_session c/25` deletes the class with the ID `c/25` from the class list.
=======
Deletes the specified session from the address book.

Format: `delete_session c/ID`

* Deletes the session with the specified `c/ID`.
* The c/ID refers to the session ID shown in the displayed session list.

Examples:
* `list sessions` followed by `delete c/2` deletes the session with session ID c/2 in the address book.
>>>>>>> 065d41c5beb56f55bd609db8c15dc3b359ccad5d

### Clearing all entries : `clear`

Clears all entries from the list of students, tutors and classes.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

EzManage data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

EzManage data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | For Person:`add_person tp/ROLE n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add_person tp/student n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`<br> For Class: `add_session d/Saturday t/1300 to 1500 l/Upper Secondary s/A Math` <br> e.g. `add_session d/Saturday t/1300 to 1500 l/Upper Secondary s/A Math` 
**Clear** | `clear`
**Delete** | Tutor <br> `delete_person t/ID`<br> e.g., `delete_person t/8`<br><br> Student <br> `delete_person s/ID`<br> e.g., `delete_person s/22` <br><br> Class<br>`delete_session c/ID` <br> e.g., `delete_session c/9`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
