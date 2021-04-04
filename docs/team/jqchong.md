---
layout: page
title: John Doe's Project Portfolio Page
---

## Project: SpamEZ

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Major Feature**: Added the ability to undo previous commands.
  * What it does: allows the user to undo previous commands one at a time.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Credits: The implementation of this feature is mainly inspired from AB3 developer guide.

* **Major Feature**: Added a feature that allows the user to navigate to previous commands using up/down keys.
  * Justification: This feature reduces the memory work needed to remember some commands with many parameters such as `add`, `edit` etc. Users simply need to navigate to the previous commands and change the parameters instead of retyping everything.
  * Highlights: It was originally decided to implement this feature with the LinkedList class provided by Java. However, it was scraped and the feature is implemented with a self-made linked list instead due to a problem with how the linked list iterator works in Java.

* **Minor Feature**: Making a person's list view cell to turn black when the person is blacklisted.
  * Justification: This provides a visual response when the user decides to blacklist a contact.
  * Highlights: The current solution changes the css manually using `setStyle()` method as `setBackground()` did not work as intended. However, this causes some black lines appearing in the interface when the list of contacts is updated.
    Interestingly enough, the fix takes only one line of code.

* **Minor Feature** : Display the mode of contact using an icon.
  * Justification: As the main focus of the application is to allow users to disseminate information to the contacts easily, it would be better to represent the mode of contact field using an icon instead of a text field.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=JQchong&tabRepo=AY2021S2-CS2103-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Created the team repository.
  * Set up the CI for the project.
  * Reviewed and merged teammate's pull requests.

* **Enhancements to existing features**:
  * Adding support to find by other fields instead of only by name.
    * Justification: This enhancement is added to allow users to carry out mass operation on a certain group of contacts, e.g. all blacklisted contacts.
    * Highlights: This enhancement is the most tedious to implement as it allows users to find contacts by 7 fields at the same time. Furthermore, more test cases are needed to ensure this enhancement works correctly.

* **Documentation**:
  * User Guide:
    * Added documentation for the `undo` and `find` commands and navigating previous commands: [\#70](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/70), [\#144](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/144)
    * Clean up commands that are eventually not implemented: [\#142](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/142)
  * Developer Guide:
    * Added implementation details of the `undo` and `find` feature: [#52](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/52), [\#70](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/70/commits/645903a8755ea8df6573c9e63f151a0e64572801)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2021S2-CS2103-T16-1/tp/pull/60), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (example: [\#121](https://github.com/nus-cs2103-AY2021S2/forum/issues/121))
  * Reported bugs and suggestions for other teams in the class (example: https://github.com/JQChong/ped/issues)
