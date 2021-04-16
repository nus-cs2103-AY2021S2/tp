---
layout: alt-page
title: Tan Boon Ji's Project Portfolio Page
---

## Project: A-Bash Book

A-Bash Book (ABB) is a Command Line Interface (CLI) based Employee and Business Contact Management
System. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written
in Java, and has about 13k LoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=tanboonji)

* **New Feature**: Added add, delete and list alias commands that allows user to create, delete and list command aliases.
  for existing commands.
  * What it does: Allows the user to create and delete commands aliases for existing commands. The user can also list out their 
    command aliases.
  * Justification: This feature helps the user to type commands faster as users can type command aliases that will be converted
    to the actual existing commands that it is aliased to. These command aliases are much shorter and can be words that are easier
    for the users to remember than the actual existing commands. e.g. `a` can be aliased to `add`.
  * Highlights: This feature uses complex checking and parsing to check for the validity of the command to be aliased. Partial commands
    which are valid can also be aliased, allowing user to create more customised command aliases that can include empty prefixes.

* **New Feature**: Added add and delete tag commands that allows user to add and delete tags from persons in the address book.
  * What it does: Allows the user to add and delete tags from persons in the address book. The user can also bulk add and delete 
    multiple tags from multiple persons in the address book.
  * Justification: The original implementation to edit tags of person is through the `edit` command, and the new tags in `edit` command 
    will overwrite all the existing tags of the person. This feature allows the user to add one or multiple tags to the existing tags of 
    the person in the address book without overwriting the existing tags of the person.
  * Highlights: This feature supports adding one or many tags to one or many persons in the address book.

* **Enhancements to existing features**:
  * Added `Company` and `JobTitle` field for `Person` in address book.

* **Project Management**:
  * Co-managed milestones, issues, pull requests and documentation with the team for all milestones.
  
* **Documentation**:
  * User Guide:
    * Add documentation for `alias` and `tag` features 
      [\#42](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/42/),
      [\#132](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/132/),
      [\#249](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/249)
    * Update UG diagrams
      [\#116](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/116)
  * Developer Guide:
    * Add implementation details for `alias` and `tag` features
      [\#265](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/265)
  
* **Community**:
  * PRs review (with non-trivial review comments): [\#103](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/103), 
    [\#119](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/119), 
    [\#207](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/207),
    [\#226](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/226),
    [\#234](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/234),
    [\#239](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/239)

