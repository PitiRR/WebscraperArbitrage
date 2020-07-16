# WebscraperArbitrage

## About
This program calculates arbitrage, if one exists, to find **profit with virtually no risk**. It uses Bellman-Ford algorithm to find a negative cycle. Using hashmaps and hashsets, rather than arrays and lists, allows this program to run quickly thanks to constant lookup and update times. 
There are 4 sample websites scraped using jsoup library. If you wish to add/replace them with your own, be mindful of **inconsistent websites** (e.g. some currencies have an exchange ratio of 1:1, others 100:100).

## Requirements
- [jsoup 1.13.1](https://jsoup.org/)

## Running
Simply add jsoup.jar as a dependency, compile and see results!
