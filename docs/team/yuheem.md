---
layout: page
title: Yu Heem's Project Portfolio Page
---

## Project: BudgetBaby

BudgebtBaby is a desktop budget tracking application used to help busy university students manage their finances. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **Set Budget Feature**:

  - Added the `set-bg` command which allows users to set their monthly budget for the current month (Pull requests [#57](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/57), [#64](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/64))
  - What it does: This feature allows users to specify a `BG_AMOUNT` to set for the current month. This amount set persists into the next 12 months.
  - Justification: This is one of the core features of the application which allows users to set their budget and then track their expenses. Other features make use of this budget to generate data that is useful for the user.
  - Highlights: This enhancement is the basis for many other features and it required careful consideration of its design when implementing.

- **Help Feature**:

  - Added the `help` command which allows user to ask for help regarding usage of the applications and commands (Pull request [#143](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/143))
  - What it does: This feature opens up a smaller window which links the user to our user guide to reference
  - Justification: This helps new users learn how to use the application.

- **Enhancements to existing features**:

  - Enhanced deletion of financial records (Pull request [#60](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/60)) to support multiple deletion (Pull request[#144](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/144)).

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=yuheem&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

- **Project management**:

  - Managed deadlines and deliverables for the group, including updating the issue tracker and milestones.
  - Helped distribute workload evenly among team members during weekly meetings.
  - Provided feedback for existing features and feature enhancements during weekly meetings.

- **Documentation**:

  - TODO
  - User Guide:
    - Added documentation for the features `delete` and `find` [\#72]()
    - Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  - Developer Guide:
    - Added implementation details of the `delete` feature.

- **Community**:

  - TODO
  - PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  - Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  - Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  - Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
