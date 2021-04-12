---
layout: page
title: Liu Shuyang's Project Portfolio Page
---

# Project: Link.me

## Project Overview

Link.me is a desktop client management application for insurance agents to manage their contacts. The basic
Link.me codebase is adapted from AddressBook Level 3, an address book app created under the Seedu Initiative. The user
interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

## Summary of contributions

### Features

* **New Feature**: Add notes feature.
  [PR](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/49)
  * What it does: allows the user to record, view and clear notes for clients.
  * Justification: This feature improves the product significantly because it gives users the flexibility to record any type of information for clients. This means that users are no longer restricted to the fields we have provided, but can record anything they deem fit.
  * Highlights: Unlike most other commands, the `note` command supports 3 seperate functionalities: recording, viewing and clearing notes. The implementation was to store the desired action in the `NoteCommand` object, and execute the `NoteCommand` differently depending on the action. Furthermore, viewing notes required implementing a seperate UI window to display the notes, and also required a way to pass notes all the way from `Model` to `UI`.


* **New feature**: Add gender and birthdate fields to client.
  [PR](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/28)
  * What it does: allows the user to add gender and birthdate information for each client.
  * Justification: This feature improves the product significantly because gender and age (which can be obtained from birthdate) information are particularly relevent when choosing an insurance plan. Insurance agents would likely find these additional fields useful.
  * Highlights: For gender and birthdate, it is very important to ensure the validity of user input, as we don't want users to enter an invalid gender or invalid birthdate (e.g. 1990-02-31). This cannot be performed using regex like the other commands, and requires robust checks.


### Code contributed
[RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Shuyang0&tabRepo=AY2021S2-CS2103T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

[Pull requests](https://github.com/AY2021S2-CS2103T-W12-3/tp/pulls?q=is%3Apr+author%3AShuyang0+)

### Project management
* Managed milestones `v1.1`, `v1.2`, `v1.3`, `v1.3b` and `v1.4` (5 milestones) on GitHub
* Managed releases `v1.2`, `v1.3b` and `v1.4` (3 releases) on GitHub
* Setup and maintain issue tracker on Github
* Maintain project notes document
* Create product demo for `v1.2` and `v1.3` on project notes document


### Documentation:
* **User Guide:**
  * Added documentation for the `note` feature: [\#46](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/46)
  * Added functionalities overview table: [\#46](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/46)
  * Proofreading and general formatting: [\#145](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/145)
  

* **Developer Guide:**
  * Added all user stories: [\#22](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/22)
  * Added implementation details of the `note` feature: [\#143](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/143)
  * Added implementation details of the `Gender` and `Birthdate` fields: [\#143](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/143)
  * Added 2 sequence diagrams and updated 2 class diagrams: [\#143](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/143)
  

* **Others:**
  * Added initial UI mockup: [\#12](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/12)

### Community
  * Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/281))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103-T16-3/tp/issues/207), [2](https://github.com/AY2021S2-CS2103-T16-3/tp/issues/208), [3](https://github.com/AY2021S2-CS2103-T16-3/tp/issues/210), [4](https://github.com/AY2021S2-CS2103-T16-3/tp/issues/212), [5](https://github.com/AY2021S2-CS2103-T16-3/tp/issues/213))
