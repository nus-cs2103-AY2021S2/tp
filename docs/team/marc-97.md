---
layout: page
title: Marcus's Project Portfolio Page
---

## Project: FlashBack

FlashBack is a desktop flashcard application to help improve the performance of students via improved accessibility and organisation of notes, together with interactive sessions that enhance memory retention. The user interacts with it using a CLI. It is written in Java and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=Marc-97&tabRepo=AY2021S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
    <br><br>
* **Enhance Delete Feature**: Enhanced the ability find flashcards by given user input.
    * What it does previously: Allows the user to delete a person in AddressBook.
    * What it does now: Allows the user to delete a flashcard in FlashBack.
    <br><br>
* **New feature**: Added the ability to undo an undoable command. [#65](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/65)
    * What it does: Allows user to reverse a command that changes flashcard.
    * Justification: In the event that users accidentally execute a wrong command that modifies flashcards, this feature allows user to undo the action.
    * Highlights: This feature mainly involves creating a versionedFlashBack that stores the states of FlashBack.
    * Credit: Implementations adapted from [SE-EDU](https://github.com/se-edu/addressbook-level4) on Github
    <br><br>
* **New feature**: Added the ability to redo a command. [#88](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/88)
    * What it does: Similar to undo, except it reverses the undo command.
    * Justification: Similar to undo, this features allows user to redo a command when the users accidentally undo a command.
    <br><br>
* **New feature**: Added the ability to define an alias for a command. [#111](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/111)
    * What it does: Allows the user to define an alias for a command.
    * Justification: For advanced users who wish to rename their command to something else so that there are able to use the application more effectively. Example: delete -> rm, list -> ls, etc. for users who are familiar to Unix-like operating system.
    * Highlights: This feature requires the implementation of a new class that handles the mapping of aliases and commands. As the alias mapping is saved in preferences.json file, it is possible for users to change the mapping directly to something invalid, for example add -> delete. Hence an additional check is also added when loading the preferences.json into FlashBack.
    <br><br>
* **Documentation**:
    * User Guide:
        * Added the documentation for the features `delete`, `undo`, `redo` and `alias`. [#31](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/31), [#69](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/69), [#93](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/93), [#133](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/113)
        * Standardised the note sections. [#180](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/180)
    * Developer Guide:
        * Updated NFR and Glossary. [#37](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/37)
        * Updated implementation details for `undo` and `redo` feature with sequence and activity diagram. [#93](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/93)
        * Added the documentation for the features `alias`. [#113](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/113)
        * Added manual testing for `undo`, `redo` and `alias` feature. [#113](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/113)
        * Added effort section for `undo`, `redo` and `alias` feature. [#180](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/180)
    <br><br>
* **Other Contributions**:
    * Refactor project package. [#183](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/183)
    * Bugs reported for other team in PED. [W15-1](https://github.com/marc-97/ped/issues)
