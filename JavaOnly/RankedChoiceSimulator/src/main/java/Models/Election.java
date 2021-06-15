package Models;

import Helpers.Party;
import Helpers.RandomCollection;
import com.github.javafaker.Faker;
import lombok.Data;

import java.util.*;

@Data
public class Election {
    String countyName;
    Party countyLean; //enum for how the county leans to dictate the popularity
    public int totalVotes;
    List<Party> choices = new ArrayList<Party>(4);
    public List<Candidate_Att> candidates = new ArrayList<>();
    public String winner;
    //parties with their respective estimated percentages: garnered from https://voterrecords.com/charts
    public RandomCollection parties = new RandomCollection().add(36.8, "Democratic").add(34.2,"Republican").add(0.5, "Libertarian")
            .add(1.3,"Independent").add(0.2, "Green").add(27.2,"Non-partisan");
    private int votesLeft;
    private int nthPlaceVotes = 0;

    public Election(int totalVotes, String name, Party lean){
        this.totalVotes = totalVotes;
        countyName = name;
        countyLean = lean;
    }

public void printCandidates(){
        String format = "%-25s%-10s%-10b%n";
    System.out.printf("%-25s%-10s%-10s", "Candidate:", "Party:", "Favored?");
    System.out.println("\n_________________________");
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
        //boolean is for if the candidate is favored or not
    HashMap<Boolean, List<Candidate_Att>> candidateFavored = new HashMap<>();
    Faker fakeData = new Faker();
    Random r = new Random();
//we want 4 candidates and to fill out the list.
    for (int i = 0; i < 4; i++) {
//    votesLeft = totalVotes;
//    nthPlaceVotes = 0;
        //filling candidate names and parties
        String name = fakeData.name().fullName();
        Candidate_Att candidate = new Candidate_Att(name, Party.setParty((String)parties.next()));
        candidates.add(candidate);
        choices.add(candidate.getParty());
    }
        printCandidates();
    tieBreaker();

    System.out.println("\n\n");
        generateVotes();
        printVotes();
}

//finds the most prevalent party to perform a tie-breaker on if there are multiple candidates
public Party partyPrevalance(){
    int max = 0;
    int curr = 0;
    Party currKey =  null;
    Set<Party> unique = new HashSet<>(choices);
    for (Party key : unique) {
        curr = Collections.frequency(choices, key);
        if(max < curr){
            max = curr;
            currKey = key;
        }
    }
    return currKey;
}

//will break the tie between candidates of the same party
public void tieBreaker(){
        List<Candidate_Att> favoredCandidates = new ArrayList<>();
        Party mostPrevParty = partyPrevalance();
    for(Candidate_Att candidate: candidates){
     if(candidate.getParty() == mostPrevParty){
         favoredCandidates.add(candidate);
     }
    }
    favoredCandidates.get((int)Math.random()*favoredCandidates.size()).setFavored(true); //sets one of the candidates as the favorite at random.
    printCandidates();
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
