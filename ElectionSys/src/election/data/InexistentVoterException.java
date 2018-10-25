package election.data;
/**
 * This exception displays that the Voter's Email could not be found when searched.
 * @author Trevor Chevrier 1640405
 * @version 1.0
 * @date 3/11/17
 */
public class InexistentVoterException extends Exception{
private static final long  serialVersionUID = 42031768871L;
	
	public InexistentVoterException(){
		super("The Voter's Email could not be found.");
	}

	public InexistentVoterException(String message) {
		super(message);
	}

}
