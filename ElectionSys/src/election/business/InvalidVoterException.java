package election.business;
/**
 * This exception displays a message indicating that a Voter tried to access a ballot that is not in progress
 * or that the Voter is not eligible for.
 * @author Trevor Chevrier 1640405
 * @version 1.0
 * @date 3/11/17
 */
public class InvalidVoterException extends RuntimeException{
private static final long  serialVersionUID = 42031768871L;
	
	public InvalidVoterException(){
		super("The Voter cannot access the ballot because it is in progress or the Voter is not eligible.");
	}

	public InvalidVoterException(String message) {
		super(message);
	}
}
