---
layout: page
title: Yao Wei's Project Portfolio Page
---

## Project: A-Bash Book

A-Bash Book (ABB) is a Command Line Interface (CLI) based Employee and Business Relations Management
System. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written
in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added an email command that allows the user to email contacts using the operating
  system's email client
    * What it does: allows the user to email one or many contacts via the operating system's email
      client.
    * Justification: This feature improves the product significantly because a user can be able to
      mass email contacts if needed such as a broadcast message.
    * Highlights: This command makes use of the 'mailto' URI
      scheme [RFC 6068](https://tools.ietf.org/html/rfc6068) to open the email client. However,
      Outlook for Windows does not support commas as a delimiter by default which requires the user
      to tweak their configuration before getting this feature to work.
    * Credits: <https://stackoverflow.com/a/16615384/3903483> for the idea of using JavaFX host
      services to open the 'mailto' link.

* **New Feature**: Added a select command that allows the user to select multiple contacts to
  modify.
    * What it does: Allows the user to select multiple contacts to modify. Similar to how checkboxes
      are used in the web browser.
    * Justification: Enables the user to quickly perform modifications to contacts without having to
      explicitly remembering the index number.
    * Highlights: This feature affects multiple components (UI, Model, Logic) and thus designing the
      solution must ensure that original design patterns and abstractions must not be violated.

* **Enhanced Feature**: Enhance `edit` and `delete` features to work with select feature and
  multiple indexes.
    * What it does: Allows the user to select multiple contacts then act on it using `edit`
      and `delete`.
    * Justification: Enables the user to quickly perform modifications to multiple contacts and
      without having to explicitly remembering the index number.
    * Highlights: This feature affects how indexes are parsed and to take into account the selected
      contacts.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=yaowei-soc)
  
* **Project management**:
    * Managed milestones and tagging issues or pull requests to milestones.

* **Enhancements to existing features**:
    * Enhanced `edit`, `delete` command to be used with `select` command.

* **Documentation**:
    * User Guide:
        * Updated UG diagrams [\#131](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/131)
          , [\#140](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/140)
          , [\#211](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/211)
        * Updated documentation/cosmetic tweaks for existing features `edit`, `delete` and overall
          UG structure: [\#212](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/212)
        * Add documentation for implemented features `select`
          , `email` [\#119](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/119)
          , [\#129](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/129)
    * Developer Guide:
        * Add implementation details of the `select`
          feature [\#119](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/119)

* **Community**:
    * PRs reviewed (with non-trivial review
      comments): [\#64](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/64)
      , [\#71](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/71)
      , [\#73](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/73)
      , [\#76](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/76)
      , [\#77](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/77)
      , [\#80](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/80)
      , [\#95](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/95)
      , [\#98](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/98)
      , [\#121](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/121)
      , [\#124](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/124)
      , [\#128](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/128)
      , [\#130](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/130)
      , [\#132](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/132)
      , [\#138](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/138)
    * Contributed to forum
      discussions [\#287](https://github.com/nus-cs2103-AY2021S2/forum/issues/287)

* **Tools**:
    * Integrated a new Github plugin (Codecov) to the team repo so that it is able give codecoverage
      comments in the review page.
    * Integrated a new Github plugin (Netlify) to the team repo so that the team can review
      documentation changes for PRs.

* **Other Contribution**:
    * Assisted @justgnohUG with exploring the implementation of autocomplete functionality in
      JavaFX.
