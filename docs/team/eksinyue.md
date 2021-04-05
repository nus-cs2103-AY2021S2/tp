---
layout: page
title: He Xinyue's Project Portfolio Page
---

## Project: BudgetBaby

BudgetBaby is a desktop budget tracking application used to manage finances. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added financial records related commands

  - What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  - Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  - Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  - Credits: _{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}_

- **New Feature**: Added `view-month` command

  - What it does: allows users to view any specific month's associated data and financial records.
  - Justification: This feature is essential to our budget tracker, as users are likely to view financial records and related information of a past month frequently when managing their finances and reviewing their spending habits.
  - Highlights: This command alters all information displayed in the GUI. Adding this command requires a comprehensive understanding of most models in the project, as well as the overall logic flow from GUI to Logic to Model. The implementation of this command was challenging as it affects many components of the project.

- **Project management**:

  - Set up project organisation, repository and issue tracker
  - Set up and wrap up project milestones `v1.1` - `v1.4` on Github
  - Managed releases `v1.3 trial`, `v1.3` and `v1.4` (3 releases) on GitHub

- **Enhancements to existing features**:

  - Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  - Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

- **Documentation**:

  - User Guide:
    - Added documentation for financial record related features [\#tbc]()
    - Added documentation for month related features [\#tbc]()
    - Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#tbc]()
  - Developer Guide:
    - Added design details for Model component of the project
    - Added implementation details for financal record related feature.

- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  - Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  - Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  - Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

- **Tools**:
  - Integrated a third party library (Natty) to the project ([\#42]())
  - Integrated a new Github plugin (CircleCI) to the team repo
