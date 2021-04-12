---
layout: page
title: Neo Soon Keat's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

## Project: DietLAH!

DietLAH! is a desktop diet-tracking application that uses CLI-based inputs to allow for typists to easily record their meals and track their weight-loss/gain journey.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=SoonKeatNeo&tabRepo=AY2021S2-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: New help command to provide updated help-information specific to the DietLAH! product.
    * What it does: on first launch, shows the help window with common tasks. On user command `help`, it shows the same text.
    * Justification: As there are many implemented commands in the application, we provide the most relevant subset to get users started. Users may refer to the User Guide if they need more specific help.

* **New Feature**: Updated Storage to manage storage of user with new attributes
    * What it does: Manages the saving and loading of the user object with varying attributes (e.g. BMI, food list, selected diet plan)
    * Justification: This feature allows the application to save the user object in memory at the time the application was running and load it from the disk so that the state is restored.

* **New Feature**: Implemented model of User and related-objects (e.g. Bmi) that allows corresponding information to be stored and computed.
    * What it does: Provides the base model that corresponds to the actual user of the application. Also provides base calculation for how the Bmi will be used in the calculation of diet plan recommendations.
    * Justification: Allows for OOP-oriented storage of further features.

* **Project management**:
    * Managed releases `v1.2.1` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the UI to match the intended design.

* **Documentation**:
    * User Guide:
        * Added documentation for the introduction and features `food_intake_query` and `food_intake_add` [\#20](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/20)
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added NFRs to the application.
        * Documented User portion of the code.
        * Added appendixes for manual testing and effort.
    * Website:
        * Update various placeholders and leftovers from AB3

* **Contributions to team-based tasks**:
    * Set up the GitHub team org and repo.
    * Updated documentation and config for different product (PRs [\#23](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/23), [\#93](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/93)).
    * Set up tools (e.g. CodeCov and Gradle) (PR [\#2](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/2)).
    * Set up the issue tracker with required labels

* **Community**:
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/5#issuecomment-761537781), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/156#issuecomment-780712826), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/145#issuecomment-779743886), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/58#issuecomment-768847590))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-T10-1/tp/issues/304), [2](https://github.com/AY2021S2-CS2103T-T10-1/tp/issues/310), [3](https://github.com/AY2021S2-CS2103T-T10-1/tp/issues/303))
