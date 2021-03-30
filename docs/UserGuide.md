---
layout: page
title: User Guide
---


FlashBack is a **desktop application for managing notes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This application allows the improvement of student performance via improved accessibility and organisation of notes, together with interactive sessions that enhance memory retention.

## Table of Contents

* [Quick start](#quick-start)
* [Application layout](#application-layout)
* [Features](#features)
    * [Main mode](#main-mode)
        * [Viewing help](#viewing-help-help): `help`
        * [Adding a new flashcard](#adding-a-new-flashcard-add): `add`
        * [Listing all flashcards](#listing-all-flashcards--list): `list`
        * [Deleting a flashcard](#deleting-a-flashcard--delete): `delete`
        * [Viewing a flashcard](#viewing-a-flashcard--view): `view`
        * [Finding flashcards](#finding-flashcards--find): `find`
        * [Filtering flashcards](#filtering-flashcards-filter): `filter`
        * [Clearing all flashcards](#clearing-all-entries--clear): `clear`
        * [Undoing a command](#undoing-a-command--undo): `undo`
        * [Redoing a command](#redoing-a-command--redo): `redo`
        * [Sorting all flashcards](#sorting-all-flashcards-sort): `sort`
        * [Entering review mode](#entering-review-mode-review): `review`
        * [Viewing statistics of cards](#viewing-statistics-of-cards-stats): `stats`
        * [Exiting the program](#exiting-the-program--exit): `exit`
        * [Saving data](#saving-the-data)
        * [Editing the data file](#editing-the-data-file)
    * [Review mode](#review-mode)
        * [Showing next flashcard](#showing-next-flashcard--n): `n`
        * [Showing previous flashcard](#showing-previous-flashcard--p): `p`
        * [Showing answer](#showing-answer--a): `a`
        * [Hiding answer](#hiding-answer--h): `h`
        * [Reviewing a flashcard as correct](#reviewing-a-flashcard-as-correct--t): `t`
        * [Reviewing a flashcard as wrong](#reviewing-a-flashcard-as-wrong--f): `f` 
        * [Quitting review mode](#quitting-review-mode--q): `q`
* [FAQ](#faq)
* [Command Summary](#command-summary)
    * [Main mode command](#main-mode-command)
    * [Review mode command](#review-mode-command)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
1. Download the latest `FlashBack.jar` from [here](https://github.com/AY2021S2-CS2103T-T13-3/tp/releases).
1. Copy the file to the folder you want to use as the home folder for your FlashBack.
1. Double-click the file to start the app. The GUI should appear in a few seconds. <br><br>
   ![GUI](./images/Ui.png) <br><br>
1. Below are some commands you can try, type the command in the command box and press Enter to execute it:
    * **`help`** : Opens the help window.
    * **`add`**`q/ What is the Einstein’s Equation? a/e=mc^2 c/Physics p/High t/ModernPhysics`: Adds a new flashcard
      named `What is the Einstein's Equation?` to FlashBack.
    * **`view`**`2`: Views the 2nd flashcard shown in the current list.
    * **`delete`**`1`: Deletes the 1st flashcard shown in the current list.
    * **`clear`**: Deletes all flashcards.
    * **`exit`**: Exits the application.
1. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Application layout

The figures below explain the different components in FlashBack. <br>
Main window<br>
![MainWindowComponents](./images/UiMainWindowComponents.png) <br>
Review mode <br>
![ReviewModeComponents](./images/UiReviewModeComponents.png) <br><br>
Brief explanation of each components:

Components      | Explanation
----------------|------------------
Command Box | This is where you type all the commands.
Result Display | This is where the result of your input to the command box is displayed.
View Pane | This is where the output for `view` and `stats` command is displayed.
Flashcard List | This is where all the flashcards are displayed to user.
Main mode | This is the first window when you open the app. Most of the command is executed here.
Review mode | This is where you can review all your flashcards. You can enter this mode by typing `review` in the Command Box of the Main mode.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in UPPER_CASE are the parameters to be supplied by the user. <br>
  e.g. In `add q/QUESTION`, `QUESTION` is a parameter which can be used as `add q/What is Fermat's Last Theorem?`.<br>

* Items in square brackets are optional. <br>
  e.g. `q/QUESTION [t/TAG]` can be used as `q/What is Singapore Independence Day?` or
  as `q/What is Singapore Independence Day? t/Singapore`<br>

* Items with `…​` after them can be used multiple times including zero times. <br>
  e.g. `[t/TAG]…`​ can be used as `t/vocabulary`, `t/vocabulary t/SAT` etc.

* Parameters can be in any order. <br>
  e.g. If the command specifies `q/QUESTION a/ANSWER`, `a/ANSWER q/QUESTION` is also acceptable.<br>

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `undo`, `exit` and `clear`)
  will be ignored. <br>
  e.g. If the command specifies `help 123`, it will be interpreted as `help`.

* Prefixes are case-sensitive. <br>
  e.g. `q/` is not the same as `Q/`.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken. <br>
  e.g. If you specify `c/Geography c/History`, only `c/History` will be taken.

</div>

<div style="page-break-after: always;"></div>

## Main mode

### Viewing help: `help`

Shows a message explaining how to access the help page.<br><br>
![HelpMessage](./images/helpMessage.png) <br><br>
Format: `help`

### Adding a new flashcard: `add`

Adds a new flashcard to the flashcard list.<br>
Format: `add q/QUESTION a/ANSWER c/CATEGORY p/PRIORITY [t/TAG]...` <br />
<div markdown="span" class="alert alert-primary">:memo: **Note:** <br>
The `TAG` is optional when adding a new flashcard.<br>
Priority can only take 1 out of 3 values: `High`, `Mid` or `Low`.
</div>

<div style="page-break-after: always;"></div>

Examples:

* `add q/Einstein’s Equation a/e=mc^2 c/Physics p/High` <br><br>
  ![UIAdd](./images/UiAddResult.png) <br><br>
* `add q/Independence day of Singapore a/August 9th 1965 c/History p/Mid t/Singapore`

### Editing a flashcard: `edit`

Edits an existing flashcard in the flashcard list.

Format: `edit INDEX [q/NEW QUESTION] [a/NEW ANSWER] [c/NEW CATEGORY] [p/NEW PRIORITY] [t/TAG]`

* Edits the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …
* At least 1 updated card field must be provided for modification.
* If the tag field is specified in the command, all existing tag(s) will be removed and replaced by the new tag(s).
* New tag(s) should be alphanumeric, and there should not be any spacing between characters.
* New priority can only be "Low", "Mid" or "High", case-sensitive.

<div style="page-break-after: always;"></div>

Examples:

* `edit 1 p/Low` <br><br>
  ![UIEdit](./images/UiEditResult.png) <br><br>
* `edit 3 q/What is the time complexity for Quick Sort? t/Algorithms`

### Listing all flashcards : `list`

Shows all flashcards in the flashcard list.

Format: `list`

<div style="page-break-after: always;"></div>

### Deleting a flashcard : `delete`

Deletes the specified flashcard from the flashcard list.

Format: `delete INDEX`

* Deletes the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd flashcard in the flashcard list. <br>
  Before executing command `delete 2`: <br>
  ![UIBeforeDelete](./images/UiBeforeDelete.png) <br>
  After executing command `delete 2`: <br>
  ![UIAfterDelete](./images/UiAfterDelete.png)

### Viewing a flashcard : `view`

Views a specific flashcard from the flashcard list. <br>

Format: `view INDEX`

* Views the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

<div style="page-break-after: always;"></div>

Examples:

* `view 2` shows the 2nd flashcard (in the displayed flashcard list). <br><br>
  ![UIView](./images/UiViewResult.png)

### Finding flashcards : `find`

Finds flashcards containing any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `equation` will match `Equation`
* The order of the keywords does not matter. e.g. `Newton Equation` will match `Equation Newton`
* Partial words can be matched when searching. e.g. `Wh` will match `What?` but `What?` will not match `What`
* Any flashcard's fields (e.g. question, answer, category, priority, and tag) matching any keyword will be returned.
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can list all flashcards again by using `list` command

</div>

<div style="page-break-after: always;"></div>

Examples:
* `find computer` will return cards with `computer` in any of its fields.<br><br>
![result for `find computer`](images/findComputerResult.png) <br><br>
* `find computer formula` will return cards with `computer` or `formula` in any of its fields.<br><br>
![result for `find computer formula`](images/findComputerFormulaResult.png) <br><br>
* `find phy` will return cards with `phy` contained in any of the words in any of its fields.<br><br>
![result for `find phy`](images/findPhyResult.png) <br><br>

### Filtering flashcards: `filter`

Filter flashcards based on specified field input.

<div markdown="span" class="alert alert-primary">

:bulb: **Note:** You can only filter by `question`, `category`, `priority`, and `tag`.

</div>

Format: `filter [q/QUESTION] [c/CATEGORY] [p/PRIORITY] [t/TAG]`

* The filter is case-insensitive. e.g. `equation` will match `Equation`
* Must filter by at least 1 field, but not every field is required.
* Can filter by multiple keywords for each field. e.g. `filter q/einstein math`
* The order of the keywords does not matter. e.g. `Newton Equation` will match `Equation Newton`
* The order of the field input does not matter. e.g. `filter q/QUESTION c/CATEGORY` is same as `filter c/CATEGORY q/QUESTION`
* Filtered flashcards must match all field inputs. e.g. `filter q/einstein p/mid` will only display flashcards with question containing `einstein` and `mid` priority.
* Partial words can be matched when filtering. e.g. `Wh` will match `What?` but `What?` will not match `What`
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** You can list all flashcards again by using `list` command.

</div>

Examples:
* `filter q/charles recursion` will return cards with `charles` or `recursion` contained in its question.<br><br>
  ![result for `filter q/charles recursion`](images/filterCharlesRecursionResult.png) <br><br>
* `filter p/mid q/formula` will return cards with `formula` contained in its question and `mid` priority.<br><br>
  ![result for `filter p/mid q/formula`](images/filterMidFormulaResult.png) <br><br>
* `filter c/com t/ran` will return cards with `com` contained in its category and `ran`contained in any of its tags.<br><br>
  ![result for `filter c/com t/ran`](images/filterComRanResult.png) <br><br>

### Clearing all entries : `clear`

Clears all entries from FlashBack.

Format: `clear`

### Undoing a command : `undo`

Restores FlashBack to the state before the previous command was executed.
<div markdown="span" class="alert alert-primary">:memo:
**Note:**  Only commands that modify FlashBack's content can be reversed. (`add`, `delete`, `edit` and `clear`).
</div>

Format: `undo`

Examples:
`delete 3` <br><br>
![UiDeleteBeforeUndo](./images/UiDeleteBeforeUndo.png) <br><br>
`clear`  <br><br>
![UiClearBeforeUndo](./images/UiClearBeforeUndo.png) <br><br>
`undo` will reverse the `clear` command. <br><br>
![UiClearAfterUndo](./images/UiClearAfterUndo.png) <br><br>
`undo` will reverse the `delete 3` command. <br><br>
![UiDeleteAfterUndo](./images/UiDeleteAfterUndo.png) <br><br>

### Redoing a command : `redo`

Restores FlashBack to the state before the previous command was undo.

Format: `redo`

Examples:
`clear` <br><br>
![UiClearBeforeUndoBeforeRedo](./images/UiClearBeforeUndoBeforeRedo.png) <br><br>
`undo` will reverse the `clear` command. <br><br>
![UiClearAfterUndoBeforeRedo](./images/UiClearAfterUndoBeforeRedo.png) <br><br>
`redo` will reverse the `undo` command. <br><br>
![UiClearAfterUndoAfterRedo](./images/UiClearAfterUndoAfterRedo.png) <br><br>

### Sorting all flashcards: `sort`
Sorts all flashcards according to a given option.
<div markdown="span" class="alert alert-primary">

:bulb: **Note:** You can only sort by `priority` or `question`.

</div>

Format: `sort OPTION ORDER` <br>
Examples:

`sort priority -a` will sort the flashcards by ascending priority. <br>
`sort priority -d` will sort the flashcards by descending priority. <br>
`sort question -a` will sort the flashcards by ascending question. <br>
`sort question -d` will sort the flashcards by descending question. <br>
Before sort command is executed. <br><br>
![UiBeforeSort](./images/UiBeforeSort.png) <br><br>
`sort priority -a`<br><br>
After sort command is executed. <br><br>
![UiAfterSort](./images/UiAfterSort.png)

### Entering review mode: `review`
Reviews the current list of flashcards.<br>
When the user enters `review` in the command box, this new window will appear. <br><br>
![UiReviewMode](./images/UiReviewModeNoAnswer.png) <br><br>
Format: `review`

### Viewing statistics of flashcards: `stats`
Shows the statistics of an individual flashcard, or the statistics of all flashcards.

The following statistics are displayed:
* Number of times the user reviewed the flashcard(s).
* Number of times the user reviewed the flashcard(s) and got the correct answers.
* The correct rate of the flashcard(s). i.e The number of correct answer reviews over the total number of reviews.
* The wrong rate of the flashcard(s). i.e The number of wrong answer reviews over the total number of reviews.

Format: `stats INDEX` <br>

<div markdown="span" class="alert alert-primary">:memo: **Note:**
If a valid `INDEX` is provided, the statistics of the flashcard identified by the provided index is shown.
If the `INDEX` is omitted, FlashBack will display overall statistics for all flashcards in the current list.
</div>

Examples:

`stats 2` shows the statistics of the 2nd flashcard in the list.

![UiStats](./images/UiStatsIndex.png) <br><br>

`stats` shows the overall statistics of the current flashcard list.

![UiStats](./images/UiStatsNoIndex.png) <br><br>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FlashBack data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

FlashBack data are saved as a JSON file `[JAR file location]/data/flashback.json`. Advanced users are welcome to update
data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FlashBack will discard all data and start with an empty data
file at the next run.

</div>

## Review mode

### Showing next flashcard : `n`

Moves on to the next flashcard.<br>
Format: `n` <br>
Example: <br><br>
![UiReviewModeNext](./images/UiReviewNext.png)

### Showing previous flashcard : `p`

Moves back to the previous flashcard. <br>
Format: `p` <br>
Example: <br><br>
![UiReviewModePrev](./images/UiReviewPrev.png)

### Showing answer : `a`

Displays the answer of the current flashcard. <br>
Format: `a` <br>
Example: <br><br>
![UiReviewModeWithAnswer](./images/UiReviewModeWindow.png)

### Hiding answer : `h`

Hides the answer of the current flashcard. <br>
Format: `h` <br>
Example: <br><br>
![UiReviewModeHide](./images/UiReviewHide.png)

### Reviewing a flashcard as correct: `t`

Marks that the user got the answer correct for the current flashcard. <br>
<div markdown="span" class="alert alert-primary">

:bulb: **Note:** This command can only be executed if the answer of the current flashcard is shown.

</div>

Format: `t` <br>
Example: <br><br>
![UiReviewModeCorrectAnswer](./images/UiReviewAnsTrue.png)

### Reviewing a flashcard as wrong : `f`

Marks that the user got the answer wrong for the current flashcard. <br>
<div markdown="span" class="alert alert-primary">

:bulb: **Note:** This command can only be executed if the answer of the current flashcard is shown.

</div>

Format: `f` <br>
Example: <br><br>
![UiReviewModeWrongAnswer](./images/UiReviewAnsFalse.png)

### Quitting review mode : `q`

Quits the review mode and goes back to the main window. <br>
Format: `q` <br>
Example: <br><br>
![UiReviewModeExit](./images/UiReviewExit.png)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous FlashBack home folder.


--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

### Main mode command

Action | Format, Examples
--------|------------------
**Add** | `add q/QUESTION a/ANSWER c/CATEGORY p/PRIORITY [t/TAGS]...` <br> e.g. `add q/ What is the Einstein’s Equation? a/e=mc^2 c/Physics p/High t/ModernPhysics`
**Delete** | `delete INDEX` <br> e.g. `delete 1`
**Edit** | `edit INDEX` <br> e.g. `edit 3 a/NEW ANSWER p/NEW PRIORITY`
**View** | `view INDEX` <br> e.g. `view 2`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find equation`
**Filter** | `filter [q/QUESTION] [c/CATEGORY] [p/PRIORITY] [t/TAG]`<br> e.g. `filter q/einstein c/phy p/high t/modern` <br> or `filter p/low t/formula`
**Clear** | `clear`
**Undo** | `undo`
**Redo** | `redo`
**Sort** | `sort OPTION ORDER` <br> e.g. `sort priority -a`
**Review** | `review`
**Statistics** | `stats [INDEX]` <br> e.g. `stats 4`, `stats`
**List** | `list`
**Help** | `help`
**Exit** | `exit`

### Review mode command

Action          | Format
----------------|------------------
**Next flashcard**   | `n`
**Previous flashcard** | `p`
**Show answer** | `a`
**Review a flashcard as correct** | `t`
**Review a flashcard as wrong** | `f`
**Hide answer** | `h`
**Quit review** | `q`
