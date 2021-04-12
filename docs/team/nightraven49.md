---
layout: page
title: Warren Ng's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimized for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=NightRaven49&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features & Enhancements

- **Enhancement**: Beautified header fields ([\#316](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/316)).

  - What it does: Add background fill to headers in each endpoint card to match the aesthetics of tags.
  - Justification: Originally the header field was regular plain text and placed just above the API URL, so without any form of differentiation both the headers and URL would be difficult to distinguish.
  - Highlights: Different colours used for every theme.

- **Enhancement**: Include static banner in application ([\#393](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/393)).

  - What it does: Replace MenuBar with a banner that clearly labelled the list of saved endpoints and response box. 
  - Justification: The original MenuBar from AB3 only had help and exit, which are already accessible through the command box, so it was deemed redundant and thus replaced with a more useful UI component.
  - Highlights: A special font is used for the banner when using the Imposter theme.

- **Enhancement**: Added GIFs suited for different themes ([\#338](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/338), [\#397](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/397), [\#409](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/409)).

  - What it does: Have GIFs with different coloured text appear for different themes.
  - Justification: The GIFs that were originally used had white text and hence were suitable for dark themes similar to the original AB3. However, when light themes were implemented, the white text would be difficult to read against a light background. Thus, GIFs with black text was required for such light themes.

<div style="page-break-after: always;"></div>

#### Documentation

  - Developer Guide:
    - Added implementation details of the `toggle` feature ([\#464](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/464)).
    - Added test cases for `help`, `exit` and data storage features ([\#588](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/588), [\#625](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/625)).
    - Formatted most of the DG for proper page breaks, image sizes and centering ([\#576](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/576), [\#625](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/625)).

#### Project management

  - Managed release `v1.4b` on GitHub.

#### Community

  - PRs reviewed (with non-trivial review comments): [\#572](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/572)
