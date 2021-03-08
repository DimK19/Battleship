package backend;

import java.io.IOException;
import java.util.Random;

public class Game
{
    static String playerFilePath = "src/resources/player_default.txt";
    static String computerFilePath = "src/resources/computer_default.txt";
    Board playerBoard, computerBoard;
    HumanPlayer player;
    ComputerPlayer computer;
    //boolean turn; // 1 -> player, 0 -> computer
    
    public Game() throws IOException, OverlapTilesException, OversizeException, AdjacentTilesException, InvalidCountException
    {
        this.playerBoard = new Board(Utilities.readFile(playerFilePath));
        this.computerBoard = new Board(Utilities.readFile(computerFilePath));
        
        this.player = new HumanPlayer();
        this.computer = new ComputerPlayer();
    }
    
    public static void setFilePath(String p)
    {
        playerFilePath = "src/resources/player_" + p + ".txt";
        computerFilePath = "src/resources/enemy_" + p + ".txt";
    }
    
    public Board getPlayerBoard()
    {
        return playerBoard;
    }

    public Board getComputerBoard()
    {
        return computerBoard;
    }

    public HumanPlayer getPlayer()
    {
        return player;
    }

    public ComputerPlayer getComputer()
    {
        return computer;
    }

    // for command line implementation
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
                    outcome = computerBoard.getHitOutcome(currentMove);
                    validMove = true;
                    computer.updateShips(outcome);
                    player.incrementMoveCounter();
                    player.pushToMoves(currentMove, outcome);
                    if(outcome > 0)
                        if(computer.getShips().get(outcome - 1).getState().equals("sunken"))
                            player.addPoints(computer.getShips().get(outcome - 1).getSinkBonus());
                }
            }
            else
               while(!validMove)
               {
                   currentMove = computer.makeMove();
                   outcome = playerBoard.getHitOutcome(currentMove);
                   validMove = true;
                   player.updateShips(outcome);
                   computer.incrementMoveCounter();
                   computer.pushToMoves(currentMove, outcome);
                   if(outcome > 0)
                       if(player.getShips().get(outcome - 1).getState().equals("sunken"))
                           computer.addPoints(player.getShips().get(outcome - 1).getSinkBonus());
               }
            flag ^= true;
        }
        while(checkGameState() == 0);
    }
    
    public boolean decideOrder()
    {
        return (new Random()).nextBoolean();
    }
    
    public void printBoard()
    {
        System.out.println(this.playerBoard);
    }

    public int checkGameState()
    {
        /* 0: game still going on
         * 1: computer player has lost all ships
         * 2: human player has lost all ships
         * 3: 40 moves have been played with no victor
         */
        int state;
        if(computer.getActiveShips() == 0) state = 1;
        else if(computer.getActiveShips() == 0) state = 2;
        else if(player.getMovesPlayed() == 40 && computer.getMovesPlayed() == 40) state = 3;
        else state = 0;
        return state;
    }
}
