---
layout: page
title: Arihant Jain's Project Portfolio Page
---

## Project: NuFash

NuFash is a desktop clothing application used for organising wardrobes, and taking better clothing decisions.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
12 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=Arihant%20Jain&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)


* **Project management**:
    * Managed releases `v1.1` - `v1.4` (3 releases) on GitHub.
    * Oversaw workflow for documentation in the development phases.
    * Delegated raised PED issues to team members.
    * Contributed valuable testing and feedback for implemented features by other members of team.

* **New Feature**: Added the `select` command allows a user to check out a garment from the digital wardrobe.
  [#93](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/93)
    * What it does: Each garment has a `LastUse` attribute. When a garment is newly added by using the `add` command, 
      the attribute reflects this by showcasing the phrase: `Never used`. Alternatively,
      it showcases the last known usage of the garment by the user.
      The `select` command essentially updates this attribute to the current local date, indicating to the application 
      that the user would wish to use the garment today.
    * Justification: This feature is significant, as one of the application's goals is for the user to easily cycle 
      through the entire catalogue of his/her clothes, 
      Therefore, a command that allows for the indication of the garment's usage is necessary. If the `select` feature
      was not implemented, the user would have to modify the `LastUse` attribute through the `edit` command which can
      be extremely tedious and error-prone, e.g. the user might remember the wrong date to enter.
    * Highlights: Users can easily select a garment via `INDEX`. This is easy to use, and the garment's attributes are
      updated to reflect this.
      <br><br>

* **New Feature**: Added `Type` attribute.
  [#61](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/61)
  * What it does: Each garment object has attributes such as `size` and `name`. The added `Type` attribute allows
    the application to keep track of the added garment's form. The garments in the garment list can then shown to users sorted
    by `Type`.
  * Justification: Each garment needs to be categorised by its form. This constraint allows for the matching of clothes to be
    done more sensibly, e.g. garments with the same form will not be matched.
  * Highlights: `Type` fulfills this criteria by providing three categories the
    garment can belong to: upper, lower or footwear.
      <br><br>

* **New Feature**: Added `DressCode` attribute.
  [#53](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/53)
    * What it does: Each garment object has attributes such as `size` and `name`. The added `DressCode` attribute allows
      the application to keep track of the added garment's dressing characteristic. 
    * Justification: For more other features such as `match`, the constraints in pairing arguments should be that they
      should have a similar dressing characteristic. 
    * Highlights: `Dresscode` fulfills this criteria by providing three categories the
      garment can belong to: formal, casual or active.
      <br><br>
      
* **Enhancements to existing features**:
    * Updated List feature to prevent nonsensical arguments from being accepted.
      [#164](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/164)
    * Added visual representation of the garment's dress-code in GUI.
      [#167](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/167)
    * Added shortcuts for command input (add, delete and edit).
      [#181](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/181)
    * Added functionality for users to cycle through previously added commands.
      [#181](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/181)
      
* **Documentation**:
    * User Guide:
        * Edited `Introduction`, wrote `User Interface Overiew` and edited overall format
          [\#191](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/191/files)
        * Finalised the documentation of entire UG draft, added all screenshots, formatted and edited all text content.
          [\#95](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/95/files)
        * Added the `list` and `help` commands into the UG :
          [\#32](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/32/files)
    * Developer Guide:
        * Added User Stories into DG
          [\#19](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/19/files)
          [\#177](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/177/files)
        * Added the `select` command implementation in DG with UML diagrams
          [\#172](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/172/files)
          
* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#45](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/155),
      [\#56](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/151)
    * Reported bugs and suggestions for other teams in the class (examples:
      [1](https://github.com/arihantjain97/ped/issues/6), [2](https://github.com/arihantjain97/ped/issues/5),
      [3](https://github.com/arihantjain97/ped/issues/3))
      
      