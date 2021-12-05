# Score Estimator (Sellics Home Assignment)
Score Estimator is a REST microservice which retrives popularity score of a keyword at Amazon.com 

## Overview
Its a Java based microservice with the following end point available for retrieving popularity score:
GET /api/estimate

The API expects a parameter named 'keyword' for which the score needs to be calculated.
## What assumptions did you make?
* The Amazon Auto complete API is designed to retrieve currently most popular search keywords for a given prefix.
* Once the searched keyword is found in the retrieved words, there is no need to search further.
* Once the searched keyword is found as prefix for all retrieved words, there is no need to search further as we wont find an exact match.
* An exact match gets higher score than a prefix match.
* The score gets reduced when neither of the above two criterion match.

## Algorithm
The basic algorithm for retrieving the popularity score involves querying the Amazon AutoComplete API for every substring of the keyword and calculating a score until a complete match or a prefix match is found. Following are the detailed steps:
1. Assign weight to every character in the keyword. This is computed by dividing maximum points (100) with the length of keyword. The processing starts with allocating maximum score to the keyword.
2. For every substring, retrieve the matching words from Amazon API. For e.g. for keyword 'samsung' we will be retrieving score with substrings 's','sa','sam'...'samsung'.
3. There are two possibilities when the search ends : 
     * The keyword matches completely with one of the retrieved words.
     * The keyword matches as a prefix with all the retrieved words,
Different weights are allocated for each of the above case and points are awarded accordingly.

4. In case, the above two conditions are not met, the score is reduced by points allocated to each new character.
5. The final score represents the popularity index of the search keyword

## Do you think the ( *hint ) that we gave you earlier is correct and if so - why?
Yes, the hint is correct. The order of the retrieved words is insignificant because if our search keyword is part of the result, then its one of the most popular keywords and therefore needs to be treated the same by our score estimator.

## How precise do you think your outcome is and why?
I believe the outcome is quite precise and is able to retrieve popularity index across all departments of Amazon. The precision can further be improved by searching across a specific department as well in the future.

## Technologies
1. Frameworks: SpringBoot, Junit
2. Java 8

## Instructions
1. Start the server by running the following command in the projects root directory.
   ```
   mvn spring-boot:run
   ``` 
2. For accessing the score estimator API, perform the following request: 
   ```
   http://localhost:8080/estimate?keyword=samsung
   ```

## Testing
For running the unit tests, use the following command in the projects root directory:
```
mvn clean test
```
The integration tests are present in ScoreEstimatorControllerTest.java.
The test coverage of the project is around 99%.

## Examples
1. http://localhost:8080/api/estimate?keyword=samsung
    
    Response{
         "keyword": "samsung",
         "score": 100
    }
2. http://localhost:8080/api/estimate?keyword=MI power bank
    
    Response{
        "keyword": "MI power bank",
        "score": 91
    }
3. http://localhost:8080/api/estimate?keyword=a star
    
    Response{
        "keyword": "a star",
        "score": 40
    }
