---
layout: page
title: User Guide
---


FlashBack is a **desktop application for managing notes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This application allows the improvement of student performance via improved accessibility and organisation of notes, together with interactive sessions that enhance memory retention.
## Table of Contents
1. [Quick start](#1-quick-start)
1. [GUI-layout](#2-gui-layout)
1.  [Features](#3-features)
    * [Viewing help](#viewing-help-help): `help`
    * [Adding a new card](#adding-a-new-card-add): `add`
    * [Listing all cards](#listing-all-cards--list): `list`
    * [Deleting a card](#deleting-a-card--delete): `delete`
    * [Viewing a card](#viewing-a-card--view): `view`
    * [Finding cards](#finding-cards--find): `find` 
    * [Clearing all cards](#clearing-all-entries--clear): `clear`
    * [Exiting the program](#exiting-the-program--exit): `exit`
    * [Saving data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Filtering cards](#filtering-cards-coming-in-v13): `[coming in v1.3]`
    * [Review mode](#review-mode-coming-in-v13): `[coming in v1.3]`
1. [FAQ](#4-faq)
1. [Command Summary](#5-command-summary)


--------------------------------------------------------------------------------------------------------------------

## 1. Quick start


1. Ensure you have Java `11` or above installed in your Computer.
1. Download the latest `FlashBack.jar`.
1. Copy the file to the folder you want to use as the _home folder_ for your FlashBack.
1. Double-click the file to start the app. The GUI should appear in a few seconds.
![GUI](./images/Ui.png)
1. Below are some commands you can try, type the command in the command box and press Enter to execute it:
    * **`help`** : Opens the help window.
    * **`add`**`q/ What is the Einstein’s Equation? a/e=mc^2 c/Physics p/High t/ModernPhysics`: Adds a new flashcard named `What is the Einstein's Equation?` to FlashBack.
    * **`delete`**`1`: Deletes the 1st card shown in the current list.
    * **`clear`**: Deletes all cards. 
    * **`exit`**: Exits the application.
1. Refer to the Features below for details of each command.


--------------------------------------------------------------------------------------------------------------------
## 2. GUI layout

--------------------------------------------------------------------------------------------------------------------
## 3. Features

### Viewing help: `help`
Shows a message explaining how to access the help page.

Format: `help`

### Adding a new card: `add`

Adds a new card to the card list.

Format: `add q/QUESTION a/ANSWER c/CATEGORY p/PRIORITY [t/TAG]` <br />
Note: The TAG is optional when adding a new card.

Examples:
* `add q/Einstein’s Equation a/e=mc^2 c/Physics p/High`  
* `add q/Independence day of Singapore a/August 9th 1965 c/History p/Mid t/Singapore`

### Editing a card: `edit`

Edits an existing flash card in the card list.

Format: `edit INDEX [q/NEW QUESTION] [a/NEW ANSWER] [c/NEW CATEGORY]` <br />
`[p/NEW PRIORITY] [t/TAG]`

* Edits the card at the specified `INDEX`.
* The index refers to the index number shown in the displayed card list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least 1 updated card field must be provided for modification.


### Listing all cards : `list`

Shows all cards in the card list.

Format: `list`

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

### Finding cards : `find`

Find cards according to a search criteria containing any of the given keywords.

Format: `find CRITERIA KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `equation` will match `Equation`
* The order of the keywords does not matter. e.g. `Newton Equation` will match `Equation Newton`
* The search will be according to the `CRITERIA` given, does not support searching by more than one different criteria:
  * `q/` to search by questions.
  * `c/` to search by categories.
  * `p/` to search by priorities.
  * `t/` to search by tags.
* Partial words can be matched when searching by questions or priorities. e.g. `Wh` will match `What?`
* Only full words will be matched when searching by categories or tags. e.g. `Wh` will not match `What?`
* Flashcards matching at least one keyword will be returned. e.g. `find q/ Newton's Equation` will return flashcards with question of `Newton's Second Law of Motion` and `Einstein's Equation`
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can list all flashcards again by using `list` command

</div>

Examples:
* `find q/ equation` will return flashcards with `Equation` or `equation` in its questions.
* `find c/ computer` will return flashcards with `computer` in its category.<br>
![result for `find c/ computer`](images/findComputerResult.png)
* `find p/ low` will return flashcards with `low` as its priority.
* `find t/ trivia` will return flashcards with `trivia` in its tags.

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

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FlashBack will discard all data and start with an empty data file at the next run.

</div>

### Filtering cards: `[coming in v1.3]`

_Details coming soon ..._

### Review mode: `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FlashBack home folder.


--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action | Format, Examples
--------|------------------
**Add** | `add q/QUESTION a/ANSWER c/CATEGORY p/PRIORITY [t/TAGS]` <br> e.g., `add q/ What is the Einstein’s Equation? a/e=mc^2 c/Physics p/High t/ModernPhysics`
**Delete** | `delete INDEX` <br> e.g., `delete 1`
**Edit** | `edit INDEX` <br> e.g., `edit 3 a/NEW ANSWER p/NEW PRIORITY`
**View** | `view INDEX` <br> e.g., `view 2`
**Find** | `find CRITERIA KEYWORD [MORE_KEYWORDS]`<br> e.g., `find q/ equation`, `find c/ computer science`,<br> `find p/ low`, `find t/ random`
**Clear** | `clear`
**List** | `list`
**Help** | `help`
**Exit** | `exit`

