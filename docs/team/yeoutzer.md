---
layout: page title: Loo Yeou Tzer's Project Portfolio Page
---

## Project: FlashBack

FlashBack is a desktop flashcard application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contributed** [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=yeoutzer&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=yeoutzer&tabRepo=AY2021S2-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
<br><br>
* **Enhance Find Feature**: Enhanced the ability find flashcards by given user input. [#56](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/56), [#63](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/63), [#75](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/75)
    * What it does previously: Allows the user to find a person in AddressBook only by their name.
    * What it does now: Allows the user to find flashcards matching the given keywords in any of its fields (e.g. question, 
      category, priority, and tags), FlashBack displays flashcards that matches any keywords in any of its fields.
    * Justification: This improves the product significantly because a user may add a huge amount of flashcards
      in FlashBack which makes it difficult to search for flashcards with certain keywords in one or some of its fields, 
      and the app should provide a way for users to easily search for flashcards that contains certain keywords in any of 
      its fields.
    * Highlights: This enhancement affects existing commands. It required a good understand of existing code to enhance the
      ability of finding flashcards, iterating through all its fields and comparing with keywords. The implementation 
      was challenging as it required changes to existing command and test cases.
<br><br>
* **New Feature**: Added the ability to filter flashcards by given user input. [#82](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/82)
    * What it does: Allows the user to filter flashcards according to user specified fields (e.g. question, category, 
      priority, and tag), FlashBack displays flashcards that matches keywords of all the fields specified by the user.
    * Justification: This feature improves the product significantly because a user may want to filter flashcards according 
      to certain fields so that the user can narrow down what flashcards are being reviewed, and the app should provide
      a way for users to filter flashcards they may want to search for.
    * Highlights: This enhancement required good understanding of existing code to add the ability to filter flashcards,
      iterating through all its fields and comparing with keywords of the specified fields, and only displaying flashcards
      that matches all the specified fields. This implementation was challenging as it required addition to some utility 
      classes and allowing multiple keywords for a certain specified field.
<br><br>
* **Contributions to team-based tasks**:
    * Refactored test functions and changed test data to fit our project.[#56](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/56)
<br><br>
* **Project Management**:
    * Managed release for `FlashBack v1.2` on Github.
<br><br>
* **Documentation**:
    * User Guide:
        * Added anchor links in the table of contents.[#61](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/61)
        * Added the documentation for the features `list`, `find`, and `filters`.[#24](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/24), [#61](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/61), [#83](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/83/files)
    * Developer Guide:
        * Added anchor links in the table of contents.[#89](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/89)
        * Added implementation details for `filter` feature with sequence and activity diagram.[#89](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/89)
        * Added the documentation for `list`, `find`, and `filters`.[#38](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/38), [#61](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/61), [#89](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/89)
        * Added the Effort section. [#89](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/89)
<br><br>
* **Review/Mentoring Contributions**:
    * Non-trivial PR reviews: [#111](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/111), [#91](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/91), [#86](https://github.com/AY2021S2-CS2103T-T13-3/tp/pull/86),
<br><br>
* **Contributions beyond the project team**:
    * Bugs reported for PE dry run. [Issue Tracker](https://github.com/yeoutzer/ped/issues)

