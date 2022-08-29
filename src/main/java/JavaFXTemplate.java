// CS 342 Project 2 - KENO 
// Partners:
// Mohammed Hisham Moizuddin | UIN : 650344339, netID : mmoizu2
// Kevin Savath | UIN : 668759935, netID : ksavat2

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class JavaFXTemplate extends Application {
	
	private Stage stage;
	
	private Scene sceneMenu;
	private HBox hboxMenu;
	private TextArea welcomeText = new TextArea("Welcome to KENO");
	private Button buttonPlay;
	private Button buttonRules;
	private String rulesString = "Players wager by choosing a set amount of numbers( pick 2 numbers, pick 10 numbers,\n"
			+ "etc.) ranging from 1 to 80. After all players have made their wagers and picked their\n"
			+ "numbers, twenty numbers are drawn at random, between 1 and 80 with no duplicates.\n"
			+ "Players win by matching a set amount of their numbers to the numbers that are\n"
			+ "randomly drawn.";
	private TextArea textRules;
	private Button buttonWinnings;
	private String oddsString = "Odds of winning:\n\n" + " 1 Spot Game\n" + " Match     Prize\n" +
					            "     1     $2\n" + "Overall Odds: 1 in 4.00\n\n" +
					    		" 4 Spot Game\n" + " Match     Prize\n" + "     4     $75\n" +
					            "     3     $5\n" + "     2     $1\n" +
					            "Overall Odds: 1 in 3.86\n\n" +
					    		" 8 Spot Game\n" + " Match     Prize\n" + "     8     $10,000\n" +
					            "     7    	$150\n" + "     6     $50\n" + "     5     $12\n" +
					            "     4     $1\n" + "Overall Odds: 1 in 9.77\n\n"+
					    		" 10 Spot Game\n" + " Match     Prize\n" + "    10     $100,000\n" +
					            "     9     $4,250\n" + "     8    	$450\n" + "     7     $40\n" +
					            "     6     $15\n" + "     5     $2\n" + "     0     $5\n" +
					            "Overall Odds: 1 in 9.05\n";
	private TextArea textWinnings;
	private Button buttonQuit;
	private BorderPane borderPaneMenu;
	
	private Scene sceneGame;
	private Button buttonBack;
	private Button buttonLook;
	private int userScore = 0;
	Label OverallScore = new Label("Score: "+userScore);
	private List<Integer> UserNum;
	private List<Integer> ComputerNum;
	EventHandler<ActionEvent> returnButtons;
	EventHandler<ActionEvent> returnSpots;
	EventHandler<ActionEvent> returnDrawings;
	HashMap<Integer, Integer> spotMap1 = new HashMap<>();
	HashMap<Integer, Integer> spotMap2 = new HashMap<>();
	HashMap<Integer, Integer> spotMap3 = new HashMap<>();
	HashMap<Integer, Integer> spotMap4 = new HashMap<>();

	int userSpots = 0;
	int userSpotSelected = 0;
	int computerFoundSpots = 0;
	private Label spotLabel = new Label("Pick a number of Spots:");
	private Button spots1 = new Button("1");
	private Button spots4 = new Button("4");
	private Button spots8 = new Button("8");
	private Button spots10 = new Button("10");

	int userDrawings = 0;
	int userDrawingSelected = 0;
	private Label drawingsLabel = new Label("Pick a number of Drawings:");
	private Button drawings1 = new Button("1");
	private Button drawings2 = new Button("2");
	private Button drawings3 = new Button("3");
	private Button drawings4 = new Button("4");
	
	private VBox vboxGame;
	private HBox hboxGame;
	private HBox hboxGameRow1;
	private HBox hboxGameRow2;
	private GridPane gridPane;
	private BorderPane borderPaneGame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		stage = primaryStage;
		stage.setTitle("Welcome to JavaFX");
		
		sceneMenu = menuMaker();
		sceneGame = gameMaker();
		stage.setScene(sceneMenu);
		stage.show();
	}
	
	private Scene menuMaker() {
		borderPaneMenu = new BorderPane();
		buttonPlay = new Button("Play");
		buttonRules = new Button("Rules");
		buttonWinnings = new Button("Odds to Win");
		buttonQuit = new Button("Quit");
		
		textRules = new TextArea(rulesString);
		textRules.setWrapText(true);
		textWinnings = new TextArea(oddsString);
		
		textWinnings.setWrapText(true);
		
		welcomeText.setDisable(true);
		
		buttonPlay.setOnAction(e->switchScenes(sceneGame));
		
		buttonRules.setOnAction(e-> {
			textRules.setDisable(true);
			borderPaneMenu.setCenter(textRules);
		});
		
		buttonWinnings.setOnAction(e-> {
			textWinnings.setDisable(true);
			borderPaneMenu.setCenter(textWinnings);
		});
		
		
		buttonQuit.setOnAction(e->System.exit(0));	
		borderPaneMenu.setStyle("-fx-font-family: SansSerif;");
		hboxMenu = new HBox(10, buttonPlay,buttonRules,buttonWinnings,buttonQuit);
		hboxMenu.setAlignment(Pos.CENTER);
		borderPaneMenu.setTop(hboxMenu);
		borderPaneMenu.setCenter(welcomeText);
		
		sceneMenu = new Scene(borderPaneMenu, 600, 600);
		
		return sceneMenu;
	}
	
	private Scene gameMaker() {
		buttonBack = new Button("Back");
		buttonRules = new Button("Rules");
		buttonWinnings = new Button("Odds to Win");
		buttonLook = new Button("New Look");
		borderPaneGame = new BorderPane();

		textRules = new TextArea(rulesString);
		textWinnings = new TextArea(oddsString);
	
		buttonBack.setOnAction(e->switchScenes(sceneMenu));

		buttonRules.setOnAction(e-> {
			textRules.setDisable(true);
			borderPaneGame.setCenter(textRules);
		});
		
		buttonWinnings.setOnAction(e-> {
			textWinnings.setDisable(true);
			borderPaneGame.setCenter(textWinnings);
		});
	
		buttonLook.setOnAction(e-> {
			borderPaneGame = newLook(borderPaneGame);
		});
		
		returnSpots = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Button b = (Button)e.getSource();
				String buttonUnit = b.getText();
				int unitInt = Integer.parseInt(buttonUnit);
				userSpots = unitInt;
				spots1.setDisable(true);
				spots4.setDisable(true);
				spots8.setDisable(true);
				spots10.setDisable(true);
				System.out.println("Spots returned: "+ userSpots);
				checkDrawsAndSpots();
			}
		};
		
		spots1.setOnAction(returnSpots);
		spots4.setOnAction(returnSpots);
		spots8.setOnAction(returnSpots);
		spots10.setOnAction(returnSpots);
		
		spotMap1.put(1, 2);
		
		spotMap2.put(4, 75);
		spotMap2.put(3, 5);
		spotMap2.put(2, 1);
		
		spotMap3.put(8, 10000);
		spotMap3.put(7, 150);
		spotMap3.put(6, 50);
		spotMap3.put(5, 12);
		spotMap3.put(4, 1);
		
		spotMap4.put(10, 100000);
		spotMap4.put(9, 4250);
		spotMap4.put(8, 450);
		spotMap4.put(7, 40);
		spotMap4.put(6, 15);
		spotMap4.put(5, 2);
		spotMap4.put(0, 5);

		returnDrawings = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Button b = (Button)e.getSource();
				String buttonUnit = b.getText();
				int unitInt = Integer.parseInt(buttonUnit);
				userDrawings = unitInt;
				drawings1.setDisable(true);
				drawings2.setDisable(true);
				drawings3.setDisable(true);
				drawings4.setDisable(true);
				System.out.println("Drawings returned: "+ userDrawings);
				checkDrawsAndSpots();
			}
		};
		
		drawings1.setOnAction(returnDrawings);
		drawings2.setOnAction(returnDrawings);
		drawings3.setOnAction(returnDrawings);
		drawings4.setOnAction(returnDrawings);

		hboxGame = new HBox(10, buttonBack,buttonRules,buttonWinnings, buttonLook);
		hboxGame.setAlignment(Pos.CENTER);
		hboxGameRow1 = new HBox(10, spots1,spots4,spots8,spots10);
		hboxGameRow1.setAlignment(Pos.CENTER);
		hboxGameRow2 = new HBox(10, drawings1,drawings2,drawings3,drawings4);
		hboxGameRow2.setAlignment(Pos.CENTER);
		vboxGame = new VBox(10, hboxGame, spotLabel, hboxGameRow1, drawingsLabel, hboxGameRow2,OverallScore);

		gridPane = new GridPane();
		gridPane.setDisable(true);
		UserNum = new ArrayList<>();
		ComputerNum = new ArrayList<>();

		returnButtons = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Button b = (Button)e.getSource();
				String buttonUnit = b.getText();
				int unitInt = Integer.parseInt(buttonUnit);
				System.out.println("Integer returned: "+ unitInt);
				userSpotSelected++;
				b.setDisable(true);
				UserNum.add(unitInt);
				System.out.println("Spots: "+ userSpots + " SpotsSelected: "+ userSpotSelected);
				System.out.println("Drawings: "+ userDrawings + " DrawingsSelected: "+ userDrawingSelected);
				if(userSpots == userSpotSelected && userDrawings == userDrawingSelected) {
					gridPane.setDisable(true);
				} else if (userSpots == userSpotSelected){
					ObservableList <Node> childrens = gridPane.getChildren();
					for (Node node : childrens) {
						node.setDisable(false);
					}
					userSpotSelected = 0;
					userDrawingSelected++;
					while (ComputerNum.size() != 20) {
						Random r = new Random();
						int randomNum = r.nextInt(81)+1;
						if (!ComputerNum.contains(randomNum)) {
							ComputerNum.add(randomNum);
						}
					}
					
					for (int number : UserNum) {
						if (ComputerNum.contains(number)) {
							computerFoundSpots++;
						}
					}
					
					switch (userSpots) {
					case 1:
						if(spotMap1.containsKey(computerFoundSpots)) {
							int retrieve = spotMap1.get(computerFoundSpots);
							userScore = userScore + retrieve;
						}
						break;
					case 4:
						if(spotMap2.containsKey(computerFoundSpots)) {
							int retrieve = spotMap1.get(computerFoundSpots);
							userScore = userScore + retrieve;
						}
						break;
					case 8:
						if(spotMap3.containsKey(computerFoundSpots)) {
							int retrieve = spotMap1.get(computerFoundSpots);
							userScore = userScore + retrieve;
						}
						break;
					case 10:
						if(spotMap4.containsKey(computerFoundSpots)) {
							int retrieve = spotMap1.get(computerFoundSpots);
							userScore = userScore + retrieve;
						}
						break;
					default:
						System.out.println("No Map was retrieved");
					}
					computerFoundSpots = 0;
					UserNum.clear();
					ComputerNum.clear();
					OverallScore.setText("Score: "+ userScore);
					
				}
			}
		};

		for (int i = 0; i < 8;i++) {
			for(int j = 0; j < 10;j++) {
				int id = (i*10)+j+1;
				Button b = new Button("" + id);
				b.setOnAction(returnButtons);
				gridPane.add(b, j, i);
			}
		}

		gridPane.setAlignment(Pos.CENTER);
		vboxGame.setAlignment(Pos.CENTER);

		borderPaneGame.setStyle("-fx-font-family: SansSerif;");
		borderPaneGame.setTop(vboxGame);
		borderPaneGame.setCenter(gridPane);
		sceneGame = new Scene(borderPaneGame, 600,600);
		return sceneGame;
	}
	
	// to switch scenes
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}
	
	// checks if spots and draws have both been selected
	public void checkDrawsAndSpots () {
		if (userSpots != 0 && userDrawings != 0) {
			System.out.println("Both have been received");
			gridPane.setDisable(false);
		}
	}
	
	// To change the look of the game
	public BorderPane newLook(BorderPane bp) {
		ArrayList<String> myLooks = new ArrayList<String>();
		myLooks.add("-fx-font-family: SansSerif;" + "-fx-background-color: #47baa7;");
		myLooks.add("-fx-font-family: Courier New;" + "-fx-background-color: #bd171c;");
		myLooks.add("-fx-font-family: SansSerif;" + "-fx-background-color: #4f07eb;");
		myLooks.add("-fx-font-family: Courier New;" + "-fx-background-color: #af95e8;");
		myLooks.add("-fx-font-family: SansSerif;" + "-fx-background-color: #edcb0c;");
		myLooks.add("-fx-font-family: Courier New;" + "-fx-background-color: #1bcc14;");
		myLooks.add("-fx-font-family: SansSerif;" + "-fx-background-color: #8a5817;");
	
		Random rand_looks = new Random();
		for (int i = 0; i < myLooks.size(); i++) 
        	{	int index = rand_looks.nextInt(myLooks.size());
				myLooks.get(index);
				bp.setStyle(myLooks.get(index));
				break;
        	}
		return bp;
	}
}