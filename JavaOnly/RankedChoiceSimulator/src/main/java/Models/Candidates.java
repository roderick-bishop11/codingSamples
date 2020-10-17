package Models;
import lombok.Data;

@Data
public class Candidates {
    private String candidateName;
    private String party;

    //default values are zero
    private int firstPlaceVotes = 0;
    private int secondPlaceVotes = 0;
    private int thirdPlaceVotes = 0;
    private int fourthPlaceVotes = 0;


    //this constructor only has the name and party which are the only args we need. The rest will be randomized
    public Candidates(String candidateName, String party){
        this.candidateName = candidateName;
        this.party = party;
    }
}
