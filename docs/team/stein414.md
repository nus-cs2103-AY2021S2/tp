---
layout: page
title: Ian's Project Portfolio Page
---

## Project: SunRez

**SunRez** is a desktop app designed for college residential staff to efficiently manage student housing services. It features a Graphical User Interface (GUI) but is optimized for use via a Command Line Interface (CLI). It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=stein414&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added support for issues
  * What it does: The issue feature allows the user to track issues that pertain to certain rooms in SunRez.
  * Justification: This feature is needed as Users need to be able to keep a record of issues that happen to rooms so as to address them. The ability to see a record of issues also allows the user to identify rooms that maybe prone to damages.
  * Highlights: This feature entailed an intermediate amount of considerations in how it would integrate with the room feature due to issues being tied to rooms. Although it is not heavily coupled with rooms, future adjustment to rooms need to take into account how issues might be affected.

* **Enhancements to existing features**:
  * Allow text to wrap when it is too long in the ListPanel [#326](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/326).
  * Limit tag lengths to prevent tags from being too long does being unreadable [#329](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/329).

* **Contributions to User Guide**:
  * Added documentation for the issue features `iadd`, `ilist`, `ifind`, `iedit`, `iclo` and `idel`. [#62](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/62)
  * [PRs made for documentation](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+author%3Astein414+doc)

* **Contributions to Developer Guide**:
  * Wrote uses cases UC-012 to UC-016. [#48](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/48)
  * Updated `UI` component's class diagram and implementation details to reflect the latest version of SunRez. [#414](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/414), [#426](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/426)
  * Added Activity, Sequence, and Class Diagrams for issue feature. [#117](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/117)
  * Added manual testing instructions for issue feature. [#394](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/394)
  * [PRs made for documentation](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+author%3Astein414+doc)

* **Team tasks**:
  * Introduced Conventional Commits and discussed its usage pertaining to the project.
    * Conventional Commits is to standardize commit messages so as to help with understanding what the commit is about.
  * Set up [user stories and tasks](https://github.com/AY2021S2-CS2103-T14-1/tp/issues?q=is%3Aissue+author%3Astein414+as+a+) for the team in various sprints.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#103](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/103), [#175](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/175), [#330](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/330).
  * Bug reports made for my team: [#89](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/89), [#140](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/140), [#145](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/145), [#146](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/146), [#180](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/180), [#344](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/344), and [#397](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/397).
  * Bug reports made for other teams: [PE-D](https://github.com/stein414/ped/issues).
  * Forum participation: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/41).

* **Tools**:
  * Integrated Visual Studio Code ([\#12](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/12)).
  * Integrated GIT pre-commit ([\#54](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/54)) hook to ensure tests and check style pass before commits are made to reduce breaking commits  and reduce github CI fail test checks.
