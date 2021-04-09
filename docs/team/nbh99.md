---
layout: page
title: Ng Boon Hong's Project Portfolio Page
---

## Project: ClientBook

ClientBook is a desktop address book application used by insurance agents to store their clients' information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a sort command that allows the user to sort the list of clients in ClientBook.
    * What it does: allows the user to sort the list of clients by their names or number of insurance policies either in ascending or descending order.
    * Justification: This feature improves the product because a user can make their list of clients more organised and is able to read the list more easily.
    * Highlights: This enhancement affects existing list of clients and the list inside the json storage. Other commands need to take that into account when executing.
    * Credits: Structure of sort command mainly come from the AB3 command structure.

* **New Feature**: Added a new attribute meeting for person and a meet command that allows the user to schedule meetings with clients.
    * What it does: allows the user to schedule meetings with clients and check for clashes between the meetings when adding a new meeting.
    * Justification: This feature improves the product because a user can store the information of their meetings with clients and be free from clashes.
    * Highlights: This enhancement affects existing constructor for person object and all the person objects inside the code need to be transformed to fit with the new constructor.
    * Credits: Structure of meet command mainly come from the AB3 command structure and structure of meeting mainly come the AB3 tag structure.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=NBH99&tabRepo=AY2021S2-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Added new interface ModifiableAddressBook to make changing the original list of clients easier: [\#41](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/41)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `sort` and `meet`: [\#41](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/41), [\#74](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/74)
        * Added initial glossary: [\#89](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/89)
    * Developer Guide:
        * Added implementation details of the `sort` and `meet` features: [\#177](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/177)
        * Added user stories and use cases: [\#21](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/21), [\#84](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/84)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#24](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/24), [\#40](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/40), [\#42](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/42), [\#43](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/43), [\#98](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/98), [\#101](https://github.com/AY2021S2-CS2103T-W15-2/tp/pull/101)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/NBH99/ped/issues))
