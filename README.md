[![Build Status](https://travis-ci.org/rudky4/PA165---Liquor-bottles-registry.svg?branch=master)](https://travis-ci.org/rudky4/PA165---Liquor-bottles-registry)

# Liquor bottles registry

Info: Development in progress.

Project developed as part of PA165 Enterprise Java - course at Faculty of Informatics Masaryk University.

Idea:
Everybody knows why prohibition went into effect in Czech Republic some time ago. 
In order to prevent this in the future there is a need for liquor bottles registry. 
Such a registry would keep track of all the liquor bottles that are, or will be, on the market. 
For each bottle there is a need to keep record of its producer. 

Allow customer/store/lab (anyone) to mark bottle as toxic by entering taxStampID of the bottle to the system.
The customers are interested in how many bottles of which liquor type are currently in particular stores. 
Police can obtain the statistics of the toxic bottles for each store and producer.


## Installation

This project is build using maven, backend powered by Java and frontend by AngularJC.

**Usage:** 

1. build the project `mvn clean install`

2. then go to folder web `cd web`

3. and run tomcat `mvn tomcat7:rum`

You will find web interface of application at: localhost:8080/pa165/

You should be able to view a list of stores and bottles, list of manufacturers and bottle types they produce (no login required). 
If you are manufacturer, from police, laboratory or you own some store, just log in and manage what is in your competences.

Prepared accounts (login/pass): manufacturer/manufacturer, police/police, laboratory/laboratory, store/store.

## Project webpage
https://rudky4.github.io/PA165---Liquor-bottles-registry/ (in development)
