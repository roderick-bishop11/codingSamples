# Ranked choice voting simulator

# Purpose
I wanted to do this to simulate how a Ranked-choice election would operate. I think it it helpful to have a way for someone to see it, and see the effects as opposed to our current system. 
Seeing the effects and would maybe make the idea itself more tangible and accessible.

# Implementation

In basic terms, there is an election, within the election there are candidates and ballots. Ballot objects consist of first - rth place votes for the 4 candidates. Elections have candidates who each have a data fields for name, party, votes, and if they are favored or not based on the way the county leans. Within each election, the votes are cast and the votes are tallied in rounds that reveal the winner by counting votes in a placed-priority manner. The winner either has the most first place votes, or has the most higher seeded votes over all other candidates leaving more voters served. 

## UML
<div style="width: 640px; height: 480px; margin: 10px; position: relative;"><iframe allowfullscreen frameborder="0" style="width:640px; height:480px" src="https://lucid.app/documents/embeddedchart/5c04cc3d-6cbb-4a11-aa46-42156c1e5571" id="~fcJe0~t~jzn"></iframe></div>


# Logs
* 6/5/21- updated README and UML.
* 6/2/21- cleaned up some logic in fillCandidates(), added tie-breaker functionality, and Party (enum). 


# Resources 


