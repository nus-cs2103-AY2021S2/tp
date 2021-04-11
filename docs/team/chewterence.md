---
layout: page
title: Chew Hong Wei Terence's Project Portfolio Page
---

## Project: Green Mileage Efforts

Green Mileage Efforts (GME) is an efficient carpooling management solution designed to help corporations reduce their 
carbon footprint. The GME system allows for the simple creation and management of weekly carpooling groups of employees 
looking to carpool to and from their office. These pools of employees can then carpool from the office 
regularly on the specified days and times every week.

Given below are my contributions to the project.

* **Code contributed**: View the code that I have contributed at my
  [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=chewterence&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code).

* **New Feature**: Added the `pool` command feature. (Pull requests [\#121](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/121))
  * What it does: Allows the user to create a pool from the list of passengers, and add it to the list of pools in the GME system.
  * Justification: This feature is the core feature of the product, where pools are created and added to the pool list by the user.
  * Highlights: Initially the command was implemented as the `drive` command which edits the list of passengers to include a driver with a phone
    number. The model was eventually updated to include a list of pools, and the logic of the pool command was required to be changed,
    this required an amount of refactoring of the code. Finally, towards the final iterations of the project, trip day and time validation was required to be
    added into the pool command, which further enhances the product.

* **New Feature**: Added the `unpool` command feature. (Pull requests [\#101](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/101), 
  [\#121](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/121))
  * What it does: Allows the user to remove a pool from the existing list of pools.
  * Justification: This feature complements the core feature of the product, as pools within the pool list could either 
    already be completed or entered with wrong details.
  * Highlights: Initially this command was implemented as the `undrive` command that edits the assigned driver of a passenger to be empty. However, after
    reconsidering and conceptualizing how the user would use the product, the model was updated to include a list of pools which the `unpool` command would then delete from.
    Implementation of this command was the precursor that lead to the consideration of redesigning the model to include both a pool and passenger list.

* **Enhancements implemented**:
  * Updated `add` and `edit` commands to utilize the new fields added (Pull requests [\#52](https://github.com/AY2021S2-CS2103T-W10-1/tp/commit/cc6891e016c15be52a00996aa8a74f383efb2e7e))
    * What it does: The enhancement allows users to add and edit passengers with the new fields of TripDay, TripTime and Price.
    * Justification: TripDay, TripTime and Price fields were added to the model and thus the `add` and `edit` commands were required to
      be updated with the new fields.
    * Highlights: A great amount of effort was put into ensuring that the implementation of the code was of suitable quality. This included
      writing additional tests to increase coverage for methods that involved the new fields 
      (Pull requests [\#56](https://github.com/AY2021S2-CS2103T-W10-1/tp/commit/85b7432bce0d7bbc5dba1b961959e919b297f6d8)).
      Moreover, the addition of the Price field proved to be far more challenging than expected, as it was an optional field that the user could enter.
      The Price field was thus implemented in a way that avoided the use of nulls as much as possible, thereby wrapping it in a java Optional.

* **Contributions to User Guide**:
  * Drafted initial version of the User Guide.
  * Added user guide introduction and quick start.
  * Added documentation for the command summary and FAQ.
  * Updated documentation for `add` and `edit` commands to match newly added fields of TripDay and TripTime.
  * Added documentation for `pool` and `unpool` commands.
  
* **Contributions to Developer Guide**:
  * Added documentation for `pool` and `unpool` commands (Pull requests [\#234](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/234)).
  * Added UML sequence and activity diagrams for `pool` and `unpool` commands (Pull requests [\#234](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/234)).
  * Updated glossary to contain descriptions of words that require an elaboration on.

* **Contributions to Team based tasks**:
  * Managed and assigned tasks through the use of the issue tracker.
  * PRs reviewed (with non-trivial review comments):
    [\#57](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/57)
    [\#100](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/100)
    [\#117](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/117)
    [\#218](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/218)
    [\#237](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/237)

* **Contributions beyond the project team**:
  * Contributed to forum discussions
    * Facilitated a discussion on [streams versus loops](https://github.com/nus-cs2103-AY2021S2/forum/issues/145).
    * Started a discussion on [the use of optionals](https://github.com/nus-cs2103-AY2021S2/forum/issues/243).
    * Participated and responded to some forum posts.
  * Reported [13 Bugs](https://github.com/chewterence/ped/issues) for team AY2021S2-CS2103T-W13-3 for the PE-D.
