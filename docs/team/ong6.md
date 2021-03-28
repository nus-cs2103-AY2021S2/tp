---
layout: page
title: Jun Xiong's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimized for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=ong6&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

#### New Features & Enhancements

- **Enhancements**: Added new models such as `method`, `address`, `data` and `header`

  - What it does: Models the attributes in `endpoint` to display and accept correct information.
  - Justification: The core functionality of our product is heavily dependent on the model. Without the models, there would be no where to store the `endpoints`.
  - Highlights: Ensures users do not enter in invalid fields. Helps to prevent errors from occurring.

- **Enhancements**: Added the ability to add and edit endpoints through the `add` and `edit` commands.

  - What it does: Allows the user to add/edit an endpoint for use in other commands.
  - Justification: This command is needed for a user to include new endpoints in their list.
  - Highlights: Allows for optional fields to be entered as well as prevents the users from entering unrealistic attributes.

- **Enhancements**: Added the ability to find/filter through specific endpoints through the `find` command.

  - What it does: Allows the user to find and filter through endpoints.
  - Justification: Being able to quickly find the endpoint in a large list of endpoints is important as it would save the developer time and reduce confusion.
  - Highlights: Allows users to specify the field to be searched through and allows for combining of the specifications to allow for a more targeted search.

- **Enhancements**:

  - Added more detailed error messages to commands.
  - Upgraded and redesigned the `imposter`, `light theme` and `dark theme` for the UI.

#### Project management

  - Added the team self-initiated [main project website](https://imposter-dev.tk).
  - Managed releases `v1.1`, `v1.2`, `v1.2b`, `v1.3` (4 releases) on GitHub.
  - Maintained [gantt chart](https://docs.google.com/spreadsheets/d/10HzmFh2pCHIu-8VpJSCRy0jzpVehnYpm/edit#gid=577662797) and [project development board](https://github.com/AY2021S2-CS2103T-T12-4/tp/projects/1).
  - Setup the GitHub team organization/repository and added favicon to project page.
  - Setup [tools](#tools) and [workflow guide](../WorkflowGuide.md) to ease project workflow.

#### Community

  - to-do
#### Tools

  - Integrated the third party library [Apache HttpComponents](http://hc.apache.org/index.html) to the project ([\#125](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/125)).
  - Added a [script](https://github.com/AY2021S2-CS2103T-T12-4/tp/blob/master/scripts/sync.sh) to easily sync local, remote individual and team repositories ([\#45](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/45)).

#### Documentation

  - User Guide:
    - Added documentation for `add`, `edit` and `find` command.
    - Updated all commands summary table.
  - Developer Guide:
    - Added implementation details of the `add` and `find` features.
