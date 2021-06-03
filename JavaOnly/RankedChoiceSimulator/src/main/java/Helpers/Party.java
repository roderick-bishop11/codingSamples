package Helpers;

//this enum is for selecting the type of county that the election is being held in and will decide the popularity numbers
public enum Party {
    DEM, REP, NON, GREEN, LIB, IND;


    public static Party setParty (String choice) {
        switch (choice.toUpperCase()) {
            case "REPUBLICAN":
                return Party.REP;
            case "DEMOCRATIC":
                return Party.DEM;
            case "LIBERTARIAN":
                return Party.LIB;
            case "INDEPENDENT":
                return Party.IND;
            case "GREEN":
                return Party.GREEN;
        }
        return Party.NON;
    }


}