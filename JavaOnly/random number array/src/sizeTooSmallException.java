/*
custom exception to catch a possible edge case when picking a nonduplicating set of numbers with a range that is too small
 */

public class sizeTooSmallException extends Exception {
    public sizeTooSmallException(String m){
        super(m);
    };
}
