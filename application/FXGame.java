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
        currentMove = game.getPlayer().getLatestMove();
        outcome = game.getComputerBoard().getHitOutcome(currentMove);
        if(outcome == -1) return false;

        game.getComputer().updateShips(outcome);
        game.getPlayer().incrementMoveCounter();
        game.getPlayer().pushToMoves(currentMove, outcome);
        if(outcome > 0)
            if(game.getComputer().getShips().get(outcome - 1).getState().equals("sunken"))
                game.getPlayer().addPoints(game.getComputer().getShips().get(outcome - 1).getSinkBonus());
        playerFXBoard.update(currentMove, outcome);
        
        return true;
    }
    
    public void computerTurn()
    {
        Coordinate currentMove = game.getComputer().makeMove();
        int outcome = game.getPlayerBoard().getHitOutcome(currentMove);
        game.getPlayer().updateShips(outcome);
        game.getComputer().incrementMoveCounter();
        game.getComputer().pushToMoves(currentMove, outcome);
        if(outcome > 0)
            if(game.getPlayer().getShips().get(outcome - 1).getState().equals("sunken"))
                game.getComputer().addPoints(game.getPlayer().getShips().get(outcome - 1).getSinkBonus());
        computerFXBoard.update(currentMove, outcome);
    }

}
