---
layout: page
title: User Guide
---


FlashBack is a **desktop application for managing notes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This application allows the improvement of student performance via improved accessibility and organisation of notes, together with interactive sessions that enhance memory retention.
* Table of Contents
{:toc}
* Quick start
*  Features
    *  Listing all cards: list
*  Command Summary


--------------------------------------------------------------------------------------------------------------------

## Quick start


1. Ensure you have Java 11 or above installed in your Computer.
1. Download the latest FlashBack.jar.
1. Copy the file to the folder you want to use as the home folder for your FlashBack.
1. Type the command in the command box and press Enter to execute it:
    * **`add`**`k/ Einstein’s Equation d/ e=mc^2 t/ Physics` and pressing `enter` will add a new card.
1. Refer to the Features below for details of each command.


--------------------------------------------------------------------------------------------------------------------

## Features


### Adding a new card: `add`

Adds a new card to the card list.

Format: `add k/ KEYWORD d/ DESCRIPTION [t/ TOPIC]` <br />
Note: The TOPIC is optional when adding a new card.

Examples:
* `add k/ Einstein’s Equation d/ e=mc^2 t/ Physics`
* `add k/ Independence day of Singapore d/ August 9th 1965 t/ History`


### Listing all cards : `list`

Shows all cards in the card list.


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

### Deleting a card : `delete`

Deletes the specified card from the card list.

Format: `delete INDEX`

* Deletes the card at the specified `INDEX`.
* The index refers to the index number shown in the displayed card list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd card in the card list.

### Viewing a card : `view`

Views a specific card from the card list.

Format: `view INDEX`

* Views the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `view 1` shows the 1st flashcard (in the displayed flashcard list).

### Clearing all entries : `clear`

Clears all entries from FlashBack.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FlashBack data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FlashBack data are saved as a JSON file `[JAR file location]/data/flashback.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FlashBack will discard all data and start with an empty data file at the next run.
</div>

### Filtering cards: `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FlashBack home folder.


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------

**Add** | `add k/ KEYWORD d/ DESCRIPTION [t/ TOPIC]` <br> e.g., `add k/ Einstein’s Equation d/ e=mc^2 t/ Physics`

**List** | `list`

