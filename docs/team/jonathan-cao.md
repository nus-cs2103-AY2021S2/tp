---
layout: page
title: Cao Jiahao, Jonathan's Project Portfolio Page
---

## Project: NuFash

NuFash is a desktop clothing application used for organising wardrobes, and taking better clothing decisions.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
12 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=Jonathan-Cao&tabRepo=AY2021S2-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2.1` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updates the Model from being AddressBook to Wardrobe [\#48](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/4aab44387539ea107f13108fa3b6ab42a27d42cb)
    * Updated the Model from being Person to a Garment. [\#52](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/ce862e99430b16c737a79174c76f04e4b2952d6a)
    * Updated the attribute Tag of the Person to fit the Description attribute of Garment. [\#50](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/ce862e99430b16c737a79174c76f04e4b2952d6a)
    * Wrote additional tests for existing features to increase test coverage.

* **New Feature**: Added the ability to view outfits (upper, lower, footwear) [\#88](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/772073bb1c5b0adfc24f61fc1279596636ea305b)
    * What it does: allows the user to preview how an entire outfit will look. 
    * Justification: This feature improves the product significantly because a user will be able to see at a glance if each piece of an outfit (upper, lower and footwear) complement one another.
    
* **New Feature**: Added LastUse attribute to Garment [\#69](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/60120aae445f820427742e7266cfc357369b3219)
    * What it does: keeps track of when a Garment was last selected (i.e. worn).
    * Justification: This feature improves the product significantly as users will be able to tell which pieces of Garment they have not worn in a long time such that they can maximise the usage of the Garments that they own.
    * Highlights: This enhancement is used in the sorting of Garments in the wardrobe.
    
* **Enhancements to existing features**: Sort Garments in the Wardrobe by their LastUse attribute by default [\#149](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/b051fcc300c3b8338f94bf647eb84e0f4db65a11)
    * What it does: sorts Garments in the Wardrobe in ascending order of LastUse (from earliest date to latest date)
    * Justification: Similar to that of LastUse attribute
    * Highlights: This enhancement affects the list view of all the Garments in the Wardrobe such that they will always be sorted by LastUse.

* **Documentation**:
    * User Guide:
        * Reformat Features Section and added documentation for `edit` feature [\#34](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/03da6f4e27e9ebb144cf33fa52967dab887813c9#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Add documentation for `view` feature. [\#88](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/1422604b534f89d10a5da0fbfbe3bef4d1c4aa63#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    * Developer Guide:
        * Add Use Cases to DG [\#23](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/7be5637cc2f035f8ababef5e06fe08287b2df291#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
        * Added implementation details of the `find` feature. [\#71](https://github.com/AY2021S2-CS2103T-T12-1/tp/commit/278b80bde0e18bc1ae6b042a3ce2830f6fdfa5bd#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#258](https://github.com/nus-cs2103-AY2021S2/ip/pull/258),
      [\#143](https://github.com/nus-cs2103-AY2021S2/ip/pull/143)
    * Reported bugs and suggestions for other teams in the class (examples:
      [1](https://github.com/Jonathan-Cao/ped/issues/2), [2](https://github.com/Jonathan-Cao/ped/issues/5),
      [3](https://github.com/Jonathan-Cao/ped/issues/1))
    
