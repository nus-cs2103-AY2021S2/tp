---
layout: page
title: Visnu S/O Ravindrans's Project Portfolio Page
---

## Project: NuFash

NuFash is a desktop clothing application used for organising wardrobes, and taking better clothing decisions. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 
12 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=Visnu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=VisnuRavi&tabRepo=AY2021S2-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2.1` - `v1.4` (3 releases) on GitHub
  <br><br>

* **Enhancement to Existing Feature**: Added the ability to find clothings based on various attributes
  [\#63](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/63),
  [\#97](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/97)
  * What it does: allows the user to input various types of attributes, and filters out the clothing based on his input
  * Justification: This feature improves NuFash significantly because a user would require to easily find his
    clothing based on the various attributes, to view them, know where he placed them, or even to know whether he has
    a particular clothing.
  * Highlights: This enhancement affects some of our new features, such as the match feature which relies heavily on the
    find feature, and potentially new features as well. It required an in-depth analysis of design alternatives. 
    The implementation too was challenging as it required changes to the existing find command, taking in 
    multiple different attributes of a garment, manipulating the input to a suitable format, and then making use of
    this format to give the correct output to the user. <br><br>

<div style="page-break-after: always;"></div>
* **Enhancement to Existing Feature**: Added the ability to match a garment based on its colour, dresscode, and type
  [\#97](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/97)
  * What it does: allows the user to find the list of garments that match his chosen garment 
  * Justification: This feature improves NuFash significantly as previously, it only outputs garments that have 
    matching colour, but now it outputs garments that have the matching dresscode and type as well as the colour.
  * Highlights: This enhancement affects the list of matching garments viewed by the user, giving the user more 
    accurate garment suggestions.  
    <br>
    
* **Enhancements to Existing general features**:
  * Updated the Model from being Person to a Garment.
    [\#52](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/52)
  * Updated the attributes, such as Phone Number, of the Person to fit the attributes of a Garment, such as Size.
    [\#42](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/42)
  * Added new classes predicates, which allows the find and match features to find the right 
    garments to output.
    [\#63](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/63),
    [\#97](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/97)
  * Wrote additional tests for existing features to increase test coverage.
    [\#63](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/63),
    [\#97](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/97)
    <br><br>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `clear` and `find` 
      [\#89](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/a38810c24bc1cf61ff88c4d39e2610618eb9d77f)
    * Added relevant explanation to the existing documentation of the general UG: 
      [\#108](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/1d74d3847cb942b9be755fe970908f70e31e970c)
    * Edited the UG based on the bugs found by our peers in terms of phrasing and updated the screenshots of NuFash:
      [\#154](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/350935872a3f88b9934c607a5f00752162055fff),
      [\#155](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/4cbfef176c0ffa1651e7657dd8e2b6d428cd080b)
  * Developer Guide:
    * Added implementation details of the `find` feature. 
      [\#174](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/456ac61e49df04c55cbc40609a11f8aa35e31a82)
    * Added Find Use Case to DG 
      [\#183](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/dbdba85f9bdffd1536f5768b92408f0fe98dde11)


* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [\#45](https://github.com/nus-cs2103-AY2021S2/ip/pull/45),
    [\#56](https://github.com/nus-cs2103-AY2021S2/ip/pull/56)
  * Reported bugs and suggestions for other teams in the class (examples: 
    [1](https://github.com/VisnuRavi/ped/issues/9), [2](https://github.com/VisnuRavi/ped/issues/6), 
    [3](https://github.com/VisnuRavi/ped/issues/15))
    
