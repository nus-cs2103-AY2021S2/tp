---
layout: page
title: User Guide
---

Weeblingo is a desktop app for managing flashcards, **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). With a nice and friendly interface, users can learn Japanese at a comfortable pace with this application.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Starting a session: `start`](#starting-a-session-start)
  * [Checking flashcard answers: `check`](#checking-flashcard-answers-check)
  * [Going to next flashcard: `next`](#going-to-next-flashcard-next)
  * [Listing out all flashcards: `list`](#listing-out-all-flashcards-list)
  * [Ending the session: `end`](#ending-the-session-end)
  * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [Asking for help: `help`](#asking-for-help-help)
* [FAQ](#faq)
* [Command Summary](#command-summary)
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `weeblingo.jar` from [here](https/linkhere-tbc).

1. Copy the file to the folder you want to use as the _home folder_ for your WeebLingo application.

1. Double-click the file to start the app. The GUI similar to the image below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`start`** : Shows the first flashcard.

   * **`check`** : Gives the answer to previously shown flashcard.

   * **`next`** : Only entered after show or check, goes to next flashcard.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

* Items in square brackets are optional.<br>

* Items with `…`​ after them can be used multiple times including zero times.<br>

* Parameters can be in any order.<br>

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help あいうえお`, it will be interpreted as `help`.

</div>

### Starting a session: `start`

Shows the first flashcard.

Format: `start`

### Checking flashcard answers: `check`

Reveals the answer to the previous shown flashcard.

Format: `check`

### Going to next flashcard: `next`

Goes to the next flashcard, if any.

Format: `next`

### Listing out all flashcards: `list`

Lists out all current flashcards.

Format: `list`

### Ending the session: `end`

Ends the current session, saving progress.

Format: `end`

### Exiting the application: `exit`

Exits the application.

Format: `exit`

### Asking for help: `help`

Shows a message explaning how to access the help page. (to be updated)

![help message](images/helpMessage.png)

Format: `help`

### Adding and removing entries `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my flashcards/scores to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WeebLingo home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Start** | `start`
**Check** | `check`
**Next** | `next`
**List** | `list`
**End** | `end`
**Exit** | `exit`
**Help** | `help`
