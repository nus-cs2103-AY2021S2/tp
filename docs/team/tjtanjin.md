---
layout: page
title: Tan Jin's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimised for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=tjtanjin&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=)

#### New Features & Enhancements

- **New Feature**: Added the `request` feature which is heavily used by the `send` and `run` command.

  - What it does: Supports the making of API calls to an endpoint.
  - Justification: The core functionality of our product is heavily dependent on this feature. Without this, the product is unable to send requests and receive responses.
  - Highlights: This new feature is used by the `send` and `run` commands to perform API calls. The implementation was challenging as it required the use of threading to ensure that the UI does not freeze up when API calls take longer than expected.
  - Credits: The request feature is built on top of [Apache HttpComponents](http://hc.apache.org/index.html).

- **New Feature**: Added the ability to make an API call through the `send` command.

  - What it does: Allows the user to make an API call to a saved endpoint.
  - Justification: This command is needed for a user to make an API call to an endpoint.
  - Highlights: The `send` command complements and is supported by the above `request` feature which allows the user to specify an endpoint to make an API call for.

- **New Feature**: Added the ability to cancel an API call through <kbd>ctrl</kbd> + <kbd>d</kbd>.

  - What it does: Allows the user to cancel an ongoing API call.
  - Justification: Certain endpoints may take a long time to respond. Without this feature, users may be held hostage by the wait time without an option to abort the API call.
  - Highlights: This keyboard command complements the above `request` feature by providing users with an option to terminate an ongoing API call. The implementation was challenging as the API call was made in a thread and terminating it required the closing of the HTTP connection with careful handling of exceptions.

- **New Feature**: Added switching of application theme through the `toggle` command.

  - What it does: Allows users to switch the application theme easily.
  - Justfication: This improves the visual comfort for the user as they are able to work under visuals they are more accustomed to (e.g. light/dark theme).
  - Highlights: This feature was implemented such that new themes can be added easily by modifying only a single line of code and adding a new css file.

- **Enhancements**:

  - Added `response` attribute to `endpoint` to store API call responses
  - Added `project icon` to dock/taskbar when the application is launched.
  - Added an `orange outline` on the focused component of the UI for clarity to users.
  - Added the `general template`, `light theme` and `dark theme` for the UI.
  - Added `GIFs` to represent ongoing API call and error message.

#### Project management

  - Added the team self-initiated [main project website](https://imposter-dev.tk).
  - Managed releases `v1.1`, `v1.2`, `v1.2b`, `v1.3` (4 releases) on GitHub.
  - Maintained [gantt chart](https://docs.google.com/spreadsheets/d/10HzmFh2pCHIu-8VpJSCRy0jzpVehnYpm/edit#gid=577662797) and [project development board](https://github.com/AY2021S2-CS2103T-T12-4/tp/projects/1).
  - Setup the GitHub team organisation/repository and added favicon to project page.
  - Setup [tools](#tools) and [workflow guide](../WorkflowGuide.md) to ease project workflow.

#### Community

  - PRs reviewed (with non-trivial review comments): [\#122](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/122), [\#378](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/378).
  - Helped and provided tips through forum discussions (examples: [Checkstyle Guide](https://github.com/nus-cs2103-AY2021S2/forum/issues/93), [Fat JAR guide](https://github.com/nus-cs2103-AY2021S2/forum/issues/40), [Peer Help](https://github.com/nus-cs2103-AY2021S2/forum/issues/52)).

#### Tools

  - Integrated the third party library [Apache HttpComponents](http://hc.apache.org/index.html) to the project ([\#125](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/125)).
  - Added a [pre-commit githook](https://github.com/AY2021S2-CS2103T-T12-4/tp/blob/master/.githooks/pre-commit) to the repository to improve CI ([\#66](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/66)).
  - Added a [script](https://github.com/AY2021S2-CS2103T-T12-4/tp/blob/master/scripts/sync.sh) to easily sync local, remote individual and team repositories ([\#45](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/45)).

#### Documentation

  - User Guide:
    - Added documentation for `send` command ([\#52](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/52)).
    - Added cover page and significantly improved styling/formatting ([\#296](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/296), [\#314](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/314), [\#382](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/382)).
    - Added appendix for additional information on API, Requests and JSON ([\#353](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/353)).
  - Developer Guide:
    - Added implementation details of the `request` feature ([\#351](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/351)).
    - Added cover page ([\#351](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/351)).
    - Added several use cases and glossary terms ([\#71](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/71), [\#102](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/102)).
