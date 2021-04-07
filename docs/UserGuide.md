---
layout: page 
title: User Guide
---

Welcome to the User Guide of _Teaching Assistant_!

Are you a JC/Secondary school teacher, having troubles keeping track of all your consultations, meetings and your
students' contacts? No worries! Our application, _Teaching Assistant_ will provide an all-in-one platform for you to
organise your entries (tasks and schedules) and contacts!

_Teaching Assistant_ mainly uses a Command Line Interface (CLI). This means that a user can use the application by
typing into a Command Box. For users who type fast, they can use this application more efficiently than other
applications that heavily use Graphical User Interface (GUI), where a user uses the application by interacting with
graphical features such as buttons.

If you are interested, jump to [Quick Start](#quick-start) to learn how to learn how to start using _Teaching Assistant_
.

An image of our UI is shown below!

![Ui](images/Ui.png)

---

- [Quick Start](#quick-start)
- [Features](#features)
    - [Viewing help](#viewing-help)
    - [Contact](#adding-a-contact)
        - [Add](#adding-a-contact)
        - [Find](#finding-a-contact)
        - [Filter](#filter-contact-tags)
        - [Edit](#editing-a-contact)
        - [List](#listing-contacts)
        - [Delete](#deleting-a-contact)
        - [Clear](#clearing-all-contacts)
    - [Entry](#adding-an-entry)
        - [Add](#adding-an-entry)
        - [Find](#finding-an-entry)
        - [Filter](#filter-entry-tags)
        - [Edit](#editing-a-contact)
        - [List](#listing-entries)
        - [Free](#checking-if-time-interval-is-free)
        - [Delete](#deleting-an-entry)
        - [Clear](#clearing-overdue-entries)
    - [Exiting](#exiting-the-program)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java 11 installed in your computer.
1. Download the latest `teachingAssistant.jar` [here](https://github.com/AY2021S2-CS2103T-W13-4/tp/releases).
1. Copy the file to the folder you want to use as the *home folder* for your Teaching Assistant.
1. Double-click the file to start the app. The GUI similar to the image above should appear.
1. Try out some of the commands below.

---

## Features

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add n/NAME`, `NAME` is a parameter which
  can be used as `add n/John Doe`.
* Items in the square brackets are optional. Users can choose to leave the field empty.
* For `[t/Tag]` and `[MORE_KEYWORDS]`, 0 or more of such arguments can be specified.
* Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also
  acceptable.
* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken. e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

---

### Viewing help

Shows a message with all the commands.

Format: `help`

---

### Adding a contact

Adds a person's information into the address book.

Format: `add n/NAME p/NUMBER e/EMAIL a/ADDRESS [t/TAG]`

* A person can have any number of tags (including 0).

Example(s):

* `add n/Danny p/98765432 e/danny@email.com a/311, Clementi Ave 2, #02-25`
* `add n/Amy p/12345678 e/amy@email.com a/311, Clementi Ave 2, #02-25 t/Friend t/OwesMoney`

### Finding a contact

Finds an existing contact by name in the address book.

Format: `find KEYWORD [MORE_KEYWORDS]`

* Only names are searched.
* The search is case-insensitive e.g. `amy` will match `Amy`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Only contacts matching all keywords will be returned (i.e. AND search). E.g. `Hans Bo` will only return `Hans Bo`.

Example(s):

* `find John` returns `john` and `John Doe`
* `find alex yeoh` returns only `Alex Yeoh`

### Filter contact tags

Filters all persons that have the tags of the specified keywords and displays them as a list with index numbers.

Format: `filter KEYWORD [MORE_KEYWORDS]`

* Only tags are searched.
* The filtering is case-insensitive e.g. `CS2100` will match `cs2100`.
* Only full words will be matched e.g. `Friend` will not match `Friends`.
* If more than one keyword is provided, only contacts with all the keywords provided will be displayed.
  E.g. `filter colleagues friends` will only return a contact with both tags `colleagues` and `friends`. Contacts with
  only one of the 2 keywords will not be displayed.
* The order of the keywords does not matter. e.g. `colleagues friends` will match `friends colleagues`

Example(s):

* `filter student english`
* `filter colleagues`

### Editing a contact

Edits an existing contact in the address book specified by name.

Format: `edit NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`

* `NAME` provided must be the full `NAME` of the contact to be edited e.g. If the contact is `Alex Yeoh`, to edit
  it, `Alex Yeoh` must be provided as `NAME`.
* `NAME` is case-sensitive so `Alex` will not match `alex`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Example(s):

* `edit Alex Yeoh p/91234567 e/alexyeoh@example.com` Edits the phone number and email address of `Alex Yeoh` to
  be `91234567` and `alexyeoh@example.com` respectively.
* `edit Bernice Yu n/Bernice Yu Xiao Ling t/` Edits the name of `Bernice Yu` to be `Bernice Yu Xiao Ling` and clears all
  existing tags.

### Listing contacts

Lists all the contacts in the address book.

Format: `list`

### Deleting a contact

Deletes an existing contact with the specified index in the address book.

Format: `delete INDEX`

* `INDEX` refers to the index number shown in the displayed person list.
* `INDEX` must be a positive integer 1, 2, 3, ...

Example(s):

* `delete 1`

### Clearing all contacts

Clears all entries from the address book.

Format: `clear`

---

### Adding an entry

Adds a new entry into the entry list.

Format: `eadd n/NAME [sd/START DATE] ed/END DATE [t/TAG]`

* `START DATE` and `END DATE` are in the format `yyyy-mm-dd hh:mm`.
* `START DATE` should be before `END DATE`.
* Entries cannot overlap. i.e. Entries with overlapping timings will not be added.
* `START DATE` is optional so that if the entry is a task with only a due date, it can be specified with only an end
  date.

Example(s):

* `eadd n/meeting sd/2021-02-15 21:00 ed/2021-02-15 23:00`
* `eadd n/consultation ed/2021-02-15 23:00 t/consultation`

### Finding an entry

Finds all entries whose name contain any of the specified keywords and displays them as a list.

Format: `efind KEYWORD [MORE_KEYWORDS]`

* Only names are searched.
* The search is case-insensitive e.g. `meeting` will match `Meeting`.
* The order of the keywords does not matter. e.g. `teaching assistant` will match `assistant teaching`.
* Only full words will be matched e.g. `meeting` will not match `meetings`.
* Entries matching at least one keyword will be returned (i.e. OR search). E.g. `Assignment` will return `Assignment 1`
  , `Assignment 2`.

Example(s):

* `efind assignment 2`

### Filter entry tags

Filters all entries that have the tags of the specified keywords and displays them as a list.

Format: `efilter KEYWORD [MORE_KEYWORDS]`

* Only tags are searched.
* The filtering is case-insensitive e.g. `CS2100` will match `cs2100`.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`.
* If more than one keyword is provided, only entries with all the keywords provided will be displayed.
  E.g. `filter meeting CS2103T` will only return an entry with both tags `meeting` and `CS2103T`. Entries with only one
  of the 2 keywords will not be displayed.
* The order of the keywords does not matter. e.g. `meeting CS2103T` will match `CS2103T meeting`.

Example(s):

* `efilter CS2103T`
* `efilter CS2100 Meeting`

### Listing entries

Lists all entries by displaying them as a list sorted by date. Entries can also be listed by day/week.

Format: `elist [day/week]`

* No argument: listing all entries
* Day: listing entries for today
* Week: listing entries for the next 7 days

Example(s):

* `elist`
* `elist day`
* `elist week`

### Checking if time interval is free

Indicates if an interval is free. If free, a message indicating that will be shown. If not, entries occupying that
interval will be shown in the entries list.

Format: `free sd/START_DATE ed/END_DATE`

* `START DATE` and `END DATE` are in the format `yyyy-mm-dd hh:mm`.
* `START DATE` should be before `END DATE`.

Example(s):

* `free sd/ 2021-12-20 12:00 ed/ 2021-12-20 13:00` if the time interval is free, entries list will be empty and _"You're
  free!"_ message is shown. If not, a message _"Sorry, you're not free. Entries occupying that time interval listed
  below!"_ will be shown, accompanied by occupying entries in the entry list.

### Deleting an entry

Deletes an existing entry with the specified name in the entries list.

Format: `edelete NAME`

* The delete is case-insensitive e.g. `meeting` will match `Meeting`.
* Entries matching at least one keyword will be considered for deletion. However only the first occurrence of the entry
  will be deleted.

Example(s):

* `edelete meeting`

### Clearing overdue entries

Clears all entries that have dates after today's date.

Format: `eclear`

---

### Exiting the program

Exits the program.

Format: `exit`

---

## Command summary

### Others

Action | Format
------- | ------------------
**View all commands** | `help`

### Address Book

Action | Format
-------- | ------------------
**Add** | `add n/NAME p/NUMBER e/EMAIL a/ADDRESS [t/TAG]`
**Find and View (by name)** | `find KEYWORD [MORE_KEYWORDS]`
**Filter tags** | `filter KEYWORD [MORE_KEYWORDS]`
**Edit** | `edit NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`
**List** | `list`
**Delete** | `delete INDEX`
**Clear** | `clear`

### Entries

Action | Format
-------- | ------------------
**Add** | `eadd n/NAME [sd/START DATE] ed/END DATE [t/TAG]`
**Find and View (by name)** | `efind KEYWORD [MORE_KEYWORDS]`
**Filter tags** | `efilter KEYWORD [MORE_KEYWORDS]`
**List (by day/week)** | `elist [day/week]`
**Check if free** | `free sd/START_DATE ed/END_DATE`
**Delete** | `edelete NAME`
**Clear overdue entries** | `eclear`

