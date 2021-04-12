#Overview Of Product

Car@leads is a **desktop app for a car salesperson to manage customer contacts** (CLI), to facilitate easy access to 
clients and enabling them to maintain a good relationship with their clients without hassle.

## Contribution 

This is the [code](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=rajobasu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=rajobasu&tabRepo=AY2021S2-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) I contributed.

- I mainly implemented the Filter Combinator portion to effectively combine different filters using logical operators 
like `and` `or` `not` as well as brackets to give rise to more complex query structures. This is the at the heart of 
  our application, and I essentially implemented an ExpressionTree which I parsed from the user input.  
  
- I also implemented some Filters like EmailFilter, PhoneFilter, DOBFilter and so on, and helped implemented a typo 
  tolerant NameFilter. 
  
- I also created the CommandHistory class, and the associated `/up` function to easily rerun previous commands.

- I added a fair share of user stories, and wrote the description of the `find` command as well as the `/up` command 
  in the user guide. 
  
- In the DG, I updated some Use cases.

