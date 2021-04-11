---
layout: page
title: User Guide
---

Weeblingo is a desktop application for **learning Japanese, optimized for use via a Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI). With a nice and friendly interface,
users can learn Japanese at a comfortable pace and manage flashcards with this application.

## Table of contents
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
  * [**General Commands**](#general-commands)
     * [Ending the session: `end`](#ending-the-session-end)
     * [Asking for help: `help`](#asking-for-help-help)
     * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [**Learn Mode Commands**](#learn-mode-commands)
     * [Entering learn mode: `learn`](#entering-learn-mode-learn)
     * [Tagging a flashcard: `tag`](#tagging-a-flashcard-tag)
     * [Deleting tags from a flashcard: `delete`](#deleting-tags-from-a-flashcard-deletetag)
  * [**Quiz Mode Commands**](#quiz-mode-commands)
     * [Entering quiz mode: `quiz`](#entering-quiz-mode-quiz)
     * [Starting a quiz session: `start`](#starting-a-quiz-session-start)
     * [Checking flashcard answers: `check`](#checking-flashcard-answers-check)
     * [Going to next flashcard: `next`](#going-to-next-flashcard-next)
  * [**History Mode Commands**](#history-mode-commands)
     * [Entering history mode: `history`](#entering-history-mode-history)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Note for Testers](#note-for-testers)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

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
<div style="page-break-after: always;"></div>
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `weeblingo.jar` from [here](https://github.com/AY2021S2-CS2103T-T13-1/tp/releases/tag/v1.3).

1. Copy the file to the folder you want to use as the _home folder_ for your WeebLingo application.

1. Launch the app through running `java -jar weeblingo.jar` or double-clicking the icon. The GUI similar to the image below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

    <div style="page-break-after: always;"></div>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`learn`** : Enters Learn mode.

   * **`tag 1 t/easy`** : Tags the first flashcard shown as easy.

   * **`end`** : Returns to menu.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `next` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help あいうえお`, it will be interpreted as `help`.

</div>

### General Commands
Commands listed in this section can be run in all modes of the app.

#### Ending the session: `end`

Prematurely ends the current quiz session. Quiz progress will not be saved if this is done.

The end command is only applicable while the user is in Quiz mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If you want to end your current quiz
session but want to see the statistics of the questions answered so far, consider skipping all questions until the
end using next instead. The end command is meant as a way to quickly discard and close the current quiz session.

</div>

Format: `end`

<div style="page-break-after: always;"></div>

#### Asking for help: `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the application: `exit`

Exits the application.

Format: `exit`

<div style="page-break-after: always;"></div>

### Learn Mode Commands

#### Entering learn mode: `learn`

Enters Learn Mode, where all current flashcards are listed out. Answers to current flashcards are shown as well.

Users can utilise this mode to facilitate the learning process,
and look through complete flashcards without having to test themselves.

Tagging related functions are also only available in this mode.

Format: `learn`

Learn Mode:
![learn mode](images/learn_mode.png)

<div style="page-break-after: always;"></div>

#### Tagging a flashcard: `tag`

Reads in a positive index and one or more tags, and adds these tags to the flashcard at the desired index.
At least one tag must be provided, else the user will be prompted to enter a valid command.
Tags can include spaces between words, but any trailing whitespaces will be removed.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo,
which can neither be deleted nor edited.
The purpose of this command is to allow users to add their own tags as well to assist in the learning process.

</div>

Format: `tag INDEX t/TAGS…`

Examples:
* `tag 1 t/difficult`
* `tag 5 t/fire`

After successfully adding a tag:
![tag successful](images/tag_success.png)

<div style="page-break-after: always;"></div>

#### Deleting tags from a flashcard: `deleteTag`

Reads in a positive index and zero or more tags, and removes these tags from the flashcard at the desired index.
If no tags are provided, all user-added tags will be removed from the flashcard.

Tagging of flashcards can only be done in Weeblingo's Learn Mode.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Default tags are provided by Weeblingo, which can neither be deleted nor edited.
The purpose of this command is to allow users to delete tags added by themselves.

</div>

Format: `deleteTag INDEX [t/TAG]…`

Examples:
* `deleteTag 3`
* `deleteTag 1 t/difficult`

After successfully deleting sampleTag from the flashcard at index 3:
![delete_successful](images/delete_success.png)

<div style="page-break-after: always;"></div>

### Quiz Mode Commands

#### Entering quiz mode: `quiz`

Enters Quiz Mode. The answer to all displayed flashcards will be hidden. 

Format: `quiz [t/TAG]…​`

* When used with tags, only flashcards that posses all specified tag(s) will be displayed.
* When used without tags, all flashcards will be displayed.

Examples:

* `quiz`
* `quiz t/hiragana`

#### Starting a quiz session: `start`

Starts a quiz session with randomly ordered flashcards.

Format: `start [n/NUMBER] [t/TAG]…`

* NUMBER specifies the number of questions for the quiz session.
* NUMBER specified should be a positive integer and less than number of available flashcards in the database.
* When used without a number, either all existing flashcards (if no tag is specified) or flashcards with specified tags will be tested.
* TAG specifies the categories of questions for the quiz session.
* There are currently four default tags, gojuon, hiragana, katakana and numbers.
* When used without tags, all categories of questions can be tested.

Examples:
* `start`
* `start n/10`
* `start t/katakana`
* `start n/5 t/gojuon t/hiragana`

<div style="page-break-after: always;"></div>

Before executing start command:
![before start](images/start_before.png)

After executing start command:
![after start](images/start_after.png)

<div style="page-break-after: always;"></div>

#### Checking flashcard answers: `check`

Checks if user attempt matches the answer of the currently displayed flashcard.

Format: `check ATTEMPT`

* `ATTEMPT` is case-insensitive and ignores leading and trailing white spaces.
* Each time the user checks an attempt, number of attempts increments by one.
* If attempt is correct, answer to the current flashcard will be displayed and number of correct attempts increments by one.
* If attempt is incorrect, user may choose to try again through `check`, skip the question though `next` or end the session through `end`.

Examples:
* `check hi`
* `check hI`

If user attempt matches answer of flashcard:
![correct answer](images/check_correct.png)

<div style="page-break-after: always;"></div>

If user attempt does not match answer of flashcard:
![incorrect answer](images/check_incorrect.png)

If user tries to answer already correctly answered flashcard:
![answered again](images/check_again.png)

#### Going to next flashcard: `next`

Goes to the next flashcard in the quiz session.

Format: `next`

* If the quiz session has reached an end (i.e. no more flashcards to be tested), result statistic and 
flashcards tested (with answers shown) will be displayed. User may choose to start a new session through `start`,
return to quiz view through `quiz` or return to menu through `end`.

<div style="page-break-after: always;"></div>

### History Mode Commands

#### Entering history mode: `history`

View scores of all past quiz attempts.

Format: `history`

Example:

![HistoryExample](images/HistoryUseExample.png)
- If a quiz is left unfinished (i.e. there are still questions in the quiz), the history statistics will not be updated.
- If a quiz session has zero attempt, a default correct ratio of `0.000%` will be recorded.
- In future versions, `history` will provide more useful information to better optimize users' learning experience.

<div style="page-break-after: always;"></div>

### Adding and removing flashcards `[coming in v2.0]`

_These functions are likely to be included in future versions of Weeblingo, stay tuned._

### Saving the data

Weeblingo data is saved in the hard disk automatically after any command.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my quiz attempt scores to other computers?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file
that contains the data of your previous Weeblingo home folder.

**Q**: Can I add my customized flashcards?<br>
**A**: Sorry, the current version of the application does not allow self-defined flashcards to be added. However, if you
are advanced and know how to manipulate the storage files, you can do so if you want. However, the application assumes
a certain format for each data field, thus if you modified the data wrongly, the app will use the fallback option, which
is to load the storage file with built-in default flashcards and tags.

**Q**: Why can't I delete some tags?<br>
**A**: Default tags are provided by the application to aid in the learning process and guide you along the way. You can,
however, add your own tags to any flashcard. The deletion of tags exists to complement this user tagging function, 
in case you have a change of mind or typed any tag wrongly.

**Q**: I just want to do a short quiz! Is there any way to do that?<br>
**A**: There are multiple ways to set up a shorter quiz session: one would be to start with a selected number of
questions, and let the application randomise some questions for you. The other option could be to tag questions you
would like to be tested on; starting with these tags will give you a quiz session with only these selected flashcards.
A combination of the above two options is also possible.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples |
-------|------------------|
**Learn** | `learn [t/TAG]…` <br> e.g. `learn`, `learn t/hiragana`
**Tag**| `tag INDEX t/TAG [t/TAG]…` <br> e.g. `tag 1 t/difficult`
**DeleteTag**| `deleteTag INDEX [t/TAG]…` <br> e.g. `deleteTag 1 t/difficult`, `deleteTag 3`
**Quiz** | `quiz [t/TAG]…` <br> e.g. `quiz`, `quiz t/hiragana`
**Start** | `start [n/NUMBER] [t/TAG]…` <br> e.g. `start`, `start n/5`, `start t/hiragana`, `start n/15 t/hiragana`
**Check** | `check ATTEMPT` <br> e.g. `check he`, `check HE`
**Next** | `next`
**History**|`history`
**End** | `end`
**Help** | `help`
**Exit** | `exit`



--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Note for Testers
By the nature of Weeblingo, it is inconvenient for testers who do not possess some elementary
knowledge of Japanese to test our application.

Therefore, to facilitate testers, we have provided a list of flashcards which reside in the database
of the current version of application for your reference. The link is [here](Flashcards.html).

--------------------------------------------------------------------------------------------------------------------
