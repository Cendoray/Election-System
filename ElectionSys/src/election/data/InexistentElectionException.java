package election.data;
/**
 * This exception displays that the Election name could not be found when searched.
 * @author Trevor Chevrier 1640405
 * @version 1.0
 * @date 3/11/17
 */
public class InexistentElectionException extends Exception{
private static final long  serialVersionUID = 42031768871L;
	
	public InexistentElectionException(){
		super("The Election name could not be found.");
	}

	public InexistentElectionException(String message) {
		super(message);
	}

}
