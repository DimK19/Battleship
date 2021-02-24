package application;

import java.io.IOException;

import backend.AdjacentTilesException;
import backend.Coordinate;
import backend.Game;
import backend.InvalidCountException;
import backend.OverlapTilesException;
import backend.OversizeException;

public class FXGame
{
    Game game;
    FXBoard playerFXBoard, computerFXBoard;
    boolean running;
    
    public FXGame(FXBoard playerFXBoard, FXBoard computerFXBoard)
    {
        this.playerFXBoard = playerFXBoard;
        this.computerFXBoard = computerFXBoard;
        running = false;
    }
    
    public void activateGame() throws IOException, OverlapTilesException, OversizeException, AdjacentTilesException, InvalidCountException
    {
        game = new Game();
        playerFXBoard.flush();
        computerFXBoard.flush();
        if(!game.decideOrder()) computerTurn(); // 1 -> player, 0 -> computer
        running = true;
    }
    
    public void terminateGame()
    {
        running = false;
    }
    
    public boolean isRunning()
    {
        return running;
    }
    
    public int getGameState()
    {
        return this.game.checkGameState();
    }
    
    public boolean playerTurn()
    {
        Coordinate currentMove;
        int outcome;
        currentMove = game.player.getLatestMove();
        outcome = game.computerBoard.getHitOutcome(currentMove);
        if(outcome == -1) return false;

        game.computer.updateShips(outcome);
        game.player.incrementMoveCounter();
        game.player.pushToMoves(currentMove, outcome);
        playerFXBoard.update(currentMove, outcome);
        
        return true;
    }
    
    public void computerTurn()
    {
        Coordinate currentMove = game.computer.makeMove();
        int outcome = game.playerBoard.getHitOutcome(currentMove);
        game.player.updateShips(outcome);
        game.computer.incrementMoveCounter();
        game.computer.pushToMoves(currentMove, outcome);
        computerFXBoard.update(currentMove, outcome);
    }

}
