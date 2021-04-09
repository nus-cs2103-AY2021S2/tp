---
layout: page
title: Zhang Anli's Project Portfolio Page
---

## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the
school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a keyboard.
You can navigate the application with ease and execute instructions by typing text-based commands in the command box provided.

Given below are my contributions to the project.

* **New Feature**: View Command
  * Implemented the View Command that allows users to view all the related entities of one particular
  * In the process, I had to build `ViewCommandParser`, `ViewCommandComparator` and `IdMatchPredicate` to allow the proper functioning of this command.
  * Determined the order in which the entities are presented

* **New Feature**: Find Command
  * Re-enabled the Find Command after the initial disabling during the refactoring stage of the initial code base.
  * Refactored the Find Command to work with our implementation of the three entities of dog, owner and program.

* **Code Refactoring**: Change the result display order for all commands.
  * Refactored the ordering of the way the results are displayed in the for all commands.
  * In the process, built `IdMatchPredicate` to assist in the way the entities are displayed on the main list display.

* **Code Refactoring**: Removed all references to `AB3-AddressBook`
  * Removed project-wide references and usage of variables linked to `AddressBook` and changed it to `Database` to fit with our project instead.
  * Fixed all related test cases that started failing after the major refactoring.

* **Code Refactoring**: Code Quality Improvements
  * Removed unnecessary assertions and improved Javadoc comments.
  * General housekeeping of code quality during development.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=ZhangAnli&tabRepo=AY2021S2-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

* **Project management**:
    * Managed milestones `v1.2 - v1.4` on GitHub
    * Assigned and allocated issues to team members for smoother work allocation.

* **Enhancements to existing features**: GUI Refactoring (in progress...)
  * Refactored the look and feel of the UI, such as through re-arrangement of the JavaFX components and layering.
  * Change the UI interface from a single list design to tab design that allows for switching between lists of different entities.

* **Documentation**:
  * User Guide:
    * Contributed significant portions in Introduction, Purpose, Syntax, GUI Layout, Find Command, View Command, Command Summary and Glossary.
    * Streamlined and cleaned up the User Guide frequently to match current implementations of Pawbook.

  * Developer Guide:
    * Completed Find Command, View Command and their respective activity diagrams.

* **Community**:
  * Reviewed and merged pull requests of other contributors frequently.
  * Reported bugs and suggestions to other teams in the class.
