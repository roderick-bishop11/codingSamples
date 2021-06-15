package Models;

import java.util.List;
import java.util.stream.Collectors;

public class Ballot {
   public Ballot(){

    }

    //TODO: change this to assign votes based on pop values
    public static void vote(List<Candidate_Att> candidates){
            quickShuffle(candidates);
            //this way the candidate chosen at the index of candidates will have their votes added to their respective place votes
            candidates.get(0).add1st(1);
            candidates.get(1).add2nd(1);
            candidates.get(2).add3rd(1);
            candidates.get(3).add4th(1);
    }


    //fisher-yates shuffling alg --- can i find a way that based on popularity one name will show up more often???
    public static void quickShuffle(List<Candidate_Att> candidates){
        int j = 0;
        for(int i = candidates.size()-1; i>=0; i--){
            j = (int) (Math.random() * (i-1));
            swap(candidates, i, j);
        }
    }

    public static void swap(List<Candidate_Att> a, int index1, int index2){
        Candidate_Att temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }



}
