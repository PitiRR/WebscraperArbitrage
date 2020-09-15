# WebscraperArbitrage
> Scrape currency exchange information from online bureaus around the world in real time and see if there is a possible arbitrage to be earned, with minimal risk.

## About
This is achieved thanks to custom implementation of Bellman-Ford algorithm to find a negative cycle that calculates an arbitrage for potentially unlimited profit at minimal risk. Currently, buearus available are from Germany, Spain, Sweden and Poland, however, if desired, implementing additional exchanges is very simple - just add a class and base the scraping method on the samples provided.

Calculations are optimized. Thanks to clever use of hashmaps (sometimes, hashmaps of hashmaps!), enumeration of vertex neighbour takes minimal time, while updating edge weight takes constant time.

This hobby project is very insightful for anyone wanting to improve webscraping skills for a practical, real-life purpose. It is implemented using jsoup library. It is also a good real-life example of applying graph-theory, particularly Bellman-Ford algorithm mentioned above.

There are 4 sample websites scraped using jsoup library. If you wish to add/replace them with your own, be mindful of **inconsistent websites** (e.g. some currencies have an exchange ratio of 1:1, others 100:100).

## Requirements
- [jsoup 1.13.1](https://jsoup.org/)

## Setup / How to

1. Clone the repo with `git clone https://github.com/PitiRR/WebscraperArbitrage/`
2. Add jsoup.jar as a dependency in the project
3. Compile and run!
