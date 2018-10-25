package election.data;

/**
 * PROVIDED CODE
 * This exception signals that the provided voter email exists in the database already.
 * 
 * @author Maja, Jaya
 */
public class DuplicateVoterException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public DuplicateVoterException(){
		super("The provided Email address is associated with an existing voter.");
	}

	public DuplicateVoterException(String message) {
		super(message);
	}
}
