---
layout: page
title: User Guide
---
Hello! Are you a parent whose phone's contact book is filling up with the contacts of teachers? Do you have trouble
remembering who exactly that contact in your contact book is referring to? Do you have multiple children and want
a centralised place to keep track of all their activities? Then ParentPal could be the app for you!

ParentPal is a **desktop application that helps busy parents manage their children's contacts and related appointments, 
optimized for use via a Command Line Interface** (CLI) <sup>[(1)](#glossary)</sup> while still having the benefits of a
Graphical User Interface (GUI) <sup>[(1)](#glossary)</sup>. 
If you can type fast, ParentPal can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed on your computer.

1. Download the latest `parentpal.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ParentPal.

1. Double-click the file to start the app. The GUI similar to the below image should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.
--------------------------------------------------------------------------------------------------------------------
## Using this Guide

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you see a blue box with a light-bulb like this, it means there is a tip to help you use ParentPal better.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If you see a yellow box with an exclamation mark like this, it means there is a warning to take note of.
</div>

`COMMAND` : If you see words formatted in monospace like this, it represents a command or part of a command.

*Interface* : If you see italicized words, it represents a part of the ParentPal interface. See [Interface of ParentPal](#interface-of-parentpal)
for more information.

>If you see words in a blockquote like this, it represents an example use case for a feature. Give it a try!

--------------------------------------------------------------------------------------------------------------------

## Understanding ParentPal

### Interface of ParentPal
![ParentPalExplanation](images/ParentPalExplanation.png)

1. Button
    * Click to see an option to exit.
1. Command Box
    * ParentPal makes it easy for you to manage your contacts and appointments with a single command.
    * Enter the command in the command box, a success message will be displayed in the status box when the action has been executed successfully.
    * Should an error occur, an error message will be displayed in the status box.
1. Status Box
    * Displays success message or error message.
1. Contact List 
    * Scroll to view all of your contacts.
1. Appointment List
    * Scroll to view all of your appointments.

### Contacts
![ContactCard](images/ContactCard.png)
1. Index
1. Name
1. Favourite Icon
1. Child Tag 
1. Tag
1. Phone Number
1. Email

### Appointments
![AppointmentCard](images/AppointmentCard.png)
1. Index
1. Name
1. Child Tag
1. Address
1. Date and Time
1. Contacts

### Expired appointments
ParentPal helps you to manage your expired appointments by colouring them red, once expired. 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Should the appointment not appear red despite it having expired, click on the appointment box to refresh it.
</div>

![expiredAppt](images/expiredAppt.png)

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

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

This section is separated into the following sub-sections:
* [General Commands](#general-commands): Commands related to managing ParentPal itself
* [Address Book Commands](#address-book-commands): Commands related to managing contacts
* [Appointment Book Commands](#appointment-book-commands): Commands related to managing appointments

The command summary table can also be accessed [here](#command-summary).

### General Commands

#### Viewing help : `help`

Shows information about available commands and how they can be used.

Format: `help [COMMAND]`

* If command is not specified, a summary of all available commands will be displayed, along with a link to access the full user guide.
* Click the **'OPEN URL'** button to open the full user guide.
* If command is specified, detailed information about the command will be displayed.
* If multiple commands are specified, only the last command will be taken.

>Example 1 
> 
>Let's say you are unsure about the commands that ParentPal offers.
You can follow the steps below to view a help window.
>
>Steps: 
>1. Type `help` in the *Command Box*.
>2. Press Enter to execute.
> 
>Outcome: 
>1. The *Status Box* will show a success message.
>2. A help window with details of all commands will open.

> Example 2
> 
> Let's say that you want to view more details about the `add` command.
You can follow the steps below to view a help window for the `add` command.
> 
> Steps: 
> 1. Type `help add` in the *Command Box*.
> 2. Press Enter to execute.
> 
> Outcome: 
> 1. The *Status Box* will show a success message.
> 2. A help window with details of the `add` command will open.


#### Exiting the program : `exit`

Exits the program.

Format: `exit`

>Example 1
>
>Let's say you are done with ParentPal and would like to close the application.
You can follow the steps below to close ParentPal.
>
>Steps:
>1. Type `exit` in the *Command Box*.
>2. Press Enter to execute.
>
>Outcome:
>1. ParentPal closes.

#### Changing ParentPal's theme : `theme`

Changes the theme of ParentPal.

Format: `theme o/OPTION`

Currently available options for the `OPTION` field include: 

Option  | Description
-------- | ------------------
`light` | Sets ParentPal to light theme
`dark` | Sets ParentPal to dark theme

Light theme:
  ![LightTheme](images/lightTheme.png)
Dark theme:
  ![DarkTheme](images/Ui.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
ParentPal's theme is set to dark by default.
</div>

>Example 1
>
>Let's say you would like to try ParentPal's light theme.
You can follow the steps below to change ParentPal to the light theme.
>
>Steps:
>1. Type `theme o/light` in the *Command Box*.
>2. Press Enter to execute.
>
>Outcome:
>1. The *Status Box* will show a success message.
>2. ParentPal will change to the light theme.

### Address Book Commands

#### Adding a contact: `add`

Adds a contact to the address book.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tc/CHILDTAG]…​ [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can add a contact with only some of the information, you can fill in the rest later on using
the <a href="#editing-a-contact--edit">edit</a> command.
</div>

> Example 1
>
> Let's say you want to add a contact, but you only know his phone number.
You can follow the steps below to add the contact with incomplete fields.
>
> Steps:
> 1. Type `add n/John Doe p/98765432` in the *Command Box*.
> 2. Press Enter to execute.
>
> Outcome:
> 
  
> Example 2
> 
> Let's say you want to add contact of your child's math teacher and you have all her details.
> You can follow the steps below to add the contact with complete fields.
> 
> Steps:
> 1. Type add n/Betsy Crowe e/betsycrowe@example.com a/ABC Primary School p/1234567 t/teacher t/math tc/Alice` in the *Command Box*.
> 2. Press Enter to execute.
> 
> Outcome:
> 

#### Deleting a contact : `delete`

Deletes the specified contact(s) from the address book.

Format: `delete INDEX [MORE_INDEXES]…​`

* Deletes the contact at the specified `INDEX` or multiple `INDEXES`.
* The index refers to the index number shown in the displayed contact list.
* The index/indexes **must be a positive integer/integers** 1, 2, 3, …​
* If deleting multiple contacts by multiple indexes, the indexes *must* be separated by a whitespace and must all be valid.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Contacts that are involved with appointments cannot be deleted.
</div>

> Example 1
> 
> Let's say you want to delete the second contact in the address book.
> You can follow the steps below.
> 
> Steps:
> 1. Type `delete 2` in the *Command Box*.
* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `list` followed by `delete 1 2 3` deletes the 1st, 2nd and 3rd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

#### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tc/CHILDTAG]…​ [t/TAG]…​`

* Edits the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove the optional fields (phone, email and address) by typing `p/`, `e/` or `a/` without specifying any phone, email or address after it.
* You can remove all the contact’s tags by typing `t/` or `tc/` without specifying any tags after it. Note: both regular tags and child tags will be removed in both situations.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
When editing tags, the existing tags of the contact will be removed i.e. adding of tags is not cumulative. For cumulative addition of tags,
see the <a href = "#addingreplacing-tags-to-a-contact-tag">tag</a> command.
</div>

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.
*  `edit 4 e/` Edits to remove the email of the 4th contact in the displayed contact list.

>Example 1
>
>Let's say you want to edit the name of a co
You can follow the steps below to view a help window.
>
>Steps:
>1. Type `help` in the *Command Box*.
>2. Press Enter to execute.
>
>Outcome:
>1. The *Status Box* will show a success message.
>2. A help window with details of all commands will open.

#### Finding contacts: `find`

Find contacts based on the given option.

**If no options are specified**, all of a contact's
fields will be searched and any keyword matches in any one of the fields will display that contact.

Format: `find [o/OPTION] KEYWORD [MORE_KEYWORDS]…​`

Currently available options for the `[OPTION]` field include:

Option | Description
-------- |------------------
`name`  | Finds by the name of the contact
`address` | Finds by the address of the contact 
`phone` | Finds by the phone number of the contact 
`email` | Finds by the email of the contact 
`tag` | Finds by the tags of the contact (only exact tags will be matched) 

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
When using the <code>tag</code> option <code>t/</code> needs to be placed in front of the tag 
you are searching for. Also, note that only exact matches will be returned for find by tag.<br>
Example: <code>find o/tag t/first t/second</code>
</div>

* The search is case-insensitive. e.g `alex` will match `ALEX`.
* The order of the keywords does not matter. e.g. `john doe` will match `doe john`.
* Incomplete words will also be matched e.g. `Ale` will match `Alex`.
* Contacts with any field matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Alex David` will return `Alex Yeoh`, `David Li`.
* If *n* contacts can be found, message “*n* Contact(s) listed!” will be displayed
  e.g. when 0 results are found, "0 Contact(s) listed!" is displayed.

Examples:
* `find John` returns `john` and `John Doe`.
* `find alex annie` returns `Alex Yeoh`, `Annie Li` when no exact matches are found.
* `find o/phone 9927` return contacts whose phone number contains 9927 (partial matches will also be returned).

#### Listing all contacts : `list`

Shows a list of all contacts in the address book.

Format: `list [o/OPTION]`

Currently available options for the `[OPTION]` field include:

Option  | Description
-------- | ------------------
`fav` | Shows list of favourited contacts in the address book

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To favourite a contact, use the <a href = "#favourite-a-contact--fav">fav</a> command.
</div>

Examples:
* `list` List all contacts in the address book
* `list o/fav` Lists all favourited contacts in the address book

#### Adding/replacing tags to a contact: `tag` 

Adds or replaces tags to the specified contact by index.

Format: `tag INDEX [o/OPTION] [tc/CHILDTAG]…​ [t/TAG]…​`

* Tags the contact at the specified `INDEX`. 
* The index refers to the index number shown in the displayed contact list. 
* The index **must be a positive integer** 1, 2, 3, …​

Currently available options for the `[OPTION]` field include:

Option  | Description
-------- | ------------------
`replace` | Replaces the currently existing tags with the given new set of tags 
  
Examples:
*  `tag 4 t/School t/English` Adds the tags School and English to the 4th contact.
*  `tag 2 o/replace tc/Alexa t/English` Replaces all existing tags of the 2nd contact with the 
   child tag Alexa and the tag English.

#### Favourite a contact : `fav`

Format: `fav INDEX [o/OPTION]`

* Favourite the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Currently available options for the `[OPTION]` field include:

Option  | Description
-------- | ------------------
* `remove` Unfavourites the specified contact

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When a contact is favourited, the star next to their name will become filled.
When a contact is unfavourited, the star will turn empty.
</div>

Examples:
* `list` followed by `fav 2` favourites the 2nd contact in the address book.
* `find Betsy` followed by `fav 1` favourites the 1st contact in the results of the `find` command.
* `fav 3 o/remove` unfavourites the 3rd contact in the address book.

#### Sorting all contacts : `sort`

Sorts the address book in the order based on the given option.

Format: `sort o/OPTION`

Currently available options for the `[OPTION]` field include:

Option  | Description
-------- | ------------------
`name` | Sorts by name (alphabetical order)
`date` | Sorts by date added (chronological order)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The sorting order is saved across different use sessions.
The default order is by the date the contact was added.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If sort is entered after executing find, a sorted found list will be displayed as explained in the 3rd example above.<br>
The sort order will also be saved and the full address book will be sorted.
</div>

Examples:
* `sort o/name` returns the contact list sorted in alphabetical order.
* `sort o/date` returns the contact list sorted in chronological order.
* `find Alice` followed by `sort o/name` returns the list of contacts found sorted in alphabetical order.



#### Clearing all entries : `clear`

Clears all entries from the address book or clears all contacts with the specified tags.

Format: `clear [t/TAG]…​`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
The <code>TAG</code> here does not differentiate between child tags and regular tags. 
This command will delete all entries that match **any** of the given tags.
</div>

Examples:
* `clear` deletes all entries in the address book.
* `clear t/teacher` deletes all contacts with the tag `teacher`

### Appointment Book Commands

#### Adding an appointment : `addAppt`

Adds an appointment to the appointment book.

Format: `addAppt n/NAME a/ADDRESS d/DATE [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​`

* Contact in the address book at the specified `CONTACT_INDEX` is added to the appointment.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* `DATE` has to be in the format "`dd/MM/yyyy` `HH:mm`".

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
An appointment with the exact same name, date, time and address as an appointment that already exists in the appointment book cannot be added.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
ParentPal currently does not support checking for clashing appointments. Please make sure to check your availability before adding new appointments. This can be done with the help of findAppt by date.
</div>

>Example
>
>Say you just received the details of the annual parent-teacher meeting at Alice's school.
You can follow the steps below to add the appointment to ParentPal.
>
>Steps:
>1. Type `addAppt n/PTM a/ABC Primary School d/21/03/2021 10:00 c/2 tc/alice` in the *Command Box*.
>2. Press Enter to execute.
>
>Outcome:
>1. The *Status Box* will show a success message. 
>2. The contact appears in the appointment list.

#### Deleting an appointment : `deleteAppt`

Deletes the specified appointment from the appointment book.

Format: `deleteAppt INDEX`

* Deletes the appointment at the specified `INDEX`.
* The index refers to the index number shown in the displayed appointment list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteAppt 2` deletes the 2nd appointment in the appointment book.
* `findAppt ptm` followed by `deleteAppt 1` deletes the 1st appointment in the results of the `findAppt` command.

>Example
>
>Say you just received the details of the annual parent-teacher meeting at Alice's school.
You can follow the steps below to add the appointment to ParentPal.
>
>Steps:
>1. Type `addAppt n/PTM a/ABC Primary School d/21/03/2021 10:00 c/2 tc/alice` in the *Command Box*.
>2. Press Enter to execute.
>
>Outcome:
>1. The *Status Box* will show a success message.
>2. The contact appears in the appointment list.

#### Editing an appointment : `editAppt`

Edits an existing appointment to the appointment book.

Format: `editAppt INDEX [n/NAME] [a/ADDRESS] [d/DATE] [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​`

* Contacts in the address book at the specified `CONTACT_INDEX` is added to the appointment.
* Edits the appointment at the specified `INDEX`.
* The index refers to the index number shown in the displayed appointment book.
* The index **must be a positive integer** 1, 2, 3, …​
* `DATE` has to be in the format "`dd/MM/yyyy` `HH:mm`".
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags and contacts, the existing tags and contacts of the contact will be removed 
  i.e the operation is not cumulative.
  
Examples:

* `editAppt 1 n/PSG meeting a/ABC Secondary School c/1` Edits the name and address of the 1st appointment to
be `PSG meeting` and `ABC Secondary School` respectively and replaces all related contacts with the 1st contact 
on the contact list.

#### Finding appointments: `findAppt`

Find appointments based on the given option.

**If no options are specified**, all of an appointment's 
fields will be searched and any keyword matches in any one of the fields will return that appointment.

Format: `findAppt [o/OPTION] KEYWORD [MORE_KEYWORDS]…​`

* The search is case-insensitive. e.g `ptm` will match `PTM`.
* The order of the keywords does not matter. e.g. `Teacher meeting` will match `Meeting teacher`.
* Incomplete words will also be matched e.g. `PT` will match `PTM`.
* Appointments with any field matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Teacher meeting` will return `Speak to ballet teacher`, `PSG meeting`.
* If *n* appointments can be found, message “*n* Appointment(s) listed!” will be displayed
  e.g. when 0 results are found, "0 Appointment(s) listed!" is displayed.
  
Currently available options for the `[OPTION]` field include:

Option | Description
-------- |------------------
`name`  | Finds by the name of the appointment
`child` | Finds by the child that the appointment is tagged to
`address` | Finds by the address of the appointment
`date` | Finds by the date of appointment
`contact` | Finds by the name of the contacts involved in the appointment


Examples:
* `findAppt ptm` returns appointments with any field containing `PTM`.
* `findAppt o/contact annie` returns appointments with at least one contact whose name contains `annie`.

#### Listing all appointments : `listAppt`

Shows a list of all appointments in the appointment book.

List of appointments is always sorted in chronological order.

Format: `listAppt`

Examples:
* `listAppt` List all appointments in the appointment book.

--------------------------------------------------------------------------------------------------------------------

## Managing ParentPal Data

### Saving your data

ParentPal data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing data files

ParentPal data is saved as two JSON files in the `data` directory at `[JAR file location]/data` as
`addressbook.json` and `appointmentbook.json`. 
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ParentPal will discard all data and start with an empty data file at the next run.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Changes to the data files may cause ParentPal to behave unexpectedly, edit the files at your own risk.
</div>

### Transferring your data to another device
#### Exporting your data
1. After running ParentPal at least once, locate the `data` folder on your device which can be found in the same directory as your JAR file. 
2. Transfer this `data` folder to your other device.
   
#### Importing your data
1. Install ParentPal on your new device and run it once, exit the program before proceeding.
2. Copy the `data` folder from your old device to the new device to the same directory as where you installed the JAR file.
3. Override the files on your new device when prompted.

Congratulations! You have successfully transferred your data to a new device.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What's the difference between a ChildTag and a regular Tag? <br>
**A**: ChildTags are meant to represent your children, useful especially 
if you have multiple children. ChildTags will always appear at the front of the list of tags
for each contact and are displayed in a different color to differentiate them. Any command
that works with regular Tags such as `find` will also work with ChildTags.

**Q**: Why is your application named *ParentPal*? <br>
**A**: It is named *ParentPal* because it aims to be a 'pal' to the busy parents who need help managing their kids' schedules and important contacts.

--------------------------------------------------------------------------------------------------------------------

## Glossary

* **Action**: Executed command
* **Address book**: Section of the application that stores and manages data related to contacts
* **Appointment**: Entry in the appointment book containing an appointment's information
* **Appointment list**: List of appointments displayed
* **Appointment book**: Section of the application that stores and manages data related to appointments
* **Backup file**: JSON file that stores address and appointment book data in the hard disk
* **CLI**: Application where you perform actions by typing commands in the Command Box
* **Contact**: Entry in the address book containing a contact's contact information
* **Contact list**: List of contacts displayed
* **GUI**: Application where you interact with it via graphical icons such as buttons
* **Index**: Index number shown in the displayed contact/appointment list
* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
​ | **General Commands**
**Exit** | `exit`
**Help** | `help [COMMAND]` <br> e.g., `help find`
**Theme** | `theme o/OPTION` <br> e.g., `theme o/light`
​ | **Address Book Commands**
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tc/CHILDTAG]…​ [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tc/CHILDTAG]…​ [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [o/OPTION] KEYWORD [MORE_KEYWORDS]`<br> e.g., `find John`
**List** | `list [o/OPTION]`
**Tag** | `tag INDEX [o/OPTION] [tc/CHILDTAG]…​ [t/TAG]…​`<br> e.g., `tag 4 t/School t/English`
**Fav** | `fav INDEX [o/OPTION]` <br> e.g., `fav 3 o/remove`
**Sort** | `sort o/OPTION` <br> e.g., `sort o/name`
**Clear** | `clear [t/TAG]…​`
​ | **Appointment Book Commands**
**Add** | `addAppt n/NAME a/ADDRESS d/DATE [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​` <br> e.g., `addAppt n/PTM a/ABC Primary School d/21/03/2021 10:00 c/2`
**Delete** | `deleteAppt INDEX` <br> e.g., `deleteAppt 2`
**Edit** | `editAppt INDEX [n/NAME] [a/ADDRESS] [d/DATE] [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​` <br> e.g., `editAppt 1 n/PSG meeting a/ABC Secondary School c/1`
**Find** | `findAppt [o/OPTION] KEYWORD [MORE_KEYWORDS]…​` <br> e.g., `findAppt PTM`
**List** | `listAppt`
