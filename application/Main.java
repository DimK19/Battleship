package application;

import backend.Coordinate;
import backend.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Font largerFont = new Font(14);
        
        // create individual menus
        Menu appMenu = new Menu("Application");

        MenuItem startOption = new MenuItem("Start");
        MenuItem loadOption = new MenuItem("Load");
        appMenu.getItems().addAll(startOption, loadOption);
        
        appMenu.getItems().add(new SeparatorMenuItem());
        
        MenuItem exitOption = new MenuItem("Exit");
        appMenu.getItems().add(exitOption);
        exitOption.setOnAction(e -> {Platform.exit();});
        
        Menu detailsMenu = new Menu("Details");
        
        MenuItem enemyShipsOption = new MenuItem("Enemy Ships");
        MenuItem playerShotsOption = new MenuItem("Player Shots");
        MenuItem computerShotsOption = new MenuItem("Computer Shots");
        detailsMenu.getItems().addAll(enemyShipsOption, playerShotsOption, computerShotsOption);
        
        
        // create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(appMenu, detailsMenu);
            
        // playing board
        FXBoard board1 = new FXBoard();
        FXBoard board2 = new FXBoard();

        
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        root.setTop(menuBar); // install menu bar
        
            

        // bottom text box
        TextField moveField = new TextField();
        Label promptLabel = new Label("Your move:");
        promptLabel.setFont(largerFont);
        Button shootButton = new Button("Fire!");       
            
        HBox userArea = new HBox();
            
        userArea.getChildren().addAll(promptLabel, moveField, shootButton);
        root.setBottom(userArea);
            
        // create board label boxes
        VBox numbers = new VBox(), numbers2 = new VBox();
        HBox letters = new HBox(), letters2 = new HBox();
        for(int i = 1; i <= 10; ++i)
        {
            Label n = new Label(String.valueOf(i));
            n.setFont(new Font(17));
            numbers.getChildren().add(n);
            Label l = new Label(String.valueOf((char)(i + 'A' - 1)));
            l.setFont(new Font(20));
            letters.getChildren().add(l);
                
            // Each Node can have exactly one Parent
            Label n2 = new Label(String.valueOf(i));
            n2.setFont(new Font(17));
            numbers2.getChildren().add(n2);
            Label l2 = new Label(String.valueOf((char)(i + 'A' - 1)));
            l2.setFont(new Font(20));
            letters2.getChildren().add(l2);
        }
            
        
        // create top labels
        Label pShipsLabel = new Label("Player Active Ships:  ");
        pShipsLabel.setFont(largerFont);
        Label pslValue = new Label(""); 
        Label pPointsLabel = new Label("Player Points:  ");
        pPointsLabel.setFont(largerFont);
        Label pplValue = new Label("");
        Label pRateLabel = new Label("Player Success Rate:  ");
        pRateLabel.setFont(largerFont);
        Label prlValue = new Label("");
        
        HBox pline1 = new HBox();
        pline1.getChildren().addAll(pShipsLabel, pslValue);
        HBox pline2 = new HBox();
        pline2.getChildren().addAll(pPointsLabel, pplValue);
        HBox pline3 = new HBox();
        pline3.getChildren().addAll(pRateLabel, prlValue);
        
        VBox playerInfoBox = new VBox();
        playerInfoBox.getChildren().addAll(pline1, pline2, pline3);
        
        
        Label cShipsLabel = new Label("Computer Active Ships:  ");
        cShipsLabel.setFont(largerFont);
        Label cslValue = new Label(""); 
        Label cPointsLabel = new Label("Computer Points:  ");
        cPointsLabel.setFont(largerFont);
        Label cplValue = new Label(""); 
        Label cRateLabel = new Label("Computer Success Rate:  ");
        cRateLabel.setFont(largerFont);
        Label crlValue = new Label("");
        
        HBox cline1 = new HBox();
        cline1.getChildren().addAll(cShipsLabel, cslValue);
        HBox cline2 = new HBox();
        cline2.getChildren().addAll(cPointsLabel, cplValue);
        HBox cline3 = new HBox();
        cline3.getChildren().addAll(cRateLabel, crlValue);
        
        VBox computerInfoBox = new VBox();
        computerInfoBox.getChildren().addAll(cline1, cline2, cline3);
        
        // install boards
        // board1.setAlignment(Pos.CENTER);
        // letters.setAlignment(Pos.CENTER);
        letters.setSpacing(15);
        numbers.setAlignment(Pos.TOP_RIGHT);
        board1.getBoard().getChildren().add(letters);
        HBox board1WithLabels = new HBox();
        board1WithLabels.getChildren().addAll(numbers, board1.getBoard());
        board1WithLabels.setSpacing(5);
        VBox finalBoard1 = new VBox();
        finalBoard1.getChildren().addAll(playerInfoBox, board1WithLabels);
        finalBoard1.setSpacing(10);
        
        letters2.setSpacing(15);
        numbers2.setAlignment(Pos.TOP_RIGHT);
        board2.getBoard().getChildren().add(letters2);
        HBox board2WithLabels = new HBox();
        board2WithLabels.getChildren().addAll(numbers2, board2.getBoard());
        board2WithLabels.setSpacing(5);
        VBox finalBoard2 = new VBox();
        finalBoard2.getChildren().addAll(computerInfoBox, board2WithLabels);
        finalBoard2.setSpacing(10);
        
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(100);
        GridPane.setConstraints(finalBoard1, 1, 0); // Player board to the right
        GridPane.setConstraints(finalBoard2, 0, 0); // Computer board to the left
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(finalBoard1, finalBoard2);
        root.setCenter(gridPane);
        
        // set up game
        loadOption.setOnAction(lambda -> { formPopUp(); });
        
        FXGame fxgame = new FXGame(board1, board2);
        startOption.setOnAction(f -> {
            // flush everything
            try
            {
                fxgame.activateGame();
                cslValue.setText(String.valueOf(fxgame.game.getComputer().getActiveShips()));
                cplValue.setText(String.valueOf(fxgame.game.getComputer().getTotalPoints()));
                crlValue.setText(String.format("%.2g", fxgame.game.getComputer().getSuccessRate()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        
        
        shootButton.setOnAction(lambda -> {
            if(fxgame.isRunning())
            {
                
                if(Coordinate.isValidCoordinate(moveField.getText()))
                {
                    fxgame.game.getPlayer().setLatestMove(new Coordinate(moveField.getText()));
                    moveField.clear();
                    if(fxgame.playerTurn())
                    {
                        pslValue.setText(String.valueOf(fxgame.game.getPlayer().getActiveShips()));
                        pplValue.setText(String.valueOf(fxgame.game.getPlayer().getTotalPoints()));
                        prlValue.setText(String.format("%.2g", fxgame.game.getPlayer().getSuccessRate()));
                        
                        int s = fxgame.getGameState();
                        if(s != 0) fxgame.terminateGame();
                        else
                        {
                            fxgame.computerTurn();
                            cslValue.setText(String.valueOf(fxgame.game.getComputer().getActiveShips()));
                            cplValue.setText(String.valueOf(fxgame.game.getComputer().getTotalPoints()));
                            crlValue.setText(String.format("%.2g", fxgame.game.getComputer().getSuccessRate()));
                        }
                    }
                }
            }
        });
        
        enemyShipsOption.setOnAction(lambda -> {
            if(fxgame.isRunning())
                showPopUp("Enemy Ships", fxgame.game.getComputer().getShipReport());
        });
        
        playerShotsOption.setOnAction(lambda -> {
            if(fxgame.isRunning())
                showPopUp("Player Shots", fxgame.game.getPlayer().getShotReport());
        });
        
        computerShotsOption.setOnAction(lambda -> {
            if(fxgame.isRunning())
                showPopUp("Computer Shots", fxgame.game.getComputer().getShotReport());
        });
        
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("MediaLab Battleship");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {Platform.exit();});
        // closes all pop-ups upon exiting with 'X', otherwise they remain open
        primaryStage.show();
    }

    private void showPopUp(String title, String param)
    {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        
        Label report = new Label();
        report.setText(param);
        report.setFont(new Font(14));
        
        Button close = new Button("Close");
        close.setOnAction(foo -> {stage.close();});
        
        VBox hb = new VBox();
        hb.getChildren().addAll(report, close);
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER);
        
        Scene popup = new Scene(hb, 300, 190);
        stage.setScene(popup);
        stage.show();
    }
    
    private void formPopUp()
    {
        Stage stage = new Stage();
        stage.setTitle("Load Text Files");
        stage.setResizable(false);
        
        TextField tf = new TextField();
        
        Button load = new Button("Load");
        load.setOnAction(foo -> {
            if(!tf.getText().isEmpty())
            {
                Game.setFilePath(tf.getText());
                tf.clear();
            }
        });
        
        Button close = new Button("Close");
        close.setOnAction(bar -> {stage.close();});
        
        HBox buttons = new HBox();
        buttons.getChildren().addAll(load, close);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(tf, buttons);
        
        Scene popup = new Scene(vb, 250, 160);
        stage.setScene(popup);
        stage.show();
    }
    
	public static void main(String[] args)
	{
		launch(args);
	}
}
