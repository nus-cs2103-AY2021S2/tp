---
layout: page
title: Tan De Yi's Project Portfolio Page
---

## Project: BudgetBaby

BudgetBaby is a desktop budget tracking application used to manage finances. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense Report](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=deyixtan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **New Feature**: Added bar charts representing expenditure statistics
  * What it does: Collates the statistics in a graphical representation for users to easily visualise their monthly and per-category expenditures.
  * Justification: This feature provides an avenue for users to better manage and plan their finances as it shows clearly in which areas they have overspent (i.e. in a particular month or category).
  * Highlights: This was an enhancement which followed from an initial side panel displaying the top 5 categories.

* **New Feature**: Added UI panel displaying summary of progress and all categories
  * What it does: Displays allocated budget, progress bar and all categories with their corresponding total expenditures.
  * Justification: This feature tracks the user's progress as more records are added. It also groups all financial records belonging to the same category together and displays it to the user in a simple and informative side panel.
  * Highlights: Progress bar moves according to how much of the monthly budget have been spent and changes in colour from green to yellow to red, providing a visual indicator of the user's spendings. Categories are arranged in lexographical order to provide a quicker way for users to locate specific categories.

* **New Feature**: Added `undo` command that allows users to find financial records based on specified fields.
  * What it does: Allows users to undo any commands used
  * Justification: This feature provides a way for users to revert any changes made from incorrectly using any commands.

* **New Feature**: Added `redo` command that allows users to view the original list of financial records.
  * What it does: Allows users to redo any commands used
  * Justification: This is an essential feature that complements `undo`. While providing users a way to revert changes, `redo` allows users to advance changes.
  
* **Project Management**:
  * Designed the user interface of BudgetBaby using JavaFX to provide a seamless experience for users.
  * Contributed to the opening of several major issues on the project's Github repository and assigned them to respective members.

* **Enhancements to existing features**:
  * Added encoding to JSON file containing user data, providing additional layer of security. (Pull request [\#240](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/240))
  * Allowed `undo` and `redo` features on more commands (i.e. add-fr, delete-fr and set-bg).

* **Documentation**:
  * User Guide:
    * Added UI overview description and screenshots (Pull request [\#224](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/224))
    * Added documentation for the features `undo` and `redo` (Pull request [\#261](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/261))

  * Developer Guide:
    * Added implementation details of the `undo` and `redo` feature. (Pull request [\#]())
    * Added glossary and non-functional requirements (Pull request [\#38](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/38), [\#39](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/39))
    * Added documentation for UI component (Pull request [\#95](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/95))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#135](https://github.com/AY2021S2-CS2103T-W14-2/tp/issues/135), [\#139](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/139)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/194), [2](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/193), [3](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/192), [4](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/190), [5](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/186))
