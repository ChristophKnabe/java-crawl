# java-crawl
A command line web crawler as a Java/Maven miniproject

Source code taken from chapter 3 of [jsoup – Basic web crawler example](https://www.mkyong.com/java/jsoup-basic-web-crawler-example/).
Then mavenized, styled more constant and configurable on the command line.

You can run the crawler on the command line by
```
mvn exec:java
```
or in your IDE by running the main method of class `crawl.WebCrawlerWithDepth`.

The first argument to the main method is the start URL, the second argument is the maximum link depth.
Without arguments it will crawl only depths 0 and 1 starting from
the home directory of the author, thus printing all links found directly in the start page.
