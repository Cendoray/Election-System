package election.data;
/**
 * This exception displays that the Election name is not unique.
 * @author Trevor Chevrier 1640405
 * @version 1.0
 * @date 3/11/17
 */
public class DuplicateElectionException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public DuplicateElectionException(){
		super("The Election's name is not unique.");
	}

	public DuplicateElectionException(String message) {
		super(message);
	}

}
