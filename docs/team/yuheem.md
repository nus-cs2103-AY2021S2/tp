---
layout: page
title: Yu Heem's Project Portfolio Page
---

## Project: BudgetBaby

BudgetBaby is a desktop budget tracking application used to help busy university students manage their finances. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **Set Budget Feature**:

  - Added the `set-bg` command which allows users to set their monthly budget for the current month (Pull requests [#57](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/57), [#64](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/64))
  - What it does: This feature allows users to specify a `BG_AMOUNT` to set for the current month. This amount set persists into the next 12 months.
  - Justification: This is one of the core features of the application which allows users to set their budget and then track their expenses. Other features make use of this budget to generate data that is useful for the user. Users are able to make use of this command to set a budget which they can try and stick to.
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
  - Remind and update teammates on upcoming deadlines during weekly meetings.

- **Documentation**:

  - User Guide:
    - Added documentation for the features `set-bg` and `help`.
    - Update style, language and tone to be more user-centred (Pull request [#124](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/124)).
    - Update structure and organization of the user guide to enhance readability and and navigability (Pull request [#253](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/253))
  - Developer Guide:
    - Added design details of the logic component (Pull request [#91](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/91)).
    - Added design and implementation details of the budget feature as well as the `set-bg` command (Pull request [#269](https://github.com/AY2021S2-CS2103T-W14-2/tp/pull/269))
