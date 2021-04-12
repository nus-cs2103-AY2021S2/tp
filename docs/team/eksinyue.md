---
layout: page
title: He Xinyue's Project Portfolio Page
---

## Project: BudgetBaby

BudgetBaby is a desktop budget tracking application used to manage finances. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 10 kLoC.

### Summary of Contribution

Given below are my contributions to the project.

- **New Feature**: Added financial records related commands `add-fr`, `edit-fr` and `delete-fr` commands

  - What it does: allows the user to add, edit and delete financial records from the budget tracker.
  - Justification: These features are fundamental to the product, as financial records are the main items stored in the budget tracker. The management of financial records are tasks which are likely to be carried out by users on a daily basis.

- **New Feature**: Added `view-month` command

  - What it does: allows users to view any specific month's associated data and financial records.
  - Justification: This feature is essential to our budget tracker, as users are likely to view financial records and related information of a past month frequently when managing their finances and reviewing their spending habits. This command alters all information displayed in the GUI. Adding this command requires a comprehensive understanding of most models in the project, as well as the overall logic flow from GUI to Logic to Model. This command made use of Observer Patterna and the overall implementation was challenging as it affects many components of the project.
  - Highlights: Although it is just one single command, the implementation effort goes beyond the command itself. In order to introduce the concept of `Month` into the project, a whole new layer of models including both `UniqueMonthList` and `Month` which contains `FinancialRecordList` will need to be added to the project. Implementing `Month` related features also introduce many complications in other basic features regarding `FinancialRecord`, as `FinancialRecord` now needs to be added to their respective `Month` and there are much more constraints which need to be taken into consideration, for example, if the `FinancialRecord` will violate the restrictions we set for a `Month`.

- **Code Contribution** [RepoSense Report](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=eksinyue&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

  - Initiate BudgetBaby OOP classes and implement `add-fr` command [\#45](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/45)
  - Implement `view-month` command [\#86](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/86)
  - Implement `edit-fr` and `add-fr` enhancement features [\#131](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/131)

- **Enhancement**:

  - Wrote additional tests for existing features to increase coverage (Pull requests [\#216]())

- **Project management**:

  - Set up project organisation, repository and issue tracker
  - Set up and wrap up project milestones `v1.1` - `v1.4` on Github
  - Managed releases `v1.3 trial`, `v1.3` and `v1.4` (3 releases) on GitHub
  - Create [30+ issues](https://github.com/AY2021S2-CS2103T-W14-2/tp/issues?page=1&q=is%3Aissue+author%3Aeksinyue) on project issue tracker.

- **Documentation**:

  - User Guide:

    - Labelled and added screenshots for all features`: [\#tbc]()
    - Updated Quick Start section [\#117](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/117)
    - Added documentation for financial record and month related features [\#199](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/199/files)

  - Developer Guide:

    - Added design details for Model component of the project [\#89](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/89)
    - Added implementation details for financal record related feature [\#256](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/256) and add [AddFrActivityDiagram.png](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/docs/images/AddFrActivityDiagram.png)
    - Added implementation details for month related feature [\#256](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/256) and add [ViewMonthSequenceDiagram.png](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/docs/images/ViewMonthSequenceDiagram.png) and [ViewMonthActivityDiagram.png](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/docs/images/ViewMonthActivityDiagram.png)

  - Volunteered to make cosmetic changes to all documentations [\#243](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/246/files), [\#246](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/246/files)

- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#87](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/87)
  - Contributed to forum discussions (examples: [\#34](https://github.com/nus-cs2103-AY2021S2/forum/issues/34)
  - Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103-T14-4/tp/issues/258), [2](https://github.com/AY2021S2-CS2103-T14-4/tp/issues/259), [3](https://github.com/AY2021S2-CS2103-T14-4/tp/issues/252), [4](https://github.com/AY2021S2-CS2103-T14-4/tp/issues/254), [5](https://github.com/AY2021S2-CS2103-T14-4/tp/issues/251))
