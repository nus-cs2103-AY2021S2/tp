---
layout: page
title: User Guide
---

Weeblingo is a desktop application for **learning Japanese, optimized for use via a Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI). With a nice and friendly interface,
users can learn Japanese at a comfortable pace and manage flashcards with this application.

* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
  * [Entering quiz mode: `quiz`](#entering-quiz-mode-quiz)
  * [Starting a quiz session: `start`](#starting-a-quiz-session-start)
  * [Checking flashcard answers: `check`](#checking-flashcard-answers-check)
  * [Going to next flashcard: `next`](#going-to-next-flashcard-next)
  * [Entering learn mode: `learn`](#listing-out-all-flashcards-learn)
  * [Ending the session: `end`](#ending-the-session-end)
  * [Viewing past scores: `history`](#viewing-past-scores-history)
  * [Tagging a flashcard: `tag INDEX TAGS…`](#tagging-a-flashcard-tag-index-tags)
  * [Deleting tags from a flashcard: `delete INDEX [TAGS…]`](#deleting-tags-from-a-flashcard-delete-index-tags)
  * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [Asking for help: `help`](#asking-for-help-help)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Introduction

### About WeebLingo
WeebLingo is a desktop application that helps users learn the Japanese language. It has three primary modes,
which are the **learn**, **quiz** and **history** modes. The app contains flashcards that pairs a Japanese word and
its corresponding English syllable for users to learn. It allows users to test themselves on their grasp of the Japanese language
by starting a quiz session. In addition, users can view their history of past quiz attempts and the relevant statistics.

### Additional information
These are symbols used throughout the User Guide you might want to take note of.
* :information_source: : Indicates information that is likely to be helpful

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
eg. if the command specifies `n/NUMBER t/TAG`, `t/TAG n/NUMBER` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
eg. if you specify `n/5 n/10`, only `n/10` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help あいうえお`, it will be interpreted as `help`.

</div>

### Entering quiz mode: `quiz`

Enters Quiz Mode, where all current flashcards are listed out. Answers to current flashcards are not shown.


### Starting a quiz session: `start`

Starts a quiz session.

Format: `start [n/NUMBER] [t/TAG]`

* NUMBER specifies the number of questions for the quiz session.
* TAG specifies the type of questions for the quiz session.
* NUMBER specified should be positive.
* There are currently four tags available, gojuon, hiragana, katakana and numbers.
* You can specify one or two tags.

Examples:
* `start n/10`
* `start t/katakana`
* `start n/5 t/gojuon t/hiragana`

Before executing start command:
![before start](images/start_before.png)

After executing start command:
![after start](images/start_after.png)

### Checking flashcard answers: `check`

Reads in user attempt and check if it matches the answer of currently shown flashcard question.
If attempt is correct, answer to the current flashcard will be display. Else, the user will be prompted to re-enter an answer.

Format: `check ATTEMPT`

Examples:
* `check a`
* `check hi`

If user attempt matches correct answer of flashcard:
![correct answer](images/check_correct.png)

If user attempt does not match correct answer of flashcard:
![incorrect answer](images/check_incorrect.png)

If user tries to answer already correctly answered flashcard:
![answered again](images/check_again.png)

### Going to next flashcard: `next`

Goes to the next flashcard in the quiz session, if any.

Format: `next`

### Entering learn mode: `learn`

Enters Learn Mode, where all current flashcards are listed out. Answers to current flashcards are shown as well.

Users can utilise this mode to facilitate the learning process,
and look through complete flashcards without having to test themselves.

Tagging related functions are also only available in this mode.

Format: `learn`

Learn Mode:
![learn mode](images/learn_mode.png)

### Ending the session: `end`

Ends the current session, saving progress.

Format: `end`

### Viewing Past Scores: `history`

View scores of all past quiz attempts.

Format: `history`

### Tagging a flashcard: `tag INDEX TAGS…`

Reads in an index and one or more tags, and adds these tags to the flashcard at the desired index.
At least one tag must be provided, else the user will be prompted to enter a valid command.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo,
which can neither be deleted nor edited.
The purpose of this command is to allow users to add their own tags as well to assist in the learning process.

</div>

Format: `tag INDEX TAGS…`

Examples:
* `tag 1 t/difficult`
* `tag 5 t/fire`

After successfully adding a tag:
![tag successful](images/tag_success.png)

### Deleting tags from a flashcard: `delete INDEX [TAGS…]`

Reads in an index and zero or more tags, and removes these tags from the flashcard at the desired index.
If no tags are provided, all user-added tags will be removed from the flashcard.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo, which can neither be deleted nor edited.
The purpose of this command is to allow users to delete tags added by themselves.

</div>

Format: `delete INDEX [TAGS…]`

Examples:
* `delete 3`
* `delete 1 t/difficult`

After successfully deleting sampleTag from the flashcard at index 3:
![delete_successful](images/delete_success.png)

### Exiting the application: `exit`

Exits the application.

Format: `exit`

### Asking for help: `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding and removing entries `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my quiz attempt scores to other computers?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file
that contains the data of your previous WeebLingo home folder.

**Q**: Can I add my customized flashcards?<br>
**A**: Sorry, the current version of the application does not allow self-defined flashcards to be added. However, if you
are advanced and know how to manipulate the storage files, you can do so if you want.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples |
-------|------------------|
**Quiz** | `quiz`
**Start** | `start`
**Check** | `check ATTEMPT` <br> e.g. `check a`
**Next** | `next`
**Learn** | `learn`
**End** | `end`
**History**|`history`
**Tag**| `tag INDEX TAGS…` <br> e.g. `tag 1 t/difficult`
**Delete**| `delete INDEX [TAGS…]` <br> e.g. `delete 1 t/difficult`
**Exit** | `exit`
**Help** | `help`
