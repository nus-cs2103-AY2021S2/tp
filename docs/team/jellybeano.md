---
layout: page
title: Lan Yu Xuan's Project Portfolio Page
---

## Project: WeebLingo

Weeblingo is a desktop app for learning Japanese through flashcards, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). With a nice and friendly interface,
users can learn Japanese at a comfortable pace with this application. The user interacts with it via a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add unique user tags.
    * What it does: Allow the users to add tags to flashcards that are separate from the default and immutable tags that the application provides.
    * Justification: This feature improves the product significantly because the application provides functions that allow for viewing and sorting flashcards by their tags, which complements the Learn mode of the application greatly.
    * Highlights: This enhancement affected one of the key modes, Learn Mode, of the application greatly and became one of the main features of that mode, along with viewing all flashcards.
      Tagging also affected most other sections of the application and thus required deeper insight into how the entire application functioned.
    * Credits: The code took some inspiration from the original Address Book that the project was morphed from.

* **New Feature**: Added the deleteTag command that tied in with the above command. Users could delete tags, but not default ones set by the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=t13-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=Jellybeano&tabRepo=AY2021S2-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Coordinated and led project meetings.
    * Assisted in managing milestones for the team.

* **Enhancements to existing features**:
    * Updated the GUI color scheme and added visual improvements (Pull request [\#60](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/60))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `learn`, `tag` and `deleteTag` (Pull requests [\#96](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/96), [\#111](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/111))
        * Maintain accuracy of screenshots used (Pull request [\#192](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/192))
        * Included more questions and answers in the FAQ (Pull request [\#192](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/192))

    * Developer Guide:
        * Added implementation details of the `learn` feature and updated various UML diagrams. (Pull requests [\#81](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/81), [\#96](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/96), [\#195](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/195))
        * Added implementation details of the `tag` feature, along with relevant diagrams. (Pull request [\#81](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/81))
        * Update the UML diagrams to match the upstream repo after they were changed (Pull request [\#111](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/111))
        * Added implementation details of the `deleteTag` feature, along with relevant diagrams. (Pull request [\#192](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/192))

* **Team-Based Tasks**:
    * Assisted with refactoring and morphing Address Book into the current application. (Pull request [\#22](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/22))
    * Changed the icon for the application. (Pull request [\#179](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/179))
    * Helped maintain the issue tracker for the team repository.
