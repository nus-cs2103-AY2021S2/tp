---
layout: page
title: Linh's Project Portfolio Page
---

## Project: SunRez

SunRez is a desktop resident management application used for managing NUS hostels and residential colleges. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added support for user-defined aliases ([#65](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/65))
  * What it does: The `alias` command adds a user-defined shortcut for a longer command, allowing the (typically shorter) alias to be used in place of the actual command.
  * Justification: Enhances the user's CLI experience, especially for advanced users, as users can define shorter and more concise aliases that are more intuitive to them. Makes the execution of commands more flexible.
  * Highlights: This feature involves a heavy refactoring of the way commands are parsed. Multiple iterations and design alternative considerations were needed. It was also tested comprehensively to handle several edge-cases (such as when the user tries to define a recursive alias). 
  * Credits: Initial design was adapted from https://github.com/briyanii/main.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=cnlinh&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

* **Project management**:
  * Managed release `v1.2` on GitHub.
  * Managed post-mortem review.

* **Enhancements to existing features**:
  * Modify AddressBook parser to support Alias and its ability to undo Alias-related command: [\#71](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/71).

* **Documentation**:
  * User Guide:
    * Added the documentation for the features `alias`, `unalias` and `aliases`: [\#156](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/156).
    * Added a summary section for prefixes: [\#306](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/306).
    * Updated initial user guide intro: [\#10](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/10).
  * Developer Guide:
    * Added implementation details of the `alias` feature: [\#116](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/116).
    * Added manual testing for the `alias` feature: [\#383](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/383).
    * Wrote user case UC-022 and UC-023.
    * Updated Storage component section: [\#407](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/407)

* **Team Task**:
  * Contributed to initial draft of value proposition, target user and NFRs.
  * Set up the GitHub team org.  
  * Created UI mockup.  
  * Set up tasks for the team in various sprints.
  * Assisted in bug triaging and allocation after PE-D.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#341](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/341), [\#104](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/104), [\#107](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/107).
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/271), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/249), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/123)).
  * Bug reports made for other teams: [PED](https://github.com/cnlinh/ped/issues).
  
  
