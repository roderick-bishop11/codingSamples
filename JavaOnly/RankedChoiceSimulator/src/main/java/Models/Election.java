package Models;

import Helpers.Party;
import Helpers.RandomCollection;
import com.github.javafaker.Faker;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Election {
    String countyName;
    public static Party countyLean; //enum for how the county leans to dictate the popularity
    public int totalVotes;
    List<Party> choices = new ArrayList<Party>(4);
    public List<Candidate_Att> candidates = new ArrayList<>();
    public String winner;
    //parties with their respective estimated percentages: garnered from https://voterrecords.com/charts
    public RandomCollection parties = new RandomCollection().add(36.8, "Democratic").add(34.2,"Republican").add(0.5, "Libertarian")
            .add(1.3,"Independent").add(0.2, "Green").add(27.2,"Non-partisan");
    private int votesLeft;
    private int nthPlaceVotes = 0;
    public static Party mostPrevParty = null;

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
    public void printPop(){
    String format = "%-25s%-4f%n";
        candidates.forEach(i->System.out.printf(format,i.getCandidateName(), i.getPopularityFactor()));
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
        assignWeights();
}

//finds the most prevalent party to perform a tie-breaker on if there are multiple candidates
public Party partyPrevalence(){
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
        mostPrevParty = partyPrevalence();
    //finds a candidate out of the party most prevalent and sets that as the favored.
    candidates.stream().filter(a -> a.getParty().equals(mostPrevParty)).findFirst().ifPresent(brokenTie -> brokenTie.setFavored(true));
    printCandidates();
}

//generates the votes from the Ballot Class
public void generateVotes(){
        for(int i = 0; i < this.totalVotes; i++){
            Ballot.vote(this.candidates);
        }
}

    //this function assigns popularity values
    public void assignWeights(){
        /*cases:
         * favored and party lean || only candidate that matches party lean: even distro +15
         * not favored and party lean: even distro +5
         * not favored and not party lean: even distribution +0
         * */
        double totalPopPoints = 100.00;
        if(candidates.stream().anyMatch(a-> a.getParty().equals(countyLean))){
            //not favored and party lean
                List<Candidate_Att> sameAsLean = candidates.stream().filter(a -> a.getParty().equals(countyLean)).collect(Collectors.toList());
                for (Candidate_Att c : sameAsLean) {
                    c.addPopFactorPoints(5.0);
                    totalPopPoints -= 5.0;
                    }
                if(sameAsLean.size() == 1){
                    sameAsLean.get(0).addPopFactorPoints(10.0);
                    totalPopPoints -= 10.0;
                }
            }
                //favored and party lean
            Candidate_Att mostFav = candidates.stream().filter(a-> a.getParty().equals(countyLean) && a.getFavored()).findAny().orElse(null);
            if(mostFav != null) {
                mostFav.setPopularityFactor(10.0);
                totalPopPoints -= 10.0;
            }
        //even distro
        for(Candidate_Att c: candidates){
            c.addPopFactorPoints(Math.floor(totalPopPoints/ candidates.size()));
        }
        printPop();
    }

//for actually counting the votes and seeing who is in and out of the race
//public String processVotes(){}

}
