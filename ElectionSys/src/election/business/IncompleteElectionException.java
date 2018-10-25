package election.business;

/**
 * This exception signals that the election is not yet complete and the
 * votes to date cannot be revealed.
 */
public class IncompleteElectionException extends RuntimeException {

		private static final long  serialVersionUID = 42031768871L;
		
		public IncompleteElectionException(){
			super("The election has not yet ended.");
		}

		public IncompleteElectionException(String message) {
			super(message);
		}
	
}
