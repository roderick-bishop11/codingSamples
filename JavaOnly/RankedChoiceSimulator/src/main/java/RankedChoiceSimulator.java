import Models.Election;

public class RankedChoiceSimulator {
    public static void main(String[] args) {
        Election election = new Election(27000);
        election.fillCandidates();
    }
}
