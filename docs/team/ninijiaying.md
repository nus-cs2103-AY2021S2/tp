---
layout: page
title: Jiaying's Project Portfolio Page
---

## Project: BookCoin

BookCoin is a CLI facility management system which allows users to type in commands to manage bookings and presents users a structured and detailed information on facility availability.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=NiniJiaying&tabRepo=AY2021S2-CS2103-W17-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability to add a booking into the system.
    * Justification: This feature improves the product by allowing users to add a booking into the system.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Implemented multi-step prompting structure on `add_person`
    * What it does: allows the user to add persons one at a time into the system through a multi step command with compulsory fields such as name, email, phone and optional field tags.
    * Justification: A lot of information input is needed to create a person, making the `add_person` process troublesome and confusing.
      This feature breaks down the input process into several steps, improving the input experience for users.

* **New Feature**: Added the ability to edit any attributes belonging to any bookings.
    * What it does: allows the user to edit all previously added bookings one at a time.
    * Justification: This feature improves the product significantly because a user can make mistakes in the addition of booking details, and the app should provide a convenient way to rectify them.

* **Enhancements to existing features**:
    * Wrote additional tests for existing features (Pull requests [\#122](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/122), [#235](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/235)), [#247](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/247)), [#248](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/248))
    * Managed storage function for Booking class and Person class (Pull requests [\#55](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/55), [#98](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/98))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add_person` and `find` (Pull requests [\#239](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/239))
        * Updated command descriptions (Pull requests [\#235](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/235))
    * Developer Guide:
        * Updated and Ui Class Diagram and Storage Class Diagram (Pull requests [\#256](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/256), [\#257](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/257))
        * Added implementation details of the `add_person`, `add_venue` and `add_booking` feature (Pull request [\#239](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/239))
        * Wrote uses cases (Pull request [\#97](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/97))
        * Wrote user stories (Pull request [\#97](https://github.com/AY2021S2-CS2103-W17-3/tp/pull/97))

* **Community**:
    * Reported bugs and suggestions for other teams in mock PE
