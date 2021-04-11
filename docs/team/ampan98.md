---
layout: page
title: Ernest's Project Portfolio Page
---

## Project: SpamEZ

SpamEZ is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
It helps users to classify contacts based on their details in order to facilitate effective and efficient information dissemination to a large audience.

SpamEZ is based on AddressBook - Level 3, which is a desktop address book application used for teaching Software Engineering principles.

Given below are my contributions to the project.

* **New Feature**: Added the ability to blacklist/un-blacklist a contact.
  * What it does: Allows the user to mark a contact as blacklisted/un-blacklisted.
  * Justification: This feature improves the product significantly because a user can specify which contacts to not include when sending out information.
  * Highlights: This enhancement together with the find command provides the user with a list of blacklisted/un-blacklisted contacts.
    The implementation was slightly challenging as it required checking the blacklist status of a person before changing it.
  * Credits: No external code / libraries were used.
  
* **New Feature**: Added a collect command that allows the user to collect specified details.
  * What it does: Collects specified details from every listed contact, such as their email addresses, and displays it in one line in the GUI.
  * Justification: This allows the user to directly copy and paste the line into the recipient list of any message sending application.
  * Highlights: This enhancement together with the find command allows the user to filter out undesired contacts.
    The implementation was simple, but it was challenging to coordinate it with the requirements of the find command to provide the most flexibility to the user,
    while not being too complicated. Several extensions were considered but eventually dropped in favor of simplicity.
  * Credits: No external code / libraries were used.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ampan98&tabRepo=AY2021S2-CS2103-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub.
  * Managed requirements and implementations of several features (`blacklist`, `find`, `collect`) for more cohesive usage.
  * Organised documents (developer guide and user guide) to improve logical flow and ease of access.

* **Enhancements to existing features**:
  * Added blacklist field for each contact (Pull request [\#29](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/29))

* **Documentation**:
  * User Guide and Developer Guide:
    * Added table of contents (Pull request [\#158](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/158))
    * Improved consistency and accuracy of terms (Pull requests [\#32](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/32), [\#147](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/147))
    * Added documentation for the features `blacklist` and `collect` (Pull requests [\#29](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/29), [\#45](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/45),
    [\#67](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/67))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#44](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/44#pullrequestreview-622634722), [\#159](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/159#pullrequestreview-632615017)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/121))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/ampan98/ped/issues/8), [2](https://github.com/ampan98/ped/issues/6), [3](https://github.com/ampan98/ped/issues/3))
