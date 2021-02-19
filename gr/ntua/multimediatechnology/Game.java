package gr.ntua.multimediatechnology;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game
{
    String playerFilePath = "src/resources/player_default.txt";
    String computerFilePath = "src/resources/computer_default.txt";
    Board playerBoard, computerBoard;
    HumanPlayer player;
    ComputerPlayer computer;
    
    public Game() throws IOException, OverlapTilesException, OversizeException, AdjacentTilesException, InvalidCountException
    {
        this.playerBoard = new Board(this.readFile(this.playerFilePath));
        this.computerBoard = new Board(this.readFile(this.computerFilePath));
        
        this.player = new HumanPlayer();
        this.computer = new ComputerPlayer();
    }
    
    public ArrayList<String> readFile(String path) throws IOException
    {
        ArrayList<String> l = new ArrayList<String>();
        
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line != null)
        {
            l.add(line);
            line = br.readLine();
        }
        
        br.close();
        
        return l;
    }
    
    public void play()
    {
        boolean flag = decideOrder(), validMove;
        // 0 -> computer, 1 -> player
        Coordinate currentMove;
        int outcome;
        
        do
        {
            validMove = false;
            if(flag)
            {
                System.out.println("your turn");
                while(!validMove)
                {
                    currentMove = player.makeMove();
                    try
                    {
                        outcome = computerBoard.getHitOutcome(currentMove.getX(), currentMove.getY());
                        validMove = true;
                        computer.updateShips(outcome);
                        player.incrementMoveCounter();
                    }
                    catch (AlreadyBeenShotException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
               while(!validMove)
               {
                   currentMove = computer.makeMove();
                   try
                   {
                       outcome = playerBoard.getHitOutcome(currentMove.getX(), currentMove.getY());
                       validMove = true;
                       player.updateShips(outcome);
                       computer.incrementMoveCounter();
                   }
                   catch (AlreadyBeenShotException e)
                   {
                       e.printStackTrace();
                   }
               }
            flag ^= true;
        }
        while(checkGameState() == 0);
    }
    
    private boolean decideOrder()
    {
        return new Random().nextBoolean();
    }
    
    public void printBoard()
    {
        System.out.println(this.playerBoard);
    }

    private int checkGameState()
    {
        /* 0: game still going on
         * 1: computer player has lost all ships
         * 2: human player has lost all ships
         * 3: 40 moves have been played with no victor
         */
        int state;
        if(computer.hasLostAllShips()) state = 1;
        else if(player.hasLostAllShips()) state = 2;
        else if(player.getMovesPlayed() == 40 && computer.getMovesPlayed() == 40) state = 3;
        else state = 0;
        System.out.println(state);
        return state;
    }
}
