---
layout: page
title: Joel Wong's Project Portfolio Page
---

## Project: Weeblingo

Weeblingo is a desktop app for learning the Japanese language via flashcards. It is designed specifically with the needs
of budding Japanese language students in mind, providing everything one would need to embark on their academic journey.
With a simple and friendly design and carefully crafted user-centric features, one can learn Japanese at a comfortable
pace to the level of proficiency one desires.

Given below are my contributions to the project.

* **New Feature**: Created the flashcard database and updated it as the project progressed
  * What it does: stores all flashcard data, including questions, answers and tags.
  * Justification: This is critical for the functioning of our application.


* **New Feature**: Added ability to start and proceed through a quiz session
  * What it does: Starts a quiz session, randomizing the flashcards read from the flashcard database and showing them one at a time without their answers.
  * Justification: This allows the user to test themselves using the flashcards, without having the answers in front of them, and without knowing which flashcard comes next.
  * Highlights: This is the basis for the quizzing functionality, one of three main objectives of our project.


* **New Feature**: Added ability to review the flashcards tested in each quiz immediately when the quiz ends.
  * What it does: Keeps a record of each question as it is attempted, then consults that list to show the attempted questions after the quiz ends.
  * Justification: This allows the user to quickly revise the flashcards that they got wrong immediately after the quiz ends, instead of having to find them individually in Learn mode.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=yanneko&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Yanneko&tabRepo=AY2021S2-CS2103T-T13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Assigned issues to relevant parties for efficient responsibility management
  * Gave non-trivial reviews for some pull requests.


* **Enhancements to existing features**:
  * **Enhancement 1**: Added support for the start command to take arguments, allowing the user to filer flashcards by tags and limit the number of flashcards in the quiz.
    This allows them to start a quiz with a specific set of flashcards and/or a specific number of flashcards. (Pull requests [\#94](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/94) [\#104](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/104))
  * **Enhancement 2**: Added support for the quiz command to take arguments, allowing the user to filter the shown flashcards before choosing what quiz to start. (Pull request [\#166](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/166))
  * **Enhancement 3**: Added support for the learn command to take arguments, allowing the user to filter flashcards in Learn mode in order to revise specific sets of flashcards. (Pull requests [\#163](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/163) [\#166](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/166))

* **Documentation**:
  * Developer Guide:
    * Implementation - Quiz Command (Pull request [\#91](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/91))
    * User Stories (Pull requests [\#16](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/16) [\#91](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/91))
    * Future Proposed Features (Pull request [\#198](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/198))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#17](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/17), [\#28](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/28), [\#93](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/93), [\#98](https://github.com/AY2021S2-CS2103T-T13-1/tp/pull/98)
