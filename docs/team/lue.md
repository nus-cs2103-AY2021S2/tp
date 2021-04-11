---
layout: page
title: Lin Lue's Project Portfolio Page
---

## Project: FriendDex

FriendDex is a relationship management tool built to help offload the tedious mental task of manually keeping track of
relationships in our heads. It is a Java application where users interact with it using a CLI, and it has a GUI created
with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to search for contacts by name with regular expressions
    * Justification: This feature provides a way for users to search for names based on properties of the name rather
      than naive string matching, which requires user to memorise an entire token in a person's name for matching to
      occur. <br><br>

* **New Feature**: Added the ability to allow users to change colorscheme of the application
    * What it does: apply a colorscheme to the application given a file containing a valid colorscheme format.
    * Justification: This allows the user to customize the appearance of FriendDex into something more "integrated"
      into the user's system.
    * Highlights: Implementing this feature took some time as it requires interaction with almost all aspect of
      FriendDex (`Model`, `Logic`, `UI`, and `Storage`). Multiple default themes are also shipped with the
      `FriendDex.jar` binary.
    * Elaboration: Allowing the user to change colorscheme "on the fly" as opposed to "restarting the application for
      changes to take effect" required modification of certain properties from `ui` during runtime. A temporary css file
      had to be generated with each theme applied as JavaFX doesn't allow raw css strings as argument. <br><br>

* **New feature**: Added relationship goals.
    * What it does: Allow users to set relationship goals with each contact in FriendDex.
    * Justification: This feature constantly reminds the user the deadline of a relationship goal and thus assist users
      in remembering when is the next time to meet a contact. <br><br>

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=lue97&breakdown=true)
  
* **Documentation**:
    * User Guide:
        * Added documentation for `find`, `theme`, and `set-goal` commands
        * Updated FAQ section
        * Added Theme section <br><br>

    * Developer Guide:
        * Added implementation details for `theme` command
        * Added instructions for manual testing for the commands: `add`, `find`, `goal`, `theme`, and `exit`.

* **Review contributions**:
    * PRs reviewed (with non-trivial review comments):
      [#245](https://github.com/AY2021S2-CS2103T-W14-1/tp/pull/245).
      <br><br>
