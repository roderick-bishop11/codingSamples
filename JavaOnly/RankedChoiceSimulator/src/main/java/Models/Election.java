package Models;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class Election {
    public int totalVotes;
    public List<Candidates> candidates;
    public String winner;

    //parties with their respective estimated percentages: garnered from https://voterrecords.com/charts
    public Map<String, Double> parties = Map.of("Democratic", 36.8, "Republican",34.2, "Libertarian", 0.5, "Independent", 1.3, "Green", 0.2, "Non-partisan", 27.2 );

    public Election(int totalVotes){

    }

public void printCandidates(){

}


//using Faker to fake data
public void fillCandidates(){

}
//public String processVotes(){}

}
