package election.ui.gui;

import election.business.DawsonElectionOffice;

import election.business.DawsonElectionOffice;
import election.business.interfaces.ElectionType;
import election.business.interfaces.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/** Form that gets the voter email, finds the Voter in the model
  * If the voter is eligible for the election, instantiate and start
  * a SingleBallotFormGUI
  */
public class VoterEmailFormGUI {
	
	private ElectionOffice model;

	private Election election;	
	private Stage primaryStage;
	private TextField emailTextField;
	private Text actionTarget;
	
//TODO add any additional properties
	
	/** 
	 * Constructor validates that the parameters are not null 
	 * and the election has ElectionType.SINGLE. Invokes the
	 * initialize() method
	 *  @throws IllegalArgumentException if the conditions are not met.
	 */
    public VoterEmailFormGUI(ElectionOffice model, Election election) {

		if (model == null)
		{
			throw new IllegalArgumentException("The model sent in is null.");
		}
		if (election == null)
		{
			throw new IllegalArgumentException("The election sent in is null.");
		}
		if (election.getElectionType() != ElectionType.SINGLE)
		{
			throw new IllegalArgumentException("The election sent in is not a single election.");
		}
		this.model = model;
		this.election = election;
		initialize();
    }

    /**
     * The stage and the scene are created in the start.
     *
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
    	this.primaryStage=primaryStage;
        // Set Window's Title
        primaryStage.setTitle("Get Voter Email");
        GridPane root = createUserInterface();
        Scene scene = new Scene(root, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Create the user interface as the root
     *
     * @return GridPane with the UI
     */
    private GridPane createUserInterface() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        Label email = new Label("Enter email address:");
        grid.add(email, 0, 1);
                
        grid.add(emailTextField, 1, 1);
        
        Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		actionTarget.setId("actiontarget");
		grid.add(actionTarget, 0, 6, 2, 1);
		btn.setOnAction(this::signInButtonHandler);
		
		return grid;
    }

    /**
     * Event handler for the Sign In Button
     *
     * @param e
     */
    private void signInButtonHandler(ActionEvent e) throws IllegalArgumentException{
    	try
    	{
    		Voter voter = model.findVoter(this.emailTextField.getText());
    		Ballot ballot = model.getBallot(voter, election);
    		SingleBallotFormGUI gui = new SingleBallotFormGUI(model, election, voter, ballot, this);
    		gui.start(primaryStage);
    	}
        catch (Exception error)
        {
        	actionTarget.setText("There was a problem with sign in: " + error.getMessage());
        }
    }

    /**
     * This method is usually used for data binding of a "data bean" class
     * to the JavaFX controls. A "bean" class is a simple class with getters
	 * and setters for all properties.
     * Changes to a control are immediately set on the bean and a change to
     * the bean is immediately shown in the control.
     */
    private void initialize() {
    	actionTarget = new Text();
    	emailTextField = new TextField();
    }
}
