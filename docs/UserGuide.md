---
layout: page
title: User Guide
---

TutorsPet is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TutorsPet can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutorspet.jar` from [here](https://github.com/AY2021S2-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/Alice Tan s/Abc Secondary School p/98765432 e/alicet@example.com a/John street, block 123, #01-01 gn/Mary Tan gp/23456789` : Adds a student's contact named `Alice Tan` to TutorsPet.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a contact: `add`

Adds a student’s contact to TutorsPet.

Format: `add n/NAME s/SCHOOL p/PHONE e/EMAIL a/ADDRESS gn/GUARDIAN_NAME gp/GUARDIAN_PHONE [t/TAG]…​ [l/LESSON]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student’s contact can have any number of tags (including 0)
</div>

Examples:
* `add n/Alice Tan s/Abc Secondary School p/98765432 e/alicet@example.com a/John street, block 123, #01-01 gn/Mary Tan gp/23456789`
* `add n/Bob Lee t/sec3 s/Def Secondary School p/87654321 e/bobl@example.com a/Bob street, block 321, #01-02 gn/John Lee gp/12345678 t/classA l/monday 1300`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [s/SCHOOL] [p/PHONE] [e/EMAIL] [a/ADDRESS] [gn/GUARDIAN_NAME] [gp/GUARDIAN_PHONE] [t/TAG] [l/LESSON]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags or lessons, the existing tags or lessons of the person will be removed i.e adding of tags or lessons are not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 1 l/monday 1300 l/tuesday 1400` Edits the 1st person's contact to add 2 lesson details, `monday 1300` and `tuesday 1400`

### Searching for a contact: `search`

Searches for a student’s contact whose details contain any of the given keywords.

Format: `search [n/KEYWORDS] [s/KEYWORDS] [t/KEYWORDS] [MORE_KEYWORDS]`

* The search is case-insensitive. E.g. `TAN` will match `Tan` .
* The order of the keywords does not matter. E.g. `Tan Alice` will match `Alice Tan`.
* Name, school and tags can be searched according to the prefix.
* Only full words will be matched e.g. `Ta` will not match `Tan`
* Contacts matching at least one keyword will be returned. 
  E.g. `Alice Tan` will return `Alice Ng` and `Bob Tan`.

Examples:
* `search n/eliza s/woodlands t/math` returns student whose name is `Eliza`, students who are studying in `woodlands primary school`, and students with the `math` tag
* `search n/Patrick Lim` returns `patrick lim` and `Lim Zi Ying`
* `search s/raffles hwa` returns students studying in `Raffles Institution`,
  `Hwa chong institution`, and also students whose name consists of Hwa or Raffles if there is any.

Searches for a student’s contact whose contact name contains any of the given keywords.

Format: `search n/KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive.

  E.g. `TAN` will match `Tan`
* The order of the keywords does not matter.

  E.g. `Tan Alice` will match `Alice Tan`
* Only the name is searched.
* Only full words will be matched.

  E.g. `Ta` will not match `Tan`
* Contacts matching at least one keyword will be returned.

  E.g. `Alice Tan` will return `Alice Ng` and `Bob Tan`

Examples:
* `search n/eliza` returns `Eliza` and `Eliza Ng`
* `search n/Patrick Lim` returns `patrick lim` and `Lim Zi Ying`

Searches for contacts from a specific school using keywords

Format: `search s/KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. <br/>
  e.g `RAFFLES JUNIOR COLLEGE` will match students studying in `Raffles junior college`
* The order of the keywords does not matter.<br/>
  e.g. `Chong Hwa` will match students studying in `Hwa Chong Institution`
* Only the stated keyword is searched.
* Only full words will be matched e.g. `Raffle` will not match `Raffles`
* The contact matching at least one keyword will be returned (i.e. OR search). <br/>
  e.g. `Raffles Hwa` will return students studying in `Raffles Junior College`or `Hwa Chong Institution`

Examples:
* `search s/woodlands` returns students studying in `woodlands primary school` and `woodlands secondary school`
* `search s/raffles hwa` returns students studying in `Raffles Institution` and `Hwa chong institution`

### Viewing a contact details: `detail`

View the specified student's contact from the address book.

Format: `detail INDEX`

* Views the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `detail 2` views the 2nd student in the address book.
* `find Betsy` followed by `detail 1` views the 1st student in the results of the `find` command.

### Deleting a person : `delete`

Deletes the specified student's contact from the address book.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

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
**Add** | `add n/NAME s/SCHOOL p/PHONE e/EMAIL a/ADDRESS gn/GUARDIAN_NAME gp/GUARDIAN_PHONE [t/TAG]…​ [l/LESSON]…​` <br> e.g., `add n/Bob Lee t/sec3 s/Def Secondary School p/87654321 a/Bob street, block 321, #01-02 gn/John Lee gp/12345678 t/classA`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [s/SCHOOL] [p/PHONE] [e/EMAIL] [a/ADDRESS] [gn/GUARDIAN_NAME] [gp/GUARDIAN_PHONE] [t/TAG]…​ [l/LESSON]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Search** | `search KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
