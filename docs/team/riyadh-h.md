---
layout: page
title: Alzahrani Riyadh Hamdan S's Project Portfolio Page
---

## Project: Dictionote

**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

Given below are my contributions to the project.

* **New Feature**: Added the ability to send emails to contacts.
  * What it does: allows the user to send an email to any of their contacts stored in the contacts list. The body of the email can contain the contents of any note present in the notes list.
  * Justification: This feature provides a method to communicate with the stored contacts by utilizing the existing AddressBook feature of storing email addresses. Not only so, but the ability to include stored notes in emails simplifies the process of sharing them with others.
  * Highlights: Due to the use of `mailto` links in the implemtnation of this feature, it would rely on the user's operating system (OS) default mail client to handle the email composition and sending requests, which might need to be installed if not present.
  * Credits: [TorstenH. and alexey_s from CodeProject for the Java URL invocation code](https://www.codeproject.com/questions/398241/how-to-open-url-in-java). Note that the Java method used in Dictionote's source code was not the one specified in the aforementioned posts, but a similar one from the same Java API. 
  
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*
  
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
