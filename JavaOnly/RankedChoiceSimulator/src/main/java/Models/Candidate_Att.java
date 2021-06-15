package Models;
import Helpers.Party;
import lombok.Data;

@Data
public class Candidate_Att {
    private String candidateName;
    private Party party;

    //default values are zero
    private int firstPlaceVotes = 0;
    private int secondPlaceVotes = 0;
    private int thirdPlaceVotes = 0;
    private int fourthPlaceVotes = 0;
    private double popularityFactor = 0.0;
    private boolean favored;


    //this constructor only has the name and party which are the only args we need. The rest will be randomized
    public Candidate_Att(String candidateName, Party party){
        this.candidateName = candidateName;
        this.party = party;
        favored = false;
    }

    public void add1st(int i){
        this.firstPlaceVotes += i;
    }
    public void add2nd(int i){
        this.secondPlaceVotes += i;
    }
    public void add3rd(int i){
        this.thirdPlaceVotes += i;
    } public void add4th(int i){
        this.fourthPlaceVotes += i;
    }

    public boolean getFavored() {
        return favored;
    }

    public void addPopFactorPoints(double added){
        this.popularityFactor += added;
    }


}
