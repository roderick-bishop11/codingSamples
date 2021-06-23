# Ranked choice voting simulator

# Purpose & Intent
I wanted to do this to simulate how a Ranked-choice election would operate. I think it it helpful to have a way for someone to see it, and see the effects as opposed to our current system. The purpose & intent is to simply create my own programming experience while providing a means to educate voters on their options.

# Goals

- [ ] Create an immersive and realistic simulation of the process of Ranked Choice Voting to bring awareness and invoke voter involvement. 
- [ ] Develop hypothesis around the effects of the ranked choice voting system using data from the sim. 
- [ ] Make V1 available as JAR 
- [ ] Rewrite and delpoy as a web app for accesibility.

# Implementation

In basic terms, there is an election, within the election there are candidates and ballots. Ballot objects consist of first - rth place votes for the 4 candidates. Elections have candidates who each have a data fields for name, party, votes, and if they are favored or not based on the way the county leans. Within each election, the votes are cast and the votes are tallied in rounds that reveal the winner by counting votes in a placed-priority manner. The winner either has the most first place votes, or has the most higher seeded votes over all other candidates leaving more voters served. 

## UML
![Alt text](https://github.com/roderick-bishop11/codingSamples/blob/master/JavaOnly/RankedChoiceSimulator/RankedChoice%20UML.png?raw=true "UML for this project")


# Logs
* 6/22/21 - README change
* 6/15/21- more concise logic for `tieBreaker()` along with `partyPrevalence()`, added `assign weights()` to give out popularity bonuses based on party and lean, `printPop()` to print `popularityFactor` data field for `Candidate_Att`. 
* 6/5/21- updated README and UML.
* 6/2/21- cleaned up some logic in fillCandidates(), added tie-breaker functionality, and Party (enum). 


# Resources 

[How RCV Works](https://www.commoncause.org/democracy-wire/ranked-choice-voting-how-does-it-work/)