package Models;

import java.util.List;

public class Ballot {
   public Ballot(){

    }

    public static void vote(List<Candidates> candidates){
            quickShuffle(candidates);
            //this way the candidate chosen at the index of candidates will have their votes added to their respective place votes
            candidates.get(0).add1st(1);
            candidates.get(1).add2nd(1);
            candidates.get(2).add3rd(1);
            candidates.get(3).add4th(1);
    }


    //fisher-yates shuffling alg --- can i find a way that based on popularity one name will show up more often???
    public static void quickShuffle(List<Candidates> candidates){
        int j = 0;
        for(int i = candidates.size()-1; i>=0; i--){
            j = (int) (Math.random() * (i-1));
            swap(candidates, i, j);
        }
    }

    public static void swap( List<Candidates> a, int index1, int index2){
        Candidates temp = a.get(index1);
        a.set(index1, a.get(index2));
        a.set(index2, temp);
    }
}
