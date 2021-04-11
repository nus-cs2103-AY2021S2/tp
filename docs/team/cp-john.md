---
layout: page
title: Cao Peng's Project Portfolio Page
---

## Project: PocketEstate

PocketEstate enables easy organisation of mass clientele property information through sorting of information by price, location and housing type, that may otherwise be difficult to manage.

Given below are my contributions to the project:

#### New Features & Enhancements
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=Cp-John&tabRepo=AY2021S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Feature**: Implemented `delete` commands for `appointment` and `property`.
    * What it does: Deletes the appointment or property at the specified index.
    * Justification: Users now can delete an appointment or property in the app.
* **Feature**: Implemented `clear` commands for `appointment`, `property`, and `all`.
    * What it does: Allows the user to clear all `appointment`, `property`, or `all` in the app.
    * Justification: Users can easily clear all the appointments, properties, or all in the app.
* **Feature**: Implemented `sort` commands for `appointment` and `property`.
    * What it does: Allows the user to sort `appointment` or `property` based on specified sorting keys.
    * Justification: Users can easily sort all the appointments or properties in the app.
* **Feature**: Implemented `undo` command.
    * What it does: Allows the user to undo the previous command.
    * Justification: Users can easily undo the last mistake operation.
* **Documentation**:
    * User guide: Updated guides for the usage of `delete appointment`, `delete property`, `clear appointment`, `clear property`, `clear all`, `sort appointment`, `sort property`, and `undo` commands.
    * Developer guide: Added in some user stories.
* **Testing**:
    * Wrote unit tests for:
        * `delete` commands and parsers
        * `clear` command and parser
        * `sort` command and parser
        * `undo` command
* **Team-based tasks**:
    * Reported bugs as issues for better tracking.
* **Review/mentoring**:
    * Reviewed and merged pull requests
    * Implemented several bugfixes.
