---
layout: page
title: User Guide
---

**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dictionote.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Dictionote application.

1. Double-click the file to start the app. The GUI should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addNote c/CONTENT`, `CONTENT` is a parameter which can be used as `addnote c/This is a note`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listcommand`, `exit` and `clearcontact`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
* Extraneous parameter for commands that only take in one parameters (such as `open`, `close`, and `setdividerc) will not be ignored.<br>
  e.g. if the command specifies `open -c 123`, the command will be invalid.
  
  
</div>

### Guide Feature
#### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Viewing Command List : `listcommand`

Shows a list of available command

Format: `listcommand`

### Dictionary Features

#### Finding content in the Dictionary using keywords: `findcontent`

Finds relevant content in the dictionary that matches the keywords.

Format: `findcontent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `programming` will match `PROGRAMMING` or `Programming`
* The order of the keywords does not matter. e.g. `content some` will match `some content`
* Only the content is searched, headers and week numbers are not included.
* Only full words will be matched e.g. `prog` will not match `programming`
* Contents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `some different` will return `some content`, `different thing`

Examples:
* `findcontent programming` returns `Programming` and `PROGRAMMING LANGUAGE`
* `findcontent some different` returns `some content`, `different thing`<br>


#### Finding definition in the Dictionary using keywords: `finddef`

Finds relevant content in the dictionary that matches the keywords.

Format: `finddef KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `programming` will match `PROGRAMMING` or `Programming`
* The order of the keywords does not matter. e.g. `content some` will match `some content`
* Only the content is searched, headers and week numbers are not included.
* Only full words will be matched e.g. `prog` will not match `programming`
* Contents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `some different` will return `some content`, `different thing`

Examples:
* `finddef programming` returns `Programming Language` and `Object-Oriented Programming` <br>


#### Show a dictionary content : `showdc`

Showcases a dictionary content.

Format: `showdc INDEX​`

* Shows the dictionary content at the specified `INDEX`.
* The index refers to the index number shown in the displayed dictionary list. The index **must be a positive integer** 1, 2, 3, …​

#### Listing all content : `listcontent`

Shows a list of all contents in the Dictionary.

Format: `listcontent`

#### Listing all definitions : `listdef`

Shows a list of all the definitions in the Dictionary.

Format: `listdef`


### Note Features

#### Add a new note: `addnote`

Adds a note equipped with some tags.

Format: `addnote c/CONTENT [t/TAG]...`

* Tags are optional. However, there must be exactly one content.
* In the current version, notes will be stored as a pure string.

Examples:
* `addnote c/Do Homework`
* `addnote c/Study for Midterms t/CS2103`

#### Delete a new note: `deletenote`

Deletes the specified note from the note list.

Format: `deletenote INDEX`

* Deletes the note at the specified `INDEX`.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​

#### Mark a note as done: `markasdonenote`

Marks a note in a list as done.

Format: `markasdonenote INDEX`

* Marks the note at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​
* After execution, the affected note will be marked with a green tick.

#### Mark a note as undone: `markasundonenote`

Marks a note in a list as undone.

Format: `markasundonenote INDEX`

* Marks the note at the specified `INDEX` as undone.
* The index refers to the index number shown in the displayed note list.
* The index **must be a positive integer** 1, 2, 3, …​
* After execution, the affected note will not be marked with a green tick.

#### Mark all notes as undone: `markallasundonenote`

Marks all notes in a list as undone.

Format: `markallasundonenote`

* Marks all the notes as undone.
* After execution, all notes marked with a green tick will be reset.

#### Editing a note : `editnote`

Edits an existing note in the note list.

Format: `editnote INDEX c/CONTENT [t/TAG]…​`

* Edits the note at the specified `INDEX`. The index refers to the index number shown in the displayed note list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the note will be removed (i.e., adding of tags is not cumulative).

Examples:
*  `editnote 1 c/Hello t/Important` Edits the content and tags of the 1st contact to be `Hello` and `Important` respectively.

#### Show a note : `shownote`

Showcases a note.

Format: `shownote INDEX​`

* Shows the note at the specified `INDEX`. The index refers to the index number shown in the displayed note list. The index **must be a positive integer** 1, 2, 3, …​

#### List all notes : `listnote`

Lists every note on the note list.

Format: `listnote​`

#### Sort all notes : `sortnote`

Sort every note on the note list alphabetically.

Format: `sortnote`

#### Find notes using a keyword : `findnote`

Find notes whose names contain any of—or tags contain all of—the given keywords.

Format: `findnote c/NAME_KEYWORD... [t/TAG_KEYWORD]...`

* The search is case-insensitive. e.g `c/cs2103` will match the name `CS2103`
* Only the content and tags are searched.
* Notes and tags will be matched if they contain the given keywords e.g. `c/CS` will match the note containing `CS2103T`
* Notes matching at least one content keyword will be returned (i.e. OR search). e.g. `c/CS c/Important` will return `CS Midterm`, `Important stuff`
* Notes matching all of the given tag keywords will be returned (i.e. AND search). e.g. `c/urgent` will return all notes that are tagged with `urgent`.

Examples:

* `findnote c/CS2103` returns note containing `CS2103`

#### Edit a note in edit mode : `editmode`

Edits a note in edits mode.

Format: `editmodenote`

* A note have to be show on the note content panel using `shownote` command.
* In edit note mode, all others note related command will be disable.
* To exit edit note mode, use `exitnote` to discard all changes or `savenote` to save all changes


Examples:
* `editmodenote`
  * note content will be editable

#### Save and quit edit mode: `save`

Save edited content and quit edits mode and.

Format: `save`

* The program have to be in edit mode.
* All changes will be saved.

Examples:
* `save`
  * exit edit mode and save all changes.

#### Quit edit mode : `quit`

Quit edits mode.

Format: `quit`

* The program have to be in edit mode.
* All changes will be discarded.

Examples:
* `quit`
  * quit edit mode and discard all changes.
  

### Contact Features

#### Adding a contact: `addcontact`

Adds a contact to the contacts list.

Format: `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addcontact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Listing all contacts : `listcontact`

Shows a list of all contacts in the contacts list.

Format: `listcontact`

#### Editing a contact : `editcontact`

Edits an existing contact in the contacts list.

Format: `editcontact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contacts list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed (i.e., adding of tags is not cumulative).
* You can remove all of the contact’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `editcontact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `editcontact 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

#### Locating contacts: `findcontact`

Finds contacts whose names and emails contain any of—or tags contain all of—the given keywords.

Format: `findcontact [n/NAME_KEYWORD]... [e/EMAIL_KEYWORD]... [t/TAG_KEYWORD]...`

* The search is case-insensitive. e.g `n/hans` will match the name `Hans`
* The order of the keywords does not matter. e.g. `n/Hans n/Bo` will match the name `Bo Hans`
* Only the name, email, and tags are searched.
* Names, emails, and tags will be matched if they contain the given keywords e.g. `n/Han` will match the name `Hans`
* Contacts matching at least one name keyword will be returned (i.e. `OR` search).
  e.g. `n/Hans n/Bo` will return `Hans Gruber`, `Bo Yang`
* Contacts matching at least one email keyword will be returned (i.e. `OR` search).
  e.g. `e/@mail.com e/@mail.net` will return contacts with the following emails: `hans@mail.com`, `bo@mail.net`
* Contacts matching all of the given tag keywords will be returned (i.e. `AND` search).
  e.g. `t/friends t/university` will only return all contacts that are tagged with both `Friends` and `University`

Examples:
* `findcontact n/John` returns `john` and `John Doe`
* `findcontact n/alex n/david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* (More examples to be added...)

#### Deleting a contact : `deletecontact`

Deletes the specified contact from the contacts list.

Format: `deletecontact INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contacts list.
* The index **must be a positive integer** 1, 2, 3, …​

#### Sending an email to a contact : `emailcontact`

Opens a new window to send an email to the specified contact from the contacts list.

Format: `emailcontact INDEX`

* Opens a new message composition window with the *to* field containing the email address of the contact at the specified `INDEX`.
* The application uses the user's default mailing application to provide email features.
* The application **does not guarantee** the success of sending an email, as it is handled by the mailing application used.
* The index refers to the index number shown in the displayed contacts list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listcontact` followed by `emailcontact 2` opens a new message composition window targeting the 2nd contact in the contacts list.
* `findcontact n/Alice` followed by `emailcontact 1` opens a new message composition window targeting the 1st contact in the results of the `findcontact` command.

#### Sorting the contacts list by most frequently-contacted: `mostfreqcontact`

Sorts the contacts list in descending order based on the number of email sending attempts to each contact.

Format: `mostfreqcontact`

* Each sucessful execution of `emailcommand` will be counted as an email sending attempt, regardless of whether an email was actually sent or not.
* The ordering of the contacts list after sorting will replace the original ordering. (i.e., the sorted ordering is stored by Dictionote)

#### Clearing the contacts list : `clearcontact`

Clears the contacts list from all contacts.

Format: `clearcontact`


### User Interface Feature
Dictionote allows the user to manipulate the user-interface via command. 
The following are the 5 region where the user can manipulate

![Ui Panel ](images/UiPanel.png)

The region name is as follow :
1. Contact Panel
1. Dictionary List Panel
1. Dictionary Content Panel
1. Note List Panel
1. Dictionary Content Panel


#### Opening and Closing UI Panel

##### Opening UI Panel: `open`


Open selected UI Panel.

Format: `open Option`

* The following `Option` are supported
    * `-a`: All panel
    * `-c`: Contact panel
    * `-d`: Dictionary panel
    * `-dc`: Dictionary content panel
    * `-dl`: Dictionary list panel
    * `-n`: Note panel
    * `-nc`: Note content panel
    * `-nl`: Note list panel
    * `-l`: Both dictionary list and note list panel

Examples:
* `open -c`
    * show contact panel
* `open -a`
    * show all panel

##### Closing UI Panel: `close`

Close selected UI Panel.

Format: `close Option`

* The following `Option` are supported
    * `-a`: All panel
    * `-c`: Contact panel
    * `-d`: Both dictionary list panel and dictionary content panel
    * `-dc`: Dictionary content panel
    * `-dl`: Dictionary list panel
    * `-n`: Both note list panel and note content panel
    * `-nc`: Note content panel
    * `-nl`: Note list panel
    * `-l`: Both dictionary list and note list panel

Examples:
* `close -c`
    * close contact panel
* `close -a`
    * close all panel

#### Setting UI Divider Position
Dictionote allows the user to manipulate the divider between the region via command.
The following are the 4 divider where the user can manipulate

![Ui Panel ](images/UiDivider.png)

The divider name is as follow :
1. Contact Divider
1. Dictionary Divider
1. Note Divider 
1. Main Divider

The following images show the position the divider will be set when user enter a value from 1 to 9 horizontally and vertically

![Ui Panel ](images/UiDividerConfig.png)

##### Set contact divider position: `setdividerc`

Sets the position of the contact divider.
The note divider is the divider separating the contact and others user interface.

Format: `setdividerc Position`

* The `Position` **must be a between 1 to 9** (inclusive)
* The `Position` **must be a positive integer** 1, 2, 3, …​

Examples:
* `setdividerc 8`

##### Set dictionary divider position: `setdividerd`

Sets the position of the dictionary divider.
The dictionary divider is the divider separating the dictionary list and dictionary content.

Format: `setdividerd Position`

* The `Position` **must be a between 1 to 9** (inclusive)
* The `Position` **must be a positive integer** 1, 2, 3, …​

Examples:
* `setdividerd 5`

##### Set note divider position: `setdividern`

Sets the position of the note divider.
The note divider is the divider separating the note list and note content.

Format: `setdividern Position`

* The `Position` **must be a between 1 to 9** (inclusive)
* The `Position` **must be a positive integer** 1, 2, 3, …​

Examples:
* `setdividern 6`

##### Set main divider position: `setdividerm`

Sets the position of the main divider.
The main divider is the divider separating the note and dictionary.

Format: `setdividerm Position`

* The `Position` **must be a between 1 to 9** (inclusive)
* The `Position` **must be a positive integer** 1, 2, 3, …​

Examples:
* `setdividerm 3`

#### Toggle Divider Orientation
Dictionote allows the user to change the orientation of the divider between the region via command.
The following are the 2 divider where the user can manipulate

![Ui Panel ](images/UiOrientation.png)

The divider name is as follow :
1. Dictionary Divider
1. Note Divider

##### Toggle dictionary divider orientation: `toggledividerd`

Toggle the orientation of the dictionary divider.
If the current orientation of the dictionary divider on horizontal,
it will be changed to vertical and vice versa.

Format: `toggledividerd`

Examples:
* `togglerdividerd`

##### Toggle note divider orientation: `toggledividern`

Toggle the orientation of the note divider.
If the current orientation of the note divider horizontal,
it will be changed to vertical and vice versa.

Format: `toggledividern`

Examples:
* `toggledividern`


### Others Feature

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Viewing help** | `help`
**Viewing Command List** | `listcommand`
**Exit** | `exit`
***Dictionary Features*** | -
**Find content** | `findcontent KEYWORD [MORE_KEYWORDS]`
**Find definition** | `finddef KEYWORD [MORE_KEYWORDS]`
**Show specific content** | `showdc INDEX`
**List content** | `listcontent`
**List Definitions** | `listdef`
***Note Features*** | -
**Add note** | `addnote c/CONTENT [t/TAG]…​`
**Delete note** | `deletenote INDEX`
**Mark note as done** | `markasdonenote INDEX`
**Mark note as undone** | `markasundonenote INDEX`
**Mark all notes as undone** | `markallasundonenote`
**Edit note** | `editnote INDEX c/CONTEXT [t/TAG]…​`
**Show note** | `shownote`
**List all notes** | `listnote`
**Sort all notes** | `sortnote`
**Find notes using keywords** | `findnote c/NAME_KEYWORD…​ [t/TAG_KEYWORD]…​`
**Edit note in edit mode** | `editmodenote`
**Exit edit mode** | `exitnote`
**Save changes to note** | `savenote`
***Contact Features*** | -
**Add contact** | `addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addcontact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**List all contacts** | `listcontact`
**Edit contact** | `editcontact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editcontact 2 n/James Lee e/jameslee@example.com`
**Find contacts** | `findcontact [n/NAME_KEYWORD]... [e/EMAIL_KEYWORD]... [t/TAG_KEYWORD]...`<br> e.g., `findcontact n/James e/@mail.com t/family`
**Delete contact** | `deletecontact INDEX`<br> e.g., `deletecontact 3`
**Send email to contact** | `emailcontact INDEX`<br> e.g., `emailcontact 2`
**Sort contacts by most-frequent** | `mostfreqcontact`
**Clear contacts list** | `clearcontact`
***UI Features*** | -
**Open** | `open Option` <br> e.g., `open -c`
**Close** | `close Option` <br> e.g., `close -c`
**Set contact divider position** | `setdividerc POSITION`
**Set dictionary divider position** | `setdividerd POSITION`
**Set note divider position** | `setdividern POSITION`
**Set main divider position** | `setdividerm POSITION`
**Toggle dictionary divider orientation** | `toggledividerd`
**Toggle note divider orientation** | `toggledividern`
