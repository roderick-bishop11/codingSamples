package Models;

import Helpers.FavoredParty;
import Helpers.RandomCollection;
import com.github.javafaker.Faker;
import lombok.Data;

import java.util.*;

@Data
public class Election {
    String countyName;
    FavoredParty countyLean; //enum for how the county leans to dictate the popularity
    public int totalVotes;
    public List<Candidates> candidates = new ArrayList<>();
    public String winner;
    //parties with their respective estimated percentages: garnered from https://voterrecords.com/charts
    public RandomCollection parties = new RandomCollection().add(36.8, "Democratic").add(34.2,"Republican").add(0.5, "Libertarian")
            .add(1.3,"Independent").add(0.2, "Green").add(27.2,"Non-partisan");
    private int votesLeft;
    private int nthPlaceVotes = 0;

    public Election(int totalVotes, String name, FavoredParty lean){
        this.totalVotes = totalVotes;
        countyName = name;
        countyLean = lean;
    }

public void printCandidates(){
        String format = "%-25s%s%n";
    System.out.printf(format, "Candidate:", "Party:", "Favored?");
    System.out.println("________________________________________");
    candidates.forEach(i-> System.out.printf(format, i.getCandidateName(), i.getParty(), i.getFavored()));
}

//printing votes in a "row-by-row" fashion
public void printVotes(){
    for(int i = 0; i < candidates.size(); i++){
        System.out.println("\n\n" + candidates.get(i).getCandidateName() + "'s votes are as follows");
        System.out.println("First place: " + candidates.get(i).getFirstPlaceVotes() + "\nSecond place: "
                + candidates.get(i).getSecondPlaceVotes() + "\nThird place: "+ candidates.get(i).getThirdPlaceVotes()
                + "\nFourth place: " + candidates.get(i).getFourthPlaceVotes());
    }
}


public void fillCandidates() {
    HashMap<Boolean, List<Candidates>> candidateFavored = new HashMap<>();
    Faker fakeData = new Faker();
    Random r = new Random();
    candidateFavored.put(false, new ArrayList<Candidates>());
//we want 4 candidates and to fill out the list.
    for (int i = 0; i < 4; i++) {
//    votesLeft = totalVotes;
//    nthPlaceVotes = 0;
        //filling candidate names and parties
        String name = fakeData.name().fullName();
        Candidates candidate = new Candidates(name, (String) parties.next());
        candidateFavored.get(false).add(candidate);
//    candidateFavored.get(0).stream().filter(i -> (i.getParty().equals(candidate.getParty()))).map();
        if (candidateFavored.containsKey(false)) {
            for (Candidates k : candidateFavored.get(false)) {
                if (k.getParty().equals(candidate.getParty())) {
                    if (!k.getFavored() && !candidate.getFavored()) {
                        k.setFavored(true);
                        candidateFavored.put(true, candidateFavored.get(0));
                        candidateFavored.remove(false);
                    }
                }
            }
        }
        //find other candidates with same party, pick one that is favored, if there is a favorite... don't pick and choose
/*
    //column-by-column approach
 //1st place
         nthPlaceVotes = r.nextInt(totalVotes+1);
         candidate.setFirstPlaceVotes(nthPlaceVotes);
         votesLeft = votesLeft - nthPlaceVotes;
 //2nd place
         nthPlaceVotes = r.nextInt(votesLeft+1);
         candidate.setSecondPlaceVotes(nthPlaceVotes);
         votesLeft = votesLeft - nthPlaceVotes;
 //3rd place
         nthPlaceVotes = r.nextInt(votesLeft+1);
         candidate.setThirdPlaceVotes(nthPlaceVotes);
         votesLeft = votesLeft - nthPlaceVotes;
 //4th place
         candidate.setFourthPlaceVotes(votesLeft);
         */

candidates = candidateFavored.get(true) != null? candidateFavored.get(true): candidateFavored.get(false);
        printCandidates();
        System.out.println("\n\n");
        generateVotes();
        printVotes();
    }
}

/*
in here we need to create a vote, which has a
 */
public void generateVotes(){
        for(int i = 0; i < this.totalVotes; i++){
            Ballot.vote(this.candidates);
        }
}


//for actually counting the votes and seeing who is in and out of the race
//public String processVotes(){}

}
