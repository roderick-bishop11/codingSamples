
public class DayException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DayException(){
		System.out.println("Invalid day, input a day that is <= the maximum day for that month.");
	}

}
