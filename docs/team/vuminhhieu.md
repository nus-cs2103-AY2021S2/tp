---
layout: page
title: Vu Minh Hieu's Project Portfolio Page
---

## Project: FlashBack

FlashBack is a desktop flashcard application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=vuminhhieunus2019&tabRepo=AY2021S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **New features**: Added the ability to view a specific card given the index. [#52](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/52)
    * What is does: displays the question and answers of a specific card in the view pane.
    * Justification: In the list view, the user can only see parts of the question (if the question is long) and cannot see the answer. This feature allows users to see the completed question with its answer.
    * Highlights: This feature mainly involves design the UI for the view pane.
* **New features**: Added the ability to review the deck of flashcards. [#74](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/74)
    * What is does: allows the user to review all the cards one-by-one with detailed question and answer. All commands in review mode only contain 1 character, so the user do not have to type long commands.
    * Justification: In the main window, in order to see the question and answer of all the cards, users have to type a lot of view command, which is very time-consuming. This feature is implemented to solve this problem. In addition, users can track their progress if they remember the answer correctly. This is then reflected in `stats` command.
    * Highlights: This feature is quite hard to implement since it requires designing a completely new window that connected to the flashcard list that is currently displayed in the main window.
* **New features**: Added UI components: TextArea Command Box. [#74](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/74)
    * What is does: allows the user to see most of their commands so that they can easily spot a mistake in their commands if any.
    * Justification: A command, especially `add` and `edit` command tends to be long. Previously, when using TextField, all the command was in a single line, and it was impossible for user to see the whole content of their command.
    * Highlights: This features required the implementation of a new custom TextArea that disallows users to enter multiline commands.
    * Credit: [jewelsea](https://gist.github.com/jewelsea/5624145) on Github
* **Enhancements to existing features**:
    * Changed the whole GUI design and color. [#26](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/26), [#55](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/55)
    * Refactored method names and variables from AB3 code. [#55](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/55)
* **Contributions to documentation**:
    * Added the documentation for `view` command and `review` mode. [#71](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/71), [#74](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/74)
    * Added screenshots of the application for each user input. [#71](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/71)
* **Contributions to the DG**:
    * Added the User Stories section [#36](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/36), [#53](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/53)
    * Added implementation details including class diagram and sequence diagram for Review feature [#91](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/91)
    * Added use case for Review feature [#91](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/91)
* **Contribution to Team-Based Tasks**:
    * Changed the product name and icon.
